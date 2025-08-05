package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;

public class TripPlanningActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] exCategories = {
            "Food", "Entertainment",
            "Travel"
    };

    private String exCat;
    private Button btnNext;
    private Spinner spnCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tripplanning);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Spinner Code
        spnCategories = findViewById(R.id.spinner2);
        spnCategories.setOnItemSelectedListener(this);

        //Array Adapter
        ArrayAdapter <String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                exCategories
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategories.setAdapter(adapter);

        // Button Code
        btnNext = findViewById(R.id.btnNextPage);

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(TripPlanningActivity.this, BudgetingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
//        Toast.makeText(getApplicationContext(), exCategories[pos], Toast.LENGTH_SHORT).show();
        exCat = exCategories[pos];
        Toast.makeText(this, exCat, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}