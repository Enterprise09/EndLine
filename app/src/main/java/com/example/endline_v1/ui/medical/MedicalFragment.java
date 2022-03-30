package com.example.endline_v1.ui.medical;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endline_v1.util.DisplayDataFromFirebase;
import com.example.endline_v1.R;

public class MedicalFragment extends Fragment {

    private MedicalViewModel medicalViewModel;
    private RecyclerView recyclerView;
    private Activity activity;
    private DisplayDataFromFirebase displayer;
    private Spinner spinner_filter_medical;
    private String filter_index;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medicalViewModel =
                new ViewModelProvider(this).get(MedicalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medical, container, false);
        
        spinner_filter_medical = (Spinner) root.findViewById(R.id.spinner_filter_medical);
        spinner_filter_medical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filter_index = parent.getItemAtPosition(position).toString();
                displayer = new DisplayDataFromFirebase("의약품", filter_index, recyclerView, activity.getApplicationContext());
                displayer.DisplayData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_filter_medical.setSelection(0);
                filter_index = "등록일자순";
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView_medical);
        DisplayDataFromFirebase displayer = new DisplayDataFromFirebase("의약품", recyclerView, activity.getApplicationContext());
        displayer.DisplayData();

        return root;
    }
}