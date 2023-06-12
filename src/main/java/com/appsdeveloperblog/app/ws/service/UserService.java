package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

public interface UserService {
    public UserDTO createUser(UserDTO user);
    public UserDTO getUser(String id);
    public UserDTO getUserByUserName(String username);
}
