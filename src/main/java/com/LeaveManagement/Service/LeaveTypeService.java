package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.LeaveTypeDTO;
import com.LeaveManagement.Entity.LeaveType;

import java.util.List;

public interface LeaveTypeService {
    Long addLeaveType(LeaveTypeDTO leaveTypeDTO);
    List<LeaveType> getAllLeaveTypes();
    LeaveType getLeaveTypeById(Long id);
    void updateLeaveType(Long id, LeaveTypeDTO leaveTypeDTO);
    void deleteLeaveType(Long id);
}
