package com.appsdeveloperblog.app.ws.io.dao;

import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

public interface DAO {
    public void openConnection();
    public UserDTO getUserByUserName(String username);
    public UserDTO saveUser(UserDTO user);
    public UserDTO getUser(String id);
    public void updateUser(UserDTO userProfile);
    public void closeConnection();

}
