
package com.feryalcodes.maintenanceapp.model.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceModel {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("0")
    @Expose
    private String message;
    @SerializedName("1")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<ServiceResult> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void set0(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ServiceResult> getData() {
        return data;
    }

    public void setData(List<ServiceResult> data) {
        this.data = data;
    }

}
