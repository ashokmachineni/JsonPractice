package com.ashok.jsonpractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashok.jsonpractice.events.RecyclerItemClickEvent;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

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
        RecyclerViewHolders rcv = new RecyclerViewHolders(context, layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "url: "+ itemList.get(position).getUrl(), Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new RecyclerItemClickEvent(itemList.get(position).getUrl()));
            }
        });
        holder.title.setText(itemList.get(position).getTitle());
        //holder.category.setText(" " + itemList.get(position).getCategory());
        //holder.poster.setText("Song Author: " + itemList.get(position).getPoster());
        Glide.with(context).load(itemList.get(position).getImage()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}