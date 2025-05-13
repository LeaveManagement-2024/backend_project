package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.ServiceDTO;
import com.LeaveManagement.Entity.Departement;
import com.LeaveManagement.Entity.Employees;
import com.LeaveManagement.Entity.Filiere;
import com.LeaveManagement.Entity.ServiceE;
import com.LeaveManagement.Repo.DepartementRepo;
import com.LeaveManagement.Repo.EmployeeRepo;
import com.LeaveManagement.Repo.GradesRepo;
import com.LeaveManagement.Repo.ServiceRepo;
import com.LeaveManagement.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceImp implements ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public Long addService(ServiceDTO serviceDTO) {
        Departement departement = departementRepo.findById(serviceDTO.getIdDepartment()).orElseThrow(()->new IllegalArgumentException("Department not found"));
        Employees employee = employeeRepo.findById(serviceDTO.getRespServiceId()).orElse(null);
        ServiceE serviceE =new ServiceE();
        serviceE.setServiceNameFr(serviceDTO.getServiceNameFr());
        serviceE.setServiceNameAr(serviceDTO.getServiceNameAr());

        serviceE.setDepartement(departement);

        serviceE.setRespService(employee);
        serviceRepo.save(serviceE);
        return serviceE.getIdService();
    }

    @Override
    public List<ServiceE> getAllService() {
        return serviceRepo.findAll();
    }

    @Override
    public ServiceE GetServiceById(Long id) {
        return serviceRepo.findById(id).get();
    }

    @Override
    public void updateService(Long id, ServiceDTO serviceDTO) {
        ServiceE serviceEToUpdate = serviceRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Service not found"));
        Departement departement = departementRepo.findById(serviceDTO.getIdDepartment()).orElseThrow(()->new IllegalArgumentException("Department not found"));
        Employees employee = employeeRepo.findById(serviceDTO.getRespServiceId()).orElse(null);
        serviceEToUpdate.setServiceNameFr(serviceDTO.getServiceNameFr());
        serviceEToUpdate.setServiceNameAr(serviceDTO.getServiceNameAr());
        serviceEToUpdate.setDepartement(departement);
        serviceEToUpdate.setRespService(employee);
        serviceRepo.save(serviceEToUpdate);

    }
    @Override
    public void deleteService(Long id) {
        serviceRepo.deleteById(id);

    }
}
