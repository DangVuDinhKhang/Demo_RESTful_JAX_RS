package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.exceptions.CouldNotCreateRecordException;
import com.appsdeveloperblog.app.ws.exceptions.NoRecordFoundException;
import com.appsdeveloperblog.app.ws.io.dao.DAO;
import com.appsdeveloperblog.app.ws.io.dao.impl.MySQLDAO;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.utils.UserProfileUtils;

public class UserServiceImpl implements UserService {
    DAO database;
    public UserServiceImpl(){
        this.database = new MySQLDAO();
    }
    UserProfileUtils userProfileUtils = new UserProfileUtils();
    public UserDTO createUser(UserDTO user) {
        UserDTO returnValue = null;
        //Validate the required field
        userProfileUtils.validateRequiredFields(user);

        //Check if user already exist
        UserDTO existingUser = this.getUserByUserName(user.getEmail());
        if(existingUser != null)
            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXIST.name());

        //Generate secure public user id
        String userId = userProfileUtils.generateUserId(30);
        user.setUserId(userId);

        //Generate salt
        String salt = userProfileUtils.getSalt(30);
        String encryptedPassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setEncryptedPassword(encryptedPassword);

        //record data into database
        returnValue = this.saveUser(user);

        return returnValue;
    }

    @Override
    public UserDTO getUserByUserName(String username){
        UserDTO userDto = null;

        if (username == null || username.isEmpty()) {
            return userDto;
        }

        // Connect to database
        try {
            this.database.openConnection();
            userDto = this.database.getUserByUserName(username);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }

    private UserDTO saveUser(UserDTO user){
        UserDTO returnValue = null;
        try {
            this.database.openConnection();
            returnValue = this.database.saveUser(user);
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    }

    @Override
    public UserDTO getUser(String id) {
        UserDTO returnValue = null;
        try {
            this.database.openConnection();
            returnValue = this.database.getUser(id);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        finally {
            this.database.closeConnection();
        }
        return returnValue;
    }


}
