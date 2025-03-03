package ru.todo.service.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.todo.model.User;
import ru.todo.service.UserService;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class JsonUserParse {
    private static final String USERLIST_JSON = "src/main/java/data/users.json";

    public void read() {
        try (FileReader reader = new FileReader(USERLIST_JSON);) {
            ObjectMapper mapper = new ObjectMapper();

            UserService.getInstance().setUserList(
                    mapper.readValue(reader, mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class)));
        } catch (Exception e) {
            System.out.println("Parsing read error " + e.getMessage());
        }
    }

    public void write() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = UserService.getInstance().getUserList();
        try (FileWriter writer = new FileWriter(USERLIST_JSON)) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            writer.write(mapper.writeValueAsString(users));
        } catch (Exception e) {
            System.out.println("Writing user_list_json error " + e.getMessage());
        }
    }
}
