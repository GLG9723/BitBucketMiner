package aiss.proyecto.service;

import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentsService {

    RestTemplate restTemplate = new RestTemplate();

    public Comment parseaComment(ValueComment valueComment){
        Comment res = new Comment();
        res.setBody(valueComment.getContent().getRaw());

        UserBB userBB = restTemplate.getForObject(valueComment.getUser().getLinks().getSelf().getHref(), UserBB.class);
        UserService userService = new UserService();
        res.setAuthor(userService.parseaUser(userBB));

        res.setCreatedAt(valueComment.getCreatedOn());

        res.setUpdatedAt(valueComment.getUpdatedOn().toString()); // esto es un object que es null y lo traformamos a string
        return res;
    }
}
