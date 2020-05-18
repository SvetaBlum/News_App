package com.example.work_2_android_2.Api;


import com.example.work_2_android_2.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
@GET("top-headlines")
    Call<News>getNews(
        @Query("country") String country,
        @Query("apiKey") String apiKey
);
}
