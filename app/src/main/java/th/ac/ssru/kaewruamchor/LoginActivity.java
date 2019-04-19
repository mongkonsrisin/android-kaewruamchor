package th.ac.ssru.kaewruamchor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.AuthDao;
import th.ac.ssru.kaewruamchor.Net.Http;

public class LoginActivity extends AppCompatActivity  {

    Http http = new Http();
    final String BASE_URL = http.getUrl();
    final String PREFNAME = "SamplePreferences";
    final String FNAME = "Fname";
    final String LNAME = "Lname";
    final String BIRTHDAY = "Birthday";

    TextView manual;
    EditText etFname;
    EditText etLname;
    EditText etBirthday;
    Button btnLogin;
    ImageView info;
    ProgressDialog progressDialog;
    AlertDialog.Builder alertDialogBuilder;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private ApiService getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiService mInterfaceService = retrofit.create(ApiService.class);
        return mInterfaceService;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        editor = sp.edit();


       /* if(sp.getString("ISLOGIN","").equals("")) {
            String id = sp.getString("ID","");
            Intent intent = new Intent(LoginActivity.this,ContentActivity.class);
            intent.putExtra("stuId",id);
            startActivity(intent);
        }*/


        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.setTitle("แจ้งเตือน")
                        .setMessage("เข้าสู่ระบบ รหัสผ่านคือวัน/เดือน/ปีเกิด เช่น 05/01/2527")
                        .setCancelable(true)
                        .show();

                progressDialog.hide();
            }
        });

        manual = (TextView)findViewById(R.id.manual);
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://reg.ssru.ac.th/ssru80th/manual_android.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        etFname.setMaxLines(1);
        etLname.setMaxLines(1);
        etBirthday.setMaxLines(1);

        etFname.setText(sp.getString(FNAME, ""));
        etFname.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(FNAME, s.toString());
                editor.commit();
            }
        });

        etLname.setText(sp.getString(LNAME, ""));
        etLname.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(LNAME, s.toString());
                editor.commit();
            }
        });

        etBirthday.setText(sp.getString(BIRTHDAY, ""));
        etBirthday.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(BIRTHDAY, s.toString());
                editor.commit();
            }
        });




        btnLogin = (Button) findViewById(R.id.btnLogin);
        alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("กำลังโหลด...");



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = etFname.getText().toString().trim();
                String lname = etLname.getText().toString().trim();
                String birthday = etBirthday.getText().toString().trim();

                doLogin(fname,lname,birthday);

            }




            //Login method
            private void doLogin(String fname, String lname,String birthday)  {
                progressDialog.show();
                ApiService mApiService = getInterfaceService();
                Call<AuthDao> mService = mApiService.auth(fname, lname, birthday);
                mService.enqueue(new Callback<AuthDao>() {
                    @Override
                    public void onResponse(Call<AuthDao> call, Response<AuthDao> response) {
                        AuthDao authDao = response.body();
                        if(authDao.getSuccess()) {
                            //Login OK
                            String id = authDao.getUser();
                           // editor.putBoolean("ISLOGIN",true);
                            //editor.putString("ID",id);
                            Intent intent = new Intent(LoginActivity.this,ContentActivity.class);
                            intent.putExtra("stuId",id);
                            startActivity(intent);
                            progressDialog.hide();
                            finish();

                        } else {
                            //Login error
                            alertDialogBuilder.setTitle("แจ้งเตือน")
                                    .setMessage("ข้อมูลผิดพลาด กรุณาลองอีกครั้ง \nเข้าสู่ระบบ รหัสผ่านคือ วัน/เดือน/ปีเกิด เช่น 05/01/2527")
                                    .setCancelable(true)
                                    .setNegativeButton("ลองใหม่", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setPositiveButton("สมัครสมาชิก", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri uri = Uri.parse("https://reg.ssru.ac.th/ssru80th/register.php");
                                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();

                            progressDialog.hide();

                        }
                    }
                    @Override
                    public void onFailure(Call<AuthDao> call, Throwable t) {
                        call.cancel();
                        Log.e("Error",t.toString());
                        //Server error or network error
                        progressDialog.hide();

                    }
                });
            }
        });
    }

}
