package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdDepartement;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String departementNameFr;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String departementNameAr;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "respDepartementId")
    private Employees respDepartement ;

    public Departement() {
    }

    public Departement(long idDepartement, String departementNameFr, String departementNameAr, Employees respDepartement) {
        IdDepartement = idDepartement;
        this.departementNameFr = departementNameFr;
        this.departementNameAr = departementNameAr;
        this.respDepartement = respDepartement;
    }

    public long getIdDepartement() {
        return IdDepartement;
    }

    public void setIdDepartement(long idDepartement) {
        IdDepartement = idDepartement;
    }

    public String getDepartementNameFr() {
        return departementNameFr;
    }

    public void setDepartementNameFr(String departementNameFr) {
        this.departementNameFr = departementNameFr;
    }

    public String getDepartementNameAr() {
        return departementNameAr;
    }

    public void setDepartementNameAr(String departementNameAr) {
        this.departementNameAr = departementNameAr;
    }

    public Employees getRespDepartement() {
        return respDepartement;
    }

    public void setRespDepartement(Employees respDepartement) {
        this.respDepartement = respDepartement;
    }
}

