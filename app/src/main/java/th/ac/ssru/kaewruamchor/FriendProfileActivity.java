package th.ac.ssru.kaewruamchor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.GetStudentDao;
import th.ac.ssru.kaewruamchor.Net.Http;


public class FriendProfileActivity extends AppCompatActivity {

    Http http = new Http();
    final String BASE_URL = http.getUrl();


    TextView tvFullname,tvDegree,tvLevel,tvFaculty,tvMajor,tvYear,tvSec,tvHouseNumber,tvMoo,tvAlley,tvStreet,tvProvince,tvAmphur,tvDistrict,tvZipcode,tvEmail,tvPhone,tvFacebook,tvLine,tvPrefix,tvEngFname,tvEngLname,tvJob,tvStatus;
    ImageView ivProfile;
    String json;
    String id,firstname,lastname,degree,level,faculty,major,year,sec,photo,email,phone,facebook,line,prefix,engfirstname,englastname,job,status,HouseNumber,Moo,Alley,Street,Province,Amphur,District,Zipcode;
    ProgressDialog progressDialog;
    SSRU ssru;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ssru = ((SSRU)getApplicationContext());
        setContentView(R.layout.activity_friend_profile);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.show();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvFullname = (TextView) findViewById(R.id.tvFullname);
        tvFaculty = (TextView) findViewById(R.id.tvFaculty);
        tvMajor = (TextView) findViewById(R.id.tvMajor);
        tvDegree = (TextView) findViewById(R.id.tvDegree);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvSec = (TextView) findViewById(R.id.tvSec);
        tvHouseNumber = (TextView) findViewById(R.id.tvHouseNumber);
        tvMoo = (TextView) findViewById(R.id.tvMoo);
        tvAlley = (TextView) findViewById(R.id.tvAlley);
        tvStreet = (TextView) findViewById(R.id.tvStreet);
        tvProvince = (TextView) findViewById(R.id.tvProvince);
        tvAmphur = (TextView) findViewById(R.id.tvAmphur);
        tvDistrict = (TextView) findViewById(R.id.tvDistrict);
        tvZipcode = (TextView) findViewById(R.id.tvZipcode);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvFacebook = (TextView) findViewById(R.id.tvFacebook);
        tvLine = (TextView) findViewById(R.id.tvLine);
        tvPrefix = (TextView) findViewById(R.id.tvPrefix);
        tvEngFname = (TextView) findViewById(R.id.tvEngFname);
        tvEngLname = (TextView) findViewById(R.id.tvEngLname);
        tvJob = (TextView) findViewById(R.id.tvJob);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        ApiService mApiService = this.getInterfaceService();
        Call<GetStudentDao> mService = mApiService.getStudent(id);
        mService.enqueue(new Callback<GetStudentDao>() {
            @Override
            public void onResponse(Call<GetStudentDao> call, Response<GetStudentDao> response) {
                GetStudentDao getStudentDao = response.body();

                firstname = getStudentDao.getStudent().getStuFname();
                lastname = getStudentDao.getStudent().getStuLname();
                engfirstname = getStudentDao.getStudent().getStuEngfname();
                englastname = getStudentDao.getStudent().getStuEnglname();
                faculty = getStudentDao.getStudent().getFaThainame();
                year = getStudentDao.getStudent().getStuYear();
                sec = getStudentDao.getStudent().getStuSec();
                degree = getStudentDao.getStudent().getDegreeName();
                level = getStudentDao.getStudent().getLevelName();
                //address = getStudentDao.getStudent().getStuAddress();
                major = getStudentDao.getStudent().getMaThainame();
                email = getStudentDao.getStudent().getStuEmail();
                photo = getStudentDao.getStudent().getStuPhoto();
                phone = getStudentDao.getStudent().getStuPhonenumber();
                facebook = getStudentDao.getStudent().getStuFacebook();
                line = getStudentDao.getStudent().getStuLine();
                prefix = getStudentDao.getStudent().getStuPrefix();
                job = getStudentDao.getStudent().getStuJob();
                status = getStudentDao.getStudent().getStuStatus();
                HouseNumber = getStudentDao.getStudent().getStuHousenumber();
                Moo = getStudentDao.getStudent().getStuMoo();
                Alley = getStudentDao.getStudent().getStuAlley();
                Street = getStudentDao.getStudent().getStuStreet();
                Province = getStudentDao.getStudent().getStuProvince();
                Amphur = getStudentDao.getStudent().getStuAmphur();
                District = getStudentDao.getStudent().getStuDistrict();
                Zipcode = getStudentDao.getStudent().getStuZipcode();


                tvFullname.setText(firstname + " " + lastname);
                tvFaculty.setText(faculty);
                tvMajor.setText(major);
                tvYear.setText(year);
                tvSec.setText("0"+sec);
                tvDegree.setText(degree);
                tvLevel.setText(level);
                //tvAddress.setText(address);
                tvHouseNumber.setText(HouseNumber);
                tvMoo.setText(Moo);
                tvAlley.setText(Alley);
                tvStreet.setText(Street);
                tvDistrict.setText(District);
                tvAmphur.setText(Amphur);
                tvProvince.setText(Province);
                tvZipcode.setText(Zipcode);
                tvEmail.setText(email);
                tvPhone.setText(phone);
                tvFacebook.setText(facebook);
                tvLine.setText(line);

                tvPrefix.setText(prefix);
                tvEngFname.setText(engfirstname);
                tvEngLname.setText(englastname);
                tvJob.setText(job);



                switch (status) {
                    case "0": tvStatus.setText("ถึงแก่กรรม");
                        break;
                    case "1": tvStatus.setText("มีชีวิต");
                        break;
                    default: tvStatus.setText("");
                        break;}

                byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
                ivProfile.setImageBitmap(decodedByte);
                progressDialog.dismiss();



            }
            @Override
            public void onFailure(Call<GetStudentDao> call, Throwable t) {
                call.cancel();
                Log.e("Error",t.toString());
                progressDialog.dismiss();
            }
        });


        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendProfileActivity.this,FriendLargeImageActivity.class);
                //intent.putExtra("image",photo);
                ssru.setImg(photo);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


    }







    //API Service method
    private ApiService getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiService mInterfaceService = retrofit.create(ApiService.class);
        return mInterfaceService;
    }


}
