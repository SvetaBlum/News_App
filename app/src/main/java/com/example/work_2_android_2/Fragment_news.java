package com.example.work_2_android_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.work_2_android_2.Api.ApiClient;
import com.example.work_2_android_2.Api.ApiInterface;
import com.example.work_2_android_2.models.Article;
import com.example.work_2_android_2.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_news extends Fragment implements Adapter.OnItemClickListener {

    public static final String API_KEY = "72cc99ba837d4c73acbcf7ef2f38db05";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext;


    public Fragment_news() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_news, container, false);

        recyclerView = v.findViewById(R.id.recycler2);



        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Location.getCountry();
        Call<News> call;
        call = apiInterface.getNews(country, API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                articles = response.body().getArticle();
                adapter = new Adapter(articles, mContext, Fragment_news.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent webIntent = new Intent(Intent.ACTION_VIEW);

        webIntent.setData(Uri.parse(articles.get(position).getUrl()));
        startActivity(webIntent);

    }
}
