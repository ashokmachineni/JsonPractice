package com.ashok.jsonpractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ashok on 9/27/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<ItemObject> itemList;
    private Context context;
    public RecyclerViewAdapter(Context context,List<ItemObject> itemList){
        this.itemList = itemList;
        this.context = context;

    }


    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        holder.title.setText(" " + itemList.get(position).getTitle());
        //holder.category.setText(" " + itemList.get(position).getCategory());
        //holder.poster.setText("Song Author: " + itemList.get(position).getPoster());
        Picasso.with(context.getApplicationContext()).load(itemList.get(position).getPoster()).into(holder.poster);




    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
