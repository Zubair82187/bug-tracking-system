package com.bug_tracker.service;

import com.bug_tracker.exception.DeletionFailedException;
import com.bug_tracker.exception.NullValuesException;
import com.bug_tracker.exception.NotFoundException;
import com.bug_tracker.model.UserModel;
import com.bug_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    //create a user and return user
    public UserModel createUser(UserModel user){
        if(!checkDetail(user)){
            throw new NullValuesException("Enter all the details");
        }
        return userRepository.save(user);
    }


    //find a user using id and return.
    public UserModel user(int id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        throw new NotFoundException("user not found");
    }

    //return all the users
    public List<UserModel> users() {
        List<UserModel> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new NotFoundException("There is no user in the database");
        }
        return users;
    }

    public String deleteUser(int id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("user not found with id "+ id);
        }
        if(userRepository.deleteUserById(id)==1){
            return "deleted successfully";
        }
        else{
            throw new DeletionFailedException("something went wrong while deletion");
        }
    }

    private boolean checkDetail(UserModel user){
        return user.getName() != null && !user.getName().isEmpty() &&
                user.getEmail() != null && !user.getEmail().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty() &&
                user.getRole() != null;
    }
}
