package th.ac.ssru.kaewruamchor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.EducationListDao;
import th.ac.ssru.kaewruamchor.Dao.GetCertsDao;
import th.ac.ssru.kaewruamchor.Dao.GetStudentDao;
import th.ac.ssru.kaewruamchor.Net.Http;


public class ProfileFragment extends Fragment {

    Http http = new Http();
    final String BASE_URL = http.getUrl();
    Button btnLogout,btnEdit,btnChangeEdu;
    TextView tvFullname,tvFaculty,tvMajor,tvYear,tvSec,tvHouseNumber,tvMoo,tvAlley,tvStreet,tvProvince,tvAmphur,tvDistrict,tvZipcode,tvEmail,tvPhone,tvFacebook,tvLine,tvPrefix,tvEngFname,tvEngLname, tvJob,tvStatus,tvBirth,tvDegree,tvLevel;
    ImageView ivProfile;
    String json;
    String id,firstname,lastname,facultyId,faculty,major,majorId,levelId,catId,year,sec,photo,email,phone,facebook,line,prefix,engfirstname,englastname,job,status,birthday,degree,level,HouseNumber,Moo,Alley,Street,Province,Amphur,District,Zipcode;    ProgressDialog progressDialog;
    double lat,lng;
    SSRU ssru;
    String[] certsArray;

    Education educations[];
    android.support.v7.app.AlertDialog.Builder alertDialog;







    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ssru = ((SSRU)getActivity().getApplicationContext());

    }




    private void loadData() {
        ApiService mApiService = this.getInterfaceService();
        Call<GetStudentDao> mService = mApiService.getStudent(id);
        mService.enqueue(new Callback<GetStudentDao>() {
            @Override
            public void onResponse(Call<GetStudentDao> call, Response<GetStudentDao> response) {
                GetStudentDao getStudentDao = response.body();
                Gson gson = new Gson();
                json = gson.toJson(getStudentDao.getStudent());
                firstname = getStudentDao.getStudent().getStuFname();
                lastname = getStudentDao.getStudent().getStuLname();
                engfirstname = getStudentDao.getStudent().getStuEngfname();
                englastname = getStudentDao.getStudent().getStuEnglname();
                birthday = getStudentDao.getStudent().getStuBirthdayText();
                facultyId = getStudentDao.getStudent().getStuFacultyid();
                majorId = getStudentDao.getStudent().getStuMajorid();
                faculty = getStudentDao.getStudent().getFaThainame();
                levelId = getStudentDao.getStudent().getLevelId();
                catId = getStudentDao.getStudent().getCatId();
                year = getStudentDao.getStudent().getStuYear();
                sec = getStudentDao.getStudent().getStuSec();
                degree = getStudentDao.getStudent().getDegreeName();
                level = getStudentDao.getStudent().getLevelName();
                major = getStudentDao.getStudent().getMaThainame();
                email = getStudentDao.getStudent().getStuEmail();
                photo = getStudentDao.getStudent().getStuPhoto();
                phone = getStudentDao.getStudent().getStuPhonenumber();
                facebook = getStudentDao.getStudent().getStuFacebook();
                line = getStudentDao.getStudent().getStuLine();
                prefix = getStudentDao.getStudent().getStuPrefix();
                job = getStudentDao.getStudent().getStuJob();
                status = getStudentDao.getStudent().getStuStatusText();

                String lat2 = getStudentDao.getStudent().getStuLatitude();
                String lng2 = getStudentDao.getStudent().getStuLongtitude();


                if(lat2.equals("") && lng2.equals("")) {
                    lat = 13.776791;
                    lng = 100.509076;

                } else {
                    lat = Double.parseDouble(lat2);
                    lng = Double.parseDouble(lng2);
                }

                HouseNumber = getStudentDao.getStudent().getStuHousenumber();
                Moo = getStudentDao.getStudent().getStuMoo();
                Alley = getStudentDao.getStudent().getStuAlley();
                Street = getStudentDao.getStudent().getStuStreet();
                Province = getStudentDao.getStudent().getStuProvince();
                Amphur = getStudentDao.getStudent().getStuAmphur();
                District = getStudentDao.getStudent().getStuDistrict();
                Zipcode = getStudentDao.getStudent().getStuZipcode();

                ssru.setMyLat(lat);
                ssru.setMylng(lng);
                ssru.setFacultyId(facultyId);
                ssru.setMajorId(majorId);
                ssru.setYear(year);
                ssru.setCatId(catId);
                ssru.setSec(sec);
                ssru.setLevelId(levelId);


                tvFullname.setText(firstname + " " + lastname);
                tvFaculty.setText(faculty);
                tvMajor.setText(major);
                tvYear.setText(year);
                tvSec.setText("0"+sec);
                tvDegree.setText(degree);
                tvLevel.setText(level);
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
                tvBirth.setText(birthday);
                tvStatus.setText(status);


                if(photo.equals("")) {
                    ivProfile.setImageResource(R.drawable.profile);

                } else {
                    byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    ivProfile.setImageBitmap(decodedByte);
                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Call<GetStudentDao> call, Throwable t) {
                call.cancel();
                Log.e("Error",t.toString());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        //Init Dialog
        alertDialog = new android.support.v7.app.AlertDialog.Builder(getContext());
        alertDialog.setTitle("เลือกข้อมูลการศึกษา");
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("stuId");
        ssru.setStuId(id);
        tvFullname = (TextView) rootView.findViewById(R.id.tvFullname);
        tvFaculty = (TextView) rootView.findViewById(R.id.tvFaculty);
        tvMajor = (TextView) rootView.findViewById(R.id.tvMajor);
        tvYear = (TextView) rootView.findViewById(R.id.tvYear);
        tvSec = (TextView) rootView.findViewById(R.id.tvSec);
        tvDegree = (TextView) rootView.findViewById(R.id.tvDegree);
        tvLevel = (TextView) rootView.findViewById(R.id.tvLevel);
        tvHouseNumber = (TextView) rootView.findViewById(R.id.tvHouseNumber);
        tvMoo = (TextView) rootView.findViewById(R.id.tvMoo);
        tvAlley = (TextView) rootView.findViewById(R.id.tvAlley);
        tvStreet = (TextView) rootView.findViewById(R.id.tvStreet);
        tvProvince = (TextView) rootView.findViewById(R.id.tvProvince);
        tvAmphur = (TextView) rootView.findViewById(R.id.tvAmphur);
        tvDistrict = (TextView) rootView.findViewById(R.id.tvDistrict);
        tvZipcode = (TextView) rootView.findViewById(R.id.tvZipcode);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        tvPhone = (TextView) rootView.findViewById(R.id.tvPhone);
        tvFacebook = (TextView) rootView.findViewById(R.id.tvFacebook);
        tvLine = (TextView) rootView.findViewById(R.id.tvLine);
        tvPrefix = (TextView) rootView.findViewById(R.id.tvPrefix);
        tvEngFname = (TextView) rootView.findViewById(R.id.tvEngFname);
        tvEngLname = (TextView) rootView.findViewById(R.id.tvEngLname);
        tvJob = (TextView) rootView.findViewById(R.id.tvJob);
        tvStatus = (TextView) rootView.findViewById(R.id.tvStatus);
        tvBirth = (TextView) rootView.findViewById(R.id.tvBirth);
        ivProfile = (ImageView) rootView.findViewById(R.id.ivProfile);


        loadData();


        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UploadActivity.class);
                //intent.putExtra("image",photo);
                ssru.setImg(photo);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });






     btnChangeEdu = (Button)rootView.findViewById(R.id.btnChangeEdu);
     btnChangeEdu.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {



             ApiService mApiService = getInterfaceService();
             Call<EducationListDao> mService = mApiService.getEducation(id);
             mService.enqueue(new Callback<EducationListDao>() {
                 @Override
                 public void onResponse(Call<EducationListDao> call, Response<EducationListDao> response) {


                     final EducationListDao educationListDao = response.body();
                     int size = educationListDao.getEducationItemDao().size();
                     educations = new Education[size];
                     for (int i = 0 ; i < size ; i++) {
                         educations[i] = new Education(educationListDao.getEducationItemDao().get(i).getFaLogo(),
                                 educationListDao.getEducationItemDao().get(i).getFaThainame(),
                                 educationListDao.getEducationItemDao().get(i).getMaThainame(),
                                 educationListDao.getEducationItemDao().get(i).getLevelName());
                     }

                     final EducationAdapter educationAdapter = new EducationAdapter(getContext(), R.layout.item_edu, educations);
                     alertDialog.setAdapter(educationAdapter, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             id = educationListDao.getEducationItemDao().get(i).getStuId();
                             Intent intent = getActivity().getIntent();
                             intent.putExtra("stuId",id);


                             FragmentTransaction ft = getFragmentManager().beginTransaction();
                             ft.detach(ProfileFragment.this).attach(ProfileFragment.this).commit();

                         }
                     });
                     alertDialog.show();












                     /*
                     final EducationListDao educationListDao = response.body();
                     certsArray = new String[getCertsDao.getCerts().size()];
                     for (int i = 0; i < certsArray.length; i++) {
                         certsArray[i] = getCertsDao.getCerts().get(i).getMaThainame();
                     }
                     new AlertDialog.Builder(getContext())
                             .setTitle("เลือกข้อมูลการศึกษา")
                             .setItems(certsArray, new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {

                                     id = getCertsDao.getCerts().get(i).getStuId();
                                     Intent intent = getActivity().getIntent();
                                     intent.putExtra("stuId",id);


                                     FragmentTransaction ft = getFragmentManager().beginTransaction();
                                     ft.detach(ProfileFragment.this).attach(ProfileFragment.this).commit();


                                 }
                             }).create().show();*/
                 }

                 @Override
                 public void onFailure(Call<EducationListDao> call, Throwable t) {

                 }
             });



         }
     });




        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("ออกจากระบบ");
                alertDialog.setMessage("คุณต้องการออกจากระบบหรือไม่ ?");

                alertDialog.setPositiveButton("ใช่",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                //คลิกใช่ ออกจากโปรแกรม
                                getActivity().finish();
                            }
                        });

                alertDialog.setNegativeButton("ไม่",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int which) {
                                //คลิกไม่ cancel dialog
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });










        btnEdit = (Button) rootView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditActivity.class);
                //intent.putExtra("json",json);
                ssru.setJson(json);
                startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
       if(ssru.isShouldReload()) {
           loadData();
            ssru.setShouldReload(false);
       }
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

