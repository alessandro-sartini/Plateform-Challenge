package com.spring.restfinder.rest_finder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La domanda non può essere vuota.") // La domanda non può essere null o stringa vuota
    @Size(min = 10, max = 255, message = "La domanda deve contenere tra {min} e {max} caratteri.") // Lunghezza della
                                                                                                   // domanda
    @Column(name = "question", nullable = false, unique = true)
    private String question;

    @NotBlank(message = "La risposta non può essere vuota.")

    @Size(max = 255, message = "La domanda deve contenere {max} caratteri.")
    @Column(name = "itQuestion", nullable = false, unique = true)
    private String itQuestion;

    @NotBlank(message = "La risposta IT non può essere vuota.")
    @Size(min = 20, max = 1000, message = "La risposta IT deve contenere tra {min} e {max} caratteri.")

    @Column(name = "response", nullable = false, unique = true)
    private String response;

    @NotBlank(message = "La domanda IT non può essere vuota.")

    @Size(max = 255, message = "La domanda IT deve contenere {max} caratteri.")
    @Column(name = "itResponse", nullable = false, unique = true)
    private String itResponse;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getItQuestion() {
        return this.itQuestion;
    }

    public void setItQuestion(String itQuestion) {
        this.itQuestion = itQuestion;
    }

    public String getItResponse() {
        return this.itResponse;
    }

    public void setItResponse(String itResponse) {
        this.itResponse = itResponse;
    }

}