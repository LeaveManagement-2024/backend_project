package com.LeaveManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.LeaveManagement.Entity.Departement;

@EnableJpaRepositories
@Repository
public interface DepartementRepo extends JpaRepository<Departement, Long> {
}
