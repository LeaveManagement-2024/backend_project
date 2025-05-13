package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepo extends JpaRepository<LeaveType,Long> {
}
