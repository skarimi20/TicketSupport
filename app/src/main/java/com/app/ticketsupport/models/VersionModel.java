package com.app.ticketsupport.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionModel {
    private String id;
    private long lastVersion;
    private String downloadLink;
    private long v;

    @JsonProperty("_id")
    public String getID() { return id; }
    @JsonProperty("_id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("lastVersion")
    public long getLastVersion() { return lastVersion; }
    @JsonProperty("lastVersion")
    public void setLastVersion(long value) { this.lastVersion = value; }

    @JsonProperty("downloadLink")
    public String getDownloadLink() { return downloadLink; }
    @JsonProperty("downloadLink")
    public void setDownloadLink(String value) { this.downloadLink = value; }

    @JsonProperty("__v")
    public long getV() { return v; }
    @JsonProperty("__v")
    public void setV(long value) { this.v = value; }
}
