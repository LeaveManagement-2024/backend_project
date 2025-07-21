package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.FormationDTO;
import com.LeaveManagement.Entity.Employees;
import com.LeaveManagement.Entity.Formation;
import com.LeaveManagement.Entity.StatutFormation;
import com.LeaveManagement.Entity.TypeFormation;
import com.LeaveManagement.Repo.EmployeeRepo;
import com.LeaveManagement.Repo.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private EmployeeRepo employeeRepository;

    // Ajouter une formation
    public Long addFormation(FormationDTO formationDTO) {
        Optional<Employees> employeeOpt = employeeRepository.findById(formationDTO.getEmployeeId());
        if (employeeOpt.isEmpty()) {
            throw new RuntimeException("Employé non trouvé avec l'ID: " + formationDTO.getEmployeeId());
        }

        Formation formation = new Formation();
        formation.setNomFormation(formationDTO.getNomFormation());
        formation.setDescription(formationDTO.getDescription());
        formation.setTypeFormation(formationDTO.getTypeFormation());
        formation.setNomCertification(formationDTO.getNomCertification());
        formation.setDateDebut(formationDTO.getDateDebut());
        formation.setDateFin(formationDTO.getDateFin());
        formation.setDureeEnJours(formationDTO.getDureeEnJours());
        formation.setOrganisme(formationDTO.getOrganisme());
        formation.setCout(formationDTO.getCout());
        formation.setStatut(formationDTO.getStatut() != null ? formationDTO.getStatut() : StatutFormation.PLANIFIEE);
        formation.setCommentaires(formationDTO.getCommentaires());
        formation.setEmployee(employeeOpt.get());

        // Validation de durée
        if (formation.getDateDebut() != null && formation.getDateFin() != null) {
            long jours = ChronoUnit.DAYS.between(formation.getDateDebut(), formation.getDateFin()) + 1;
            formation.setDureeEnJours((int) jours);
        }

        Formation savedFormation = formationRepository.save(formation);
        return savedFormation.getIdFormation();
    }

    // Récupérer toutes les formations
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    // Récupérer les formations d'un employé
    public List<Formation> getFormationsByEmployee(Long employeeId) {
        return formationRepository.findByEmployeeIdE(employeeId);
    }

    // Récupérer une formation par ID
    public Formation getFormationById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'ID: " + id));
    }

    // Mettre à jour une formation
    public void updateFormation(Long id, FormationDTO formationDTO) {
        Formation formation = getFormationById(id);

        formation.setNomFormation(formationDTO.getNomFormation());
        formation.setDescription(formationDTO.getDescription());
        formation.setTypeFormation(formationDTO.getTypeFormation());
        formation.setNomCertification(formationDTO.getNomCertification());
        formation.setDateDebut(formationDTO.getDateDebut());
        formation.setDateFin(formationDTO.getDateFin());
        formation.setDureeEnJours(formationDTO.getDureeEnJours());
        formation.setOrganisme(formationDTO.getOrganisme());
        formation.setCout(formationDTO.getCout());
        formation.setStatut(formationDTO.getStatut());
        formation.setCommentaires(formationDTO.getCommentaires());

        // Recalculer la durée si les dates changent
        if (formation.getDateDebut() != null && formation.getDateFin() != null) {
            long jours = ChronoUnit.DAYS.between(formation.getDateDebut(), formation.getDateFin()) + 1;
            formation.setDureeEnJours((int) jours);
        }

        formationRepository.save(formation);
    }

    // Supprimer une formation
    public void deleteFormation(Long id) {
        if (!formationRepository.existsById(id)) {
            throw new RuntimeException("Formation non trouvée avec l'ID: " + id);
        }
        formationRepository.deleteById(id);
    }

    // Récupérer formations par type
    public List<Formation> getFormationsByType(TypeFormation type) {
        return formationRepository.findByTypeFormation(type);
    }

    // Récupérer formations par statut
    public List<Formation> getFormationsByStatut(StatutFormation statut) {
        return formationRepository.findByStatut(statut);
    }

    // Statistiques pour un employé
    public Map<String, Object> getEmployeeFormationStats(Long employeeId) {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalFormations", formationRepository.findByEmployeeIdE(employeeId).size());
        stats.put("formationsTerminees", formationRepository.countByEmployeeAndStatut(employeeId, StatutFormation.TERMINEE));
        stats.put("formationsEnCours", formationRepository.countByEmployeeAndStatut(employeeId, StatutFormation.EN_COURS));
        stats.put("formationsPlanifiees", formationRepository.countByEmployeeAndStatut(employeeId, StatutFormation.PLANIFIEE));

        return stats;
    }
}
