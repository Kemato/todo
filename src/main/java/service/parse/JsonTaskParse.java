package service.parse;
import model.Task;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class JsonTaskParse {
    public ArrayList<Task> read(){
        ArrayList<Task> tasks = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/tasks.json");){

            return tasks;
        }
        catch (Exception e){
            System.out.println("Parsing TaskList error "+e.getMessage());
        }

        return tasks;
    }

    public void write(ArrayList<Task> tasks){
        JSONParser parser = new JSONParser();
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
            jsonTaskArray.add(jsonTask);
        }
        try(FileWriter writer = new FileWriter("src/main/resources/users.json")){
            writer.write(jsonTaskArray.toJSONString());
            writer.flush();
        }
        catch (Exception e){
            System.out.println("Writing TaskList Error "+e.getMessage());
        }
    }
}
