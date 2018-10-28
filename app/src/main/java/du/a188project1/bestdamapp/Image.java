/* Image class with getters and setters
 */

package du.a188project1.bestdamapp;

import io.realm.RealmObject;

public class Image extends RealmObject {
    private byte[] image;

    // accessor method for image
    public byte[] getImage() {
        return image;
    }

    // mutator method for image
    public void setImage(byte[] image) {
        this.image = image;
    }
}
