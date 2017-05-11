package com.bms.yahoooauth2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "declaredInterests",
        "interestCategory"
})
public class Interest {

    @JsonProperty("declaredInterests")
    private List<String> declaredInterests = null;
    @JsonProperty("interestCategory")
    private String interestCategory;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("declaredInterests")
    public List<String> getDeclaredInterests() {
        return declaredInterests;
    }

    @JsonProperty("declaredInterests")
    public void setDeclaredInterests(List<String> declaredInterests) {
        this.declaredInterests = declaredInterests;
    }

    @JsonProperty("interestCategory")
    public String getInterestCategory() {
        return interestCategory;
    }

    @JsonProperty("interestCategory")
    public void setInterestCategory(String interestCategory) {
        this.interestCategory = interestCategory;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}