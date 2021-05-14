package com.example.newsapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllNews {
    @NonNull
    @SerializedName("status")
    private String status;

    @NonNull
    @SerializedName("totalResults")
    private String totalNews;

    @NonNull
    @SerializedName("articles")
    private List<News> newsList;

    public AllNews(){}

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    @NonNull
    public String getTotalNews() {
        return totalNews;
    }

    public void setTotalNews(@NonNull String totalNews) {
        this.totalNews = totalNews;
    }

    @NonNull
    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(@NonNull List<News> newsList) {
        this.newsList = newsList;
    }
}
