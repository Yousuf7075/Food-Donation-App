package com.example.donatethefood_helppeople.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatethefood_helppeople.R;
import com.example.donatethefood_helppeople.model_class.InformationModel;

import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationListViewHolder> {
    Context context;
    List<InformationModel> modelList;

    public DonationAdapter(Context context, List<InformationModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public DonationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donation_list_row, parent, false);
        return new DonationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationListViewHolder holder, int position) {
        final InformationModel model = modelList.get(position);
        holder.foodName.setText(modelList.get(position).getFoodName());
        holder.foodQtn.setText(modelList.get(position).getQuantity());
        holder.cDate.setText(modelList.get(position).getDate());
        holder.cTime.setText(modelList.get(position).getTime());
        holder.dAddress.setText(modelList.get(position).getAdress());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public  class DonationListViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,foodQtn, cDate, cTime, dAddress;

        public DonationListViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodNameTV);
            foodQtn = itemView.findViewById(R.id.foodQunatityTV);
            cDate = itemView.findViewById(R.id.collectionDateTv);
            cTime = itemView.findViewById(R.id.collectionTimeTv);
            dAddress = itemView.findViewById(R.id.addressTV);
        }
    }
}
