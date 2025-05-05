
package aiss.proyecto.modelUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("avatar")
    private Avatar avatar;
    @JsonProperty("repositories")
    private Repositories repositories;
    @JsonProperty("snippets")
    private Snippets snippets;
    @JsonProperty("html")
    private Html html;
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

    @JsonProperty("avatar")
    public Avatar getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("repositories")
    public Repositories getRepositories() {
        return repositories;
    }

    @JsonProperty("repositories")
    public void setRepositories(Repositories repositories) {
        this.repositories = repositories;
    }

    @JsonProperty("snippets")
    public Snippets getSnippets() {
        return snippets;
    }

    @JsonProperty("snippets")
    public void setSnippets(Snippets snippets) {
        this.snippets = snippets;
    }

    @JsonProperty("html")
    public Html getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html html) {
        this.html = html;
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
        sb.append("avatar");
        sb.append('=');
        sb.append(((this.avatar == null)?"<null>":this.avatar));
        sb.append(',');
        sb.append("repositories");
        sb.append('=');
        sb.append(((this.repositories == null)?"<null>":this.repositories));
        sb.append(',');
        sb.append("snippets");
        sb.append('=');
        sb.append(((this.snippets == null)?"<null>":this.snippets));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
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
