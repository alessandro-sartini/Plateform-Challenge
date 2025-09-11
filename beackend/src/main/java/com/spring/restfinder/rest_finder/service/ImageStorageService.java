package com.spring.restfinder.rest_finder.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class ImageStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path baseDirPath;

    private final RestTemplate restTemplate;

    public ImageStorageService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        baseDirPath = Paths.get(uploadDir);
        try {
            Files.createDirectories(baseDirPath);
            System.out.println("Upload directory pronta: " + baseDirPath.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Impossibile creare la directory di upload", e);
        }
    }

    /**
     * Scarica l’immagine da `imageUrl` e la salva in:
     * {uploadDir}/{restaurantId}/{filename}.jpg
     * Restituisce il path Thymeleaf-friendly (es. "/uploads/42/photo1.jpg").
     */
    public String downloadAndStore(Long restaurantId, String imageUrl, String filename) {
        try {
            Path dir = baseDirPath.resolve(restaurantId.toString());
            Files.createDirectories(dir);

            byte[] data;
            try {
                ResponseEntity<byte[]> resp = restTemplate.getForEntity(imageUrl, byte[].class);
                data = resp.getBody();
                if (data == null || data.length == 0) {
                    throw new IOException("Nessun contenuto da " + imageUrl);
                }
            } catch (Exception e) {
                System.err.println("Errore nel download dell'immagine da " + imageUrl + ": " + e.getMessage());
                // Return default image path or throw exception based on your requirements
                return "/images/default-restaurant.jpg";
            }

            String ext = ".jpg";
            Path target = dir.resolve(filename + ext);
            Files.write(target, data);

            return "/uploads/" + restaurantId + "/" + filename + ext;
        } catch (IOException e) {
            System.err.println("Errore nella scrittura dell'immagine: " + e.getMessage());
            return "/images/default-restaurant.jpg";
        }
    }

    /**
     * Salva un file MultipartFile caricato dall’utente:
     * {uploadDir}/{restaurantId}/{uuid}.{ext}
     * Restituisce il path Thymeleaf-friendly.
     */
    public String store(MultipartFile file, Long restaurantId) {
        try {
            Path dir = baseDirPath.resolve(restaurantId.toString());
            Files.createDirectories(dir);

            String original = Objects.requireNonNull(file.getOriginalFilename());
            String ext = original.contains(".")
                    ? original.substring(original.lastIndexOf('.'))
                    : "";
            String uuidName = UUID.randomUUID().toString() + ext;

            Path target = dir.resolve(uuidName);
            Files.copy(file.getInputStream(), target);

            return "/uploads/" + restaurantId + "/" + uuidName;
        } catch (IOException e) {
            throw new RuntimeException("Errore salvataggio file upload", e);
        }
    }

    /**
     * Elimina fisicamente il file corrispondente al path Thymeleaf-friendly,
     * es. "/uploads/42/abcd-1234.jpg"
     */
    public void delete(String thymeleafPath) {
        try {
            String relative = thymeleafPath.replaceFirst("^/uploads/", "");
            Path fileOnDisk = baseDirPath.resolve(relative);
            Files.deleteIfExists(fileOnDisk);
        } catch (IOException e) {
            System.err.println("Impossibile cancellare file: " + e.getMessage());
        }
    }

    /**
     * Carica un file come Resource (opzionale, per un controller @GetMapping).
     */
    public Resource loadAsResource(String thymeleafPath) {
        try {
            String relative = thymeleafPath.replaceFirst("^/uploads/", "");
            Path file = baseDirPath.resolve(relative);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File non leggibile: " + thymeleafPath);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL malformato per file: " + thymeleafPath, e);
        }
    }
}
