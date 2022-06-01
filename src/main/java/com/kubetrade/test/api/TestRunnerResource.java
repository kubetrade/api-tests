package com.kubetrade.test.api;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        return Response.ok(string).build();
    }

}
