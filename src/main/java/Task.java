public class Task {
    private String name, description;
    private final int id;
    //Добавить поле со временем и с сроком задачи
    public Task(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
