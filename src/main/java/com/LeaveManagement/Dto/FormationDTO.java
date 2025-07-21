package com.LeaveManagement.Dto;

import com.LeaveManagement.Entity.StatutFormation;
import com.LeaveManagement.Entity.TypeFormation;

import java.time.LocalDate;

public class FormationDTO {
    private Long idFormation;
    private String nomFormation;
    private String description;
    private TypeFormation typeFormation;
    private String nomCertification;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer dureeEnJours;
    private String organisme;
    private Double cout;
    private StatutFormation statut;
    private String commentaires;
    private Long employeeId;

    // Constructeurs
    public FormationDTO() {}

    // Getters et Setters
    public Long getIdFormation() { return idFormation; }
    public void setIdFormation(Long idFormation) { this.idFormation = idFormation; }

    public String getNomFormation() { return nomFormation; }
    public void setNomFormation(String nomFormation) { this.nomFormation = nomFormation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TypeFormation getTypeFormation() { return typeFormation; }
    public void setTypeFormation(TypeFormation typeFormation) { this.typeFormation = typeFormation; }

    public String getNomCertification() { return nomCertification; }
    public void setNomCertification(String nomCertification) { this.nomCertification = nomCertification; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Integer getDureeEnJours() { return dureeEnJours; }
    public void setDureeEnJours(Integer dureeEnJours) { this.dureeEnJours = dureeEnJours; }

    public String getOrganisme() { return organisme; }
    public void setOrganisme(String organisme) { this.organisme = organisme; }

    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }

    public StatutFormation getStatut() { return statut; }
    public void setStatut(StatutFormation statut) { this.statut = statut; }

    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
}