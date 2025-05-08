package com.bug_tracker.model;


import com.bug_tracker.enums.Priority;
import com.bug_tracker.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BugModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectModel project;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserModel created_by;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private UserModel assigned_to;

}
