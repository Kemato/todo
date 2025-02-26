package ru.todo.interfaces;

import jakarta.xml.bind.JAXBException;
import ru.todo.model.User;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface UserParse {
    public ArrayList<User> read() throws JAXBException, FileNotFoundException;
    public void write() throws JAXBException;
}
