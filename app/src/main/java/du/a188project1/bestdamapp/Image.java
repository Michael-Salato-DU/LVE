package du.a188project1.bestdamapp;

import io.realm.RealmObject;

public class Image extends RealmObject {
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
