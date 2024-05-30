package com.feryalcodes.maintenanceapp.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.feryalcodes.maintenanceapp.model.service.ServiceResult;
import com.feryalcodes.maintenanceapp.repo.ServiceRepository;

import java.util.List;

public class ServiceViewModel extends AndroidViewModel {
    private final ServiceRepository repository;
    private final LiveData<List<ServiceResult>> allServices;

    public ServiceViewModel(Application application) {
        super(application);
        repository = new ServiceRepository(application);
        allServices = repository.getAllServices();
    }

    public LiveData<List<ServiceResult>> getAllServices() {
        return allServices;
    }

    public void insert(ServiceResult serviceResult) {
        repository.insert(serviceResult);
    }
}

