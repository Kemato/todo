package ru.todo.interfaces;

import ru.todo.model.User;

import java.util.ArrayList;

public interface UserParse {
    public ArrayList<User> read();
    public void write(ArrayList<User> tasks);
}
