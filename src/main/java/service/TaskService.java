package service;

import model.Task;
import model.TaskStatus;
import java.util.ArrayList;

public class TaskService {
    private int id = 0;
    ArrayList<Task> tasks = new ArrayList();
    public boolean createTask(String name, String description, String author, String assigned) {
        try {
            Task newTask = new Task(id++, name, description, author, assigned);
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
    public boolean changeStatus(int id,String newStatus){
        //todo.. проверка на доступ к смене статуса
        try{
            for(Task task : tasks){
                if(task.getId() == id){
                    for(TaskStatus status:TaskStatus.values()){
                        if(status.toString().equalsIgnoreCase(newStatus))task.setStatus(status.toString());
                        return true;
                    }
                }
            }
            return false;
        }
        catch (Exception e){return false;}
    }
}
