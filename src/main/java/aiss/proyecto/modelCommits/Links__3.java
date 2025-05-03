
package aiss.proyecto.modelCommits;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "self",
    "html",
    "avatar"
})
@Generated("jsonschema2pojo")
public class Links__3 {

    @JsonProperty("self")
    private Self__3 self;
    @JsonProperty("html")
    private Html__3 html;
    @JsonProperty("avatar")
    private Avatar__1 avatar;

    @JsonProperty("self")
    public Self__3 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__3 self) {
        this.self = self;
    }

    @JsonProperty("html")
    public Html__3 getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html__3 html) {
        this.html = html;
    }

    @JsonProperty("avatar")
    public Avatar__1 getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar__1 avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
