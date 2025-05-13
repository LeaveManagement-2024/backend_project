package com.LeaveManagement.Service;


import com.LeaveManagement.Dto.ServiceDTO;
import com.LeaveManagement.Entity.ServiceE;
import java.util.List;

public interface ServiceService {
    Long addService(ServiceDTO serviceDTO);
    public List<ServiceE> getAllService();
    public ServiceE GetServiceById(Long  id);
    void updateService(Long id, ServiceDTO rviceDTO);
    void deleteService(Long id);
}
