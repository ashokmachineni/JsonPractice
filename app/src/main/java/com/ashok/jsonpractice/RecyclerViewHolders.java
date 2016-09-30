package com.ashok.jsonpractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashok on 9/27/16.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    public TextView category;
    public ImageView poster;
    Context ctx;

    public RecyclerViewHolders(Context context, final View itemView) {
        super(itemView);
        ctx = context;
        itemView.setOnClickListener(this);
        title = (TextView)itemView.findViewById(R.id.song_title);
        //category = (TextView)itemView.findViewById(R.id.song_year);
        //poster = (TextView)itemView.findViewById(R.id.song_author);
        poster = (ImageView)itemView.findViewById(R.id.song_author);

       // Picasso.with(ctx).load(poster).into(poster);
    }

    @Override
    public void onClick(View view) {

    }
}
