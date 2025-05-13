package com.LeaveManagement.Entity;

import java.io.Serializable;

public class AnnualLeaveLineId implements Serializable {
    private Long employee;
    private Long annualLeave;

    public AnnualLeaveLineId(Long employee, Long annualLeave) {
        this.employee = employee;
        this.annualLeave = annualLeave;
    }

    public AnnualLeaveLineId() {
    }
}
