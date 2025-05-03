package aiss.proyecto.service;

import aiss.proyecto.modelCommits.CommitBB;
import aiss.proyecto.modelCommits.Value;
import aiss.proyecto.modelMiner.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommitService {

    public Commit parseaCommit(Value value) {
        Commit res = new Commit();
        // res.setTitle();
        res.setMessage(value.getMessage());
        res.setAuthorName(value.getAuthor().getUser().getDisplayName());
        // res.getAuthorEmail()
        res.setAuthoredDate(value.getDate());
        res.setWebUrl(value.getLinks().getHtml().getHref());
        return res;
    }
}
