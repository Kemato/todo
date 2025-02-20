package ru.todo.model;

public class User{

    private final Long id;
    private String name;
    protected String password;

    public User(
            Long id,
            String name,
            String password
    ){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public Long getId() {
        return id;
    }
    public String getPassword() {return password;}
    public boolean checkPassword(String password){return (this.password.equals(password));}
}