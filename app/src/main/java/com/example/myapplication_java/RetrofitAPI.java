// RetrofitAPI 인터페이스 수정
package com.example.myapplication_java;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("get_status")
    Call<ParkingStatusResponse> getParkingStatus(@Query("api_key") String apiKey);

    @GET("generate_api_key")
    Call<ApiKeyResponse> generateApiKey();
}