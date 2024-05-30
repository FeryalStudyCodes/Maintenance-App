package com.feryalcodes.maintenanceapp.data_source.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.feryalcodes.maintenanceapp.model.service.ServiceResult;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert
    void insertService(ServiceResult serviceResult);

    @Update
    void updateService(ServiceResult serviceResult);

    @Query("SELECT * FROM Services")
    List<ServiceResult> getServices();

    @Query("SELECT * FROM Services WHERE id = :id")
    ServiceResult getOneService(int id);

    @Query("SELECT * FROM Services")
    LiveData<List<ServiceResult>> getAllServices();

    @Query("SELECT * FROM Services WHERE id=:serviceId")
    ServiceResult getService(int serviceId);

    @Query("SELECT * FROM Services ORDER BY additionDate DESC")
    LiveData<List<ServiceResult>> getOrganizedServices();

    @Query("SELECT * FROM Services WHERE id = :id")
    LiveData<ServiceResult> getServiceById(int id);
}
