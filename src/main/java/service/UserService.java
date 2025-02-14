package service;

import exeption.UserExeption;
import model.User;

import java.util.ArrayList;

public class UserService {
    private int id = 0;
    public ArrayList<User> users = new ArrayList();
    public ArrayList<String> userNames = new ArrayList();
    private User currentUser = null;

    public User CreateUser(String username, String password) {
        try {
            User newUser = new User(id++, username, password);
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
            //Я уверен что можно сделать это красивее, но пока что тупой перебор
            //Понять как достоверно работает equals
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
        currentUser = null;
        return currentUser;
    }

    public ArrayList<String> getUsers() {
        return userNames;
    }
}
