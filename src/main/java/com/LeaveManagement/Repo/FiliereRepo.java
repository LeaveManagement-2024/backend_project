package com.LeaveManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.LeaveManagement.Entity.Filiere;
@EnableJpaRepositories
@Repository
    public interface FiliereRepo extends JpaRepository<Filiere, Long> {


}
