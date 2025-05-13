package com.LeaveManagement.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdGrade;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String gradeNameFr;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String gradeNameAr;

    public Grades() {
    }
    public Grades(Long idGrade, String gradeNameFr, String gradeNameAr) {
        IdGrade = idGrade;
        this.gradeNameFr = gradeNameFr;
        this.gradeNameAr = gradeNameAr;
    }

    public Grades(Long idGrade) {
        IdGrade = idGrade;
    }

    public Long getIdGrade() {
        return IdGrade;
    }

    public void setIdGrade(Long idGrade) {
        IdGrade = idGrade;
    }

    public String getGradeNameFr() {
        return gradeNameFr;
    }

    public void setGradeNameFr(String gradeNameEn) {
        this.gradeNameFr = gradeNameEn;
    }

    public String getGradeNameAr() {
        return gradeNameAr;
    }

    public void setGradeNameAr(String gradeNameAr) {
        this.gradeNameAr = gradeNameAr;
    }
}
