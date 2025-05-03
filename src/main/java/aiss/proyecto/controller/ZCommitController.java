package aiss.proyecto.controller;

import jakarta.validation.Valid;
import aiss.proyecto.modelRepo.ZCommit;
import aiss.proyecto.repository.ZCommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commits")
public class ZCommitController {

    @Autowired
    ZCommitRepository commitRepository;

    @GetMapping
    public List<ZCommit> findAll() {
        return commitRepository.findAll();
    }

    @GetMapping("/{id}")
    public ZCommit findOne(@PathVariable long id) {
        Optional<ZCommit> commit = commitRepository.findById(id);
        return commit.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZCommit createCommit(@RequestBody @Valid ZCommit commit) {
        ZCommit com = commitRepository.save(new ZCommit(commit.getTitle(), commit.getMessage(), commit.getAuthor_email(), commit.getAuthored_date()));
        return com;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCommit(@RequestBody @Valid ZCommit updatedCommit, @PathVariable long id) {
        Optional<ZCommit> commitData = commitRepository.findById(id);

        ZCommit com = commitData.get();
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
