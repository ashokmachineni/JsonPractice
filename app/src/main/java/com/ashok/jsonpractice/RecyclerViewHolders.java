package com.ashok.jsonpractice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ashok on 9/27/16.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView title;

    //private TextView category;
    private ImageView image;
    private ItemObject currentObject;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView)itemView.findViewById(R.id.song_title);
        //category = (TextView)itemView.findViewById(R.id.song_year);
        //poster = (TextView)itemView.findViewById(R.id.song_author);
        image = (ImageView) itemView.findViewById(R.id.song_author);

        // Picasso.with(ctx).load(poster).into(poster);
    }

    public void bind(ItemObject bindObject) {
        currentObject = bindObject;
        title.setText(String.format(" %s", bindObject.getTitle()));
        Picasso.with(title.getContext().getApplicationContext()).load(bindObject.getImage()).into(image);
    }

    @Override
    public void onClick(View view) {
        if (currentObject != null) {
            itemView.getContext().startActivity(buildActivityIntent());
        }
    }

    private Intent buildActivityIntent() {
        /*Intent intent = new Intent(itemView.getContext(), VideoPlayer.class);
        intent.putExtra(VideoPlayer.EXTRA_VIDEO_URL, currentObject.getVideoUrl());*/
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(currentObject.getUrl()), "application/x-mpegURL");
        intent.setPackage("com.mxtech.videoplayer.ad");
        //startActivity( intent );
        return intent;
    }
}