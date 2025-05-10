package aiss.proyecto.controller;

import aiss.proyecto.exception.NegativeParameterException;
import aiss.proyecto.exception.PageLimitException;
import aiss.proyecto.modelMiner.Project;
import aiss.proyecto.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{workSpace}/{repo_slug}")
    public Project findOne(@PathVariable String workSpace, @PathVariable String repo_slug,
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
