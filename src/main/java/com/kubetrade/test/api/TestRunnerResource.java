package com.kubetrade.test.api;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Arrays;

@Path("/test-runner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "test-runner", description = "Test Runner Operations")
public class TestRunnerResource {

    private final TestRunnerService testRunnerService;

    @Inject
    public TestRunnerResource(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @GET
    @Path("/test-suites")
    @APIResponse(
            responseCode = "200",
            description = "Get test suite names",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = String.class)
            )
    )
    public Response getTestSuites() {
        return Response.ok(Arrays.stream(TestSuite.values()).map(TestSuite::getName)).build();
    }

    @POST
    @APIResponse(
            responseCode = "200",
            description = "Test Run executed",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = TestRunResponse.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Test Run Request",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid TestRunRequest testRunRequest) {
        String string = testRunnerService.executeTestRun(testRunRequest.suiteName());
        return Response.ok(new TestRunResponse(string)).build();
    }

}
