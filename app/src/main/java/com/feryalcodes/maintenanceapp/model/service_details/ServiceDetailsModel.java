package com.feryalcodes.maintenanceapp.model.service_details;

import com.feryalcodes.maintenanceapp.model.service.ServiceResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ServiceDetailsModel {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ServiceResult data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceResult getData() {
        return data;
    }

    public void setData(ServiceResult data) {
        this.data = data;
    }

}
