package com.feryalcodes.maintenanceapp.adapter;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.feryalcodes.maintenanceapp.R;
import com.feryalcodes.maintenanceapp.model.service.ServiceModel;
import com.feryalcodes.maintenanceapp.model.service.ServiceResult;
import com.feryalcodes.maintenanceapp.ui.activity.DetailsActivity;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ServiceAdapter.class.getSimpleName() + "Tag";

    private final Context mContext;
    private List<ServiceResult> mServiceModels;

    public ServiceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setServiceList(List<ServiceResult> mServiceModels) {
        this.mServiceModels = mServiceModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(mContext).inflate(com.feryalcodes.maintenanceapp.R.layout.custom_service_item, parent, false);
        viewHolder = new ServiceVH(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ServiceResult model = mServiceModels.get(position);

        AppCompatTextView serviceName = holder.itemView.findViewById(com.feryalcodes.maintenanceapp.R.id.service_name);
        AppCompatTextView servicePrice = holder.itemView.findViewById(com.feryalcodes.maintenanceapp.R.id.service_price);
        AppCompatTextView serviceDuration = holder.itemView.findViewById(com.feryalcodes.maintenanceapp.R.id.service_duration);

        serviceName.setText(model.getName());
        servicePrice.setText(model.getPrice() + " ريال");
        serviceDuration.setText(String.valueOf(model.getDays()));

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(mContext, DetailsActivity.class);
            i.putExtra("service_id", model.getId());
            i.putExtra("service_name", model.getName());
            i.putExtra("service_duration", model.getDays());
            i.putExtra("service_desc", model.getDescription());
            i.putExtra("service_price", model.getPrice());
            mContext.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        if (mServiceModels != null) {
            return mServiceModels.size();
        } else {
            return 0;
        }
    }

    private static class ServiceVH extends RecyclerView.ViewHolder {

        public ServiceVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
