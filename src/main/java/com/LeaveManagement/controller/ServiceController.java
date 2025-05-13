package com.LeaveManagement.controller;


import com.LeaveManagement.Dto.ServiceDTO;
import com.LeaveManagement.Entity.ServiceE;
import com.LeaveManagement.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("services")
public class ServiceController {
        @Autowired
        private ServiceService serviceService;
        @PostMapping(path = "/save")
        public  Long saveService(@RequestBody ServiceDTO serviceDTO){

            Long id =serviceService.addService(serviceDTO);
            return id;

        }
        @GetMapping(path="/getAll")
        public List<ServiceE> getAllServices(){
            return serviceService.getAllService();
        }

        @GetMapping(path="/getById/{Id}")
        public ServiceE getServiceById(@PathVariable Long Id){
            return serviceService.GetServiceById(Id);
        }

        @PutMapping(path = "/update/{id}")
        public ResponseEntity<String> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
            serviceService.updateService(id,serviceDTO);
            return ResponseEntity.ok("Service updated successfully");
        }

        @DeleteMapping(path = "/delete/{id}")
        public ResponseEntity<String> deleteService(@PathVariable Long id) {
            serviceService.deleteService(id);
            return ResponseEntity.ok("Service deleted successfully");
        }
    }



