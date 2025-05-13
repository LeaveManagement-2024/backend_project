package com.LeaveManagement.Entity;

import jakarta.persistence.*;

@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdFiliere;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String filiereName;

    @ManyToOne
    @JoinColumn(name = "IdService")
    private ServiceE service;

    public Filiere() {
    }

    public Filiere(Long idFiliere, String filiereName, ServiceE service) {
        IdFiliere = idFiliere;
        this.filiereName = filiereName;
        this.service = service;
    }

    public Long getIdFiliere() {
        return IdFiliere;
    }

    public void setIdFiliere(Long idFiliere) {
        IdFiliere = idFiliere;
    }

    public String getFiliereName() {
        return filiereName;
    }

    public void setFiliereName(String filiereName) {
        this.filiereName = filiereName;
    }

    public ServiceE getService() {
        return service;
    }

    public void setService(ServiceE service) {
        this.service = service;
    }
}