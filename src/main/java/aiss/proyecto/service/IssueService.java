package aiss.proyecto.service;

import aiss.proyecto.modelComments.CommentsBB;
import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelMiner.Issue;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    RestTemplate restTemplate = new RestTemplate();

    public Issue parseaIssue(ValueIssue valueIssue) {
        Issue res = new Issue();
        res.setTitle(valueIssue.getTitle());
        res.setDescription(valueIssue.getContent().getRaw());
        res.setState(valueIssue.getState());
        res.setCreatedAt(valueIssue.getCreatedOn());
        res.setUpdatedAt(valueIssue.getUpdatedOn());
        // res.setClosedAt();
        // res.setLabels(valueIssue.getKind());

        UserBB userBB = restTemplate.getForObject(valueIssue.getReporter().getLinks().getSelf().getHref(), UserBB.class);
        UserService userService = new UserService();
        res.setAuthor(userService.parseaUser(userBB));

        UserBB userBBAsig = restTemplate.getForObject(valueIssue.getReporter().getLinks().getSelf().getHref(), UserBB.class);
        UserService userServiceAsig = new UserService();
        res.setAssignee(null); // SIEMPRE ES NULL???

        res.setVotes(valueIssue.getVotes());

        CommentsBB commentsBB = restTemplate.getForObject(valueIssue.getLinks().getComments().getHref(), CommentsBB.class);
        List<Comment> comments = new ArrayList<>();
        for (ValueComment valueComment : commentsBB.getValues()){
            CommentsService commentsService = new CommentsService();
            comments.add(commentsService.parseaComment(valueComment));
        }
        res.setComments(comments);

        return res;
    }
}
