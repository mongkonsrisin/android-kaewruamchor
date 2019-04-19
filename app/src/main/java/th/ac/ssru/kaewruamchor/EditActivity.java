package th.ac.ssru.kaewruamchor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.AmphursDao;
import th.ac.ssru.kaewruamchor.Dao.DistrictsDao;
import th.ac.ssru.kaewruamchor.Dao.ProvincesDao;
import th.ac.ssru.kaewruamchor.Dao.Student;
import th.ac.ssru.kaewruamchor.Dao.UpdateDao;
import th.ac.ssru.kaewruamchor.Net.Http;

public class EditActivity extends AppCompatActivity {

    //Define your Web Service URL
    Http http = new Http();
    final String BASE_URL = http.getUrl();
    SSRU ssru;
    String[] province_list ;
    String[] province_id;
    ArrayAdapter<String> spinner_province;
    String[] amphur_list ;
    String[] amphur_id;
    ArrayAdapter<String> spinner_amphur;
    String[] district_list ;
    String[] district_id;
    ArrayAdapter<String> spinner_district;
    EditText etPrefix,etEngFname,etEngLname,etEmail,etPhone,etFacebook,etLine,etHouseNumber,etAlley,etMoo,etStreet,etDistrict,etAmphur,etProvince,etZipcode,etJob;
    Button btnSave,btnMarker;
    ProgressDialog progressDialog;
    Student student;
    EditText etStatus;
    String[] statusesArray = {"มีชีวิต","ถึงแก่กรรม"};
    String[] jobsArray = {"ไม่ระบุ","รับราชการ","รัฐวิสาหกิจ","หน่วยงานเอกชน","ค้าขาย","เกษตรกร","ไม่มีเงินได้","พนักงาน/ลูกจ้างหน่วยราชการ","อื่น ๆ"};
    //API Service method
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
        setContentView(R.layout.activity_edit);

        ssru = ((SSRU)getApplicationContext());

        progressDialog = new ProgressDialog(EditActivity.this);
        progressDialog.setMessage("กำลังอัพเดตข้อมูล...");

        Gson gson = new Gson();
        //student = gson.fromJson(getIntent().getStringExtra("json"),Student.class);
        student = gson.fromJson(ssru.getJson(),Student.class);
        etPrefix = (EditText) findViewById(R.id.etPrefix);
        etEngFname = (EditText)findViewById(R.id.etEngFname);
        etEngLname = (EditText)findViewById(R.id.etEngLname);
        etJob = (EditText) findViewById(R.id.etJob);
        etStatus = (EditText) findViewById(R.id.etStatus);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etFacebook = (EditText)findViewById(R.id.etFacebook);
        etLine = (EditText)findViewById(R.id.etLine);
        etHouseNumber = (EditText) findViewById(R.id.etHouseNumber);
        etMoo = (EditText)findViewById(R.id.etMoo);
        etAlley = (EditText)findViewById(R.id.etAlley);
        etStreet = (EditText)findViewById(R.id.etStreet);
        etDistrict = (EditText)findViewById(R.id.etDistrict);
        etAmphur = (EditText)findViewById(R.id.etAmphur);
        etProvince = (EditText)findViewById(R.id.etProvince);
        etZipcode = (EditText)findViewById(R.id.etZipcode);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnMarker = (Button) findViewById(R.id.btnMarker);
        etJob.setFocusable(false);
        etProvince.setFocusable(false);
        etAmphur.setFocusable(false);
        etDistrict.setFocusable(false);
        etZipcode.setFocusable(false);
        etStatus.setFocusable(false);

        etPrefix.setText(student.getStuPrefix());
        etEngFname.setText(student.getStuEngfname());
        etEngLname.setText(student.getStuEnglname());

        etStatus.setText(student.getStuStatusText());

        etStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("สถานะ")
                        .setItems(statusesArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                etStatus.setText(statusesArray[i]);
                            }
                        }).create().show();
            }
        });




        etJob.setText(student.getStuJob());
        etEmail.setText(student.getStuEmail());
        etPhone.setText(student.getStuPhonenumber());
        etFacebook.setText(student.getStuFacebook());
        etLine.setText(student.getStuLine());
        etHouseNumber.setText(student.getStuHousenumber());
        etMoo.setText(student.getStuMoo());
        etAlley.setText(student.getStuAlley());
        etStreet.setText(student.getStuStreet());
        etDistrict.setText(student.getStuDistrict());
        etAmphur.setText(student.getStuAmphur());
        etProvince.setText(student.getStuProvince());
        etZipcode.setText(student.getStuZipcode());


        //Get Provinces
        ApiService mApiService = getInterfaceService();
        Call<ProvincesDao> mService = mApiService.province();

        mService.enqueue(new Callback<ProvincesDao>() {
            @Override
            public void onResponse(Call<ProvincesDao> call, Response<ProvincesDao> response) {
                ProvincesDao provincesDao = response.body();
                if (provincesDao.getSuccess()) {



                    province_list = new String[provincesDao.getMsg().size()];
                    province_id = new String[provincesDao.getMsg().size()];
                    for (int i = 0; i < province_list.length; i++) {
                        province_list[i] = provincesDao.getMsg().get(i).getProThainame();
                    }
                    for (int i = 0; i < province_id.length; i++) {
                        province_id[i] = provincesDao.getMsg().get(i).getProGeocode();
                    }
                    spinner_province = new  ArrayAdapter<String>(EditActivity.this,android.R.layout.simple_spinner_dropdown_item, province_list);





                } else {
                    //server ok but something error


                }
            }

            @Override
            public void onFailure(Call<ProvincesDao> call, Throwable t) {
                call.cancel();
            }
        });






        etJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("อาชีพ")
                        .setItems(jobsArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                etJob.setText(jobsArray[i]);
                            }
                        }).create().show();
            }
        });





        etProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("จังหวัด")
                        .setAdapter(spinner_province, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                etProvince.setText(province_list[which].toString());
                                etAmphur.setText("");
                                etDistrict.setText("");
                                etZipcode.setText("");
                                getAmphur(province_id[which].toString());
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });


        etAmphur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("เขต/อำเภอ")
                        .setAdapter(spinner_amphur, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                etAmphur.setText(amphur_list[which].toString());
                                etDistrict.setText("");
                                etZipcode.setText("");
                                getDistrict(amphur_id[which].toString());
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });


        etDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("แขวง/ตำบล")
                        .setAdapter(spinner_district, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                etDistrict.setText(district_list[which].toString());
                                etZipcode.setText(district_id[which].toString());
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });
        btnMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this,MarkerActivity.class);
                intent.putExtra("id",student.getStuId());
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sentStatus = "";
               switch (etStatus.getText().toString()) {
                    case "ถึงแก่กรรม" : sentStatus = "0";
                    break;
                    case "มีชีวิต" : sentStatus = "1";
                        break;
                    default : sentStatus = "";
                        break;
                }


                progressDialog.show();
                ApiService mApiService = getInterfaceService();
                Call<UpdateDao> mService = mApiService.update(
                        student.getStuId(),
                        etPrefix.getText().toString(),
                        etEngFname.getText().toString(),
                        etEngLname.getText().toString(),
                       etJob.getText().toString(),
                       sentStatus,
                        etEmail.getText().toString(),
                        etPhone.getText().toString(),
                        etFacebook.getText().toString(),
                        etLine.getText().toString(),
                        etHouseNumber.getText().toString(),
                        etMoo.getText().toString(),
                        etAlley.getText().toString(),
                        etStreet.getText().toString(),
                        etDistrict.getText().toString(),
                        etAmphur.getText().toString(),
                        etProvince.getText().toString(),
                        etZipcode.getText().toString());
                mService.enqueue(new Callback<UpdateDao>() {
                    @Override
                    public void onResponse(Call<UpdateDao> call, Response<UpdateDao> response) {
                        UpdateDao updateDao = response.body();
                        if(updateDao.getSuccess()) {

                            ssru.setShouldReload(true);
                            //Update OK
                            finish();
                            progressDialog.dismiss();
                        } else {
                            //Update not OK
                            progressDialog.dismiss();

                        }

                    }

                    @Override
                    public void onFailure(Call<UpdateDao> call, Throwable t) {
                        progressDialog.dismiss();

                        Toast.makeText(ssru, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });






            }
        });
    }

    private void getDistrict(String id) {
        ApiService mApiService = getInterfaceService();
        Call<DistrictsDao> mService = mApiService.district(id);

        mService.enqueue(new Callback<DistrictsDao>() {
            @Override
            public void onResponse(Call<DistrictsDao> call, Response<DistrictsDao> response) {
                DistrictsDao districtsDao = response.body();
                if (districtsDao .getSuccess()) {



                    district_list = new String[districtsDao .getMsg().size()];
                    district_id = new String[districtsDao .getMsg().size()];
                    for (int i = 0; i < district_list.length; i++) {
                        district_list[i] = districtsDao .getMsg().get(i).getDisThainame();
                    }
                    for (int i = 0; i < district_id.length; i++) {
                        district_id[i] = districtsDao .getMsg().get(i).getDisZipcode();
                    }
                    spinner_district = new  ArrayAdapter<String>(EditActivity.this,android.R.layout.simple_spinner_dropdown_item, district_list);





                } else {
                    //server ok but something error


                }
            }

            @Override
            public void onFailure(Call<DistrictsDao> call, Throwable t) {
                call.cancel();
            }
        });

    }


    private void getAmphur(String id) {

        ApiService mApiService = getInterfaceService();
        Call<AmphursDao> mService = mApiService.amphur(id);

        mService.enqueue(new Callback<AmphursDao>() {
            @Override
            public void onResponse(Call<AmphursDao> call, Response<AmphursDao> response) {
                AmphursDao amphursDao = response.body();
                if (amphursDao.getSuccess()) {



                    amphur_list = new String[amphursDao.getMsg().size()];
                    amphur_id = new String[amphursDao.getMsg().size()];
                    for (int i = 0; i < amphur_list.length; i++) {
                        amphur_list[i] = amphursDao.getMsg().get(i).getAmThainame();
                    }
                    for (int i = 0; i < amphur_id.length; i++) {
                        amphur_id[i] = amphursDao.getMsg().get(i).getAmId();
                    }
                    spinner_amphur = new  ArrayAdapter<String>(EditActivity.this,android.R.layout.simple_spinner_dropdown_item, amphur_list);





                } else {
                    //server ok but something error


                }
            }

            @Override
            public void onFailure(Call<AmphursDao> call, Throwable t) {
                call.cancel();
            }
        });

    }


    /**
     * Created by SSRU on 10/1/2561.
     */

    public static class Education {
        private String mLogo;
        private String mFaculty;
        private String mMajor;
        private String mDegree;


        public Education(String logo, String faculty, String major, String degree) {
            mLogo = logo;
            mFaculty = faculty;
            mMajor = major;
            mDegree = degree;
        }

        public String getLogo() {
            return mLogo;
        }

        public void setLogo(String mLogo) {
            this.mLogo = mLogo;
        }

        public String getFaculty() {
            return mFaculty;
        }

        public void setmFaculty(String mFaculty) {
            this.mFaculty = mFaculty;
        }

        public String getMajor() {
            return mMajor;
        }

        public void setMajor(String mMajor) {
            this.mMajor = mMajor;
        }

        public String getDegree() {
            return mDegree;
        }

        public void setDegree(String mDegree) {
            this.mDegree = mDegree;
        }
    }
}
