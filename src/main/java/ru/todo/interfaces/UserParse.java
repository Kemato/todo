package ru.todo.interfaces;

import ru.todo.model.User;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface UserParse {
    public ArrayList<User> read() throws JAXBException, FileNotFoundException;
    public void write() throws JAXBException;
}
