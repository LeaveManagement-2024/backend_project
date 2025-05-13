package com.LeaveManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="userlogin")
public class User {
    @Id
    @Column(name="user_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name="user_name",length = 255)
    private String username;
    @Column(name="firstname",length = 255)
    private String firstname;
    @Column(name="lastname",length = 255)
    private String lastname;
    @Column(name="email",length = 255)
    private String email;
    @Column(name="password",length = 255)
    private String password;
    @Column(name="skills",length = 255)
    private String skills;
    @Column(name="education",length = 255)
    private String education;
    @Column(name="gitHub",length = 255)
    private String github;
    @Column(name="Linkedin",length = 255)
    private String linkedin;
    @Column(name="website",length = 255)
    private String website;
    @Column(name="tele",length = 255)
    private String tele;
    @Column(name="job",length = 255)
    private String job;
    @Column(name = "description", columnDefinition = "TEXT") // Définition d'une variable de type texte
    private String description;
    @Column(name = "image_path", length = 255) // Ajout du champ pour le chemin de l'image
    private String imagePath;

    @JsonIgnore // Add this annotation to prevent serialization of projects
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Projects> projects;


    public User(int id, String username, String firstname, String lastname, String email, String password, String skills, String education, String github, String linkedin, String website,
                String tele, String job, String description, String imagePath, List<Projects> projects) {
        Id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.skills = skills;
        this.education = education;
        this.github = github;
        this.linkedin = linkedin;
        this.website = website;
        this.tele = tele;
        this.job = job;
        this.description = description;
        this.projects = projects;
    }

    public User() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", skills='" + skills + '\'' +
                ", education='" + education + '\'' +
                ", github='" + github + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", website='" + website + '\'' +
                ", tele='" + tele + '\'' +
                ", job='" + job + '\'' +
                ", description='" + description + '\'' +

                ", projects=" + projects +
                '}';
    }
}
