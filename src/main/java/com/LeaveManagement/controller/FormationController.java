package com.LeaveManagement.controller;

import com.LeaveManagement.Dto.FormationDTO;
import com.LeaveManagement.Entity.Formation;
import com.LeaveManagement.Entity.TypeFormation;
import com.LeaveManagement.Service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// 6. CONTRÔLEUR FORMATION
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/formation")
public class FormationController {

    @Autowired
    private FormationService formationService;

    // Ajouter une formation
    @PostMapping("/add")
    public ResponseEntity<Long> addFormation(@RequestBody FormationDTO formationDTO) {
        try {
            Long id = formationService.addFormation(formationDTO);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Récupérer toutes les formations
    @GetMapping("/getAll")
    public ResponseEntity<List<Formation>> getAllFormations() {
        List<Formation> formations = formationService.getAllFormations();
        return ResponseEntity.ok(formations);
    }

    // Récupérer les formations d'un employé
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Formation>> getFormationsByEmployee(@PathVariable Long employeeId) {
        List<Formation> formations = formationService.getFormationsByEmployee(employeeId);
        return ResponseEntity.ok(formations);
    }

    // Récupérer une formation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        try {
            Formation formation = formationService.getFormationById(id);
            return ResponseEntity.ok(formation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour une formation
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        try {
            formationService.updateFormation(id, formationDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une formation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        try {
            formationService.deleteFormation(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer formations par type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Formation>> getFormationsByType(@PathVariable TypeFormation type) {
        List<Formation> formations = formationService.getFormationsByType(type);
        return ResponseEntity.ok(formations);
    }

    // Statistiques d'un employé
    @GetMapping("/stats/employee/{employeeId}")
    public ResponseEntity<Map<String, Object>> getEmployeeFormationStats(@PathVariable Long employeeId) {
        Map<String, Object> stats = formationService.getEmployeeFormationStats(employeeId);
        return ResponseEntity.ok(stats);
    }
}