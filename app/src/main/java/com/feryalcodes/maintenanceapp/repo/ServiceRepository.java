package com.feryalcodes.maintenanceapp.repo;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.feryalcodes.maintenanceapp.data_source.api.PCApi;
import com.feryalcodes.maintenanceapp.data_source.api.PCServiceClient;
import com.feryalcodes.maintenanceapp.data_source.db.PCRoomDatabase;
import com.feryalcodes.maintenanceapp.data_source.db.dao.ServiceDao;
import com.feryalcodes.maintenanceapp.model.service.ServiceModel;
import com.feryalcodes.maintenanceapp.model.service.ServiceResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRepository {
    private final ServiceDao serviceDao;
    private final LiveData<List<ServiceResult>> allServices;
    private final PCApi pcApi;

    public ServiceRepository(Application application) {
        PCRoomDatabase db = PCRoomDatabase.getDatabase(application);
        serviceDao = db.serviceDao();
        allServices = serviceDao.getAllServices();
        pcApi = PCServiceClient.create();
    }

    public LiveData<List<ServiceResult>> getAllServices() {
        refreshServices();
        return allServices;
    }

    public LiveData<ServiceResult> getServiceById(int id) {
        return serviceDao.getServiceById(id);
    }

    public void insert(ServiceResult serviceResult) {
        new InsertServiceAsyncTask(serviceDao).execute(serviceResult);
    }

    private static class InsertServiceAsyncTask extends AsyncTask<ServiceResult, Void, Void> {
        private ServiceDao serviceDao;

        private InsertServiceAsyncTask(ServiceDao serviceDao) {
            this.serviceDao = serviceDao;
        }

        @Override
        protected Void doInBackground(ServiceResult... serviceResults) {
            try {
                serviceDao.insertService(serviceResults[0]);
            } catch (SQLiteConstraintException e) {
                // If insert fails, update the existing record
                serviceDao.updateService(serviceResults[0]);
            }
            return null;
        }
    }

    private void refreshServices() {
        pcApi.requestAllServiceRequest().enqueue(new Callback<ServiceModel>() {
            @Override
            public void onResponse(@NonNull Call<ServiceModel> call, @NonNull Response<ServiceModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ServiceResult> services = response.body().getData();
                    for (ServiceResult service : services) {
                        insert(service);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServiceModel> call, @NonNull Throwable t) {
                // Handle failure
            }
        });
    }
}

