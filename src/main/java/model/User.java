package model;

public class User{

    private final int id;
    private String name;
    protected String password;

    public User(
            int id,
            String name,
            String password
    ){
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
    public boolean checkPassword(String password){return (this.password.equals(password));}
    public int getId() {
        return id;
    }

    public int getHash() {
        return password.hashCode();
        //todo.. Посмотреть как работает эта функция
    }
}