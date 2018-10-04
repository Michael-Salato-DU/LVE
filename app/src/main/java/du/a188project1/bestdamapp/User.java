package du.a188project1.bestdamapp;

import java.util.List;

public class User {
    private String first_name;
    private String last_name;
    private String email;
    private char[] password;
    private List<String> genre_list;
    private List<Event> saved_events;

    public String getFirst_name(){
        return this.first_name;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public List<String> getGenre_list(){
        return this.genre_list;
    }

    public void setGenre_list(List<String> genre_list){
        this.genre_list = genre_list;
    }

    public List<Event> getSaved_events(){
        return this.saved_events;
    }

    public void setSaved_events(List<Event> saved_events){
        this.saved_events = saved_events;
    }

}
