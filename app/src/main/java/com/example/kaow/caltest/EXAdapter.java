package com.example.kaow.caltest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kaow on 8/15/15 AD.
 */
public class EXAdapter extends RecyclerView.Adapter<EXAdapter.PersonViewHolder>{
    List<Exercise> exers;

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView personName;
        ImageView personPhoto;
        Button exerInfo;



        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.exercise_name);
            personPhoto = (ImageView) itemView.findViewById(R.id.exer_photo);
            exerInfo = (Button) itemView.findViewById(R.id.exer_info);
            exerInfo.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
    EXAdapter(List<Exercise> exers){
        this.exers = exers;
    }

    @Override
    public int getItemCount() {
        return exers.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_exercise_new, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        final int position = i;
        personViewHolder.personName.setText(exers.get(i).exer_name);
        personViewHolder.personPhoto.setImageResource(exers.get(i).photoId);
        personViewHolder.exerInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"this test"+ position,Toast.LENGTH_LONG).show();
                Intent m = new Intent(v.getContext(),EXER_INFOFragment.class);
                m.putExtra("pos", position);
                v.getContext().startActivity(m);


            }


        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}