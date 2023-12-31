package com.appsdeveloperblog.app.ws.exceptions;

import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider           //These providers control the mapping of Java exceptions to a JAX-RS Response instance.
public class NoRecordFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException> {

    @Override
    public Response toResponse(NoRecordFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.NO_RECORD_FOUND.name(), "http://appsdeveloperblog.com");
        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
