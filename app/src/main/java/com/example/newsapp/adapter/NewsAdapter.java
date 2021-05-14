package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private OnItemClickListener listener;
    private List<News> newsList=new ArrayList<>();
    private Context context;

    public NewsAdapter(){ }
    public NewsAdapter(List<News> newsList, Context context){
        this.newsList=newsList;
        this.context=context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News currentNews=newsList.get(position);
        holder.title.setText(currentNews.getTitle());
        holder.source.setText(currentNews.getSource().getSourceName());
        holder.publishedDate.setText(currentNews.getPublishedAt());
        Glide.with(context).load(currentNews.getUrlToImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView newsImage;
        private TextView title;
        private TextView source;
        private TextView publishedDate;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.newsTitle);
            source=itemView.findViewById(R.id.newsSource);
            publishedDate=itemView.findViewById(R.id.newsPublishDate);
            newsImage=itemView.findViewById(R.id.newsImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(newsList.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(News news);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
