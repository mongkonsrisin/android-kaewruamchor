package th.ac.ssru.kaewruamchor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;


public class LargeImageActivity extends AppCompatActivity {

    ImageView ivProfile;
    Button btnChageProfile;
    Bitmap captureImage;
    String id,photo;
    SSRU ssru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);
        ssru = ((SSRU)getApplicationContext());

        Intent intent = getIntent();
       // photo = intent.getStringExtra("image");
        photo = ssru.getImg();
        id = intent.getStringExtra("id");
        ivProfile = (ImageView) findViewById(R.id.ivProfile);

        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ivProfile.setImageBitmap(decodedByte);

        btnChageProfile = (Button) findViewById(R.id.btnChangeProfile);
        btnChageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LargeImageActivity.this,UploadActivity.class);
               intent.putExtra("id",id);
               startActivity(intent);
            }
        });


    }


}
