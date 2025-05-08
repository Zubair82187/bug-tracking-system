package com.bug_tracker.service;

import com.bug_tracker.exception.NotFoundException;
import com.bug_tracker.exception.NullValuesException;
import com.bug_tracker.model.ProjectModel;
import com.bug_tracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/*
* This is project service class which provide business logic
*/

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }


    //Getting all projects
    public List<ProjectModel> allProjects() {
        List<ProjectModel> projects = projectRepository.findAll();

        if(projects.isEmpty()){
            throw new NotFoundException("projects not found");
        }
        return projects;
    }


    //Adding a project
    public ProjectModel addProject(ProjectModel project) {
        if(!projectDetailCheck(project)){
            throw new NullValuesException("fill all the project details");
        }
        return projectRepository.save(project);
    }

    //Get a project using id
    public ProjectModel getProject(int id) {
        Optional<ProjectModel> project = projectRepository.findById(id);
        if(project.isEmpty()){
            throw new NotFoundException("project not found with "+ id);
        }
        return project.get();
    }


    //Delete a project using id
    public boolean deleteProject(int id) {
        Optional<ProjectModel> project =  projectRepository.findById(id);
        if(project.isEmpty()){
            throw new NotFoundException("there is no such project with "+id);
        }
        projectRepository.deleteById(id);
        return true;
    }

    //Check detail of a project request
    private boolean projectDetailCheck(ProjectModel project){
        return project.getProjectName() != null && !project.getProjectName().isEmpty()
                && project.getProjectDescription()!=null && !project.getProjectDescription().isEmpty()
                && project.getCreatedBy()!=null && !project.getCreatedBy().isEmpty();
    }
}
