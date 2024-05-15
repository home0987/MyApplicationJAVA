package com.example.myapplication_java;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RetrofitAPI retrofitAPI;
    private RecyclerView recyclerView;
    private ParkingStatusAdapter adapter;
    private TextView responseText;
    private String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        responseText = findViewById(R.id.responseText);

        adapter = new ParkingStatusAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        initializeRetrofit();

        new GenerateApiKeyTask().execute();
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.86:5009/") // 서버의 기본 URL 설정
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    private class GenerateApiKeyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Call<ApiKeyResponse> call = retrofitAPI.generateApiKey();
                Response<ApiKeyResponse> response = call.execute();
                if (response.isSuccessful()) {
                    ApiKeyResponse apiKeyResponse = response.body();
                    if (apiKeyResponse != null) {
                        apiKey = apiKeyResponse.getApiKey();
                        new GetParkingStatusTask().execute(apiKey);
                    }
                } else {
                    runOnUiThread(() -> responseText.setText("Failed to generate API key"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> responseText.setText("Network Error: " + e.getMessage()));
            }
            return null;
        }
    }

    private void updateRecyclerView(List<ParkingSpace> parkingSpaces) {
        ParkingStatusAdapter adapter = new ParkingStatusAdapter();
        adapter.setData(parkingSpaces);
        recyclerView.setAdapter(adapter);
    }

    private class GetParkingStatusTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String apiKey = params[0];
            try {
                Call<ParkingStatusResponse> call = retrofitAPI.getParkingStatus(apiKey);
                Response<ParkingStatusResponse> response = call.execute();
                if (response.isSuccessful()) {
                    ParkingStatusResponse statusResponse = response.body();
                    if (statusResponse != null) {
                        List<ParkingSpace> parkingSpaces = statusResponse.getParkingSpaces();
                        runOnUiThread(() -> updateRecyclerView(parkingSpaces));
                    }
                } else {
                    runOnUiThread(() -> responseText.setText("Failed to get parking status"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> responseText.setText("Network Error: " + e.getMessage()));
            }
            return null;
        }
    }
}
