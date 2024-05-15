package com.example.myapplication_java;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatusAdapter extends RecyclerView.Adapter<ParkingStatusAdapter.ViewHolder> {

    private List<ParkingSpace> parkingStatusList = new ArrayList<>();

    public void setData(List<ParkingSpace> parkingStatusList) {
        this.parkingStatusList = parkingStatusList;
        notifyDataSetChanged(); // 데이터가 변경될 때 RecyclerView에 알려줍니다.
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParkingSpace parkingSpace = parkingStatusList.get(position);
        holder.bind(parkingSpace);
    }

    @Override
    public int getItemCount() {
        return parkingStatusList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View parkingSpaceView; // 네모칸 뷰
        private TextView statusTextView;
        private TextView resultTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parkingSpaceView = itemView.findViewById(R.id.parkingSpaceView);
            statusTextView = itemView.findViewById(R.id.parkingSpaceStatus);
        }

        public void bind(ParkingSpace parkingSpace) {
            // 주차 상태와 결과를 표시합니다.
            statusTextView.setText(parkingSpace.getParkingSpaceStatus());
            parkingSpaceView.setBackgroundColor(getResultColor(parkingSpace.getParkingSpaceStatus()));
        }


        // 주차 상태에 따라 네모칸의 색을 반환하는 메서드
        private int getResultColor(String status) {
            if (status.equals("Occupied")) {
                return Color.RED; // 빨간색
            } else if (status.equals("Free")) {
                return Color.GREEN; // 초록색
            } else {
                return Color.BLACK; // 검은색
            }
        }
    }
}
