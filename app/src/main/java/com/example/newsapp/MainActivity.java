package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.api.NewsInterface;
import com.example.newsapp.api.RetrofitClient;
import com.example.newsapp.dialog.NewsDialog;
import com.example.newsapp.model.AllNews;
import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://newsapi.org/";
    //public static final String API_KEY = "9667029069b74dc78d15249a5d01aceb";
    public static final String API_KEY = "abe437d0b2c5404c8c57abc16da5fdb3";

    private NewsAdapter newsAdapter;
    private NewsInterface newsInterface;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    public List<News> newsList = new ArrayList<>();

    private CardView generalCardView, businessCardView, entertainmentCardView, sportsCardView, technologyCardView, healthCardView, scienceCardView;
    private ImageView searchImageView;
    private EditText searchEditText;
    private TextView newsAppTitleTextView, searchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        retrofit = RetrofitClient.getRetrofit(BASE_URL);
        newsInterface = retrofit.create(NewsInterface.class);

        clickListeners();
        getNews("general");
    }

    //To bind all the news
    private void bindView() {
        recyclerView = findViewById(R.id.recyclerview);
        generalCardView = findViewById(R.id.cardview_general);
        businessCardView = findViewById(R.id.cardview_business);
        entertainmentCardView = findViewById(R.id.cardview_entertainment);
        sportsCardView = findViewById(R.id.cardview_sport);
        technologyCardView = findViewById(R.id.cardview_technology);
        healthCardView = findViewById(R.id.cardview_health);
        scienceCardView = findViewById(R.id.cardview_science);
        searchImageView = findViewById(R.id.search_news_image);
        searchEditText = findViewById(R.id.search_news_editText);
        newsAppTitleTextView = findViewById(R.id.news_app_title);
        searchTextView = findViewById(R.id.search);
    }

    //for click listener
    private void clickListeners() {
        generalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("general");
            }
        });

        businessCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("business");
            }
        });

        entertainmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("entertainment");
            }
        });

        sportsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("sports");
            }
        });

        healthCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("health");
            }
        });

        technologyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("technology");
            }
        });

        scienceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews("science");
            }
        });

        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsAppTitleTextView.setVisibility(View.GONE);
                searchImageView.setVisibility(View.GONE);
                searchEditText.setVisibility(View.VISIBLE);
                searchTextView.setVisibility(View.VISIBLE);
            }
        });

        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchEditText.getText().toString();
                searchEditText.setText("");
                newsAppTitleTextView.setVisibility(View.VISIBLE);
                searchImageView.setVisibility(View.VISIBLE);
                searchEditText.setVisibility(View.GONE);
                searchTextView.setVisibility(View.GONE);
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(searchEditText.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                getSearchedNews(search);
            }
        });

    }

    //To fetch the news of particular category
    private void getNews(String category) {
        Call<AllNews> call = newsInterface.getAllNews("in", category, API_KEY);

        call.enqueue(new Callback<AllNews>() {
            @Override
            public void onResponse(Call<AllNews> call, Response<AllNews> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Try to make it specific!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AllNews allNews = response.body();
                newsList = allNews.getNewsList();
                //Log.d("MainActivity", "onResponse: "+newsList.get(0).getTitle());
                newsAdapter = new NewsAdapter(newsList, MainActivity.this);
                recyclerView.setAdapter(newsAdapter);

                newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(News news) {
                        // Toast.makeText(MainActivity.this, "news clicked!", Toast.LENGTH_SHORT).show();
                        openDialog(news);
                    }
                });
            }

            @Override
            public void onFailure(Call<AllNews> call, Throwable t) {
                Toast.makeText(MainActivity.this, "You are offline!", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    //To fetch the searched news
    private void getSearchedNews(String keyword) {
        Call<AllNews> call = newsInterface.getSearchedNews(keyword, API_KEY);

        call.enqueue(new Callback<AllNews>() {
            @Override
            public void onResponse(Call<AllNews> call, Response<AllNews> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Try to make it specific!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AllNews allNews = response.body();
                newsList = allNews.getNewsList();
                //Log.d("MainActivity", "onResponse: "+newsList.get(0).getTitle());
                newsAdapter = new NewsAdapter(newsList, MainActivity.this);
                recyclerView.setAdapter(newsAdapter);

                newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(News news) {
                        // Toast.makeText(MainActivity.this, "news clicked!", Toast.LENGTH_SHORT).show();
                        openDialog(news);
                    }
                });
            }

            @Override
            public void onFailure(Call<AllNews> call, Throwable t) {
                Toast.makeText(MainActivity.this, "You are offline!", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    //To open the dialog box
    private void openDialog(News news) {
        NewsDialog newsDialog = new NewsDialog(news);
        newsDialog.show(getSupportFragmentManager(), "News Dialog");
    }


}