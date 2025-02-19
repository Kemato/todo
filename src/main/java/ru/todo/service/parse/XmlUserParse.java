package ru.todo.service.parse;

import ru.todo.interfaces.UserParse;
import ru.todo.model.User;

import java.util.ArrayList;

public class XmlUserParse implements UserParse {
    public ArrayList<User> read(){
        ArrayList<User> users = new ArrayList<>();
        return users;
    }
    public void write(ArrayList<User> users){

    }
}
