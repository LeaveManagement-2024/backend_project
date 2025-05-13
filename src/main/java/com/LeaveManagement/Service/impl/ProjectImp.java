package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.ProjectDTO;
import com.LeaveManagement.Entity.Projects;
import com.LeaveManagement.Entity.User;
import com.LeaveManagement.Repo.ProjectRepo;
import com.LeaveManagement.Repo.UserRepo;
import com.LeaveManagement.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectImp implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String addProject(ProjectDTO projectDTO) {

        User user = userRepo.findById(projectDTO.getUserId()).orElse(null);
        if (user != null) {

            Projects project = new Projects();
            project.setProjectName(projectDTO.getProjectName());
            project.setDescription(projectDTO.getDescription());
            project.setTechno(projectDTO.getTechno());
            project.setObjective(projectDTO.getObjective());
            project.setGithub(projectDTO.getGithub());
            project.setUser(user);
            projectRepo.save(project);
            return project.getProjectName();
        } else {

            return "User not found";
        }
    }

    @Override
    public void updateProject(int id, ProjectDTO projectDTO) {
        Projects projectToUpdate = projectRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        projectToUpdate.setProjectName(projectDTO.getProjectName());
        projectToUpdate.setDescription(projectDTO.getDescription());
        projectToUpdate.setTechno(projectDTO.getTechno());
        projectToUpdate.setObjective(projectDTO.getObjective());
        projectToUpdate.setGithub(projectDTO.getGithub());
        projectRepo.save(projectToUpdate);
    }

    @Override
    public List<Projects> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Projects getProjectById(int id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProject(int id) {
        projectRepo.deleteById(id);
    }

    @Override
    public List<Projects> getProjectsByUserId(int userId) {

        User user = userRepo.findById(userId).orElse(null);

        if (user != null) {

            return user.getProjects();
        } else {

            return Collections.emptyList();
        }
    }
}
