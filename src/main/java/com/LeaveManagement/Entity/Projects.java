package com.LeaveManagement.Entity;


import jakarta.persistence.*;


@Entity
@Table(name="Projects")
public class Projects {
    @Id
    @Column(name="Project_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name="Project_name",length = 255)
    private String ProjectName;
    @Column(name="description",length = 255)
    private String Description;
    @Column(name="Used_technologie",length = 255)
    private String Techno;
    @Column(name="Objective",length = 255)
    private String Objective;
    @Column(name="GitHub",length = 255)
    private String Github;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Projects(int id, String projectName, String description, String techno, String objective, String github, User user) {
        Id = id;
        ProjectName = projectName;
        Description = description;
        Techno = techno;
        Objective = objective;
        Github = github;
        this.user = user;
    }

    public Projects() {
    }

    public Projects(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTechno() {
        return Techno;
    }

    public void setTechno(String techno) {
        Techno = techno;
    }

    public String getObjective() {
        return Objective;
    }

    public void setObjective(String objective) {
        Objective = objective;
    }

    public String getGithub() {
        return Github;
    }

    public void setGithub(String github) {
        Github = github;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "Id=" + Id +
                ", ProjectName='" + ProjectName + '\'' +
                ", Description='" + Description + '\'' +
                ", Techno='" + Techno + '\'' +
                ", Objective='" + Objective + '\'' +
                ", Github='" + Github + '\'' +
                ", user=" + user +
                '}';
    }
}
