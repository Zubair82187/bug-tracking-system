package com.bug_tracker.repository;

import com.bug_tracker.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @Modifying
    @Query("DELETE FROM UserModel u WHERE u.id = :id")
    int deleteUserById(@Param("id") int id);

    Optional<UserModel> findByEmail(String email);
}
