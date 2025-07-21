package com.LeaveManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.LeaveManagement.Entity.Departement;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface DepartementRepo extends JpaRepository<Departement, Long> {
    Optional<Departement> findByDepartementName(String departementName);

}
