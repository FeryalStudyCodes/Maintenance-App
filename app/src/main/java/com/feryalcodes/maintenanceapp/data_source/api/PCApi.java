package com.feryalcodes.maintenanceapp.data_source.api;

import com.feryalcodes.maintenanceapp.model.request_service.RequestService;
import com.feryalcodes.maintenanceapp.model.service.ServiceModel;
import com.feryalcodes.maintenanceapp.model.service_details.ServiceDetailsModel;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface PCApi {
    @GET("api/services")
    Call<ServiceModel> requestAllServiceRequest();

    @GET("api/services")
    Call<ServiceDetailsModel> requestServiceRequest(@Query("id") int service_id);

    @Multipart
    @POST("api/request_service")
    Call<RequestService> submitServiceRequest(@Part MultipartBody.Part service_id, @Part MultipartBody.Part user_name, @Part MultipartBody.Part phone_number,
                                              @Part MultipartBody.Part phone_model, @Part MultipartBody.Part description, @Part MultipartBody.Part image);
}
