package com.example.donatethefood_helppeople.model_class;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_Donation")
public class InformationModel {
    @PrimaryKey(autoGenerate = true)
    private long DonationId;
    private String donatorName;
    private String phone;
    private String adress;
    private String foodName;
    private String quantity;
    private String date;
    private String time;

    public InformationModel(String donatorName, String phone, String adress, String foodName, String quantity, String date, String time) {
        this.donatorName = donatorName;
        this.phone = phone;
        this.adress = adress;
        this.foodName = foodName;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public long getDonationId() {
        return DonationId;
    }

    public void setDonationId(long donationId) {
        DonationId = donationId;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public void setDonatorName(String donatorName) {
        this.donatorName = donatorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
