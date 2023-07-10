package com.example.yadsarah.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadsarah.AccommodationUnitsActivity;
import com.example.yadsarah.CenteroFHealthyActivity;
import com.example.yadsarah.CoomunityServiceActivity;
import com.example.yadsarah.HomeCareActivity;
import com.example.yadsarah.LegalAdviceAssitanceActivity;
import com.example.yadsarah.R;
import com.example.yadsarah.RehabilitationCenterActivity;
import com.example.yadsarah.RentToolsActivity;
import com.example.yadsarah.ScooterActivity;

import java.util.List;

public class CommunityServicesAdaptor extends RecyclerView.Adapter<CommunityServicesAdaptor.MyViewHolder> {

    private List<String> header;
    private List<Integer> photos;
    private LayoutInflater communityInflater;
    private Activity communityActivity;

    public CommunityServicesAdaptor(Context context, List<String> headers, List<Integer> photos, Activity activity) {
        this.header = headers;
        this.photos = photos;
        this.communityActivity = activity;
        this.communityInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = communityInflater.inflate(R.layout.community_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleForImg.setText(header.get(position));
        holder.itemMainImg.setImageResource(photos.get(position));
        holder.mainLayout.setAlpha(0.8F);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return header.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleForImg;
        ImageView itemMainImg;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleForImg = itemView.findViewById(R.id.titleForImg);
            itemMainImg = itemView.findViewById(R.id.itemMainImg);
            mainLayout = itemView.findViewById(R.id.mainLayoutForCommunityServices);
        }

        public void bind(final int position) {
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int servicePosition = position;

                    if (servicePosition == 0) {
                        Intent rentIntent = new Intent(communityActivity, LegalAdviceAssitanceActivity.class);
                        communityActivity.startActivity(rentIntent);
                    } else if (servicePosition == 1) {
                        Intent rehabIntent = new Intent(communityActivity, CenteroFHealthyActivity.class);
                        communityActivity.startActivity(rehabIntent);
                    } else if (servicePosition == 2) {
                        Intent scooterIntent = new Intent(communityActivity, AccommodationUnitsActivity.class);
                        communityActivity.startActivity(scooterIntent);
                    }
                }
            });
        }
    }
}
