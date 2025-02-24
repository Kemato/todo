package ru.todo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "user")
@XmlType(propOrder = { "id", "name", "password"})
public class User{

    private final Long id;

    private String name;
    private String password;
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
    public void setPassword(String password){this.password = password;}
    @XmlElement(name="title")
    public String getName(){
        return name;
    }
    public Long getId() {
        return id;
    }
    public String getPassword() {return password;}
    public boolean checkPassword(String password){return (this.password.equals(password));}
}