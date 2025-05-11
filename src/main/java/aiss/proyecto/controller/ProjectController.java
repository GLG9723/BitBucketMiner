package aiss.proyecto.controller;

import aiss.proyecto.exception.NegativeParameterException;
import aiss.proyecto.exception.PageLimitException;
import aiss.proyecto.modelMiner.Project;
import aiss.proyecto.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Tag(name= "Bitbucket miner", description = "Bitbucket miner management API")
@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    RestTemplate restTemplate = new RestTemplate();

    @Operation(
            summary = "Retrieve a Project from Bitbucket",
            description = "Get a Repository from BitBucket and transforms it into a Project object",
            tags = {"Bitbucket miner","Get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project correctly recieved", content = {@Content(schema =
            @Schema(implementation = Project.class), mediaType = "aplication/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(schema =
            @Schema())}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content(schema =
            @Schema())})
    })
    @GetMapping("/{workSpace}/{repo_slug}")
    public Project findOne(@Parameter(description = "workspace to be retrieved") @PathVariable String workSpace,
                           @Parameter(description = "repo_slug to be retrieved") @PathVariable String repo_slug,
                           @Parameter(description = "maxPages to be retrieved") @RequestParam(defaultValue = "2")int maxPages,
                           @Parameter(description = "nCommits to be retrieved per page") @RequestParam(defaultValue = "5")int nCommits,
                           @Parameter(description = "nIssues to be retrieved per page") @RequestParam(defaultValue = "5")int nIssues) throws PageLimitException, NegativeParameterException {
        ProjectService service = new ProjectService();
        Project project = service.getProject(workSpace, repo_slug, maxPages, nCommits, nIssues); // no se si es optional o que pq puede devolver null
        return project;
    }


    @Operation(
            summary = "Retrieve a Project from Bitbucket and post it in GitMiner",
            description = "Get a Repository from BitBucket, transforms it into a Project object and then post it in GitMiner aplication",
            tags = {"Bitbucket miner","Post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Project correctly created", content = {@Content(schema =
            @Schema(implementation = Project.class), mediaType = "aplication/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(schema =
            @Schema())}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content(schema =
            @Schema())})
    })
    @PostMapping("/{workSpace}/{repo_slug}")
    @ResponseStatus(HttpStatus.CREATED)
    public Project save(@Parameter(description = "workspace to be retrieved") @PathVariable String workSpace,
                        @Parameter(description = "repo_slug to be retrieved") @PathVariable String repo_slug,
                        @Parameter(description = "maxPages to be retrieved") @RequestParam(defaultValue = "2")int maxPages,
                        @Parameter(description = "nCommits to be retrieved per page") @RequestParam(defaultValue = "5")int nCommits,
                        @Parameter(description = "nIssues to be retrieved per page") @RequestParam(defaultValue = "5")int nIssues) throws PageLimitException, NegativeParameterException {
        ProjectService service = new ProjectService();
        Project project2 = service.getProject(workSpace, repo_slug, maxPages, nCommits, nIssues);
        String url = "http://localhost:8080/gitminer/projects";
        Project created = restTemplate.postForObject(url, project2, Project.class);
        return created;
    }

}
