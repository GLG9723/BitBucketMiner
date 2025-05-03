package aiss.proyecto.controller;

import jakarta.validation.Valid;
import aiss.proyecto.model.Commit;
import aiss.proyecto.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commits")
public class CommitController {

    @Autowired
    CommitRepository commitRepository;

    @GetMapping
    public List<Commit> findAll() {
        return commitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Commit findOne(@PathVariable long id) {
        Optional<Commit> commit = commitRepository.findById(id);
        return commit.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Commit createCommit(@RequestBody @Valid Commit commit) {
        Commit com = commitRepository.save(new Commit(commit.getTitle(), commit.getMessage(), commit.getAuthor_email(), commit.getAuthored_date()));
        return com;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCommit(@RequestBody @Valid Commit updatedCommit, @PathVariable long id) {
        Optional<Commit> commitData = commitRepository.findById(id);

        Commit com = commitData.get();
        com.setTitle(updatedCommit.getTitle());
        com.setMessage(updatedCommit.getMessage());
        com.setAuthor_email(updatedCommit.getAuthor_email());
        com.setAuthored_date(updatedCommit.getAuthored_date());
        commitRepository.save(com);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommit(@PathVariable long id) {
        if (commitRepository.existsById(id)) {
            commitRepository.deleteById(id);
        }
    }

}
