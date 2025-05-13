package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.AnnualLeaveLineDTO;
import com.LeaveManagement.Entity.AnnualLeaveLine;
import java.util.List;
import java.util.Optional;

public interface AnnualLeaveLineService {
   void addAnnualLeaveLine(AnnualLeaveLineDTO annualLeaveLineDTO);
    List<AnnualLeaveLine> getAllAnnualLeaveLines();
    AnnualLeaveLine getAnnualLeaveLineById(Long idE, Long annualLeaveId);
    AnnualLeaveLine getAnnualLeaveLineById47(Long idE, Long annualLeaveId);
    void updateAnnualLeaveLine(Long idE, Long annualLeaveId, AnnualLeaveLineDTO annualLeaveLineDTO);
    void deleteAnnualLeaveLine(Long idE, Long annualLeaveId);
    public List<AnnualLeaveLine> getAnnualLeaveLineByEmpId(Long ide);
    void addAnnualLeaveLineForAllOldEmp(Long annualLeaveId, int declaredDays);
    void addAnnualLeaveLineForAllNewEmp(Long annualLeaveId);
}
