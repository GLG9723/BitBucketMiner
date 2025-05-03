package aiss.proyecto.service;

import aiss.proyecto.modelComments.CommentsBB;
import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelCommits.CommitBB;
import aiss.proyecto.modelCommits.Value;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelMiner.Commit;
import aiss.proyecto.modelMiner.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    public Issue parseaIssue(ValueIssue valueIssue) {
        Issue res = new Issue();
        res.setTitle(valueIssue.getTitle());
        res.setDescription(valueIssue.getContent().getRaw());
        res.setState(valueIssue.getState());
        res.setCreatedAt(valueIssue.getCreatedOn());
        res.setUpdatedAt(valueIssue.getUpdatedOn());
        // res.setClosedAt();
        // res.setLabels(valueIssue.getKind());
        // res.setAuthor();      PARSEAR A USER
        // res.setAssignee(valueIssue.getAssignee()); // TMB PARSEAR A USER PERO SIEMPRE ES NULL
        res.setVotes(valueIssue.getVotes());

        CommentsBB commentsBB = restTemplate.getForObject(valueIssue.getLinks().getComments().getHref(), CommentsBB.class);
        List<Comment> comments = new ArrayList<>();
        for (ValueComment valueComment : commentsBB.getValues()){
            CommentsService commentsService = new CommentsService();
            comments.add(commentsService.parseaComment(valueComment));
        }

        res.setComments(comments); // parsear
        return res;
    }
}
