public class User{
    private final int id;
    private String name;
    protected String password;
    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean checkPassword(String password){
        //todo.. Нужно проверить как работает equals
        return (this.password == password);
    }
    public int getId() {
        return id;
    }
}