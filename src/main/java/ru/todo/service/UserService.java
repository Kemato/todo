package ru.todo.service;

import ru.todo.exeption.UserExeption;
import ru.todo.model.User;

import java.util.ArrayList;

public class UserService {
    private int id = 0;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public User login(String username, String password) {
        try {
            for (User user : users) {
                if (user.getName().equals(username) && user.checkPassword(password)) {
                    this.currentUser = user;
                    return this.currentUser;
                }
            }
            return this.currentUser;
        } catch (Exception e) {
            throw new UserExeption("Login failed");
        }
    }

    public User logout() {
        return currentUser = null;
    }
    public void setUsers(ArrayList<User> users) {this.users = users;}

    public ArrayList<String> getUserNames() {return userNames;}
    public ArrayList<User> getUsers() {return users;}
}
