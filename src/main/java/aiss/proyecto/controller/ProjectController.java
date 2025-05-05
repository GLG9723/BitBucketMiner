package aiss.proyecto.controller;

import aiss.proyecto.modelMiner.Project;
import aiss.proyecto.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    @GetMapping("/{workSpace}/{repo_slug}")
    public Project findOne(@PathVariable String workSpace, @PathVariable String repo_slug) {
        ProjectService service = new ProjectService();
        Project project = service.getProject(workSpace, repo_slug); // no se si es optional o que pq puede devolver null
        return project;
    }

}
