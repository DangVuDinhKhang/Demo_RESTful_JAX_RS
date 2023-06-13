package com.appsdeveloperblog.app.ws.io.dao;

import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

import java.util.List;

public interface DAO {
    void openConnection();
    UserDTO getUserByUserName(String username);
    UserDTO saveUser(UserDTO user);
    UserDTO getUser(String id);
    List<UserDTO> getUsers(int start, int limit);
    void updateUser(UserDTO userProfile);
    void closeConnection();

}
