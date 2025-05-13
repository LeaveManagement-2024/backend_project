package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.LogInDTO;
import com.LeaveManagement.Dto.UserDTO;
import com.LeaveManagement.Entity.User;
import com.LeaveManagement.response.LogInResponse;

import java.util.List;

public interface UserService {

    LogInResponse loginUser(LogInDTO logInDTO);

    String addUser(UserDTO userDTO);

    public List<User> getAllUsers();

    public User GetUserById(int id);
    void updateUser(int id, UserDTO userDTO);
    void deleteUser(int id);

}
