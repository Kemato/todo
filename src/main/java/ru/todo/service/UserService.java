package ru.todo.service;

import ru.todo.exeption.UserExeption;
import ru.todo.model.User;

import java.util.ArrayList;

public class UserService {
    private Long id = 0L;
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<String> userNames = new ArrayList<>();
    private User currentUser = null;

    public User createUser(String username, String password) {
        try {
            User newUser = new User((long) id++, username, password);
            users.add(newUser);
            userNames.add(username);
            currentUser = newUser;
            return currentUser;
        } catch (Exception e) {
            throw new UserExeption("User.User creation failed");
        }
    }

    public User login(String username, String password) {
        try {
            for (User user : users) {
                if (user.getName().equals(username) && user.checkPassword(password))return currentUser= user;
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
    public void setUsers(ArrayList<User> users) {
        this.users = users;
        if(!users.isEmpty()) {
            this.id = users.getLast().getId() + 1;
        }
    }

    public ArrayList<String> getUserNames() {return userNames;}
    public ArrayList<User> getUsers() {return users;}
}
