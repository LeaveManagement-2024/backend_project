package com.LeaveManagement.Dto;

public class ProjectDTO {
    private int id;
    private int userId;
    private String projectName;
    private String description;
    private String techno;
    private String objective;
    private String github;

    public ProjectDTO(int id, int userId, String projectName, String description, String techno, String objective, String github) {
        this.id = id;
        this.userId = userId;
        this.projectName = projectName;
        this.description = description;
        this.techno = techno;
        this.objective = objective;
        this.github = github;
    }

    public ProjectDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechno() {
        return techno;
    }

    public void setTechno(String techno) {
        this.techno = techno;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", techno='" + techno + '\'' +
                ", objective='" + objective + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}
