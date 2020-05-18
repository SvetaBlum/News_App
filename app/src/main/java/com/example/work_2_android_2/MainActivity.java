package com.example.work_2_android_2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.work_2_android_2.models.Article;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String API_KEY="72cc99ba837d4c73acbcf7ef2f38db05";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles=new ArrayList<>();
    private Adapter adapter;
    private String TAG=MainActivity.class.getSimpleName();

    double latitude, longitude;
    final int LOCATION_PERMISSION_REQUEST = 1;
    FusedLocationProviderClient client;        //////////
    TextView tv;                              //////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     tv = findViewById(R.id.textt);           //////////////////
        if (Build.VERSION.SDK_INT >= 23) {
            int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST);
            } else getLocation();
        } else getLocation();


        //small fragment
        /*getSupportFragmentManager().beginTransaction().replace(R.id.container_small,
                new RecyclerViewFragmentSmall()).commit();*/



        getSupportFragmentManager().beginTransaction().replace(R.id.container_small,
                new Fragment_weathrer()).commit();


        //large fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container_large,
                new Fragment_news()).commit();

//cordinatot
        /*

         */


//        recyclerView = findViewById(R.id.recyclerView);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);
//    LoadJson();
//    }
// public  void LoadJson(){
//     ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
//
//     String country=Utils.getCountry();
//     Call<News>call;
//     call=apiInterface.getNews(country,API_KEY);
//
//     call.enqueue(new Callback<News>() {
//         @Override
//         public void onResponse(Call<News> call, Response<News> response) {
//             if(response.isSuccessful()&&response.body().getArticle()!=null){
//                if(!articles.isEmpty()){
//                    articles.clear();
//                }
//                articles=response.body().getArticle();
//                adapter=new Adapter(articles,MainActivity.this);
//                recyclerView.setAdapter(adapter);
//               // adapter.notifyDataSetChanged();
//
//             }
//          else {
//                 Toast.makeText(MainActivity.this, "No result", Toast.LENGTH_SHORT).show();
//             }
//         }
//
//         @Override
//         public void onFailure(Call<News> call, Throwable t) {
//
//         }
//     });
//
//    }


    }
    public void getLocation(){
        client = LocationServices.getFusedLocationProviderClient(this);
        LocationCallback callback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location lastLocation = locationResult.getLastLocation();

            }
        };
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(2000);


        Toast.makeText(this, "location on", Toast.LENGTH_SHORT).show();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"in request permission");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==LOCATION_PERMISSION_REQUEST) {
            if(grantResults[0]!=PackageManager.PERMISSION_GRANTED) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Attention!").setMessage("Ypu must give location permission to the app if you want to know the wheather")
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:"+getPackageName()));
                                startActivity(intent);
                            }
                        }).show();
            }

        }
    }



}



