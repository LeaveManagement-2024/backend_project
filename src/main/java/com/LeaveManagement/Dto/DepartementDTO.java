package com.LeaveManagement.Dto;

import com.LeaveManagement.Entity.Employees;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

public class DepartementDTO {
    private Long IdDepartement;
    private String departementNameFr;
    private String departementNameAr;
    private Long respDepartementId;

    public DepartementDTO() {
    }
    public DepartementDTO(long idDepartement, String departementNameFr, String departementNameAr, Long respDepartementId) {
        IdDepartement = idDepartement;
        this.departementNameFr = departementNameFr;
        this.departementNameAr = departementNameAr;
        this.respDepartementId = respDepartementId;
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

    public Long getRespDepartementId() {
        return respDepartementId;
    }

    public void setRespDepartementId(Long respDepartementId) {
        this.respDepartementId = respDepartementId;
    }


}
