package ru.todo.service.parse;

import ru.todo.interfaces.UserParse;
import ru.todo.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class JsonUserParse {
    public ArrayList<User> read(){
        ArrayList<User> usersArray = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/java/data/users.json");){
            JSONArray usersArrayJSON = (JSONArray) parser.parse(reader);
            for(Object it: usersArrayJSON){
                JSONObject userObj = (JSONObject) it;
                Long id = (Long) userObj.get("id");
                String username = (String) userObj.get("name");
                String password = (String) userObj.get("password");
                User user = new User(id, username, password);
                usersArray.add(user);
                //Как лучше, создать пользователя за циклом и использовать setter или так
            }
            return usersArray;
        }
        catch (Exception e){
            System.out.println("Parsing read error "+e.getMessage());
        }
        return usersArray;
    }
    public void write(ArrayList<User> users){
        JSONParser parser = new JSONParser();
        JSONArray usersList = new JSONArray();
        for(User user: users){
            JSONObject userDetails = new JSONObject();
            userDetails.put("password", user.getPassword());
            userDetails.put("name", user.getName());
            userDetails.put("id", user.getId().intValue());
            usersList.add(userDetails);
        }
        try(FileWriter writer = new FileWriter("src/main/java/data/users.json")){
            writer.write(usersList.toJSONString());
            writer.flush();
        }
        catch (Exception e){
            System.out.println("Writing error "+e.getMessage());
        }
    }
}
