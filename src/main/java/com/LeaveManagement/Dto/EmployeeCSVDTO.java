package com.LeaveManagement.Dto;

public class EmployeeCSVDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String ppr;
    private String cin;
    private String address;
    private String hireDate; // Format: "yyyy-MM-dd"
    private String workLocation;
    private String profileName; // Nom du profil au lieu de l'ID
    private String departmentName; // Nom du département
    private String postName; // Nom du poste

    // Constructeurs
    public EmployeeCSVDTO() {}

    public EmployeeCSVDTO(String firstName, String lastName, String email, String password,
                          String phone, String ppr, String cin, String address, String hireDate,
                          String workLocation, String profileName, String departmentName, String postName) {
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
        this.profileName = profileName;
        this.departmentName = departmentName;
        this.postName = postName;
    }

    // Getters et Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPpr() { return ppr; }
    public void setPpr(String ppr) { this.ppr = ppr; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }

    public String getWorkLocation() { return workLocation; }
    public void setWorkLocation(String workLocation) { this.workLocation = workLocation; }

    public String getProfileName() { return profileName; }
    public void setProfileName(String profileName) { this.profileName = profileName; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getPostName() { return postName; }
    public void setPostName(String postName) { this.postName = postName; }
}