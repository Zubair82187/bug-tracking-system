package com.bug_tracker.dto;

import com.bug_tracker.model.BugModel;
import org.springframework.stereotype.Component;

@Component
public class BugMapper {

    public BugDTO mapToDTO(BugModel bugModel) {
        BugDTO dto = new BugDTO();
        dto.setTitle(bugModel.getTitle());
        dto.setDescription(bugModel.getDescription());
        dto.setPriority(bugModel.getPriority());
        dto.setStatus(bugModel.getStatus());
        dto.setProject_id(bugModel.getProject().getId());
        dto.setCreated_by_id(bugModel.getId());
        dto.setAssigned_to_id(bugModel.getId());
        return dto;
    }


}
