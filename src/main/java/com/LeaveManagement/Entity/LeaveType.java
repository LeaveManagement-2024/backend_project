package com.LeaveManagement.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveTypeId;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "leaveType")
    private List<Leave> leaves;

    public LeaveType() {
    }

    public LeaveType(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public LeaveType(Long leaveTypeId, String name, List<Leave> leaves) {
        this.leaveTypeId = leaveTypeId;
        this.name = name;
        this.leaves = leaves;
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

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }
}