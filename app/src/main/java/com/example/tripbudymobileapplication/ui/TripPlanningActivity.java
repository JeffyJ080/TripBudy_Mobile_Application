package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;

public class TripPlanningActivity extends AppCompatActivity {

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
        //Array Adapter
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.expenseCategories,
                android.R.layout.simple_spinner_item
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
}