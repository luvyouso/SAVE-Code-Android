package com.bms.yahoooauth2;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacts_ {

    @SerializedName("start")
    @Expose
    private int start;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("contact")
    @Expose
    private List<Contact> contact = null;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

}