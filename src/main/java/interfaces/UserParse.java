package interfaces;

import model.User;

import java.util.ArrayList;

public interface UserParse {
    public ArrayList<User> read();
    public void write(ArrayList<User> tasks);
}
