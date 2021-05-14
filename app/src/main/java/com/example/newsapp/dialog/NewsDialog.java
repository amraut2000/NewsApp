package com.example.newsapp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.model.News;

public class NewsDialog extends DialogFragment {
    private ImageView imageView;
    private TextView title;
    private TextView description;

    News news;
    public NewsDialog(){}
    public NewsDialog(News news){
        this.news=news;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_lyt,null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Go To Website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri webpage = Uri.parse(news.getUrl());
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                        startActivity(webIntent);
                    }
                });
        imageView=view.findViewById(R.id.newsImageBig);
        title=view.findViewById(R.id.newsTitleBig);
        description=view.findViewById(R.id.newsDescriptionBig);
        title.setText(news.getTitle());
        description.setText(news.getDescription());
        Glide.with(getContext()).load(news.getUrlToImage()).into(imageView);
        return builder.create();
    }
}
