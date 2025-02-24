package ru.todo.service;


import ru.todo.exeption.UserExeption;
import ru.todo.model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(namespace = "ru.todo.service")
public class UserService {

    @XmlElementWrapper(name = "userList")
    @XmlElement(name = "user")
    private ArrayList<User> userList = new ArrayList<>();
    private Long id = 0L;
    private User currentUser = null;

    public User createUser(String username, String password) {
        try {
            User newUser = new User((long) id++, username, password);
            userList.add(newUser);
            currentUser = newUser;
            return currentUser;
        } catch (Exception e) {
            throw new UserExeption("User.User creation failed");
        }
    }

    public User login(String username, String password) {
        try {
            for (User user : userList) {
                if (user.getName().equals(username) && user.checkPassword(password))
                    return currentUser= user;
            }
        }
        catch (Exception e) {
            throw new UserExeption("Login failed");
        }
        return currentUser = null;
    }

    public User logout() {
        return currentUser = null;
    }
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
        if(!userList.isEmpty()) {
            this.id = userList.getLast().getId() + 1;
        }
    }

    public ArrayList<User> getUserList() {return userList;}
}

