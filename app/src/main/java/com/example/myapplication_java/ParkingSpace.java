package com.example.myapplication_java;

import com.google.gson.annotations.SerializedName;

public class ParkingSpace {
    @SerializedName("Parking_Space_Status")
    private String parkingSpaceStatus;

    public String getParkingSpaceStatus() {
        return parkingSpaceStatus;
    }

    public void setParkingSpaceStatus(String parkingSpaceStatus) {
        this.parkingSpaceStatus = parkingSpaceStatus;
    }
}