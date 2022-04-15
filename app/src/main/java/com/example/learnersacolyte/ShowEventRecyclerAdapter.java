package com.example.learnersacolyte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowEventRecyclerAdapter extends RecyclerView.Adapter<ShowEventRecyclerAdapter.ViewHolder> {

    Context context;
    List<EventDataStructure> list;
    String s[] = new String[100];
    String time[] = new String[100];

    public ShowEventRecyclerAdapter(Context context, String[] s, String[] time) {
        this.context = context;
        this.s = s;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_recycler_populer, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Title.setText(s[position]);
        holder.Date.setText(time[position]);
    }

    @Override
    public int getItemCount() {
        return s.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView Title, Date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            Title = itemView.findViewById(R.id.ShowTitle);
            Date = itemView.findViewById(R.id.ShowDate);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
