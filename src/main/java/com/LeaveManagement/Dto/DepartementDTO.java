package com.LeaveManagement.Dto;

import com.LeaveManagement.Entity.Employees;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

public class DepartementDTO {
    private Long IdDepartement;
    private String departementName;
    private Long respDepartementId;

    public DepartementDTO() {
    }
    public DepartementDTO(long idDepartement, String departementName, Long respDepartementId) {
        IdDepartement = idDepartement;
        this.departementName = departementName;

        this.respDepartementId = respDepartementId;
    }

    public Long getIdDepartement() {
        return IdDepartement;
    }

    public void setIdDepartement(Long idDepartement) {
        IdDepartement = idDepartement;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public Long getRespDepartementId() {
        return respDepartementId;
    }

    public void setRespDepartementId(Long respDepartementId) {
        this.respDepartementId = respDepartementId;
    }
}
