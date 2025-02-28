package ru.todo.service.parse;
import ru.todo.interfaces.TaskParse;
import ru.todo.model.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.todo.service.TaskService;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

public class JsonTaskParse {
    public void read(){
        ArrayList<Task> tasksArray = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/tasks.json");){
            JSONArray tasksArrayJSON = (JSONArray) parser.parse(reader);
            System.out.println("Reading tasks.json");
            for(Object item: tasksArrayJSON){
                    Task task = (Task) item;
//                    JSONObject taskJSON = (JSONObject) item;
//                    Long id = (Long) taskJSON.get("id");
//                    String name = (String) taskJSON.get("name");
//                    String description = (String) taskJSON.get("description");
//                    String author = (String) taskJSON.get("author");
//                    String assigned = (String) taskJSON.get("assigned");
//                    String status = (String) taskJSON.get("status");
//                    String priority = (String) taskJSON.get("priority");
//                    Date deadLine = (Date) taskJSON.get("deadLine");
//                    Date dateCreated = (Date) taskJSON.get("dateCreated");
//                    Date dateFinished = (Date) taskJSON.get("dateFinished");
//                    Date dateUpdate = (Date) taskJSON.get("dateUpdate");
//                    Task task = new Task(id, name, description, author, assigned, status, priority, dateCreated, deadLine, dateUpdate, dateFinished);
                    tasksArray.add(task);
                }
            TaskService taskService = TaskService.getInstance();
                taskService.setTasks(tasksArray);
        }
        catch (Exception e){
            System.out.println("Я кладусь здесь Parsing TaskList error "+e.getMessage());
        }
    }

    public void write(){
        TaskService taskService = TaskService.getInstance();
        ArrayList<Task> tasks = taskService.getTasks();
        JSONArray jsonTaskArray = new JSONArray();
        for(Task task : tasks){
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("id", task.getId());
            jsonTask.put("name", task.getName());
            jsonTask.put("description", task.getDescription());
            jsonTask.put("author", task.getAuthor());
            jsonTask.put("assigned", task.getAssigned());
            jsonTask.put("status", task.getStatus());
            jsonTask.put("priority", task.getPriority());
            jsonTask.put("deadLine", task.getDeadline());
            jsonTask.put("dateCreated", task.getDateCreated());
            jsonTask.put("dateFinished", task.getDateFinished());
            jsonTask.put("dateUpdate", task.getDateUpdated());
            jsonTaskArray.add(jsonTask);
        }
        try(FileWriter writer = new FileWriter("src/main/resources/tasks.json")){
            writer.write(jsonTaskArray.toJSONString());
            writer.flush();
        }
        catch (Exception e){
            System.out.println("Writing TaskList Error "+e.getMessage());
        }
    }
}
