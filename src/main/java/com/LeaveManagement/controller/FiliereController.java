package com.LeaveManagement.controller;

import com.LeaveManagement.Dto.FiliereDTO;
import com.LeaveManagement.Entity.Departement;
import com.LeaveManagement.Entity.Filiere;
import com.LeaveManagement.Service.DepartementService;
import com.LeaveManagement.Service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("filieres")
public class FiliereController {
    @Autowired
    private FiliereService filiereService;
    @PostMapping(path = "/save")
    public  Long saveFilieres(@RequestBody FiliereDTO filiereDTO){

        Long id =filiereService.addFiliere(filiereDTO);
        return id;

    }
    @GetMapping(path="/getAll")
    public List<Filiere> getAllFilieres(){
        return  filiereService.getAllFiliere();
    }

    @GetMapping(path="/getById/{id}")
    public Filiere getFiliereById(@PathVariable Long id){
        return filiereService.GetFiliereById(id);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateFiliere(@PathVariable Long id, @RequestBody FiliereDTO filiereDTO) {
        filiereService.updateFiliere(id,filiereDTO);
        return ResponseEntity.ok("Filiere updated successfully");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGrades(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.ok("Filiere deleted successfully");
    }
}
