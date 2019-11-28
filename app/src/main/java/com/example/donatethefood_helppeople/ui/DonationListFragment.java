package com.example.donatethefood_helppeople.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.donatethefood_helppeople.R;
import com.example.donatethefood_helppeople.adapter.DonationAdapter;
import com.example.donatethefood_helppeople.donation_database.DonationDatabase;
import com.example.donatethefood_helppeople.model_class.InformationModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationListFragment extends Fragment {
    private RecyclerView recyclerView;
    private DonationAdapter adapter;
    List<InformationModel> modelList = new ArrayList<>();
    Context context;

    public DonationListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.donationRecycler);
        modelList = DonationDatabase.getInstance(context).getDonationDao().getAllDonationList();
        Collections.reverse(modelList);
        adapter = new DonationAdapter(context, modelList);
        GridLayoutManager glm = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);
    }
}
