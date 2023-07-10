package com.example.yadsarah;

import com.example.yadsarah.AboutActivity;

public class WalkingAccessoriesModelActivity extends AboutActivity {

    String walkAccessoriesName;
    String walkAccessoriesDetails;
    int walkAccessoriesImg;

    public WalkingAccessoriesModelActivity(String walkAccessoriesName, String walkAccessoriesDetails, int walkAccessoriesImg) {
        this.walkAccessoriesName = walkAccessoriesName;
        this.walkAccessoriesDetails = walkAccessoriesDetails;
        this.walkAccessoriesImg = walkAccessoriesImg;
    }

    public String getWalkAccessoriesName() {
        return walkAccessoriesName;
    }

    public void setWalkAccessoriesName(String walkAccessoriesName) {
        this.walkAccessoriesName = walkAccessoriesName;
    }

    public String getWalkAccessoriesDetails() {
        return walkAccessoriesDetails;
    }

    public void setWalkAccessoriesDetails(String walkAccessoriesDetails) {
        this.walkAccessoriesDetails = walkAccessoriesDetails;
    }

    public int getWalkAccessoriesImg() {
        return walkAccessoriesImg;
    }

    public void setWalkAccessoriesImg(int walkAccessoriesImg) {
        this.walkAccessoriesImg = walkAccessoriesImg;
    }
}
