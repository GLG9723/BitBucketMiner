package aiss.proyecto.service;

import aiss.proyecto.modelCommits.Value;
import aiss.proyecto.modelMiner.Commit;
import org.springframework.stereotype.Service;

@Service
public class CommitService {

    public Commit parseaCommit(Value value) {
        Commit res = new Commit();
        res.setTitle(value.getHash()); // ns si esto es el tittle es q no esta claro
        res.setMessage(value.getMessage());
        res.setAuthorName(value.getAuthor().getUser().getDisplayName());

        String email = value.getAuthor().getRaw();
        Integer inicio = email.indexOf("<");
        Integer fin = email.indexOf(">");
        res.setAuthorEmail(email.substring(inicio+1, fin));

        res.setAuthoredDate(value.getDate());
        res.setWebUrl(value.getLinks().getHtml().getHref());
        return res;
    }
}
