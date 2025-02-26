package ru.todo.model;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "user")
@XmlType(propOrder = { "id", "name", "password"})
public class User{

    private Long id;
    private String name;
    private String password;

    public User() {
    }

    public User(
            Long id,
            String name,
            String password
    ){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setId(Long id) {this.id = id;}
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){this.password = password;}

    public Long getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getName(){
        return name;
    }
    public String getPassword() {return password;}
}