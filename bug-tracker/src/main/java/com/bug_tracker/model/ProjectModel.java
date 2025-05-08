package com.bug_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String projectDescription;

    @Column(nullable = false)
    private String createdBy;
}
