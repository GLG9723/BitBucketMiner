package aiss.proyecto.service;

import aiss.proyecto.modelComments.CommentsBB;
import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelMiner.Issue;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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

        // si es cualquier cosa que no sea NEW es que se cerro en la fecha de update
        if (!valueIssue.getState().equals("new")) {
            res.setClosedAt(valueIssue.getUpdatedOn());
        }

        // ns que mas añadir a los labels
        List<String> labels = new ArrayList<>();
        labels.add(valueIssue.getKind());
        res.setLabels(labels);

        // le añade caracteres que no deberia a la url o algo el getForObject
        try {
            System.out.println(valueIssue.getReporter().getLinks().getSelf().getHref());
            UserBB userBB = restTemplate.getForObject("https://api.bitbucket.org/2.0/users/%7Bef8e4111-1bef-40cd-9e4e-d50546ca5a50%7D", UserBB.class);
            UserService userService = new UserService();
            res.setAuthor(userService.parseaUser(userBB));
        } catch (RestClientException e) {
            String url = valueIssue.getReporter().getLinks().getSelf().getHref();
            Integer indiceException = e.toString().indexOf("message");
            Integer indiceUrl = url.indexOf("users/");
            indiceException += 11;
            indiceUrl += 6;

            String recorteUrl = url.substring(0, indiceUrl);

            String nuevo = e.toString().substring(indiceException, e.toString().length()-4);

            /* UserBB userBB = restTemplate.getForObject(recorteUrl+nuevo, UserBB.class);
            UserService userService = new UserService();
            res.setAuthor(userService.parseaUser(userBB)); */
        }




        // UserBB userBBAsig = restTemplate.getForObject(valueIssue.getReporter().getLinks().getSelf().getHref(), UserBB.class);
        // UserService userServiceAsig = new UserService();
        // res.setAssignee(); // SIEMPRE ES NULL???

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
