package com.example.sanil.mvpexample.presenter.activity.Home;

import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanil.mvpexample.Constant;
import com.example.sanil.mvpexample.presenter.activity.R;

import java.util.List;

/**
 * Created by sanil on 20-04-2018.
 */

public class RecyclerAdpter extends RecyclerView.Adapter<RecyclerAdpter.ViewHolder> {

    List<String> list;
    CustomItemClickListener listener;
    IHomeView iHomeView;

    public RecyclerAdpter(ObservableArrayList<String> list, CustomItemClickListener customItemClickListener, IHomeView iHomeView)
    {
        Log.i(Constant.TAG,"in recycler "+list.size());
        this.list = list;
        this.iHomeView = iHomeView;
        listener = customItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ((TextView)holder.view.findViewById(R.id.txtVw)).setText(list.get(position));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(view, position);
                iHomeView.navigateToActivityfragment();
            }
        });

    }


    @Override
    public int getItemCount() {
        Log.i(Constant.TAG,"in getItemCount() "+list.size());
        return list.size();
    }
}
