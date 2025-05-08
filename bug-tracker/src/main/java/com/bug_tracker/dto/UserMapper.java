package com.bug_tracker.dto;

import com.bug_tracker.model.UserModel;

public class UserMapper {
    public UserDTO mapToDTO(UserModel userModel){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userModel.getEmail());
        userDTO.setPassword(userModel.getPassword());
        return userDTO;
    }

}
