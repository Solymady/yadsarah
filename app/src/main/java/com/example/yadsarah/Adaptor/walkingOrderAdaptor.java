package com.example.yadsarah.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yadsarah.CanadianCrutchesActivity;
import com.example.yadsarah.ChildWheelchairActivity;
import com.example.yadsarah.ElevatorActivity;
import com.example.yadsarah.ScooterActivity;
import com.example.yadsarah.R;
import com.example.yadsarah.TreadmillActivity;
import com.example.yadsarah.WalkInfoActivity;
import com.example.yadsarah.WalkingAccessoriesModelActivity;

import java.util.List;

public class walkingOrderAdaptor extends RecyclerView.Adapter<walkingOrderAdaptor.walkAccessoriesViewHolder> {

    List<WalkingAccessoriesModelActivity> walkingModelList;
    Context walkAccessContext;

    public walkingOrderAdaptor(Context walkAccessContext, List<WalkingAccessoriesModelActivity> walkingModelList) {
        this.walkingModelList = walkingModelList;
        this.walkAccessContext = walkAccessContext;
    }

    @Override
    public walkAccessoriesViewHolder onCreateViewHolder(ViewGroup walkParent, int walk) {
        View walkView = LayoutInflater.from(walkAccessContext).inflate(R.layout.walk_accessories_listitem, walkParent, false);
        return new walkAccessoriesViewHolder(walkView);
    }

    @Override
    public void onBindViewHolder(walkAccessoriesViewHolder walkHolder, int walkPosition) {
        WalkingAccessoriesModelActivity walkingModel = walkingModelList.get(walkPosition);

        walkHolder.walkAccessoriesName.setText(walkingModel.getWalkAccessoriesName());
        walkHolder.walkAccessoriesDetails.setText(walkingModel.getWalkAccessoriesDetails());
        walkHolder.walkAccessoriesImg.setImageResource(walkingModel.getWalkAccessoriesImg());
        walkHolder.walkAccessoriesImg.setContentDescription(walkingModel.getWalkAccessoriesName());
    }

    @Override
    public int getItemCount() {
        return walkingModelList.size();
    }

    public class walkAccessoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView walkAccessoriesName, walkAccessoriesDetails;
        ImageView walkAccessoriesImg;

        public walkAccessoriesViewHolder(View itemView) {
            super(itemView);

            walkAccessoriesName = itemView.findViewById(R.id.walkAccessName);
            walkAccessoriesDetails = itemView.findViewById(R.id.walkAccessDescription);
            walkAccessoriesImg = itemView.findViewById(R.id.walkAccessImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int walkPosition = getAdapterPosition();

            if (walkPosition == 0) {
                Intent walkIntent = new Intent(walkAccessContext, WalkInfoActivity.class);
                walkAccessContext.startActivity(walkIntent);
            } else if (walkPosition == 1) {
                Intent walkIntent2 = new Intent(walkAccessContext, ScooterActivity.class);
                walkAccessContext.startActivity(walkIntent2);
            } else if (walkPosition == 2) {
                Intent walkIntent3 = new Intent(walkAccessContext, TreadmillActivity.class);
                walkAccessContext.startActivity(walkIntent3);
            } else if (walkPosition == 3) {
                Intent walkIntent4 = new Intent(walkAccessContext, ChildWheelchairActivity.class);
                walkAccessContext.startActivity(walkIntent4);
            } else if (walkPosition == 4) {
                Intent walkIntent5 = new Intent(walkAccessContext, ElevatorActivity.class);
                walkAccessContext.startActivity(walkIntent5);
            } else if (walkPosition == 5) {
                Intent walkIntent6 = new Intent(walkAccessContext, CanadianCrutchesActivity.class);
                walkAccessContext.startActivity(walkIntent6);
            }
        }
    }
}
