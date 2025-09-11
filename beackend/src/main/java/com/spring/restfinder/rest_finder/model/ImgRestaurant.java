package com.spring.restfinder.rest_finder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "restaurant_images")
public class ImgRestaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // percorso o URL interno (ad es. "/uploads/rist1_photo1.jpg")
  @Lob
  @Column(name = "url", nullable = false, columnDefinition = "TEXT")
  @NotBlank(message = "Il percorso dell'immagine non può essere vuoto")
  @Size(max = 2048, message = "Il percorso dell'immagine non può superare i 2048 caratteri")
  private String path;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  // getter / setter

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Restaurant getRestaurant() {
    return this.restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

}
