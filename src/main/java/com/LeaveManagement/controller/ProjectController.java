package com.LeaveManagement.controller;

import com.LeaveManagement.Dto.ProjectDTO;
import com.LeaveManagement.Entity.Projects;
import com.LeaveManagement.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/Project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(path = "/CreateProject")
    public String saveProject(@RequestBody ProjectDTO projectDTO) {

        String id = projectService.addProject(projectDTO);
        return id;
    }
    @GetMapping(path="/ProjectsByUserId/{userId}")
    public List<Projects> getProjectsByUserId(@PathVariable int userId) {
        return projectService.getProjectsByUserId(userId);
    }
    @GetMapping(path="/ProjectbyId/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable int id) {
        Projects project = projectService.getProjectById(id);
        if (project != null) {

            int userId = project.getUser().getId();

            ProjectDTO projectDTO = new ProjectDTO(project.getId(), userId, project.getProjectName(), project.getDescription(), project.getTechno(), project.getObjective(), project.getGithub());
            return ResponseEntity.ok(projectDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping(path="/getAllProjects")
    public List<Projects> getAllProjects() {
        return projectService.getAllProjects();
    }



    @PutMapping(path = "/updateProject/{id}")
    public ResponseEntity<String> updateProject(@PathVariable int id, @RequestBody ProjectDTO projectDTO) {
        projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok("Project updated successfully");
    }

    @DeleteMapping(path = "/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
