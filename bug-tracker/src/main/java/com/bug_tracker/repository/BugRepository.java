package com.bug_tracker.repository;


import com.bug_tracker.model.BugModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<BugModel, Integer> {
}
