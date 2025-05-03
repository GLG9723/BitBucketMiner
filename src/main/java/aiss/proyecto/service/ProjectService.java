package aiss.proyecto.service;

import aiss.proyecto.modelCommits.CommitBB;
import aiss.proyecto.modelCommits.Value;
import aiss.proyecto.modelIssues.IssuesBB;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Commit;
import aiss.proyecto.modelMiner.Issue;
import aiss.proyecto.modelRepo.Issues;
import aiss.proyecto.service.CommitService;
import aiss.proyecto.modelRepo.Repository;
import aiss.proyecto.modelMiner.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService
{
    @Autowired
    RestTemplate restTemplate;

    public Project parseProject(Repository repo){
        Project project = new Project();
        project.setName(repo.getFullName()); // ns si esto o name
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
