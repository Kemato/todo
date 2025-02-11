import java.util.ArrayList;

public class UserService {
    private int id = 0;
    public ArrayList<User> users = new ArrayList();
    private User currentUser = null;
    public User CreateUser(String username, String password) {
        try{
            User newUser = new User(id++, username, password);
            users.add(newUser);
            currentUser = newUser;
            return currentUser;
        }
        catch(Exception e){
            throw new UserExeption("User creation failed");
        }
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public User login(String username, String password) {
        try {
            //Я уверен что можно сделать это красивее, но пока что тупой перебор
            //Понять как достоверно работает equals
            for(User user : users){
                if(user.getName().equals(username) && user.checkPassword(password)) {
                    this.currentUser = user;
                    break;
                }
            }
            return this.currentUser;
        }
        catch(Exception e){
            throw new UserExeption("Login failed");
        }
    }
    public User logout() {
        this.currentUser = null;
        return this.currentUser;
    }
}
