package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdDepartement;

    private String departementName;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "respDepartementId")
    private Employees respDepartement ;

    public Departement() {
    }

    public Departement(long idDepartement, String departementName,Employees respDepartement) {
        IdDepartement = idDepartement;
        this.departementName = departementName;

        this.respDepartement = respDepartement;
    }

    public long getIdDepartement() {
        return IdDepartement;
    }

    public void setIdDepartement(long idDepartement) {
        IdDepartement = idDepartement;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public Employees getRespDepartement() {
        return respDepartement;
    }

    public void setRespDepartement(Employees respDepartement) {
        this.respDepartement = respDepartement;
    }
}

