package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.AnnualLeaveLine;
import com.LeaveManagement.Entity.AnnualLeaveLineId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnualLeaveLineRepo extends JpaRepository<AnnualLeaveLine, AnnualLeaveLineId> {
    @Override
    <S extends AnnualLeaveLine> S save(S entity);
}

