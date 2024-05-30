package com.feryalcodes.maintenanceapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.feryalcodes.maintenanceapp.R;
import com.feryalcodes.maintenanceapp.adapter.ServiceAdapter;
import com.feryalcodes.maintenanceapp.model.service.ServiceResult;
import com.feryalcodes.maintenanceapp.view_model.ServiceViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ServiceViewModel serviceViewModel;
    private ServiceAdapter serviceAdapter;
    private RecyclerView recyclerView;
    private LottieAnimationView mLoading;
    private View addServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        mLoading = findViewById(R.id.loading_view);
        addServiceBtn = findViewById(R.id.add_service_btn);
        recyclerView = findViewById(R.id.services_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        serviceAdapter = new ServiceAdapter(this);
        recyclerView.setAdapter(serviceAdapter);

        serviceViewModel = new ViewModelProvider(this).get(ServiceViewModel.class);

        serviceViewModel.getAllServices().observe(this, new Observer<List<ServiceResult>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<ServiceResult> serviceResults) {
                serviceAdapter.setServiceList(serviceResults);
                serviceAdapter.notifyDataSetChanged();
                mLoading.setVisibility(View.GONE);
            }
        });

        addServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AddServiceActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}