package com.bug_tracker.service;

import com.bug_tracker.dto.BugDTO;
import com.bug_tracker.exception.DeletionFailedException;
import com.bug_tracker.exception.NotCreatedException;
import com.bug_tracker.exception.NotFoundException;
import com.bug_tracker.exception.NotUpdatedException;
import com.bug_tracker.model.BugModel;
import com.bug_tracker.model.ProjectModel;
import com.bug_tracker.model.UserModel;
import com.bug_tracker.repository.BugRepository;
import com.bug_tracker.repository.ProjectRepository;
import com.bug_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class BugService {
    private final BugRepository bugRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public BugService(BugRepository bugRepository, ProjectRepository projectRepository, UserRepository userRepository){
        this.bugRepository = bugRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    //create a bug
    public BugModel createBug(BugDTO bugDTO){

        try {
            BugModel bug = new BugModel();
            bug.setTitle(bugDTO.getTitle());
            bug.setDescription(bugDTO.getDescription());
            bug.setPriority(bugDTO.getPriority());
            bug.setStatus(bugDTO.getStatus());

            ProjectModel project = projectRepository.findById(bugDTO.getProject_id())
                    .orElseThrow(() -> new NotFoundException("Project not found"));

            UserModel created_by = userRepository.findById(bugDTO.getCreated_by_id())
                    .orElseThrow(() -> new NotFoundException("Created by user not found"));

            UserModel assigned_to = userRepository.findById(bugDTO.getAssigned_to_id())
                    .orElseThrow(()-> new NotFoundException("Assigned to user not found"));

            bug.setProject(project);
            bug.setCreated_by(created_by);
            bug.setAssigned_to(assigned_to);
            return bugRepository.save(bug);
        } catch (Exception e) {
            throw new NotCreatedException("failed to create bug" + e.getMessage());
        }
    }

    //find a bug using id
    public BugModel findBug(int id){
        Optional<BugModel> bug = bugRepository.findById(id);
        if(bug.isEmpty()){
            throw new NotFoundException("No bug found with "+ id);
        }
        return bug.get();
    }

    //Find all bugs
    public List<BugModel> getBugs() {
        List<BugModel> bugs = bugRepository.findAll();
        if(bugs.isEmpty()){
            throw new NotFoundException("No bug found");
        }
        return bugs;
    }

    //delete a bug using id
    public void deleteBug(int id) {
        Optional<BugModel> bug = bugRepository.findById(id);
        if(bug.isEmpty()){
            throw new NotFoundException("bug not found with "+id);
        }
        try{
            bugRepository.deleteById(id);
        }
        catch (Exception e){
            throw new DeletionFailedException("bug clould not deleted" + e.getMessage());
        }
    }

    public BugModel updateBugStatus(int id, Map<String, Object> updates){
        BugModel bug = bugRepository.findById(id).orElseThrow(()-> new NotFoundException("No bug found with id "+id));

        try{
            updates.forEach((key, value)->{
                Field field = ReflectionUtils.findField(BugModel.class, key);
                if(field!=null){
                    field.setAccessible(true);

                    //handle enum
                    if (field.getType().isEnum() && value instanceof String) {
                        // Convert the string to the appropriate enum
                        Object enumValue = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                        ReflectionUtils.setField(field, bug, enumValue);
                    } else {
                        // If it's not an enum, directly set the field
                        ReflectionUtils.setField(field, bug, value);
                    }
                }
            });

            return bugRepository.save(bug);
        } catch (Exception e) {
            throw new NotUpdatedException("could not update "+ e.getMessage());
        }
    }

    public BugModel assignBug(int id,int assign_to_id, Map<String, Object> updates){
        BugModel bug = bugRepository.findById(id).orElseThrow(()-> new NotFoundException("No bug found with id "+id));
        UserModel user = userRepository.findById(assign_to_id).orElseThrow(()-> new NotFoundException("assigned user not found"));
        bug.setAssigned_to(user);

        try{
            updates.forEach((key, value)->{
                Field field = ReflectionUtils.findField(BugModel.class, key);
                if(field!=null){
                    field.setAccessible(true);

                    //handle enum
                    if (field.getType().isEnum() && value instanceof String) {
                        // Convert the string to the appropriate enum
                        Object enumValue = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                        ReflectionUtils.setField(field, bug, enumValue);
                    } else {
                        // If it's not an enum, directly set the field
                        ReflectionUtils.setField(field, bug, value);
                    }
                }
            });

            return bugRepository.save(bug);
        } catch (Exception e) {
            throw new NotUpdatedException("could not update "+ e.getMessage());
        }
    }

}
