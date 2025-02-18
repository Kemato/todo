package service;

import model.Task;
import model.TaskStatus;
import model.TaskPriority;

import java.util.ArrayList;
import java.util.Date;

public class TaskService {
    private int id = 0;
    ArrayList<Task> tasks = new ArrayList();

    public boolean createTask(
            String name,
            String description,
            String author,
            String assigned,
            String priority,
            Date Deadline
    ) {
        try {
            Task newTask = new Task(
                    id++,
                    name,
                    description,
                    author,
                    assigned,
                    TaskStatus.CREATED.name(),//todo.. проверить как работает на самом деле
                    priority
            );
            tasks.add(newTask);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Task> readTask() {
        return tasks;
    }

    public Task readTask(int id) {
        return this.tasks.get(id);
    }

    public boolean updateTask(
            int id,
            String name,
            String description,
            String assigned,
            String status,
            String priority
    ) {
        try {
            this.tasks.get(id).setName(name);
            this.tasks.get(id).setDescription(description);
            this.tasks.get(id).setAssigned(assigned);
            this.tasks.get(id).setStatus(status);
            this.tasks.get(id).setPriority(priority);
            return true;
        } catch (Exception e) {
            return false;
        }

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
        System.out.println("Я сюда зашел");
        try {
            this.tasks.get(id).setName(newName);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskDescription(int id, String newDescription) {
        try {
            this.tasks.get(id).setDescription(newDescription);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskAssigned(int id, String newAssigned) {
        try{
            this.tasks.get(id).setAssigned(newAssigned);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskStatus(int id, String newStatus) {
        //todo.. проверка на доступ к смене статуса
        try {
            for (Task task : tasks) {
                if (task.getId() == id) {
                    for (TaskStatus status : TaskStatus.values()) {
                        if (status.toString().equalsIgnoreCase(newStatus)) task.setStatus(status.toString());
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeTaskPriority(int id, String newPriority) {
        try {
            for (Task task : tasks) {
                if (task.getId() == id) {
                    for (TaskPriority taskPriority : TaskPriority.values()) {
                        if (taskPriority.toString().equalsIgnoreCase(newPriority))
                            task.setPriority(taskPriority.toString());
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
