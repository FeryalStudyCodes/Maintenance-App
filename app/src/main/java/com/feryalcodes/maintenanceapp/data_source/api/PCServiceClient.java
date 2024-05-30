package com.feryalcodes.maintenanceapp.data_source.api;

import  android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.feryalcodes.maintenanceapp.model.request_service.RequestService;
import com.feryalcodes.maintenanceapp.model.service.ServiceModel;
import com.feryalcodes.maintenanceapp.model.service_details.ServiceDetailsModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Part;

public class PCServiceClient {

    private static String TAG = "PCServiceClientTag";
    public static String BASE_URL = "https://app.dev-options.com";
    public static String HEADER_CACHE = "Cache-Control";
    public static String HEADER_PRAGMA = "Pragma";

    public static PCApi create() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(160, TimeUnit.SECONDS)
                .readTimeout(150, TimeUnit.SECONDS)
                .callTimeout(150, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor())
                .build();


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PCApi.class);
    }

    private static Interceptor networkInterceptor() {
        return new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {

                okhttp3.Response responses = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(118, TimeUnit.SECONDS)
                        .build();

                return responses.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE)
                        .header(HEADER_CACHE, cacheControl.toString())
                        .build();
            }
        };
    }

    private void submitServiceRequest(@Part MultipartBody.Part service_id, @Part MultipartBody.Part user_name, @Part MultipartBody.Part phone_number,
                                      @Part MultipartBody.Part phone_model, @Part MultipartBody.Part description, @Part MultipartBody.Part image) {

        final MutableLiveData<RequestService> mutableLiveData = new MutableLiveData<>();
        Call<RequestService> responseCall = create().submitServiceRequest(service_id, user_name, phone_number, phone_model, description, image);
        responseCall.enqueue(new Callback<RequestService>() {
            @Override
            public void onResponse(@NonNull Call<RequestService> call, @NonNull Response<RequestService> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                } else {
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RequestService> call, @NonNull Throwable t) {
                mutableLiveData.postValue(null);
            }
        });
    }
}
