package aiss.proyecto.controller;

import jakarta.validation.Valid;
import aiss.proyecto.model.Project;
import aiss.proyecto.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project findOne(@PathVariable long id) {
        Optional<Project> proj = projectRepository.findById(id);
        System.out.println("commit de prueba");
        return proj.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody @Valid Project project) {
        Project proj = projectRepository.save(new Project(project.getName(), project.getWeb_url()));
        return proj;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@RequestBody @Valid Project updatedProject, @PathVariable long id) {
        Optional<Project> projData = projectRepository.findById(id);

        if (projData.isPresent()) {
            Project proj = projData.get();
            proj.setName(updatedProject.getName());
            proj.setWeb_url(updatedProject.getWeb_url());
            projectRepository.save(proj);
        } else {
            // seguir
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        }
    }
}
