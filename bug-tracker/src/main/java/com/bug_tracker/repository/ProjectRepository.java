package com.bug_tracker.repository;

import com.bug_tracker.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Integer> {
}
