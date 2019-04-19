package th.ac.ssru.kaewruamchor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.AuthDao;
import th.ac.ssru.kaewruamchor.Dao.GetFriendsLocationDao;
import th.ac.ssru.kaewruamchor.Dao.Student;
import th.ac.ssru.kaewruamchor.Net.Http;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    public GoogleMap mMap;
    Http http = new Http();
    final String BASE_URL = http.getUrl();
    ProgressDialog progressDialog;
    String photo = "";
    SSRU ssru;

    //API Service method
    private ApiService getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiService mInterfaceService = retrofit.create(ApiService.class);
        return mInterfaceService;
    }
    public static MapFragment newInstance() {


        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ssru = ((SSRU)getActivity().getApplicationContext());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.show();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);
        return rootView;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ApiService mApiService = this.getInterfaceService();
        Call<GetFriendsLocationDao> mService = mApiService.location(ssru.getFacultyId() ,ssru.getMajorId(),ssru.getLevelId(),ssru.getCatId(),ssru.getSec(),ssru.getYear());
        mService.enqueue(new Callback<GetFriendsLocationDao>() {
            @Override
            public void onResponse(Call<GetFriendsLocationDao> call, Response<GetFriendsLocationDao> response) {
                GetFriendsLocationDao getFriendsLocationDao = response.body();
                if(getFriendsLocationDao.getSuccess()) {
                    final List<Student> students = getFriendsLocationDao.getStudent();
                    for(int i = 0;i< students.size();i++) {
                        photo = students.get(i).getStuPhoto();
                        try {
                            LatLng latLng = new LatLng(Double.parseDouble(students.get(i).getStuLatitude()),
                                    Double.parseDouble(students.get(i).getStuLongtitude()));
                            mMap.addMarker(new MarkerOptions().snippet(students.get(i).getStuId() + "|||" + photo).position(latLng).title(students.get(i).getStuFname() + " "
                                    + students.get(i).getStuLname()));
                        } catch (NumberFormatException e) {

                        }
                         // mMap.addMarker(new MarkerOptions().snippet(students.get(i).getStuId()).position(latLng).title(students.get(i).getStuFname() + " "
                          //    + students.get(i).getStuLname()));
                        final int finalI = i;
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                Intent intent = new Intent(getContext(),FriendProfileActivity.class);
                                int position = marker.getSnippet().indexOf("|||");

                                String id = marker.getSnippet().substring(0,position);
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                        });

                        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {


                                                      @Override
                                                      public View getInfoWindow(Marker marker) {
                                                         return null;
                                                      }

                                                      @Override
                                                      public View getInfoContents(Marker marker) {
                                                          LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                                                          View view = layoutInflater.inflate(R.layout.map_info,null);
                                                          TextView tvFullname = (TextView) view.findViewById(R.id.tvFullname);
                                                          tvFullname.setText(marker.getTitle());
                                                          ImageView ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
                                                          ivProfile.setImageResource(R.drawable.profile);
                                                          int position = marker.getSnippet().indexOf("|||");

                                                          String img = marker.getSnippet().substring(position+3);
                                                          byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
                                                          Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                                          ivProfile.setImageBitmap(decodedByte);
                                                          if (img.equals("")) {
                                                              ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.profile));

                                                          }
                                                          return view;
                                                      }
                                                  }



                        );
                        progressDialog.dismiss();

                    }


                } else {
                    //server ok but something error
                    progressDialog.dismiss();


                }
            }
            @Override
            public void onFailure(Call<GetFriendsLocationDao> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();




            }
        });



        goToLocationZoom(ssru.getMyLat(),ssru.getMylng(),13);




    }



    private void goToLocationZoom(double lat, double lng, float zoom){
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);
    }




}
