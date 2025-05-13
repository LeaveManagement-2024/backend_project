package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.AnnualLeaveDTO;
import com.LeaveManagement.Entity.AnnualLeave;
import com.LeaveManagement.Entity.AnnualLeaveLine;


import java.util.List;

public interface AnnualLeaveService {
    Long addAnnualLeave(AnnualLeaveDTO annualLeave);
    public List<AnnualLeave> getAllAnnualLeaves();
    public AnnualLeave GetAnnualLeaveById(Long  id);
    void updateAnnualLeave(Long id, AnnualLeaveDTO annualLeave);
    void deleteAnnualLeave(Long id);
    List<AnnualLeaveLine> getAnnualLeaveLineByid(Long id );
    public void setOfStatus(Long id);
    public void setOnStatus(Long id);
}
