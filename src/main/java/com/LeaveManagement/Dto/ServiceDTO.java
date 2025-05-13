package com.LeaveManagement.Dto;

public class ServiceDTO {
    private Long IdService;
    private String serviceNameFr;
    private String serviceNameAr;
    private Long IdDepartment;
    private Long respServiceId;

    public Long getIdService() {
        return IdService;
    }

    public void setIdService(Long idService) {
        IdService = idService;
    }

    public String getServiceNameFr() {
        return serviceNameFr;
    }

    public void setServiceNameFr(String serviceNameFr) {
        this.serviceNameFr = serviceNameFr;
    }

    public String getServiceNameAr() {
        return serviceNameAr;
    }

    public void setServiceNameAr(String serviceNameAr) {
        this.serviceNameAr = serviceNameAr;
    }

    public Long getIdDepartment() {
        return IdDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        IdDepartment = idDepartment;
    }

    public Long getRespServiceId() {
        return respServiceId;
    }

    public void setRespServiceId(Long respServiceId) {
        this.respServiceId = respServiceId;
    }

    public ServiceDTO(Long idService, String serviceNameFr, String serviceNameAr, Long idDepartment, Long respServiceId) {
        IdService = idService;
        this.serviceNameFr = serviceNameFr;
        this.serviceNameAr = serviceNameAr;
        IdDepartment = idDepartment;
        this.respServiceId = respServiceId;
    }
}
