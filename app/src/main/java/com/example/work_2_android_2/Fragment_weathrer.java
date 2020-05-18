package com.example.work_2_android_2;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.work_2_android_2.cordinates.City;
import com.example.work_2_android_2.models.WeatherAdapter;
import com.example.work_2_android_2.models.WeatherItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_weathrer extends Fragment {

    final static double TelAvivLatitude=32.109333;
    final static double TelAvivlongitude = 34.855499;
    public int telAvivFlag=0;


    //The latitude of Haifa, Israel is 32.794044, and the longitude is 34.989571. Haifa, Israel is
    final static double HaifaLatitude=32.794044;
    final static double Haifalongitude =34.989571;
    int HaifaFlag=0;

    //The latitude of Ashdod, Israel is 31.801447, and the longitude is 34.643497
    final static double AshdodLatitude=31.801447;
    final static double Ashdodlongitude = 34.643497;
    int AshdodfaFlag=0;

    //The latitude of Jerusalem, Israel is 31.771959, and the longitude is 35.217018.
    final static double JerusalemLatitude=31.771959;
    final static double Jerusalemlongitude = 35.217018;
    int JerusalemFlag=0;

    //The latitude of Rehovot, Israel is 31.894756, and the longitude is 34.809322
    final static double RehovotLatitude=31.894756;
    final static double Rehovotlongitude =  34.809322;
    int RehovotmFlag=0;

    //private ArrayList<String> mcordinatot;


    private RecyclerView mRecyclerView;
    private WeatherAdapter mWeatherAdapter;
    private ArrayList<WeatherItem> mWeatherleList;
    private RequestQueue mRequestQueue;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;

    public Fragment_weathrer() {
        // Required empty public constructor
    }

//    interface  onWetherFragmentListener{//זה בשביל לעביר מידע מפרגמנט לאקטיביטי ,צריך לייצר משתנה מסוג זה
    //      void onWeather(ArrayList<String> cordinatot );
    //}
    //onWetherFragmentListener callBack;




    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // ArrayList<String> cordinatot=getArguments().getStringArrayList("cordinatot");
        // mcordinatot=cordinatot;
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_fragment_weather,container,false);
        mRecyclerView=v.findViewById(R.id.recycler1);

        layoutManager=new LinearLayoutManager(mContext, HORIZONTAL,false );

        mRecyclerView.setLayoutManager(layoutManager);

        mWeatherleList=new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(mContext);

        parseJSON(TelAvivLatitude,TelAvivlongitude);
        parseJSON(HaifaLatitude,Haifalongitude);
        parseJSON(AshdodLatitude,Ashdodlongitude);
        parseJSON(JerusalemLatitude,Jerusalemlongitude);
        parseJSON(RehovotLatitude,Rehovotlongitude);




        return v;
    }

    private void parseJSON(double latitude, double longitude){
        String url="https://api.openweathermap.org/data/2.5/weather?id=2172797&APPID=5bda833ea98063658162aac5ac577075&units=metric";//&lat=32.08337216&lon=34.77137702

        // JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url + "&lat=" + TelAvivLatitude + "&lon=" + TelAvivlongitude, null,
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url + "&lat=" + latitude + "&lon=" +longitude, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("bdika","onResponse");
                        try {
                            JSONArray jsonArray = response.getJSONArray("weather");
                            Log.d("bdika","JSONArray");
                            JSONObject bestResult = jsonArray.getJSONObject(0);
                            String weatherState = bestResult.getString("description");
                            String icon=bestResult.getString("icon");
                            JSONObject mainW = response.getJSONObject("main");
                            Log.d("bdika","mainW");
                            double temp=mainW.getDouble("temp");
                            Log.d("bdika","mainW temp="+temp);
                            String city=response.getString("name");
                            Log.d("bdika","city="+city);

                            // String imageUrl="https://cdn3.iconfinder.com/data/icons/weather-ios-11-1/50/Sunny_Clear_Sun_Sunlight_Apple_Weather-512.png";
                            String imageUrl="https://openweathermap.org/img/w/"+icon+".png";

                            Log.d("bdika","inOnresponse");

                            mWeatherleList.add(new WeatherItem(temp,weatherState,city,imageUrl));
                            Log.d("bdika","in LOOP");


                            mWeatherAdapter=new WeatherAdapter(mContext,mWeatherleList);
                            mRecyclerView.setAdapter(mWeatherAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("bdika","onError");

            }
        });
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
       /* try {
            callBack=(onWetherFragmentListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("The activity must implement onRegisterFragmentListener interface");
        }*/
    }

   /* public static RecyclerViewFragmentSmall newInstance(ArrayList<String> cordinatot){ //(פאקטורי) זה בשביל לקלוט נתונים מאקטיביטי

        RecyclerViewFragmentSmall registerFragment=new RecyclerViewFragmentSmall();
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("cordinatot",cordinatot);
        registerFragment.setArguments(bundle);
        return registerFragment;
    }*/


    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}
