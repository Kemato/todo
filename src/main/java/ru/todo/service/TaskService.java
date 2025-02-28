package ru.todo.service;

import jakarta.xml.bind.annotation.*;
import ru.todo.model.Task;
import ru.todo.model.TaskStatus;
import ru.todo.model.TaskPriority;


import java.util.ArrayList;
import java.util.Date;
@XmlRootElement(namespace = "ru.todo.service")
@XmlAccessorType(XmlAccessType.FIELD) // Указываем, что сериализуются только поля с аннотациями
public class TaskService {
    //todo поле int работает не так, как я предполагаю. Может быть путаница в индексах.
    private Long id = 0L;
    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    ArrayList<Task> tasks = new ArrayList();
    private TaskService() {}

    private static class SingletonHolder {
        private static final TaskService INSTANCE = new TaskService();
    }

    public static TaskService getInstance() {
        return TaskService.SingletonHolder.INSTANCE;
    }

    public boolean createTask(
            String name,
            String description,
            String author,
            String assigned,
            String priority,
            Date deadline
    ) {
        try {
            Task newTask = new Task(
                    id++,
                    name,
                    description,
                    author,
                    assigned,
                    TaskStatus.CREATED.name(),//todo.. проверить как работает на самом деле
                    priority,
                    new Date(),
                    deadline,
                    new Date(),
                    null
            );
            tasks.add(newTask);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        if(!tasks.isEmpty())this.id = tasks.getLast().getId() + 1;
    }
    public ArrayList<Task> getTasks() {return tasks;}

    public ArrayList<Task> readTask() {
        return tasks;
    }

    public Task readTask(int id) {
        return this.tasks.get(id);
    }

    public boolean deleteTask(int id) {

        try {
            this.tasks.remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskName(int id, String newName) {
        try {
            this.tasks.get(id).setName(newName);
            this.tasks.get(id).setDateUpdated(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskDescription(int id, String newDescription) {
        try {
            this.tasks.get(id).setDescription(newDescription);
            this.tasks.get(id).setDateUpdated(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskAssigned(int id, String newAssigned) {
        try {
            this.tasks.get(id).setAssigned(newAssigned);
            this.tasks.get(id).setDateUpdated(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskStatus(int id, String newStatus) {
        //todo.. проверка на доступ к смене статуса
        try {
            for (TaskStatus status : TaskStatus.values()) {
                if (status.toString().equalsIgnoreCase(newStatus)) {
                    tasks.get(id).setStatus(status.toString());
                    tasks.get(id).setDateUpdated(new Date());
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskPriority(int id, String newPriority) {
        try {
            for (TaskPriority taskPriority : TaskPriority.values()) {
                if (taskPriority.toString().equalsIgnoreCase(newPriority)) {
                    tasks.get(id).setPriority(taskPriority.toString());
                    tasks.get(id).setDateUpdated(new Date());
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskDeadline(int id, Date newDeadline) {
        try{
            this.tasks.get(id).setDeadline(newDeadline);
            this.tasks.get(id).setDateUpdated(new Date());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean taskFinished(int id){
        try{
            this.tasks.get(id).setDateUpdated(new Date());
            this.tasks.get(id).setDateFinished(new Date());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
