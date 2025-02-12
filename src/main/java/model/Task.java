package model;

public class Task {
    private String name, description, author, status, assigned;
    private final int id;
    //Добавить поле со временем и с сроком задачи
    public Task(int id, String name, String description, String author, String assigned) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.assigned = assigned;
    }

    public String getAuthor() {return author;}
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }
    public String getStatus() {return status;}
    public void setName(String name){
        this.name = name;
    }
    public void setStatus(String status){this.status = status;}
    public void setDescription(String description){
        this.description = description;
    }
}
