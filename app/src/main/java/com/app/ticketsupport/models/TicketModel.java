package com.app.ticketsupport.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class TicketModel {
    private String _id;
    private String department;
    private String priority;
    private String title;
    private String message;
    private String ticketType;
    private UserModel user;
    private Date date;
    private String[] answers;
    private String image;
    private long v;

    @JsonProperty("_id")
    public String getID() { return _id; }
    @JsonProperty("_id")
    public void setID(String value) { this._id = value; }

    @JsonProperty("department")
    public String getDepartment() { return department; }
    @JsonProperty("department")
    public void setDepartment(String value) { this.department = value; }

    @JsonProperty("priority")
    public String getPriority() { return priority; }
    @JsonProperty("priority")
    public void setPriority(String value) { this.priority = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("message")
    public String getMessage() { return message; }
    @JsonProperty("message")
    public void setMessage(String value) { this.message = value; }

    @JsonProperty("ticketType")
    public String getTicketType() { return ticketType; }
    @JsonProperty("ticketType")
    public void setTicketType(String value) { this.ticketType = value; }

    @JsonProperty("user")
    public UserModel getUser() { return user; }
    @JsonProperty("user")
    public void setUser(UserModel value) { this.user = value; }

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public Date getDate() { return date; }
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    public void setDate(Date value) { this.date = value; }

    @JsonProperty("__v")
    public long getV() { return v; }
    @JsonProperty("__v")
    public void setV(long value) { this.v = value; }
    @JsonProperty("image")
    public String getImage() {
        return image;
    }
    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }
    @JsonProperty("answers")
    public  String[] getAnswers() {
        return answers;
    }
    @JsonProperty("answers")
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
