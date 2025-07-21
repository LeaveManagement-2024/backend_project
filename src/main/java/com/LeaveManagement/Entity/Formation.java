package com.LeaveManagement.Entity;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "formations")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation")
    private Long idFormation;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String nomFormation;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeFormation typeFormation;

    @Column(columnDefinition = "VARCHAR(255)")
    private String nomCertification; // Nom du certificat obtenu

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Column(nullable = false)
    private Integer dureeEnJours;

    @Column(columnDefinition = "VARCHAR(255)")
    private String organisme; // Organisme formateur

    @Column(columnDefinition = "DECIMAL(5,2)")
    private Double cout; // Coût de la formation

    @Enumerated(EnumType.STRING)
    private StatutFormation statut;

    @Column(columnDefinition = "TEXT")
    private String commentaires;

    // Relation avec Employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;

    // ========== CONSTRUCTEURS ==========
    public Formation() {
        this.dureeEnJours = 0;
        this.cout = 0.0;
        this.statut = StatutFormation.PLANIFIEE;
    }

    public Formation(String nomFormation, TypeFormation typeFormation, String nomCertification,
                     LocalDate dateDebut, LocalDate dateFin, Integer dureeEnJours, Employees employee) {
        this();
        this.nomFormation = nomFormation;
        this.typeFormation = typeFormation;
        this.nomCertification = nomCertification;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeEnJours = dureeEnJours;
        this.employee = employee;
    }

    // ========== GETTERS ET SETTERS ==========

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeFormation getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(TypeFormation typeFormation) {
        this.typeFormation = typeFormation;
    }

    public String getNomCertification() {
        return nomCertification;
    }

    public void setNomCertification(String nomCertification) {
        this.nomCertification = nomCertification;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getDureeEnJours() {
        return dureeEnJours;
    }

    public void setDureeEnJours(Integer dureeEnJours) {
        this.dureeEnJours = dureeEnJours;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public StatutFormation getStatut() {
        return statut;
    }

    public void setStatut(StatutFormation statut) {
        this.statut = statut;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    // ========== MÉTHODES UTILITAIRES ==========

    @Override
    public String toString() {
        return "Formation{" +
                "idFormation=" + idFormation +
                ", nomFormation='" + nomFormation + '\'' +
                ", typeFormation=" + typeFormation +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", dureeEnJours=" + dureeEnJours +
                ", statut=" + statut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formation)) return false;
        Formation formation = (Formation) o;
        return idFormation != null && idFormation.equals(formation.idFormation);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}