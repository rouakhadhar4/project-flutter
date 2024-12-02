package com.example.demo.service;


import com.example.demo.entity.Employe;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.service.EmployeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements  EmployeService {
    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAllWithProjets();
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employe non trouvé"));
    }

    @Override
    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
