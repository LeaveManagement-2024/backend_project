package com.LeaveManagement.Dto;

public class LeaveTypeDTO {
    private Long leaveTypeId;
    private String name;

    public LeaveTypeDTO(Long leaveTypeId, String name) {
        this.leaveTypeId = leaveTypeId;
        this.name = name;
    }

    public LeaveTypeDTO() {
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
