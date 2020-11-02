package com.app.ticketsupport.models;

public class TicketRequest {
    private String department;
    private String priority;
    private String title;
    private String message;
    private String ticketType;
    private String answers;

    public TicketRequest(String department, String priority, String title, String message, String ticketType) {
        this.department = department;
        this.priority = priority;
        this.title = title;
        this.message = message;
        this.ticketType = ticketType;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
