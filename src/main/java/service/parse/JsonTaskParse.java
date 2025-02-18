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
            System.out.println("Parsing error "+e.getMessage());
        }

        return tasks;
    }

    public void write(){
        JSONParser parser = new JSONParser();
    }
}
