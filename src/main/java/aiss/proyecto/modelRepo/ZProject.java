package aiss.proyecto.modelRepo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class ZProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotEmpty()
    private String name;

    @Column(name = "web_url")
    private String web_url;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ZCommit> commits;

    public ZProject() {
    }

    public ZProject(String name, String web_url) {
        this.name = name;
        this.web_url = web_url;
        this.commits = new ArrayList<ZCommit>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<ZCommit> getCommits() {
        return commits;
    }

    public void setCommits(List<ZCommit> commits) {
        this.commits = commits;
    }

}
