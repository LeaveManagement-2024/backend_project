package com.LeaveManagement.Dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class EmployeesDTO{
    private Long idE;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String ppr;
    private String cin;
    private String address;
    private LocalDate hireDate;
    private String workLocation;
    private MultipartFile image;
    private Long profileId;
    private Long gradeId;
    private Long filiereId;
    private Long postId;

    public EmployeesDTO() {
    }

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getFiliereId() {
        return filiereId;
    }

    public void setFiliereId(Long filiereId) {
        this.filiereId = filiereId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public EmployeesDTO(Long idE, String firstNameFr, String firstNameAr, String lastNameFr, String lastNameAr, String email, String password, String phone, String ppr, String cin, String addressFr, String addressAr, LocalDate hireDate, String workLocationFr, String workLocationAr, MultipartFile image, Long profileId, Long gradeId, Long filiereId, Long postId) {
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
        this.profileId = profileId;
        this.gradeId = gradeId;
        this.filiereId = filiereId;
        this.postId = postId;
    }


}