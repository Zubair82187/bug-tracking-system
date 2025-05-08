package com.bug_tracker.controller;

import com.bug_tracker.dto.BugDTO;
import com.bug_tracker.dto.BugMapper;
import com.bug_tracker.model.BugModel;
import com.bug_tracker.service.BugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/bugs")
@RestController
public class BugController {
    private final BugService bugService;
    private final BugMapper bugMapper;
    public BugController(BugService bugService, BugMapper bugMapper){
        this.bugService = bugService;
        this.bugMapper = bugMapper;
    }

    //create a bug
    @PostMapping("/")
    public ResponseEntity<BugDTO> createBug(@RequestBody  BugDTO bugDTO){
        BugModel bug = bugService.createBug(bugDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bugMapper.mapToDTO(bug));
    }

    //get a bug by bug id
    @GetMapping("/{id}")
    public BugModel getBug(@PathVariable int id){
        return bugService.findBug(id);
    }

    //get all the bugs
    @GetMapping("/")
    public ResponseEntity<List<BugModel>> getBugs(){
        return ResponseEntity.status(HttpStatus.FOUND).body(bugService.getBugs());
    }


    //delete a bug by using bug id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBug(@PathVariable int id){
        bugService.deleteBug(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
    }

    //update bug detail partially
    @PatchMapping("/update_bug_status/{id}")
    public ResponseEntity<BugDTO> updateBug(@PathVariable int id, @RequestBody Map<String, Object> updates){

        return ResponseEntity.status(HttpStatus.OK).body(bugMapper.mapToDTO(bugService.updateBugStatus(id, updates)));
    }

    //assign bug
    @PatchMapping("/{id}/assign_bug_to_id/{assign_to_id}")
    public ResponseEntity<BugDTO> assignBug(@PathVariable int id, @PathVariable  int assign_to_id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.status(HttpStatus.OK).body(bugMapper.mapToDTO(bugService.assignBug(id, assign_to_id ,updates)));
    }

}
