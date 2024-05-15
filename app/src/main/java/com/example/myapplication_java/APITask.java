package com.example.myapplication_java;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APITask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "APITask";
    private final String apiKey;
    private final APICallback callback;

    public APITask(String apiKey, APICallback callback) {
        this.apiKey = apiKey;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.137.86:5009/get_status")
                .header("Authorization", "Bearer " + apiKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            // 결과 처리
            Log.d(TAG, "Response: " + result);
            callback.onSuccess(result);
        } else {
            callback.onError("Error occurred");
        }
    }

    public interface APICallback {
        void onSuccess(String result);
        void onError(String error);
    }
}