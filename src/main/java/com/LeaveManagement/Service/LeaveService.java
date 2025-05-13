package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.LeaveDTO;
import com.LeaveManagement.Entity.Leave;
import java.util.List;

public interface LeaveService {
    Long addLeave(LeaveDTO leave);
    List<Leave> getAllLeaves();
    Leave getLeaveById(Long id);
    void updateLeave(Long id, LeaveDTO leave);
    void deleteLeave(Long id);
    List<Leave> getLeavesByEmpId(Long ide);
    Long getNumberOfEmployeesOnLeaveToday();
    List<Leave> getLeaveToday();
    List<Leave> getLeavesById(Long id);
    Long getNumberOfEmployeesOnLeaveTomorrow();
    List<Leave> getLeaveTomorrow();
    List<Leave> findEmployeesReturningToWorkTomorrow ();
    Long NumberfindEmployeesReturningToWorkTomorrow ();
    List<Leave> ConfermedLeave();
    List<Leave> UnconfermedLeave();
    List<Leave> UnconfermedLeaveByManager();
    List<Leave> UnconfermedLeaveByResponsible();
    List<Leave> UnconfermedLeaveByRemplacment();
}
