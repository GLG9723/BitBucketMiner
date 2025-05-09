package aiss.proyecto.service;

import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CommentsService {

    RestTemplate restTemplate = new RestTemplate();

    public Comment parseaComment(ValueComment valueComment){
        Comment res = new Comment();
        res.setId(valueComment.getId().toString());

        if (valueComment.getContent().getRaw()!=null){
            res.setBody(valueComment.getContent().getRaw());
        } else {
            res.setBody("-");
        }


        // USUARIO
        String url = valueComment.getUser().getLinks().getSelf().getHref();
        URI uri = URI.create(url);

        UserBB userBB = restTemplate.getForObject(uri, UserBB.class);

        UserService userService = new UserService();
        res.setAuthor(userService.parseaUser(userBB));

        // CREATED AT
        res.setCreatedAt(valueComment.getCreatedOn());

        // esto casi siempre es null
        res.setUpdatedAt(valueComment.getUpdatedOn());

        return res;
    }
}
