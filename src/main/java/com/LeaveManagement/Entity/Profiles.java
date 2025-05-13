package com.LeaveManagement.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Profiles")
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdProfile;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String profileName;

    public Profiles() {
    }

    public Profiles(Long idProfile, String profileName) {
        IdProfile = idProfile;
        this.profileName = profileName;
    }

    public Profiles(Long idProfile) {
        IdProfile = idProfile;
    }

    public Long getIdProfile() {
        return IdProfile;
    }

    public void setIdProfile(Long idProfile) {
        IdProfile = idProfile;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}