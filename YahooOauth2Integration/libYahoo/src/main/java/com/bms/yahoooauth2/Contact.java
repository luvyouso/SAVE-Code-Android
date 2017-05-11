package com.bms.yahoooauth2;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("isConnection")
    @Expose
    private boolean isConnection;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isIsConnection() {
        return isConnection;
    }

    public void setIsConnection(boolean isConnection) {
        this.isConnection = isConnection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}