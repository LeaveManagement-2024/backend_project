package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.ProjectDTO;
import com.LeaveManagement.Entity.Projects;

import java.util.List;

public interface ProjectService {



    String addProject(ProjectDTO projectDTO);

    List<Projects> getAllProjects();

    Projects getProjectById(int id);

    void updateProject(int id, ProjectDTO projectDTO);

    void deleteProject(int id);
    public List<Projects> getProjectsByUserId(int userId);

}
