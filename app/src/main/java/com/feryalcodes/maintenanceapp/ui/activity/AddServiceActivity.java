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

public class AddServiceActivity extends AppCompatActivity {

    private View toolBar, backBtn;
    private AppCompatTextView toolbarTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolBar = findViewById(R.id.toolbar);
        backBtn = toolBar.findViewById(R.id.nav_start_btn);
        toolbarTitleTv = toolBar.findViewById(R.id.toolbar_title);
        toolbarTitleTv.setText(R.string.str_request_service);
        toolbarTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f);
        backBtn.setVisibility(View.VISIBLE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}