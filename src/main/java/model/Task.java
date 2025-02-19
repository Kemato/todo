package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Task {
    private String name, description, status, assigned, priority;
    private Date deadline, dateFinished, dateUpdated;
    private final Date dateCreated;
    private final String author;
    private final int id;
    //Добавить поле со временем и со сроком задачи
    public Task(
            int id,
            String name,
            String description,
            String author,
            String assigned,
            String status,
            String priority,
            Date deadline
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.assigned = assigned;
        this.status = status;
        this.priority = priority;
        this.dateCreated = new Date();
        this.deadline = deadline;
        this.dateUpdated = new Date();
        this.dateFinished = null;
    }


    public int getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getAuthor() {return author;}
    public String getAssigned() {return assigned;}
    public String getStatus() {return status;}
    public String getPriority() {return priority;}
    public Date getDateCreated(){return dateCreated;}
    public Date getDateUpdated(){return dateUpdated;}
    public Date getDeadline(){return deadline;}
    public Date getDateFinished(){return dateFinished;}


    public void setName(String name){this.name = name;}
    public void setStatus(String status){this.status = status;}
    public void setDescription(String description){this.description = description;}
    public void setAssigned(String assigned) {this.assigned = assigned;}
    public void setPriority(String priority) {this.priority = priority;}
    public void setDateUpdated(Date dateUpdated) {this.dateUpdated = dateUpdated;}
    public void setDateFinished(Date dateFinished) {this.dateFinished = dateFinished;}
    public void setDeadline(Date deadline) {this.deadline = deadline;}
}
