package com.feryalcodes.maintenanceapp.ui.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.feryalcodes.maintenanceapp.R;

public class DetailsActivity extends AppCompatActivity {

    private View toolBar, backBtn;
    private AppCompatTextView toolbarTitleTv, serviceDescTv;
    private String toolbarTitle, serviceDescTxt, servicePriceTxt, serviceDurationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbarTitle = getIntent().getStringExtra("service_name");
        serviceDescTxt = getIntent().getStringExtra("service_desc");
        servicePriceTxt = getIntent().getStringExtra("service_price");
        serviceDurationTxt = getIntent().getStringExtra("service_duration");

        serviceDescTv = findViewById(R.id.service_description);

        toolBar = findViewById(R.id.toolbar);
        backBtn = toolBar.findViewById(R.id.nav_start_btn);
        toolbarTitleTv = toolBar.findViewById(R.id.toolbar_title);
        toolbarTitleTv.setText(toolbarTitle);
        toolbarTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f);
        backBtn.setVisibility(View.VISIBLE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        serviceDescTv.setText(serviceDescTxt);
    }
}