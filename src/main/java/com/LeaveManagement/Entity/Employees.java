package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idE;
    @Column(columnDefinition = "VARCHAR(255)")
    private String firstName;
    @Column(columnDefinition = "VARCHAR(255)")
    private String lastName;
    @Column(columnDefinition = "VARCHAR(255)")
    private String email;
    @Column(columnDefinition = "VARCHAR(255)")
    private String password;
    @Column(columnDefinition = "VARCHAR(255)")
    private String phone;
    @Column(columnDefinition = "VARCHAR(255)")
    private String ppr;
    @Column(columnDefinition = "VARCHAR(255)")
    private String cin;
    @Column(columnDefinition = "VARCHAR(255)")
    private String address;
    private LocalDate hireDate;
    @Column(columnDefinition = "VARCHAR(255)")
    private String workLocation;
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne
    @JoinColumn(name = "IdProfile")
    private Profiles profile;

    @ManyToOne
    @JoinColumn(name = "IdPost")
    private Posts post;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "IdDepartement")
    private Departement departement;

    @JsonBackReference
    @OneToMany(mappedBy = "employee")
    private List<AnnualLeaveLine> annualLeaveLines;

    @JsonBackReference
    @OneToMany(mappedBy = "employee")
    private List<Leave> leaves;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Formation> formations = new ArrayList<>();

    public Employees() {
    }

    // Getters et Setters
    public Long getIdE() {
        return idE;
    }

    public void setIdE(Long idE) {
        this.idE = idE;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPpr() {
        return ppr;
    }

    public void setPpr(String ppr) {
        this.ppr = ppr;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Profiles getProfile() {
        return profile;
    }

    public void setProfile(Profiles profile) {
        this.profile = profile;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<AnnualLeaveLine> getAnnualLeaveLines() {
        return annualLeaveLines;
    }

    public void setAnnualLeaveLines(List<AnnualLeaveLine> annualLeaveLines) {
        this.annualLeaveLines = annualLeaveLines;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }

    // Constructeur mis à jour
    public Employees(Long idE, String firstName, String lastName, String email,
                     String password, String phone, String ppr, String cin,
                     String address, LocalDate hireDate, String workLocation,
                     String image, Profiles profile, Posts post,
                     Departement departement, List<AnnualLeaveLine> annualLeaveLines,
                     List<Leave> leaves) {
        this.idE = idE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ppr = ppr;
        this.cin = cin;
        this.address = address;
        this.hireDate = hireDate;
        this.workLocation = workLocation;
        this.image = image;
        this.profile = profile;
        this.post = post;
        this.departement = departement;
        this.annualLeaveLines = annualLeaveLines;
        this.leaves = leaves;
    }
}