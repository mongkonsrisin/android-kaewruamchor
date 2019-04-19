package th.ac.ssru.kaewruamchor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.ssru.kaewruamchor.Dao.UpdateDao;
import th.ac.ssru.kaewruamchor.Net.Http;

public class MarkerActivity extends AppCompatActivity implements OnMapReadyCallback {
    public GoogleMap mMap;
    double lat,lng;
    Button btnSave,btnSearch;
    EditText etSearch;
    String searchQuery;
    String id;
    SSRU ssru;

    Http http = new Http();
    final String BASE_URL = http.getUrl();
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
        setContentView(R.layout.activity_marker);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ssru = ((SSRU)getApplicationContext());

        etSearch = (EditText)findViewById(R.id.etSearch);


        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchQuery = etSearch.getText().toString();
                Geocoder geocoder = new Geocoder(MarkerActivity.this);
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocationName(searchQuery, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = null;
                String locality = "";
                try {
                    address = list.get(0);


                    locality = address.getLocality();
                  lat = address.getLatitude();
                     lng = address.getLongitude();


                    mMap.clear();
                    LatLng ll = new LatLng(lat, lng);
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
                    mMap.moveCamera(update);


                    MarkerOptions options = new MarkerOptions()
                            .title(locality)
                            .draggable(true)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                            .position(new LatLng(lat,lng))
                            .snippet(" I am Here");


                mMap.addMarker(options);

                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(MarkerActivity.this, "ไม่มีสถานที่นี้", Toast.LENGTH_SHORT).show();

                }
            }
        });














        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService mApiService = getInterfaceService();
                Call<UpdateDao> mService = mApiService.updateLocation(id,lat,lng);
                mService.enqueue(new Callback<UpdateDao>() {
                    @Override
                    public void onResponse(Call<UpdateDao> call, Response<UpdateDao> response) {
                        UpdateDao updateDao = response.body();
                        if(updateDao.getSuccess()) {
                            //Update OK
                            finish();
                        } else {
                            //Update not OK

                        }

                    }

                    @Override
                    public void onFailure(Call<UpdateDao> call, Throwable t) {


                    }
                });
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(Double.parseDouble(String.valueOf(ssru.getMyLat())),
               Double.parseDouble(String.valueOf(ssru.getMylng())));
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));
                lat = point.latitude;
                lng = point.longitude;
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
