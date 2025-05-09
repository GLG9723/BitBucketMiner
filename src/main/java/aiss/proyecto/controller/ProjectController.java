package aiss.proyecto.controller;

import aiss.proyecto.modelMiner.Project;
import aiss.proyecto.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{workSpace}/{repo_slug}")
    public Project findOne(@PathVariable String workSpace, @PathVariable String repo_slug) {
        ProjectService service = new ProjectService();
        Project project = service.getProject(workSpace, repo_slug); // no se si es optional o que pq puede devolver null
        return project;
    }

    @PostMapping("/{workSpace}/{repo_slug}")
    public Project save(@PathVariable String workSpace, @PathVariable String repo_slug) {
        ProjectService service = new ProjectService();
        Project project2 = service.getProject(workSpace, repo_slug);
        String url = "http://localhost:8080/gitminer/projects";
        Project created = restTemplate.postForObject(url, project2, Project.class);
        return created;
    }

}
