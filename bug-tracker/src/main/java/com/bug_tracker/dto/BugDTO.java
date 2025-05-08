package com.bug_tracker.dto;

import com.bug_tracker.enums.Priority;
import com.bug_tracker.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugDTO {

    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private int project_id;
    private int created_by_id;
    private int assigned_to_id;
}
