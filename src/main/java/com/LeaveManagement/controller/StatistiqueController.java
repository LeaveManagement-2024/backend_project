package com.LeaveManagement.controller;

import com.LeaveManagement.Entity.Employees;
import com.LeaveManagement.Entity.Leave;
import com.LeaveManagement.Service.LeaveService;
import com.LeaveManagement.Service.impl.StatistiqueImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin("*")
public class StatistiqueController {

    @Autowired
    private StatistiqueImp statistiqueService;
    @Autowired
    private LeaveService leaveService;

    @GetMapping("/newLeaveRequests")
    public long getNewLeaveRequests() {
        return statistiqueService.getNewLeaveRequests();
    }

    @GetMapping("/totalEmployees")
    public long getTotalEmployees() {
        return statistiqueService.getTotalEmployees();
    }

    @GetMapping("/unconfirmedLeavesByManager")
    public List<Leave> UnconfirmedLeaves() {
        return statistiqueService.UnconfirmedLeavesByManager();
    }

    @GetMapping("/numberUnconfirmedLeavesByManager")
    public long nbrUnconfirmedLeavesByManager() {
        return statistiqueService.getUnconfirmedLeavesByManager();
    }

    @GetMapping("/numberLeaves-today")
    public Long getNumberOfEmployeesOnLeaveToday() {
        return leaveService.getNumberOfEmployeesOnLeaveToday();
    }

    @GetMapping(path = "/LeaveToday")
    public List<Leave> getLeaveToday() {
        return leaveService.getLeaveToday();
    }
    @GetMapping("/numberLeaves-Tomorrow")
    public Long getNumberOfEmployeesOnLeaveTomorrow() {
        return leaveService.getNumberOfEmployeesOnLeaveTomorrow();
    }
    @GetMapping(path = "/LeaveEndYesterday")
    public List<Leave> LeaveEndYesterday() {
        return leaveService.findEmployeesReturningToWorkTomorrow();
    }
    @GetMapping("/numberLeaveEndYesterday")
    public Long numberLeaveEndYesterday() {
        return leaveService.NumberfindEmployeesReturningToWorkTomorrow();
    }

    @GetMapping(path = "/LeaveTomorrow")
    public List<Leave> getLeaveTomorrow() {
        return leaveService.getLeaveTomorrow();
    }

    @GetMapping("/countNewEmp")
    public long countNewEmployees() {
        return statistiqueService.countNewEmployees();
    }

    @GetMapping("/countOldEmp")
    public long countOldEmployees() {
        return statistiqueService.countOldEmployees();
    }

    @GetMapping("/newEmp")
    public List<Employees> newEmployees() {
        return statistiqueService.newEmployees();
    }

    @GetMapping("/oldEmp")
    public List<Employees> oldEmployees() {
        return statistiqueService.oldEmployees();
    }

}
