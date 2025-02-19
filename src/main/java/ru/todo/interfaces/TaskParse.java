package ru.todo.interfaces;

import java.util.ArrayList;
import ru.todo.model.Task;
public interface TaskParse {
    public ArrayList<Task> read();
    public void write(ArrayList<Task> tasks);
}
