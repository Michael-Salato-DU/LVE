/*
Tess Julien
An activity in which a user writes a review
 */
package du.a188project1.bestdamapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;
import io.realm.RealmList;

public class ReviewActivity extends AppCompatActivity {

    //declare variables
    private ImageView bandImage;
    private TextView bandName;
    private EditText reviewMessage;
    private EditText reviewRating;
    private Button saveButton;
    private User user;
    private Band band;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //set variables to views from layout
        bandImage = (ImageView) findViewById(R.id.band_image);
        bandName = (TextView) findViewById(R.id.band_name);
        reviewMessage = (EditText) findViewById(R.id.review_message);
        reviewRating = (EditText) findViewById(R.id.review_rating);
        saveButton = (Button) findViewById(R.id.save_button);
        realm = Realm.getDefaultInstance();

        //set band name based on data passed from the EventActivity
        String performer = (String) getIntent().getStringExtra("band");
        band = realm.where(Band.class).equalTo("name", performer).findFirst();
        bandName.setText(band.getName());

        //if view for the band image is clicked, open the camera
        bandImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        // save review to realm and add it to a band's list of reviews when the save button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!reviewMessage.getText().toString().matches("") && bandImage.getDrawable()!=null){
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            // create review object and fill its fields
                            Review review = new Review();
                            String currentEmail = (String) getIntent().getStringExtra("user email");
                            user = realm.where(User.class).equalTo("email", currentEmail).findFirst();
                            review.setUser(user);
                            review.setId(bandName.getText().toString()+currentEmail);
                            review.setBand(band);
                            BitmapDrawable image = (BitmapDrawable)bandImage.getDrawable();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageInByte = baos.toByteArray();
                            review.setImage(imageInByte);
                            review.setMessage(reviewMessage.getText().toString());
                            review.setRating(reviewRating.getInputType());


                            //save review to realm
                            realm.copyToRealm(review);
                            // get list currently saved to the band
                            RealmList<Review> updatedList = band.getReviews();
                            // add the new review to the band's list of reviews
                            updatedList.add(review);
                            // reset the band's list of reviews to the new list
                            band.setReviews(updatedList);
                            // update the band object in realm
                            realm.copyToRealmOrUpdate(band);
                            finish();

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");
            bandImage.setImageBitmap(imageBitmap);

        }
    }
}
