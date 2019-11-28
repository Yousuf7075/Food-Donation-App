package com.example.donatethefood_helppeople.ui;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.donatethefood_helppeople.R;
import com.example.donatethefood_helppeople.donation_database.DonationDatabase;
import com.example.donatethefood_helppeople.model_class.InformationModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import static android.app.DatePickerDialog.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationInformationFragment extends Fragment {
    TextInputEditText donatorName, phone, address, foodName;
    private Spinner foodQuantity;
    private String spinnerItem;
    private Button confirmBtn, cancelBtn;
    Context context;
    private int cYear, cMonth, cDay, cHour, cMinute;
    private DonationCompleteListener listener;
    TextView collectionDate, collectionTime;
    private String date,time;

    public DonationInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.listener = (DonationCompleteListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        donatorName = view.findViewById(R.id.nameET);
        phone = view.findViewById(R.id.phoneET);
        address = view.findViewById(R.id.addressET);
        foodName = view.findViewById(R.id.foodNameET);
        collectionDate = view.findViewById(R.id.dateET);
        collectionTime = view.findViewById(R.id.timeET);

        confirmBtn = view.findViewById(R.id.confirmBtn);
        cancelBtn = view.findViewById(R.id.cancelBtn);

        foodQuantity = view.findViewById(R.id.foodQunatity);

        final String[] quantites = getResources().getStringArray(R.array.qunantity_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,quantites);
        foodQuantity.setAdapter(adapter);

        foodQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItem = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //set current date & time
        final Calendar c = Calendar.getInstance();
        cYear = c.get(Calendar.YEAR);
        cMonth = c.get(Calendar.MONTH);
        cDay = c.get(Calendar.DAY_OF_MONTH);

        cHour = c.get(Calendar.HOUR);
        cMinute = c.get(Calendar.MINUTE);

        //date picker
        collectionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                collectionDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, cYear, cMonth, cDay);
                datePickerDialog.show();
            }
        });
        //Time picker
        collectionTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        time = amPmConverter(hourOfDay);
                        collectionTime.setText(time);
                    }
                },cHour, cMinute, false);
                timePickerDialog.show();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = donatorName.getText().toString();
                String dphone = phone.getText().toString();
                String daddress = address.getText().toString();
                String fName = foodName.getText().toString();
                String quantity = spinnerItem;
                String date = collectionDate.getText().toString();
                String time = collectionTime.getText().toString();

                InformationModel model = new InformationModel(name,dphone,daddress,fName,quantity,date,time);

                long insertedRowId = DonationDatabase.getInstance(context)
                        .getDonationDao()
                        .insertNewDonation(model);
                if (insertedRowId > 0){
                    Toast.makeText(context, "Your donation is successful", Toast.LENGTH_SHORT).show();
                    listener.donationComplete();
                }
            }
        });
    }
    private String amPmConverter(int hourOfDay){
        //condition for am & pm
        if(hourOfDay>=0 && hourOfDay<12){
            if (hourOfDay == 0){
                time = 12 + " : " + cMinute + " AM";
            }
            else{
                time = hourOfDay + " : " + cMinute + " AM";
            }
        }
        else {
            if(hourOfDay == 12){
                time = hourOfDay + " : " + cMinute + " PM";
            }
            else{
                hourOfDay = hourOfDay -12;
                time = hourOfDay + " : " + cMinute + " PM";
            }
        }
        return time;
    }
    public  interface DonationCompleteListener{
        void donationComplete();
    }
}
