package ru.todo.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Date;

@XmlRootElement(name = "task")
@XmlType(propOrder = {
        "id",
        "name",
        "description",
        "author",
        "assigned",
        "status",
        "priority",
        "dateCreated",
        "deadline",
        "dateUpdated",
        "dateFinished"})
public class Task {
    private String name, description, status, assigned, priority;
    private Date deadLine, dateFinished, dateUpdated;
    private final Date dateCreated;
    private final String author;
    private final Long id;

    //Добавить поле со временем и со сроком задачи
    public Task(
            Long id,
            String name,
            String description,
            String author,
            String assigned,
            String status,
            String priority,
            Date dateCreated,
            Date deadLine,
            Date dateUpdated,
            Date dateFinished
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.assigned = assigned;
        this.status = status;
        this.priority = priority;
        this.dateCreated = dateCreated;
        this.deadLine = deadLine;
        this.dateUpdated = dateUpdated;
        this.dateFinished = dateFinished;
    }


    public Long getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getAuthor() {
        return author;
    }
    public String getAssigned() {
        return assigned;
    }
    public String getStatus() {
        return status;
    }
    public String getPriority() {
        return priority;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public Date getDateUpdated() {
        return dateUpdated;
    }
    public Date getDeadline() {
        return deadLine;
    }
    public Date getDateFinished() {
        return dateFinished;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setDeadline(Date deadline) {
        this.deadLine = deadline;
    }
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }
}
