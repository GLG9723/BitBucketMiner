
package aiss.proyecto.modelRepo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("html")
    private Html html;
    @JsonProperty("avatar")
    private Avatar avatar;
    @JsonProperty("pullrequests")
    private Pullrequests pullrequests;
    @JsonProperty("commits")
    private Commits commits;
    @JsonProperty("forks")
    private Forks forks;
    @JsonProperty("watchers")
    private Watchers watchers;
    @JsonProperty("branches")
    private Branches branches;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("downloads")
    private Downloads downloads;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("clone")
    private List<Clone> clone;
    @JsonProperty("issues")
    private Issues issues;
    @JsonProperty("hooks")
    private Hooks hooks;

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("html")
    public Html getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html html) {
        this.html = html;
    }

    @JsonProperty("avatar")
    public Avatar getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("pullrequests")
    public Pullrequests getPullrequests() {
        return pullrequests;
    }

    @JsonProperty("pullrequests")
    public void setPullrequests(Pullrequests pullrequests) {
        this.pullrequests = pullrequests;
    }

    @JsonProperty("commits")
    public Commits getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(Commits commits) {
        this.commits = commits;
    }

    @JsonProperty("forks")
    public Forks getForks() {
        return forks;
    }

    @JsonProperty("forks")
    public void setForks(Forks forks) {
        this.forks = forks;
    }

    @JsonProperty("watchers")
    public Watchers getWatchers() {
        return watchers;
    }

    @JsonProperty("watchers")
    public void setWatchers(Watchers watchers) {
        this.watchers = watchers;
    }

    @JsonProperty("branches")
    public Branches getBranches() {
        return branches;
    }

    @JsonProperty("branches")
    public void setBranches(Branches branches) {
        this.branches = branches;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("downloads")
    public Downloads getDownloads() {
        return downloads;
    }

    @JsonProperty("downloads")
    public void setDownloads(Downloads downloads) {
        this.downloads = downloads;
    }

    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    @JsonProperty("clone")
    public List<Clone> getClone() {
        return clone;
    }

    @JsonProperty("clone")
    public void setClone(List<Clone> clone) {
        this.clone = clone;
    }

    @JsonProperty("issues")
    public Issues getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(Issues issues) {
        this.issues = issues;
    }

    @JsonProperty("hooks")
    public Hooks getHooks() {
        return hooks;
    }

    @JsonProperty("hooks")
    public void setHooks(Hooks hooks) {
        this.hooks = hooks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("avatar");
        sb.append('=');
        sb.append(((this.avatar == null)?"<null>":this.avatar));
        sb.append(',');
        sb.append("pullrequests");
        sb.append('=');
        sb.append(((this.pullrequests == null)?"<null>":this.pullrequests));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null)?"<null>":this.commits));
        sb.append(',');
        sb.append("forks");
        sb.append('=');
        sb.append(((this.forks == null)?"<null>":this.forks));
        sb.append(',');
        sb.append("watchers");
        sb.append('=');
        sb.append(((this.watchers == null)?"<null>":this.watchers));
        sb.append(',');
        sb.append("branches");
        sb.append('=');
        sb.append(((this.branches == null)?"<null>":this.branches));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("downloads");
        sb.append('=');
        sb.append(((this.downloads == null)?"<null>":this.downloads));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("clone");
        sb.append('=');
        sb.append(((this.clone == null)?"<null>":this.clone));
        sb.append(',');
        sb.append("issues");
        sb.append('=');
        sb.append(((this.issues == null)?"<null>":this.issues));
        sb.append(',');
        sb.append("hooks");
        sb.append('=');
        sb.append(((this.hooks == null)?"<null>":this.hooks));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
