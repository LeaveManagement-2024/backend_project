package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Formation;
import com.LeaveManagement.Entity.StatutFormation;
import com.LeaveManagement.Entity.TypeFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByEmployeeIdE(Long employeeId);
    List<Formation> findByTypeFormation(TypeFormation typeFormation);
    List<Formation> findByStatut(StatutFormation statut);
    List<Formation> findByDateDebutBetween(LocalDate dateDebut, LocalDate dateFin);
    List<Formation> findByEmployeeIdEAndStatut(Long employeeId, StatutFormation statut);

    // Statistiques
    @Query("SELECT COUNT(f) FROM Formation f WHERE f.employee.idE = :employeeId AND f.statut = :statut")
    Long countByEmployeeAndStatut(@Param("employeeId") Long employeeId, @Param("statut") StatutFormation statut);

    @Query("SELECT f.typeFormation, COUNT(f) FROM Formation f GROUP BY f.typeFormation")
    List<Object[]> countByTypeFormation();
}