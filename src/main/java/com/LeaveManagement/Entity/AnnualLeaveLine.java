package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(AnnualLeaveLineId.class)
public class AnnualLeaveLine {
    private int declaredDays;
    private int remainingDays;
    @Id
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "idE")
    private Employees employee;
    @Id
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "annualLeaveId")
    private AnnualLeave annualLeave;

    public AnnualLeaveLine() {
    }
    public AnnualLeaveLine(int declaredDays, int remainingDays, Employees employee, AnnualLeave annualLeave) {
        this.declaredDays = declaredDays;
        this.remainingDays = remainingDays;
        this.employee = employee;
        this.annualLeave = annualLeave;
    }

    public AnnualLeaveLine(Employees employee, AnnualLeave annualLeave) {
        this.employee = employee;
        this.annualLeave = annualLeave;
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
}
