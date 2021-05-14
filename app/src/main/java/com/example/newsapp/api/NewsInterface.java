package com.example.newsapp.api;

import com.example.newsapp.model.AllNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {


    @GET("v2/top-headlines")
    Call<AllNews> getAllNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);

    @GET("v2/everything")
    Call<AllNews> getSearchedNews(@Query("q") String query, @Query("apiKey") String apiKey);

}
