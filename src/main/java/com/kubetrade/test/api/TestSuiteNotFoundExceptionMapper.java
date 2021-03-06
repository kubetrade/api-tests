package com.kubetrade.test.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TestSuiteNotFoundExceptionMapper implements ExceptionMapper<TestSuiteNotFoundException> {

    @Override
    public Response toResponse(TestSuiteNotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }

}
