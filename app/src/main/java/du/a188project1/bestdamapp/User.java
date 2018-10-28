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

    // accessor method for first_name
    public String getFirst_name(){
        return this.first_name;
    }

    // mutator method for first_name
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    // accessor method for last_name
    public String getLast_name(){
        return this.last_name;
    }

    // mutator method for last_name
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    // accessor method for email
    public String getEmail(){
        return this.email;
    }

    // mutator method for email
    public void setEmail(String email){
        this.email = email;
    }

    // accessor method for password
    public String getPassword() {
        return password;
    }

    // mutator method for password
    public void setPassword(String password) {
        this.password = password;
    }

    // accessor method for genre_list
    public RealmList<String> getGenre_list(){
        return this.genre_list;
    }

    // mutator method for genre_list
    public void setGenre_list(RealmList<String> genre_list){
        this.genre_list = genre_list;
    }

    // accessor method for saved_events
    public RealmList<Event> getSaved_events(){
        return this.saved_events;
    }

    // mutator method for saved_events
    public void setSaved_events(RealmList<Event> saved_events){
        this.saved_events = saved_events;
    }

    // mutator method to add an event to saved_events
    public void addEvent(Event event) {
        saved_events.add(event);
    }

}
