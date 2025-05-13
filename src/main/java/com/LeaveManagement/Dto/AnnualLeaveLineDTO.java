package com.LeaveManagement.Dto;

import java.io.Serializable;

public class AnnualLeaveLineDTO implements Serializable {
    private Long idE;
    private Long annualLeaveId;
    private int declaredDays;
    private int remainingDays;


    public AnnualLeaveLineDTO() {
    }

    public AnnualLeaveLineDTO(Long idE, Long annualLeaveId, int declaredDays, int remainingDays) {
        this.idE = idE;
        this.annualLeaveId = annualLeaveId;
        this.declaredDays = declaredDays;
        this.remainingDays = remainingDays;

    }

    public Long getIdE() {
        return idE;
    }

    public void setIdE(Long idE) {
        this.idE = idE;
    }

    public Long getAnnualLeaveId() {
        return annualLeaveId;
    }

    public void setAnnualLeaveId(Long annualLeaveId) {
        this.annualLeaveId = annualLeaveId;
    }

    public int getDeclaredDays() {
        return declaredDays;
    }

    public void setDeclaredDays(int declaredDays) {
        this.declaredDays = declaredDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
}
