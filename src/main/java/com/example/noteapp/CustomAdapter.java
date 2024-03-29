package com.example.noteapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList note_id, note_title, note_description;
    Animation translate;

    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_title, ArrayList note_description){

        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_description = note_description;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.my_row, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));

        holder.note_description_txt.setText(String.valueOf(note_description.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateNoteActivity.class);

                intent.putExtra("id", String.valueOf(note_id.get(position)));

                intent.putExtra("title", String.valueOf(note_title.get(position)));

                intent.putExtra("description", String.valueOf(note_description.get(position)));

                activity.startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    public int getItemCount() {

        return note_id.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_title_txt, note_description_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            note_title_txt = itemView.findViewById(R.id.title_txt);

            note_description_txt = itemView.findViewById(R.id.description_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animating recyclerview
            translate = AnimationUtils.loadAnimation(context, R.anim.translate_anim);

            mainLayout.setAnimation(translate);
        }
    }
}
