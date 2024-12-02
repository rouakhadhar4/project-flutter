package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // Annotation pour stocker les données de l'image
    private byte[] donnees; // Nom du champ corrigé pour être plus explicite

    private String type; // Type MIME de l'image, ex : "image/jpeg"

    @ManyToOne
    private Employe employe;

	
}
