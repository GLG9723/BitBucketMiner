package aiss.proyecto.service;

import aiss.proyecto.modelCommits.CommitBB;
import aiss.proyecto.modelCommits.Value;
import aiss.proyecto.modelIssues.IssuesBB;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Commit;
import aiss.proyecto.modelMiner.Issue;
import aiss.proyecto.modelRepo.Repository;
import aiss.proyecto.modelMiner.Project;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    RestTemplate restTemplate = new RestTemplate();

    public Project getProject(String workSpace, String repo_slug) {
        String url = "https://api.bitbucket.org/2.0/repositories/" + workSpace + '/' + repo_slug;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<Repository> request = new HttpEntity<>(null, headers);
        ResponseEntity<Repository> response = restTemplate.exchange(url, HttpMethod.GET, request, Repository.class);

        Repository repository = response.getBody();
        return parseProject(repository);
    }

    public Project parseProject(Repository repo){
        Project project = new Project();
        project.setId(repo.getUuid());
        project.setName(repo.getName());
        project.setWebUrl(repo.getLinks().getHtml().getHref());

        CommitBB commitBB = restTemplate.getForObject(repo.getLinks().getCommits().getHref(), CommitBB.class);
        List<Commit> commits = new ArrayList<>();
        for (Value value : commitBB.getValues()){
            CommitService commitService = new CommitService();
            commits.add(commitService.parseaCommit(value));
        }
        project.setCommits(commits);

        IssuesBB issuesBB = restTemplate.getForObject(repo.getLinks().getIssues().getHref(), IssuesBB.class);
        List<Issue> issues = new ArrayList<>();
        for (ValueIssue valueIssue : issuesBB.getValues()){
            IssueService issueService = new IssueService();
            issues.add(issueService.parseaIssue(valueIssue));
        }
        project.setIssues(issues);
        return project;
    }
}
