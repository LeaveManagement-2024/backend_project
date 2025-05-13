package com.LeaveManagement.controller;


import com.LeaveManagement.Dto.AnnualLeaveDTO;
import com.LeaveManagement.Entity.AnnualLeave;
import com.LeaveManagement.Entity.AnnualLeaveLine;
import com.LeaveManagement.Service.AnnualLeaveService;
import com.LeaveManagement.Service.impl.AnnualLeaveImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("annualLeave")
public class AnnualLeaveController {

    @Autowired
    private AnnualLeaveService annualLeaveService;
    @Autowired
    private AnnualLeaveImpl annualLeaveImp;

    @PostMapping(path = "/save")
    public  Long saveannualLeave(@RequestBody AnnualLeaveDTO annualLeavesDTO){

        Long id =annualLeaveService.addAnnualLeave(annualLeavesDTO);
        return id;

    }
    @PostMapping(path = "/setOfStatus/{id}")
    public  void setOfStatus(@PathVariable Long id){
        annualLeaveService.setOfStatus(id);

    }
    @PostMapping(path = "/setOnStatus/{id}")
    public  void setOnStatus(@PathVariable Long id){
        annualLeaveService.setOnStatus(id);

    }
    @GetMapping(path="/getAll")
    public List<AnnualLeave> getAllannualLeave(){
        return  annualLeaveService.getAllAnnualLeaves();
    }

    @GetMapping(path="/getById/{Id}")
    public AnnualLeave getannualLeaveById(@PathVariable Long Id){
        return annualLeaveService.GetAnnualLeaveById(Id);
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AnnualLeaveDTO annualLeaveDTO) {
        annualLeaveService.updateAnnualLeave(id,annualLeaveDTO);
        return ResponseEntity.ok("annualLeave updated successfully");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteannualLeave(@PathVariable Long id) {
        annualLeaveService.deleteAnnualLeave(id);
        return ResponseEntity.ok("annualLeave deleted successfully");
    }
    @GetMapping(path="/getAnnualLeaveLineById/{Id}")
    public List<AnnualLeaveLine> getAnnualLeaveLineByid(@PathVariable Long Id){
        return annualLeaveService.getAnnualLeaveLineByid(Id);
    }


}
