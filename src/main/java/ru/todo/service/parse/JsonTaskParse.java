package ru.todo.service.parse;

import com.fasterxml.jackson.databind.SerializationFeature;
import ru.todo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.todo.model.Task;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.FileWriter;


public class JsonTaskParse {
    private static final String USERTASKLIST_JSON = "src/main/java/data/tasks.json";

    public void read(){
       try(InputStream inputStream = JsonTaskParse.class.getClassLoader().getResourceAsStream(USERTASKLIST_JSON);){
           TaskService taskService = TaskService.getInstance();
           ObjectMapper mapper = new ObjectMapper();
           ArrayList<Task> tasks = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class));
           taskService.setTasks(tasks);
       }
        catch (Exception e){
            System.out.println("я кладусь тут Read TaskList Error "+e.getMessage());
            System.out.println("я кладусь тут Read TaskList Error "+e.getMessage());
        }
    }

    public void write(){
        ObjectMapper mapper = new ObjectMapper();
        TaskService taskService = TaskService.getInstance();
        try(FileWriter writer = new FileWriter(USERTASKLIST_JSON)){
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            ArrayList<Task> tasks = taskService.getTasks();
            writer.write(mapper.writeValueAsString(tasks));
        }
        catch (Exception e){
            System.out.println("Writing TaskList Error "+e.getMessage());
        }
    }
}
