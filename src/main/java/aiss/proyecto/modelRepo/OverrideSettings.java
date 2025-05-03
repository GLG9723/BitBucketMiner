
package aiss.proyecto.modelRepo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OverrideSettings {

    @JsonProperty("default_merge_strategy")
    private Boolean defaultMergeStrategy;
    @JsonProperty("branching_model")
    private Boolean branchingModel;

    @JsonProperty("default_merge_strategy")
    public Boolean getDefaultMergeStrategy() {
        return defaultMergeStrategy;
    }

    @JsonProperty("default_merge_strategy")
    public void setDefaultMergeStrategy(Boolean defaultMergeStrategy) {
        this.defaultMergeStrategy = defaultMergeStrategy;
    }

    @JsonProperty("branching_model")
    public Boolean getBranchingModel() {
        return branchingModel;
    }

    @JsonProperty("branching_model")
    public void setBranchingModel(Boolean branchingModel) {
        this.branchingModel = branchingModel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OverrideSettings.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("defaultMergeStrategy");
        sb.append('=');
        sb.append(((this.defaultMergeStrategy == null)?"<null>":this.defaultMergeStrategy));
        sb.append(',');
        sb.append("branchingModel");
        sb.append('=');
        sb.append(((this.branchingModel == null)?"<null>":this.branchingModel));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
