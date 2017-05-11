package com.bms.yahoooauth2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacts {

    @SerializedName("contacts")
    @Expose
    private Contacts_ contacts;

    public Contacts_ getContacts() {
        return contacts;
    }

    public void setContacts(Contacts_ contacts) {
        this.contacts = contacts;
    }

}