package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

import com.example.demo.entity.Employe;
import com.example.demo.entity.Image;
import com.example.demo.service.EmployeService;
import com.example.demo.service.ImageService;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {
    private final EmployeService employeService;
    private final ImageService imageService;

    @Autowired
    public EmployeController(EmployeService employeService, ImageService imageService) {
        this.employeService = employeService;
        this.imageService = imageService;
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        // Vérifiez si l'employé existe
        Employe employe = employeService.getEmployeById(id);
        if (employe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employe not found");
        }

        // Gérer le téléchargement de l'image et l'enregistrer dans la base de données
        String imageUrl = imageService.storeImage(id, image);

        // Réponse avec un message de confirmation
        return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully for employe ID: " + id);
    }

    @GetMapping("/employes")
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeService.getAllEmployes();
        for (Employe employe : employes) {
            if (employe.getImages() != null && !employe.getImages().isEmpty()) {
                // Convertir la première image de chaque employé en base64
                Image image = employe.getImages().get(0); // Prenons la première image
                if (image.getDonnees() != null) {
                    employe.setImageBase64Data(convertImageToBase64(image.getDonnees()));
                } else {
                    employe.setImageBase64Data(null); // Si l'image est nulle, on met null
                }
            }
        }
        return ResponseEntity.ok(employes);
    }

    // Méthode pour convertir les données de l'image en base64
    private String convertImageToBase64(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
