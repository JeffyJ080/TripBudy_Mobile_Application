package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;
import com.example.tripbudymobileapplication.model.Trip;

import java.sql.Date;

public class TripPlanningActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] arrTripType = {
            "sightseeing", "hiking",
            "dining", "museum tours"
    };

    private String exCat;
    private Button btnSaveTrip;
    private ImageButton btnTrips, btnHome, btnAddMem, btnBudget, btnAccount;
    private Spinner spnTripType;

    private Trip trip;

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
        spnTripType = findViewById(R.id.spnVisitType);
        spnTripType.setOnItemSelectedListener(this);

        //Array Adapter: this populated the spinner
        ArrayAdapter <String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                arrTripType
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTripType.setAdapter(adapter);

        // Nav bar code
        btnHome = findViewById(R.id.btnHome);
        btnTrips = findViewById(R.id.btnTrips);
        btnAddMem = findViewById(R.id.btnNewMemory);
        btnBudget = findViewById(R.id.btnBudget);
        btnAccount = findViewById(R.id.btnAccount);

        btnTrips.setOnClickListener(v -> {
            Intent in = new Intent(this, TripPlanningActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        btnHome.setOnClickListener(v -> {
            Intent in = new Intent(this, DashboardActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        btnAddMem.setOnClickListener(v -> {
            Intent in = new Intent(this, MemoryPostingActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        btnBudget.setOnClickListener(v -> {
            Intent in = new Intent(this, BudgetingActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        btnAccount.setOnClickListener(v -> {
            Intent in = new Intent(this, RegistrationActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        // Save Trip Button
        btnSaveTrip = findViewById(R.id.btnSaveTrip);

        btnSaveTrip.setOnClickListener(v -> {
            createTrip();
            Trip.saveTrip(trip);
        });
    }

    public void createTrip(){
        // Trip Planning Code //

        // Destination Input
        EditText edtDestination = findViewById(R.id.edtDestination);
        String destination = edtDestination.getText().toString();

        // Start Date Input
        EditText edtStartDate = findViewById(R.id.editTextDate);
        String startDate = edtStartDate.getText().toString();

        // End Date Input
        EditText edtEndDate = findViewById(R.id.editTextDate2);
        String endDate = edtEndDate.getText().toString();

        // Notes Input
        EditText edtNotes = findViewById(R.id.edtNotes);
        String notes = edtNotes.getText().toString();

        // Expenses Input
        EditText edtExpenses = findViewById(R.id.editTextNumber);
        String expense = edtExpenses.getText().toString();
        Double expenses = Double.valueOf(expense);

        // Trip Creation
        trip = new Trip(1, destination, Date.valueOf(startDate), Date.valueOf(endDate), notes, expenses, 1);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        exCat = arrTripType[pos];
//        Toast.makeText(this, exCat, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}