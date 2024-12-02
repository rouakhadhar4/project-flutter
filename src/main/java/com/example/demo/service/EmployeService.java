package com.example.demo.service;


import com.example.demo.entity.Employe;

import java.util.List;

public interface EmployeService {
    List<Employe> getAllEmployes();
    Employe getEmployeById(Long id);
    Employe saveEmploye(Employe employe);
    void deleteEmploye(Long id);
}
