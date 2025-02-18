package interfaces;

import java.util.ArrayList;
import model.Task;
public interface TaskParse {
    public ArrayList<Task> read();
    public void write(ArrayList<Task> tasks);
}
