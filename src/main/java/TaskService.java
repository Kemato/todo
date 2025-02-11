import java.io.IOException;
import java.util.ArrayList;

public class TaskService {
    private int id = 0;
    ArrayList<Task> tasks = new ArrayList();
    public boolean createTask(String name, String description) {
        try {
            Task newTask = new Task(id++, name, description);
            tasks.add(newTask);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public ArrayList<Task> readTask(){
        return tasks;
    }
    public Task readTask(int id){
        return this.tasks.get(id);
    }
    public boolean updateTask(int id, String name, String description){
        try{
            this.tasks.get(id).setName(name);//может не работать, потому что я получаю переменную, а не меняю её по ссылке
            this.tasks.get(id).setDescription(description);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
    public boolean deleteTask(int id){

        try {
            this.tasks.remove(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
