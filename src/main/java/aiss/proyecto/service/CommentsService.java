package aiss.proyecto.service;

import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelMiner.Comment;
import aiss.proyecto.modelMiner.User;
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
        if (valueComment.getUser() != null) {
            String url = valueComment.getUser().getLinks().getSelf().getHref();
            URI uri = URI.create(url);

            UserBB userBB = restTemplate.getForObject(uri, UserBB.class);

            UserService userService = new UserService();
            res.setAuthor(userService.parseaUser(userBB));
        } else {
            User vacio = new User();
            vacio.setId("0");
            vacio.setUsername("deletedUser");
            vacio.setName("Deleted User");
            vacio.setAvatarUrl("La información de este usuario fue eliminada");
            vacio.setWebUrl("La información de este usuario fue eliminada");
            res.setAuthor(vacio);
        }


        // CREATED AT
        res.setCreatedAt(valueComment.getCreatedOn());

        // esto casi siempre es null
        res.setUpdatedAt(valueComment.getUpdatedOn());

        return res;
    }
}
