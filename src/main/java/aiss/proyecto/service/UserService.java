package aiss.proyecto.service;

import aiss.proyecto.modelMiner.User;
import aiss.proyecto.modelUser.UserBB;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User parseaUser(UserBB userBB){
        User res = new User();
        res.setId(userBB.getUuid());

        if (userBB.getNickname() != null){
            res.setUsername(userBB.getNickname());
        } else {
            res.setUsername("N/A");
        }

        res.setName(userBB.getDisplayName());
        res.setAvatarUrl(userBB.getLinks().getAvatar().getHref());
        res.setWebUrl(userBB.getLinks().getHtml().getHref());
        return res;
    }
}
