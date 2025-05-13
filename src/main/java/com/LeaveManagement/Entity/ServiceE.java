package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
@Entity
public class ServiceE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdService;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String serviceNameFr;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String serviceNameAr;
    @ManyToOne
    @JoinColumn(name = "IdDepartment")
    private Departement departement;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "respServiceId")
    private Employees respService;

    public ServiceE(){

    }

    public ServiceE(Long idService, String serviceNameFr, String serviceNameAr, Departement departement, Employees respService) {
        IdService = idService;
        this.serviceNameFr = serviceNameFr;
        this.serviceNameAr = serviceNameAr;
        this.departement = departement;
        this.respService = respService;
    }

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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Employees getRespService() {
        return respService;
    }

    public void setRespService(Employees respService) {
        this.respService = respService;
    }
}
