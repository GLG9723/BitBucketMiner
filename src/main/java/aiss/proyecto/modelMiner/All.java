package aiss.proyecto.modelMiner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class All {
    @JsonProperty("project")
    private Project project;
    @JsonProperty("comment")
    private Comment comment;
    @JsonProperty("commit")
    private Commit commit;
    @JsonProperty("issue")
    private Issue issue;
    @JsonProperty("user")
    private User user;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "All{" +
                "project=" + project +
                ", comment=" + comment +
                ", commit=" + commit +
                ", issue=" + issue +
                ", user=" + user +
                '}';
    }
}
