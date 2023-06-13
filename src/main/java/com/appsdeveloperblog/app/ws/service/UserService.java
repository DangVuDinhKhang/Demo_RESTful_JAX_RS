package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO getUser(String id);
    UserDTO getUserByUserName(String username);
    List<UserDTO> getUsers(int start, int limit);
}
