package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.DepartementDTO;
import com.LeaveManagement.Entity.Departement;

import java.util.List;

public interface DepartementService {
    Long addDepartement(DepartementDTO departementDTO);
    public List<Departement> getAllDepartements();
    public Departement GetDepartementById(Long id);
    void updateDepartement(Long id, DepartementDTO departementDTO);
    void deleteDepartement(Long id);
}

