package com.example.newsapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class News {
    @NonNull
    @SerializedName("source")
    private Source source;

    @NonNull
    @SerializedName("title")
    private String title;

    @NonNull
    @SerializedName("description")
    private String description;

    @NonNull
    @SerializedName("url")
    private String url;

    @NonNull
    @SerializedName("urlToImage")
    private String urlToImage;

    @NonNull
    @SerializedName("publishedAt")
    private Date publishedAt;


    public void setSource(Source source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @NonNull
    public Source getSource() {
        return source;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getUrlToImage() {
        return urlToImage;
    }

    @NonNull
    public String getPublishedAt() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(publishedAt);
    }

    //For inner JSON object source
    public class Source {
        @NonNull
        @SerializedName("name")
        private String sourceName;

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        @NonNull
        public String getSourceName() {
            return sourceName;
        }
    }
}
