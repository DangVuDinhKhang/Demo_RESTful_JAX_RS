package com.appsdeveloperblog.app.ws.ui.entrypoints;

import com.appsdeveloperblog.app.ws.service.AuthenticationService;
import com.appsdeveloperblog.app.ws.service.impl.AuthenticationServiceImpl;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.request.LoginCredentials;
import com.appsdeveloperblog.app.ws.ui.model.response.AuthenticationDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
public class AuthenticationEntryPoint {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthenticationDetails userLogin(LoginCredentials loginCredentials){
        AuthenticationDetails returnValue = new AuthenticationDetails();
        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserDTO authenticatedUser = authenticationService.authenticate(loginCredentials.getUsername(),loginCredentials.getPassword());
        // Reset Access Token
        authenticationService.resetSecurityCridentials(loginCredentials.getPassword(), authenticatedUser);
        String accessToken = authenticationService.issueAccessToken(authenticatedUser);
        returnValue.setId(authenticatedUser.getUserId());
        returnValue.setToken(accessToken);
        return returnValue;
    }
}
