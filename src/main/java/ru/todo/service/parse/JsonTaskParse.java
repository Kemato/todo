package ru.todo.service.parse;

import ru.todo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.todo.model.Task;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.FileWriter;


public class JsonTaskParse {
    public void read(){
       try(InputStream inputStream = JsonTaskParse.class.getClassLoader().getResourceAsStream("src/main/java/data/tasks.json");

       ){
           TaskService taskService = TaskService.getInstance();
           ObjectMapper mapper = new ObjectMapper();
           ArrayList<Task> tasks = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class));
           taskService.setTasks(tasks);
       }
        catch (Exception e){
            System.out.println("Read TaskList Error "+e.getMessage());
        }
    }

    public void write(){
        ObjectMapper mapper = new ObjectMapper();
        TaskService taskService = TaskService.getInstance();
        try(FileWriter writer = new FileWriter("src/main/java/data/tasks.json")){
            mapper.writeValue(writer, taskService.getTasks());
        }
        catch (Exception e){
            System.out.println("Writing TaskList Error "+e.getMessage());
        }
    }
}
