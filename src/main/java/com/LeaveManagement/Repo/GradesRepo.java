package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface GradesRepo extends JpaRepository<Grades,Long> {
}
