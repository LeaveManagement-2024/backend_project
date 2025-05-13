package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ProjectRepo extends JpaRepository<Projects, Integer> {


}
