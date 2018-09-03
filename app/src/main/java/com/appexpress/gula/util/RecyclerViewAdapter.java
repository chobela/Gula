package com.appexpress.gula.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appexpress.gula.FeatureFragment;
import com.appexpress.gula.PostFragment;
import com.appexpress.gula.R;
import com.appexpress.gula.ViewPostFragment;
import com.appexpress.gula.Deals;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<Deals> deals;

    public RecyclerViewAdapter(List<Deals> deals, Context context){

        super();

        this.deals = deals;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Deals mydeals =  deals.get(position);

        holder.textViewPrice.setText(mydeals.getPrice());
        holder.textViewTitle.setText(mydeals.getTitle());
        holder.textViewTown.setText(mydeals.getTown());

        Glide.with(context).load(mydeals.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);



        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case (R.id.shareme):

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = mydeals.getTitle()  + " For sale" + "at " + "K" + mydeals.getPrice() + "! Download the Gula app for android to buy, sale or advertise for free! http://bit.ly/alistm";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Gula");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;

                }
            }

        });

        holder.lview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case (R.id.itemm):

                        AppCompatActivity activity = (AppCompatActivity) v.getContext();

                        activity.findViewById(R.id.recyclerView).setVisibility(View.GONE);
                        FeatureFragment myFragment = new FeatureFragment();

                        Bundle args = new Bundle();
                        args.putString("arg_post_id", mydeals.getPostId());
                        myFragment.setArguments(args);

                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return deals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewTitle, textViewTown, textViewPrice;
        ImageView imageView, shareBtn;
        LinearLayout lview;


        public ViewHolder(View itemView) {

            super(itemView);

            textViewPrice = itemView.findViewById(R.id.price) ;
            textViewTitle = itemView.findViewById(R.id.title) ;
            textViewTown = itemView.findViewById(R.id.town) ;
            imageView = itemView.findViewById(R.id.imageq);
            shareBtn = itemView.findViewById(R.id.shareme);
            lview = itemView.findViewById(R.id.itemm);

        }

        // Click event for all items
        @Override
        public void onClick(View v) {

        }
    }
}
