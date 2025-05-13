package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.DepartementDTO;
import com.LeaveManagement.Entity.Departement;
import com.LeaveManagement.Entity.Employees;
import com.LeaveManagement.Entity.Grades;
import com.LeaveManagement.Repo.DepartementRepo;
import com.LeaveManagement.Repo.EmployeeRepo;
import com.LeaveManagement.Repo.GradesRepo;
import com.LeaveManagement.Service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartementImp implements DepartementService {
    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Long addDepartement(DepartementDTO departementDTO) {
        Employees employees = employeeRepo.findById(departementDTO.getRespDepartementId()).orElse(null);
        Departement departement=new Departement();
        departement.setDepartementNameAr(departementDTO.getDepartementNameAr());
        departement.setDepartementNameFr(departementDTO.getDepartementNameFr());
        departement.setRespDepartement(employees);
        departementRepo.save(departement);
        return departement.getIdDepartement();
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepo.findAll();
    }

    @Override
    public Departement GetDepartementById(Long id) {
        return departementRepo.findById(id).get();
    }

    @Override
    public void updateDepartement(Long id, DepartementDTO departementDTO) {
        Departement departementToUpdate = departementRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Departement not found"));
        Employees employees = employeeRepo.findById(departementDTO.getRespDepartementId()).orElseThrow(()->new IllegalArgumentException("Employee not found"));
        departementToUpdate.setDepartementNameAr(departementDTO.getDepartementNameAr());
        departementToUpdate.setDepartementNameFr(departementDTO.getDepartementNameFr());
        departementToUpdate.setRespDepartement(employees);
        departementRepo.save(departementToUpdate);
    }

    @Override
    public void deleteDepartement(Long id) {
        departementRepo.deleteById(id);

    }
}


