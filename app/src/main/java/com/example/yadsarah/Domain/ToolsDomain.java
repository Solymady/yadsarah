package com.example.yadsarah.Domain;

public class ToolsDomain {

    private String title;
    private String pic;
    private int amount;
    private Double Fee;

    public ToolsDomain(String title, String pic, int amount, Double fee) {
        this.title = title;
        this.pic = pic;
        this.amount = amount;
        Fee = fee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return Fee;
    }

    public void setFee(Double fee) {
        Fee = fee;
    }
}
