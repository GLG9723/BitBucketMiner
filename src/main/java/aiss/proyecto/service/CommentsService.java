package aiss.proyecto.service;

import aiss.proyecto.modelComments.CommentsBB;
import aiss.proyecto.modelComments.ValueComment;
import aiss.proyecto.modelMiner.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    public Comment parseaComment(ValueComment valueComment){
        Comment res = new Comment();
        res.setBody();
        return res;
    }
}
