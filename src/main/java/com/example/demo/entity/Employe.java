package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclure les champs null dans le JSON
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String poste;
    private Double salaire;

    @Transient // Ne pas persister cette propriété dans la base de données
    private String imageBase64Data;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    // Méthode pour obtenir l'image en base64
    public String getImageBase64Data() {
        return imageBase64Data;
    }
}
