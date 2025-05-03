package aiss.proyecto.controller;

import jakarta.validation.Valid;
import aiss.proyecto.modelRepo.ZProject;
import aiss.proyecto.repository.ZProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ZProjectController {

    @Autowired
    ZProjectRepository projectRepository;

    @GetMapping
    public List<ZProject> findAll() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ZProject findOne(@PathVariable long id) {
        Optional<ZProject> proj = projectRepository.findById(id);
        return proj.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZProject createProject(@RequestBody @Valid ZProject project) {
        ZProject proj = projectRepository.save(new ZProject(project.getName(), project.getWeb_url()));
        return proj;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@RequestBody @Valid ZProject updatedProject, @PathVariable long id) {
        Optional<ZProject> projData = projectRepository.findById(id);

        if (projData.isPresent()) {
            ZProject proj = projData.get();
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
