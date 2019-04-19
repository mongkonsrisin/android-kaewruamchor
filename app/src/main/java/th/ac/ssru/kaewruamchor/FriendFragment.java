package th.ac.ssru.kaewruamchor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.GetFriendsLocationDao;
import th.ac.ssru.kaewruamchor.Dao.GetFriendsPhotoDao;
import th.ac.ssru.kaewruamchor.Dao.Student;
import th.ac.ssru.kaewruamchor.Net.Http;


public class FriendFragment extends Fragment {
    ListView listView;
    Http http = new Http();
    final String BASE_URL = http.getUrl();
    String[] studentsArray;
    List<Student> students;
    SSRU ssru;
    ProgressDialog progressDialog;

    public static FriendFragment newInstance() {
        FriendFragment fragment = new FriendFragment();
        return fragment;
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







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCancelable(false);
       // progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        listView = (ListView) rootView.findViewById(R.id.list);
        listView.setVisibility(View.INVISIBLE);
        ssru = (SSRU)getActivity().getApplicationContext();

        /*Intent intent = new Intent(getContext(),BubbleActivity.class);
        String txt =  intent.getStringExtra("backpressed");
        Toast.makeText(ssru, txt, Toast.LENGTH_SHORT).show();*/

        ApiService mApiService = getInterfaceService();
        Call<GetFriendsPhotoDao> mService = mApiService.photo(ssru.getFacultyId() ,ssru.getMajorId(),ssru.getLevelId(),ssru.getCatId(),ssru.getSec(),ssru.getYear());


        mService.enqueue(new Callback<GetFriendsPhotoDao>() {
            @Override
            public void onResponse(Call<GetFriendsPhotoDao> call, Response<GetFriendsPhotoDao> response) {
                GetFriendsPhotoDao getFriendsPhotoDao = response.body();
                if (getFriendsPhotoDao.getSuccess()) {
                    students = getFriendsPhotoDao.getStudent();
                    ssru.setStudents(students);
                    String value = String.valueOf(students.size());
                    Intent intent = new Intent(getContext(),BubbleActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();











                    studentsArray = new String[students.size()];
                    for (int i = 0; i < studentsArray.length; i++) {
                        studentsArray[i] = students.get(i).getStuId();
                    }

                    final ArrayList<String> list = new ArrayList<String>();
                    for (int i = 0; i < studentsArray.length; ++i) {
                        list.add(studentsArray[i]);
                    }
                    final ArrayAdapter adapter = new  ArrayAdapter(getContext(),
                            android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        }
                    });


                } else {
                    //server ok but something error


                }
            }

            @Override
            public void onFailure(Call<GetFriendsPhotoDao> call, Throwable t) {
                call.cancel();
            }
        });


        return rootView;
    }




    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
