package aiss.proyecto.service;

import aiss.proyecto.modelMiner.User;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User parseaUser(UserBB userBB){
        User res = new User();
        res.setId(userBB.getUuid());
        res.setUsername(userBB.getNickname());
        res.setName(userBB.getDisplayName());
        res.setAvatarUrl(userBB.getLinks().getAvatar().getHref());
        res.setWebUrl(userBB.getLinks().getHtml().getHref());
        return res;
    }
}
