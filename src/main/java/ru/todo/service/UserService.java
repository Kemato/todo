package ru.todo.service;

import jakarta.xml.bind.annotation.*;
import ru.todo.exeption.UserExeption;
import ru.todo.model.User;


import java.util.ArrayList;

@XmlRootElement(namespace = "ru.todo.service")
@XmlAccessorType(XmlAccessType.FIELD) // Указываем, что сериализуются только поля с аннотациями
public class UserService {

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private ArrayList<User> userList = new ArrayList<>();
    private Long id = 0L;
    private User currentUser = null;

    // Приватный конструктор, чтобы запретить создание экземпляров извне
    private UserService() {}

    // Внутренний статический класс для ленивой инициализации
    private static class SingletonHolder {
        private static final UserService INSTANCE = new UserService();
    }

    // Публичный метод для получения единственного экземпляра
    public static UserService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public User createUser(String username, String password) {
        for (User user : userList) {
            if (user.getName().equals(username)) {
                System.out.println("User already exists");
                return null;
            }
        }
        try {
            User newUser = new User((long) id++, username, password);
            this.userList.add(newUser);
            currentUser = newUser;
            return currentUser;
        } catch (Exception e) {
            throw new UserExeption("User creation failed");
        }
    }

    public User readUser(long id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(long id, String username, String password) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.setName(username);
                user.setPassword(password);
            }
        }
    }

    public void deleteUser(long id) {
        userList.removeIf(user -> user.getId() == id);
    }

    public User login(String username, String password) {
        try {
            for (User user : this.userList) {
                if (user.getName().equals(username) && user.getPassword().equals(password))
                    return currentUser = user;
            }
        } catch (Exception e) {
            throw new UserExeption("Login failed");
        } finally {
            System.out.println("Username or password doesn't match");
        }
        return currentUser = null;
    }

    public User logOut() {
        return currentUser = null;
    }

    public void setUserList(ArrayList<User> users) {
        this.userList = users;
        if (!this.userList.isEmpty()) {
            this.id = this.userList.getLast().getId() + 1;
        }
    }

    public ArrayList<User> getUserList() {
        return this.userList;
    }
}