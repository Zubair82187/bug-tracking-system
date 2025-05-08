package com.bug_tracker.controller;

import com.bug_tracker.model.ProjectModel;
import com.bug_tracker.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class is responsible for handling project related tasks those are...
 * 1. Get all projects availale in database
 * 2. Get a single project
 * 3. Add a project to track bugs
 * 4. Delete project if needed
 */



@RestController
@RequestMapping("/project")
public class ProjectController {


    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    // This is responsible to get all projects.
    @GetMapping("/")
    public ResponseEntity<List<ProjectModel>> getProjects(){
        List<ProjectModel> projects = projectService.allProjects();
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    //this is responsible to add a project.
    @PostMapping("/")
    public ResponseEntity<ProjectModel> addProject(@RequestBody ProjectModel projectModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addProject(projectModel));
    }


    //This is responsible to get a single project.
    @GetMapping("/{id}")
    public ResponseEntity<ProjectModel> getProject(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProject(id));
    }

    //This is responsible to delete a project.
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body((Boolean) projectService.deleteProject(id));
    }
}
