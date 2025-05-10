package aiss.proyecto.service;

import aiss.proyecto.exception.PageLimitException;
import aiss.proyecto.modelCommits.CommitBB;
import aiss.proyecto.modelCommits.Value;
import aiss.proyecto.modelIssues.IssuesBB;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Commit;
import aiss.proyecto.modelMiner.Issue;
import aiss.proyecto.modelRepo.Repository;
import aiss.proyecto.modelMiner.Project;
import org.hibernate.query.Page;
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

    public Project getProject(String workSpace, String repo_slug, int maxPages, int nCommits, int nIssues) throws PageLimitException {
        String url = "https://api.bitbucket.org/2.0/repositories/" + workSpace + '/' + repo_slug;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<Repository> request = new HttpEntity<>(null, headers);
        ResponseEntity<Repository> response = restTemplate.exchange(url, HttpMethod.GET, request, Repository.class);

        Repository repository = response.getBody();
        return parseProject(repository, maxPages, nCommits, nIssues);
    }

    public Project parseProject(Repository repo, int maxPages, int nCommits, int nIssues) throws PageLimitException {
        Project project = new Project();
        project.setId(repo.getUuid());
        project.setName(repo.getName());
        project.setWebUrl(repo.getLinks().getHtml().getHref());

        CommitBB commitBB = restTemplate.getForObject(repo.getLinks().getCommits().getHref() + "?page=1&pagelen=" + nCommits, CommitBB.class);
        List<Commit> commits = new ArrayList<>();
        for (Value value : commitBB.getValues()){
            CommitService commitService = new CommitService();
            commits.add(commitService.parseaCommit(value));
        }
        Integer remaining = maxPages-1;
        CommitBB previo = commitBB;
        for (int i = 1; i <= remaining; i++){
            if ( previo.getNext()== null && i!=remaining ) {
                throw new PageLimitException(i*nCommits, (i+1)*nCommits + 1, "Commit");
            }
            CommitBB commitBBAux = restTemplate.getForObject(previo.getNext(), CommitBB.class);
            previo = commitBBAux;
            for (Value value : commitBBAux.getValues()){
                CommitService commitService = new CommitService();
                commits.add(commitService.parseaCommit(value));
            }
        }
        project.setCommits(commits);


        // Issues no salta aunque pagelen exceda el limite
        IssuesBB issuesBB = restTemplate.getForObject(repo.getLinks().getIssues().getHref()+"?page=1&pagelen=" + nIssues, IssuesBB.class);
        if (maxPages*nIssues>issuesBB.getSize()){
            throw new PageLimitException(issuesBB.getSize(), issuesBB.getSize(), "Issue");
        }
        List<Issue> issues = new ArrayList<>();

        for (ValueIssue valueIssue : issuesBB.getValues()){
            IssueService issueService = new IssueService();
            issues.add(issueService.parseaIssue(valueIssue));
        }

        IssuesBB previous = issuesBB;
        for (int i=2; i<=maxPages; i++){
            IssuesBB issuesBBAux = restTemplate.getForObject(previous.getNext(), IssuesBB.class);
            for (ValueIssue valueIssue : issuesBBAux.getValues()){
                IssueService issueService = new IssueService();
                issues.add(issueService.parseaIssue(valueIssue));
            }
            previous = issuesBBAux;
        }

        project.setIssues(issues);

        return project;
    }
}
