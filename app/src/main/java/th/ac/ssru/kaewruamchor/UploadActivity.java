package th.ac.ssru.kaewruamchor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.UpdateDao;
import th.ac.ssru.kaewruamchor.Net.Http;

public class UploadActivity extends AppCompatActivity {
    Button btnUpload, btnChoose;
    ImageView ivProfile;
    String stuId,id,photo;
    SSRU ssru;

    //Define your Web Service URL
    Http http = new Http();
    final String BASE_URL = http.getUrl();




    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    //API Service method
    private ApiService getInterfaceService() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final ApiService mInterfaceService = retrofit.create(ApiService.class);
        return mInterfaceService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ssru = ((SSRU)getApplicationContext());

        Intent intent = getIntent();
        photo = ssru.getImg();
        id = intent.getStringExtra("id");
        ivProfile = (ImageView) findViewById(R.id.ivProfile);

        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ivProfile.setImageBitmap(decodedByte);

        stuId = intent.getStringExtra("id");
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);



        btnUpload.setEnabled(false);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("ครอปรูป")
                        .setFixAspectRatio(true)
                        .setAspectRatio(1, 1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setRequestedSize(800, 800)
                        .start(UploadActivity.this);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            final CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ivProfile.setImageURI(result.getUri());
                btnUpload.setEnabled(true);
                btnUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                String filePath = getRealPathFromURIPath(result.getUri(), UploadActivity.this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), mFile);
                RequestBody id = RequestBody.create(MediaType.parse("text/plain"), stuId);
                ApiService mApiService = getInterfaceService();
                Call<String> req = mApiService.upload(fileToUpload,id);
                req.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        ssru.setShouldReload(true);
                        finish();
                    }



                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }



                });
                    }
                });

            }



        }

    }
}
