package com.tutorials.hp.recyclerviewrssmulti_items.m_UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutorials.hp.recyclerviewrssmulti_items.R;
import com.tutorials.hp.recyclerviewrssmulti_items.m_DataObject.Article;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/11/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Article> articles;

    public MyAdapter(Context c, ArrayList<Article> articles) {
        this.c = c;
        this.articles = articles;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Article article=articles.get(position);

        holder.titleTxt.setText(article.getTitle());
        holder.descTxt.setText(article.getDescription());
        holder.dateTxt.setText(article.getDate());




    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
