/* Johanan Tai
   User class with getters and setters
 */

package du.a188project1.bestdamapp;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{
    @PrimaryKey
    private String email;  
    private String first_name;
    private String last_name;
    private String password;
    private RealmList<String> genre_list;
    private RealmList<Event> saved_events;

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

    public String getPassword() {
        return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<String> getGenre_list(){
        return this.genre_list;
    }

    public void setGenre_list(RealmList<String> genre_list){
        this.genre_list = genre_list;
    }

    public RealmList<Event> getSaved_events(){
        return this.saved_events;
    }

    public void setSaved_events(RealmList<Event> saved_events){
        this.saved_events = saved_events;
    }

    public void addEvent(Event event) {
        saved_events.add(event);
    }

}
