package com.example.work_2_android_2.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_2_android_2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolde> {
    private Context mContext;
    private ArrayList<WeatherItem> mWeatherList;

    public WeatherAdapter(Context mContext, ArrayList<WeatherItem> mWeatherList) {
        this.mContext = mContext;
        this.mWeatherList = mWeatherList;
    }





    @NonNull
    @Override
    public WeatherViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


      View v= LayoutInflater.from(mContext).inflate(R.layout.item_weather_card_layout,parent,false);
        return new WeatherViewHolde(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolde holder, int position) {

        /*      ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        int likeCount = currentItem.getLikeCount();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: " + likeCount);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);*/

        WeatherItem currentItem=mWeatherList.get(position);

        double mtempture=currentItem.getMtempture();
        String mweatherState=currentItem.getMweatherState();
        String mcity=currentItem.getMcity();
        String mImage=currentItem.getmImage();

          holder.mStateTv.setText(mweatherState);
          holder.mCityTv.setText(mcity);
          holder.mTempTv.setText(mtempture+" C");

      //  Picasso.get().load(mImage).fit().centerInside().into(holder.mWeatherImagev);
        Picasso.get().load(mImage).fit().centerInside().into(holder.mWeatherImagev);
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

public class WeatherViewHolde extends RecyclerView.ViewHolder{
   public ImageView mWeatherImagev;
    public TextView mCityTv;
    public TextView mTempTv;
    public TextView mStateTv;


    public WeatherViewHolde(@NonNull View itemView) {
        super(itemView);
        mCityTv=itemView.findViewById(R.id.city_tv);
        mWeatherImagev=itemView.findViewById(R.id.state_im);
        mTempTv=itemView.findViewById(R.id.temp_tv);
        mStateTv=itemView.findViewById(R.id.state_weather_tv);



    }
  }
}
