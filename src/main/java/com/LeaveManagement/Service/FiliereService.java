package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.FiliereDTO;
import com.LeaveManagement.Entity.Filiere;

import java.util.List;

public interface FiliereService {
    Long addFiliere(FiliereDTO filiereDTO);
    public List<Filiere> getAllFiliere();
    public Filiere GetFiliereById(Long  id);
    void updateFiliere(Long id, FiliereDTO filiereDTO);
    void deleteFiliere(Long id);
}

