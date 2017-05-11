package com.bms.yahoooauth2;

import com.fasterxml.jackson.annotation.JsonInclude;


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
        "start",
        "count",
        "total",
        "connection"
})
public class Connections_ {

    @JsonProperty("start")
    private int start;
    @JsonProperty("count")
    private int count;
    @JsonProperty("total")
    private int total;
    @JsonProperty("connection")
    private List<Connection> connection = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("start")
    public int getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(int start) {
        this.start = start;
    }

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("connection")
    public List<Connection> getConnection() {
        return connection;
    }

    @JsonProperty("connection")
    public void setConnection(List<Connection> connection) {
        this.connection = connection;
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