package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "VARCHAR(255)")
    private String remplecementVisa;
    private LocalDate remplecementVisaDate;
    @Column(columnDefinition = "VARCHAR(255)")
    private String responsibleVisa;
    private LocalDate responsibleVisaDate;
    @Column(columnDefinition = "VARCHAR(255)")
    private String managerVisa;
    private LocalDate managerVisaDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employee;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "annualLeaveId")
    private AnnualLeave annualLeave;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "leaveTypeId")
    private LeaveType leaveType;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "replacementId")
    private Employees replacement;

    // Removed duplicate 'idE' column mapping
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "lmanagerId")
    private Employees lmanager;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "responsibleId")
    private Employees responsible;

    public Leave() {
    }

    public Leave(Long leaveId, LocalDate startDate, LocalDate endDate, String remplecementVisa, LocalDate remplecementVisaDate, String responsibleVisa, LocalDate responsibleVisaDate, String managerVisa, LocalDate managerVisaDate, Employees employee, AnnualLeave annualLeave, LeaveType leaveType, Employees replacement, Employees lmanager, Employees responsible) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remplecementVisa = remplecementVisa;
        this.remplecementVisaDate = remplecementVisaDate;
        this.responsibleVisa = responsibleVisa;
        this.responsibleVisaDate = responsibleVisaDate;
        this.managerVisa = managerVisa;
        this.managerVisaDate = managerVisaDate;
        this.employee = employee;
        this.annualLeave = annualLeave;
        this.leaveType = leaveType;
        this.replacement = replacement;
        this.lmanager = lmanager;
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

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public AnnualLeave getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(AnnualLeave annualLeave) {
        this.annualLeave = annualLeave;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Employees getReplacement() {
        return replacement;
    }

    public void setReplacement(Employees replacement) {
        this.replacement = replacement;
    }

    public Employees getLmanager() {
        return lmanager;
    }

    public void setLmanager(Employees lmanager) {
        this.lmanager = lmanager;
    }

    public Employees getResponsible() {
        return responsible;
    }

    public void setResponsible(Employees responsible) {
        this.responsible = responsible;
    }
}
