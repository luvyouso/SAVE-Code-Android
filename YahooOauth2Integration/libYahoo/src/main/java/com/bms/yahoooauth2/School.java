package com.bms.yahoooauth2;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "schoolName",
        "schoolType",
        "schoolYear"
})
public class School {

    @JsonProperty("id")
    private int id;
    @JsonProperty("schoolName")
    private String schoolName;
    @JsonProperty("schoolType")
    private String schoolType;
    @JsonProperty("schoolYear")
    private String schoolYear;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("schoolName")
    public String getSchoolName() {
        return schoolName;
    }

    @JsonProperty("schoolName")
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @JsonProperty("schoolType")
    public String getSchoolType() {
        return schoolType;
    }

    @JsonProperty("schoolType")
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    @JsonProperty("schoolYear")
    public String getSchoolYear() {
        return schoolYear;
    }

    @JsonProperty("schoolYear")
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
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