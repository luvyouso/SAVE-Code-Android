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
        "uri",
        "guid",
        "created",
        "familyName",
        "gender",
        "givenName",
        "memberSince",
        "image",
        "interests",
        "lang",
        "location",
        "lookingFor",
        "nickname",
        "profileUrl",
        "relationshipStatus",
        "schools",
        "status",
        "timeZone",
        "works",
        "isConnected"
})
public class Profile_ {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("created")
    private String created;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("memberSince")
    private String memberSince;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("interests")
    private List<Interest> interests = null;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("location")
    private String location;
    @JsonProperty("lookingFor")
    private List<String> lookingFor = null;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("profileUrl")
    private String profileUrl;
    @JsonProperty("relationshipStatus")
    private String relationshipStatus;
    @JsonProperty("schools")
    private List<School> schools = null;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("works")
    private List<Work> works = null;
    @JsonProperty("isConnected")
    private boolean isConnected;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("familyName")
    public String getFamilyName() {
        return familyName;
    }

    @JsonProperty("familyName")
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("givenName")
    public String getGivenName() {
        return givenName;
    }

    @JsonProperty("givenName")
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @JsonProperty("memberSince")
    public String getMemberSince() {
        return memberSince;
    }

    @JsonProperty("memberSince")
    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    @JsonProperty("interests")
    public List<Interest> getInterests() {
        return interests;
    }

    @JsonProperty("interests")
    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("lookingFor")
    public List<String> getLookingFor() {
        return lookingFor;
    }

    @JsonProperty("lookingFor")
    public void setLookingFor(List<String> lookingFor) {
        this.lookingFor = lookingFor;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("profileUrl")
    public String getProfileUrl() {
        return profileUrl;
    }

    @JsonProperty("profileUrl")
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @JsonProperty("relationshipStatus")
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    @JsonProperty("relationshipStatus")
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    @JsonProperty("schools")
    public List<School> getSchools() {
        return schools;
    }

    @JsonProperty("schools")
    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("works")
    public List<Work> getWorks() {
        return works;
    }

    @JsonProperty("works")
    public void setWorks(List<Work> works) {
        this.works = works;
    }

    @JsonProperty("isConnected")
    public boolean isIsConnected() {
        return isConnected;
    }

    @JsonProperty("isConnected")
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
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