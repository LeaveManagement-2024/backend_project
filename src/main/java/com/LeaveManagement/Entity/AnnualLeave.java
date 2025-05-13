package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
public class AnnualLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualLeaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String label;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String status;
    @JsonBackReference
    @OneToMany(mappedBy = "annualLeave")
    private List<AnnualLeaveLine> annualLeaveLines;
    @JsonBackReference
    @OneToMany(mappedBy = "annualLeave")
    private List<Leave> leaves;

    public AnnualLeave() {
    }

    public AnnualLeave(Long annualLeaveId, LocalDate startDate, LocalDate endDate, String label, String status, List<AnnualLeaveLine> annualLeaveLines, List<Leave> leaves) {
        this.annualLeaveId = annualLeaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.label = label;
        this.status = status;
        this.annualLeaveLines = annualLeaveLines;
        this.leaves = leaves;
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

    public List<AnnualLeaveLine> getAnnualLeaveLines() {
        return annualLeaveLines;
    }

    public void setAnnualLeaveLines(List<AnnualLeaveLine> annualLeaveLines) {
        this.annualLeaveLines = annualLeaveLines;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }
}
