package com.LeaveManagement.Dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Date;

public class LeaveDTO {

    private Long leaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remplecementVisa;
    private LocalDate remplecementVisaDate;
    private String responsibleVisa;
    private LocalDate responsibleVisaDate;
    private String managerVisa;
    private LocalDate managerVisaDate;

    private Long employeeId;
    private Long annualLeaveId;
    private Long leaveTypeId;
    private Long replacementId;
    private Long lmanagerId;
    private Long responsible;

    public LeaveDTO() {
    }

    public LeaveDTO(Long leaveId, LocalDate startDate, LocalDate endDate, String remplecementVisa, LocalDate remplecementVisaDate, String responsibleVisa, LocalDate responsibleVisaDate, String managerVisa, LocalDate managerVisaDate, Long employeeId, Long annualLeaveId, Long leaveTypeId, Long replacementId, Long lmanagerId, Long responsible) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remplecementVisa = remplecementVisa;
        this.remplecementVisaDate = remplecementVisaDate;
        this.responsibleVisa = responsibleVisa;
        this.responsibleVisaDate = responsibleVisaDate;
        this.managerVisa = managerVisa;
        this.managerVisaDate = managerVisaDate;
        this.employeeId = employeeId;
        this.annualLeaveId = annualLeaveId;
        this.leaveTypeId = leaveTypeId;
        this.replacementId = replacementId;
        this.lmanagerId = lmanagerId;
        this.responsible = responsible;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
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

    public String getRemplecementVisa() {
        return remplecementVisa;
    }

    public void setRemplecementVisa(String remplecementVisa) {
        this.remplecementVisa = remplecementVisa;
    }

    public LocalDate getRemplecementVisaDate() {
        return remplecementVisaDate;
    }

    public void setRemplecementVisaDate(LocalDate remplecementVisaDate) {
        this.remplecementVisaDate = remplecementVisaDate;
    }

    public String getResponsibleVisa() {
        return responsibleVisa;
    }

    public void setResponsibleVisa(String responsibleVisa) {
        this.responsibleVisa = responsibleVisa;
    }

    public LocalDate getResponsibleVisaDate() {
        return responsibleVisaDate;
    }

    public void setResponsibleVisaDate(LocalDate responsibleVisaDate) {
        this.responsibleVisaDate = responsibleVisaDate;
    }

    public String getManagerVisa() {
        return managerVisa;
    }

    public void setManagerVisa(String managerVisa) {
        this.managerVisa = managerVisa;
    }

    public LocalDate getManagerVisaDate() {
        return managerVisaDate;
    }

    public void setManagerVisaDate(LocalDate managerVisaDate) {
        this.managerVisaDate = managerVisaDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getAnnualLeaveId() {
        return annualLeaveId;
    }

    public void setAnnualLeaveId(Long annualLeaveId) {
        this.annualLeaveId = annualLeaveId;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public Long getReplacementId() {
        return replacementId;
    }

    public void setReplacementId(Long replacementId) {
        this.replacementId = replacementId;
    }

    public Long getLmanagerId() {
        return lmanagerId;
    }

    public void setLmanagerId(Long lmanagerId) {
        this.lmanagerId = lmanagerId;
    }

    public Long getResponsible() {
        return responsible;
    }

    public void setResponsible(Long responsible) {
        this.responsible = responsible;
    }
}
