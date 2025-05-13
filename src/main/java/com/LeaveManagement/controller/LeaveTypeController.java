package com.LeaveManagement.controller;

import com.LeaveManagement.Dto.LeaveTypeDTO;
import com.LeaveManagement.Dto.ServiceDTO;
import com.LeaveManagement.Entity.LeaveType;
import com.LeaveManagement.Entity.ServiceE;
import com.LeaveManagement.Service.LeaveTypeService;
import com.LeaveManagement.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("leaveTypes")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeService leaveTypeService;
    @PostMapping(path = "/save")
    public  Long saveLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO){

        Long id =leaveTypeService.addLeaveType(leaveTypeDTO);
        return id;

    }
    @GetMapping(path="/getAll")
    public List<LeaveType> getAllLeaveTypes(){
        return leaveTypeService.getAllLeaveTypes();
    }

    @GetMapping(path="/getById/{Id}")
    public LeaveType getLeaveTypeServiceById(@PathVariable Long Id){
        return leaveTypeService.getLeaveTypeById(Id);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateLeaveType(@PathVariable long id, @RequestBody LeaveTypeDTO leaveTypeDTO) {
        leaveTypeService.updateLeaveType(id,leaveTypeDTO);
        return ResponseEntity.ok("LeaveType updated successfully");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteLeaveType(@PathVariable Long id) {
        leaveTypeService.deleteLeaveType(id);
        return ResponseEntity.ok("LeaveType deleted successfully");
    }
}



