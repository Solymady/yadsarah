package com.example.yadsarah.Adaptor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.yadsarah.CanadianCrutchesActivity;
import com.example.yadsarah.ChildWheelchairActivity;
import com.example.yadsarah.CoomunityServiceActivity;
import com.example.yadsarah.ElevatorActivity;
import com.example.yadsarah.HomeCareActivity;
import com.example.yadsarah.R;
import com.example.yadsarah.RehabilitationCenterActivity;
import com.example.yadsarah.RentToolsActivity;
import com.example.yadsarah.ScooterActivity;
import com.example.yadsarah.TreadmillActivity;
import com.example.yadsarah.WalkInfoActivity;

import java.util.List;

public class ServicesAdaptor extends RecyclerView.Adapter<ServicesAdaptor.myViewHolder> {

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    Activity activity;
    private OnItemClickListener itemClickListener; // Listener for item click events

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ServicesAdaptor(Context context, List<String> titles, List<Integer> images, Activity activity) {
        this.titles = titles;
        this.images = images;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.picTitle.setText(titles.get(position));
        holder.itemImg.setImageResource(images.get(position));
        holder.mainLinearLayout.setAlpha(0.8F);

        holder.mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int servicePosition = position;

                if (servicePosition == 0) {
                    Intent walkIntent = new Intent(activity, RentToolsActivity.class);
                    activity.startActivity(walkIntent);
                } else if (servicePosition == 1) {
                    Intent walkIntent2 = new Intent(activity, RehabilitationCenterActivity.class);
                    activity.startActivity(walkIntent2);
                } else if (servicePosition == 2) {
                    Intent walkIntent4 = new Intent(activity, HomeCareActivity.class);
                    activity.startActivity(walkIntent4);
                } else if (servicePosition == 3) {
                    Intent walkIntent5 = new Intent(activity, CoomunityServiceActivity.class);
                    activity.startActivity(walkIntent5);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView picTitle;
        ImageView itemImg;
        LinearLayout mainLinearLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            picTitle = itemView.findViewById(R.id.picTitle);
            itemImg = itemView.findViewById(R.id.itemImg);
            mainLinearLayout = itemView.findViewById(R.id.mainLinearLayout);
        }
    }
}
