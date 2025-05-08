package aiss.proyecto.service;

import aiss.proyecto.modelComments.CommentsBB;
import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelIssues.ValueIssue;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelMiner.Issue;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

        // Si lo metemos como un string no funciona por restTemplate --> % --> codificacion
        String url = valueIssue.getReporter().getLinks().getSelf().getHref();
        URI uri = URI.create(url);

        UserBB userBB = restTemplate.getForObject(uri, UserBB.class);
        UserService userService = new UserService();
        res.setAuthor(userService.parseaUser(userBB));

        // ASSIGNEE
        // no puedo obtener la URL de algo NULL
        /* String urlAsig = valueIssue.getAssignee();
        URI uriAsig = URI.create(urlAsig);

        UserBB userBBAsig = restTemplate.getForObject(uriAsig, UserBB.class);
        // Siempre es NULL en la mayoria de proyectos observados
        if (userBBAsig != null) {
            UserService userServiceAsig = new UserService();
            res.setAssignee(userServiceAsig.parseaUser(userBBAsig));
        } */


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
