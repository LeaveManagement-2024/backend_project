package com.LeaveManagement.Dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AnnualLeaveDTO {
    private Long annualLeaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String label;
    private String status;


    public AnnualLeaveDTO() {
    }

    public AnnualLeaveDTO(Long annualLeaveId, LocalDate startDate, LocalDate endDate, String label, String status) {
        this.annualLeaveId = annualLeaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.label = label;
        this.status = status;

    }

    public Long getAnnualLeaveId() {
        return annualLeaveId;
    }

    public void setAnnualLeaveId(Long annualLeaveId) {
        this.annualLeaveId = annualLeaveId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
