package ru.todo.exeption;

public class UserExeption extends RuntimeException {
    public UserExeption(String message) {
        super(message);
    }
}
