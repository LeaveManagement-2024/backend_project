package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.FiliereDTO;
import com.LeaveManagement.Entity.Departement;
import com.LeaveManagement.Entity.Filiere;
import com.LeaveManagement.Entity.ServiceE;
import com.LeaveManagement.Repo.ServiceRepo;
import com.LeaveManagement.Repo.FiliereRepo;
import com.LeaveManagement.Service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FiliereImp implements FiliereService {
    @Autowired
    private FiliereRepo filiereRepo;
    @Autowired
    private ServiceRepo serviceRepo;

    @Override
    public Long addFiliere(FiliereDTO filiereDTO)
    {
        ServiceE serviceE = serviceRepo.findById(filiereDTO.getIdService()).orElseThrow(()->new IllegalArgumentException("Service not found"));
        Filiere filiere = new Filiere();
        filiere.setFiliereName(filiereDTO.getFiliereNameFr());

        filiere.setService(serviceE);
        filiereRepo.save(filiere);
        return filiere.getIdFiliere();
    }

    @Override
    public List<Filiere> getAllFiliere() {
        return filiereRepo.findAll();
    }

    @Override
    public Filiere GetFiliereById(Long id) {
        return filiereRepo.findById(id).get();
    }

    @Override
    public void updateFiliere(Long id, FiliereDTO filiereDTO) {
        Filiere filiereToUpdate = filiereRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Filiere not found"));
        ServiceE serviceE = serviceRepo.findById(filiereDTO.getIdService()).orElseThrow(()->new IllegalArgumentException("Service not found"));

        filiereToUpdate.setFiliereName(filiereDTO.getFiliereNameFr());
        filiereToUpdate.setService(serviceE);
        filiereRepo.save(filiereToUpdate);
    }

    @Override
    public void deleteFiliere(Long id) {
        filiereRepo.deleteById(id);

    }
}




