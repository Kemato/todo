package model;

public class Task {
    private String name, description, status, assigned, priority;
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
            String priority
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.assigned = assigned;
        this.status = status;
        this.priority = priority;
    }


    public int getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getAuthor() {return author;}
    public String getStatus() {return status;}
    public String getAssigned() {return assigned;}
    public String getPriority() {return priority;}


    public void setName(String name){this.name = name;}
    public void setStatus(String status){this.status = status;}
    public void setDescription(String description){this.description = description;}
    public void setAssigned(String assigned) {this.assigned = assigned;}
    public void setPriority(String priority) {this.priority = priority;}
}
