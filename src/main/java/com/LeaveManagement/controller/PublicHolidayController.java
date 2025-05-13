package com.LeaveManagement.controller;

import com.LeaveManagement.Entity.PublicHoliday;

import com.LeaveManagement.Service.PublicHolidayService;
import com.LeaveManagement.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("publicHoliday")
public class PublicHolidayController {
    @Autowired
    private PublicHolidayService publicHolidayService;
    @PostMapping(path = "/save")
    public  Long savePublicHoliday(@RequestBody PublicHoliday publicHoliday){
        Long id =publicHolidayService.addPublicHoliday(publicHoliday);
        return id;
    }
    @GetMapping(path="/getAll")
    public List<PublicHoliday> getAllPublicHolidays(){
        return publicHolidayService.getAllPublicHolidays();
    }

    @GetMapping(path="/getById/{Id}")
    public PublicHoliday getPublicHolidayById(@PathVariable Long Id){
        return publicHolidayService.getPublicHolidayById(Id);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updatePublicHoliday(@PathVariable Long id, @RequestBody PublicHoliday publicHoliday) {
        publicHolidayService.updatePublicHoliday(id,publicHoliday);
        return ResponseEntity.ok("PublicHoliday updated successfully");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletePublicHoliday(@PathVariable Long id) {
        publicHolidayService.deletePublicHoliday(id);
        return ResponseEntity.ok("PublicHoliday deleted successfully");
    }
}



