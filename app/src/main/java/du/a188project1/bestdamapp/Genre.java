package du.a188project1.bestdamapp;

import io.realm.RealmObject;

public class Genre extends RealmObject{
    public String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
