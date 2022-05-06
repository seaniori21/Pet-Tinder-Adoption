package com.codewithsean.pettinderadoption.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codewithsean.pettinderadoption.DetailActivity;
import com.codewithsean.pettinderadoption.R;
import com.codewithsean.pettinderadoption.models.Profile;

import org.parceler.Parcels;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;
    List<Profile> profiles;

    public ProfileAdapter(Context context, List<Profile> profile) {
        Log.d("ProfileAdapter", "onCreateProfileAdapter");
        this.context = context;
        this.profiles = profile;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ProfileAdapter", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.profile_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ProfileAdapter", "onBindViewHolder " + position);
        Profile profile = profiles.get(position);
        holder.bind(profile);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAge;
        TextView tvDesciption;
        TextView tvName;
        TextView tvGender;
        TextView tvId;
        ImageView ivImage;
        FrameLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDesciption = itemView.findViewById(R.id.tvDescription);
            //tvGender = itemView.findViewById(R.id.tvGender);
            tvName = itemView.findViewById(R.id.tvName);
            ivImage = itemView.findViewById(R.id.ivPhoto);
            container = itemView.findViewById(R.id.profileContainer);

        }

        public void bind(Profile profile) {
            tvName.setText(profile.name);
            //tvGender.setText(profile.gender);
            if(profile.description == "null"){
                tvDesciption.setText("No description");
            }
            else{
                tvDesciption.setText(profile.description);
            }
            Glide.with(context).load(profile.photo.full).into(ivImage);
            container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    Toast.makeText(context, profile.name,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("profiles", Parcels.wrap(profile));
                    context.startActivity(i);
                }
            });
        }
    }
}
