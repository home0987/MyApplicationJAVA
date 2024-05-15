package com.example.myapplication_java;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ParkingStatusResponse {
    @SerializedName("param")
    private List<ParkingSpace> parkingSpaces;

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
}