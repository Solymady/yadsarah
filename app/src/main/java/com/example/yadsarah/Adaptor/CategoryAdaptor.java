package com.example.yadsarah.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadsarah.AccommodationUnitsActivity;
import com.example.yadsarah.CenteroFHealthyActivity;
import com.example.yadsarah.Domain.CategoryDomain;
import com.example.yadsarah.LegalAdviceAssitanceActivity;
import com.example.yadsarah.R;

import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.example.yadsarah.WalkingAccessoriesActivity;


public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {
    ArrayList<CategoryDomain> categoryDomains;

    public CategoryAdaptor(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 1: {
                picUrl = "cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background2));
                break;
            }
            case 2: {
                picUrl = "cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 3: {
                picUrl = "cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background4));
                break;
            }
            case 4: {
                picUrl = "cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background5));
                break;
            }
            case 5: {
                picUrl = "cat_6";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background6));
                break;
            }
            case 6: {
                picUrl = "cat_7";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background7));
                break;
            }
            case 7: {
                picUrl = "cat_8";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background8));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        public void bind(final int position) {
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int servicePosition = position;

                    if (servicePosition == 0) {
                        Intent rentIntent1 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(rentIntent1);
                    } else if (servicePosition == 1) {
                        Intent rehabIntent2 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(rehabIntent2);
                    } else if (servicePosition == 2) {
                        Intent scooterIntent3 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(scooterIntent3);
                    }else if (servicePosition == 3) {
                        Intent scooterIntent4 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(scooterIntent4);
                    }else if (servicePosition == 4) {
                        Intent scooterIntent5 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(scooterIntent5);
                    }else if (servicePosition == 5) {
                        Intent scooterIntent6 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(scooterIntent6);
                    }else if (servicePosition == 6) {
                        Intent scooterIntent7 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(scooterIntent7);
                    }else if (servicePosition == 7) {
                        Intent scooterIntent8 = new Intent(view.getContext(), WalkingAccessoriesActivity.class);
                        view.getContext().startActivity(scooterIntent8);
                    }

                }
            });
        }
    }
}
