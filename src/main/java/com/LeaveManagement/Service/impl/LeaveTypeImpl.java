package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.LeaveTypeDTO;
import com.LeaveManagement.Entity.LeaveType;
import com.LeaveManagement.Repo.LeaveTypeRepo;
import com.LeaveManagement.Service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeImpl implements LeaveTypeService {
    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    @Override
    public Long addLeaveType(LeaveTypeDTO leaveTypeDTO) {
        LeaveType leaveType=new LeaveType();
        leaveType.setName(leaveTypeDTO.getName());
        leaveTypeRepo.save(leaveType);
        return leaveType.getLeaveTypeId();
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepo.findAll();
    }

    @Override
    public LeaveType getLeaveTypeById(Long id) {
        return leaveTypeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Leave Type not found"));
    }

    @Override
    public void updateLeaveType(Long id, LeaveTypeDTO leaveTypeDTO) {
        LeaveType leaveTypeToUpdate = leaveTypeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Leave Type not found"));
        leaveTypeToUpdate.setName(leaveTypeDTO.getName());
        leaveTypeRepo.save(leaveTypeToUpdate);
    }

    @Override
    public void deleteLeaveType(Long id) {
        leaveTypeRepo.deleteById(id);
    }
}
