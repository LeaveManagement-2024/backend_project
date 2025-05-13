package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.ServiceE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<ServiceE,Long> {
}
