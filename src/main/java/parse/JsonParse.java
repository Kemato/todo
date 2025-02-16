package parse;

import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class JsonParse {
    //Должен ли парсер возвращать объект, или сделать его войдом, и внутри него вызывать добавление нового объекта
    public void read(){
        JSONParser parser = new JSONParser();
        //Если впихнуть его в трай, то поток автоматически закроется
        try(FileReader reader = new FileReader("users.json");){
            JSONObject users = (JSONObject) parser.parse(reader);
            JSONArray usersArray = (JSONArray) users.get("users");
            for(Object it: usersArray){
                JSONObject userObj = (JSONObject) it;
                Integer id = (Integer) userObj.get("id");
                String username = (String) userObj.get("username");
                String password = (String) userObj.get("password");
                User user = new User(id, username, password);
            }
            //Приведение типов

        }
        catch (Exception e){
            System.out.println("Parsing error "+e.getMessage());
        }
    }
    public void write(User user){
        System.out.println("Я сюда зашел");
        JSONParser parser = new JSONParser();
        JSONObject userDetails = new JSONObject();
        userDetails.put("id", user.getId());
        userDetails.put("name", user.getName());
        userDetails.put("password", user.getHash());
        JSONObject userObj = new JSONObject();
        userObj.put("user", userDetails);
        try(FileWriter writer = new FileWriter("src/main/resources/users.json")){
            System.out.println("Я сюда зашел");
            writer.write(userObj.toJSONString());
            writer.flush();
        }
        catch (Exception e){
            System.out.println("Writing error "+e.getMessage());
        }
    }
    public void write(ArrayList<User> users){
        JSONParser parser = new JSONParser();
        JSONArray usersList = new JSONArray();
        for(User user: users){
            JSONObject userDetails = new JSONObject();
            userDetails.put("id", user.getId());
            userDetails.put("name", user.getName());
            userDetails.put("password", user.getHash());
            JSONObject userObj = new JSONObject();
            userObj.put("user", userDetails);
            usersList.add(userObj);
        }
        try(FileWriter writer = new FileWriter("src/main/resources/users.json")){
            System.out.println(usersList.toJSONString());
            writer.write(usersList.toJSONString());
            writer.flush();
        }
        catch (Exception e){
            System.out.println("Writing error "+e.getMessage());
        }
    }
}
