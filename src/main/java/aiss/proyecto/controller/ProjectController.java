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

@Tag(name= "Project", description = "Project management API")
@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    RestTemplate restTemplate = new RestTemplate();

    @Operation(
            summary = "Retrieve a Project from Bitbucket",
            description = "Get a Repository from BitBucket and transforms it into a Project object",
            tags = {"Bitbucket Project","Get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project correctly recieved", content = {@Content(schema =
            @Schema(implementation = Project.class), mediaType = "aplication/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(schema =
            @Schema())}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content(schema =
            @Schema())})
    })
    @GetMapping("/{workSpace}/{repo_slug}")
    public Project findOne(@Parameter(description = "workspace to be searched") @PathVariable String workSpace,
                           @Parameter(description = "repo_slug to be searched") @PathVariable String repo_slug,
                           @RequestParam(defaultValue = "2")int maxPages,
                           @RequestParam(defaultValue = "5")int nCommits,
                           @RequestParam(defaultValue = "5")int nIssues) throws PageLimitException, NegativeParameterException {
        ProjectService service = new ProjectService();
        Project project = service.getProject(workSpace, repo_slug, maxPages, nCommits, nIssues); // no se si es optional o que pq puede devolver null
        return project;
    }


    @PostMapping("/{workSpace}/{repo_slug}")
    @ResponseStatus(HttpStatus.CREATED)
    public Project save(@PathVariable String workSpace, @PathVariable String repo_slug,
                        @RequestParam(defaultValue = "2")int maxPages,
                        @RequestParam(defaultValue = "5")int nCommits,
                        @RequestParam(defaultValue = "5")int nIssues) throws PageLimitException, NegativeParameterException {
        ProjectService service = new ProjectService();
        Project project2 = service.getProject(workSpace, repo_slug, maxPages, nCommits, nIssues);
        String url = "http://localhost:8080/gitminer/projects";
        Project created = restTemplate.postForObject(url, project2, Project.class);
        return created;
    }

}
