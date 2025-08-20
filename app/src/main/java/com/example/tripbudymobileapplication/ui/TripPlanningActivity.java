package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;
import com.example.tripbudymobileapplication.database.DatabaseHelper;
import com.example.tripbudymobileapplication.database.dao.TripDao;
import com.example.tripbudymobileapplication.database.model.Trip;
import com.example.tripbudymobileapplication.database.model.User;

import java.sql.Date;
import java.util.ArrayList;

public class TripPlanningActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] arrTripType = {
            "Choose activities",
            "sightseeing", "hiking",
            "dining", "museum tours"
    };
    private User[] arrUsers ={
            
    };

    private ArrayList<String> act = new ArrayList<String>();

    private String exCat;
    private Button btnSaveTrip;
    private ImageButton btnTrips, btnHome, btnAddMem, btnViewMemory, btnAccount;
    private Spinner spnTripType;
    private EditText edtEndDate;
    private EditText edtStartDate;
    private EditText edtDestination;
    private EditText edtNotes;
    private EditText edtExpenses;
    private TextView txtExpenses, txtDiscount, txtTotalExpenses;
    private Trip trip;

    private SharedPreferences sharedPreferences;

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
        btnViewMemory = findViewById(R.id.btnViewMem);
        btnAccount = findViewById(R.id.btnAccount);

        btnTrips.setOnClickListener(v -> {
            Intent in = new Intent(this, TripPlanningActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
            finish();
        });

        btnHome.setOnClickListener(v -> {
            Intent in = new Intent(this, DashboardActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
            finish();
        });

        btnAddMem.setOnClickListener(v -> {
            Intent in = new Intent(this, MemoryPostingActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
            finish();
        });

        btnViewMemory.setOnClickListener(v -> {
            Intent in = new Intent(this, GalleryActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
            finish();
        });

        btnAccount.setOnClickListener(v -> {
            Intent in = new Intent(this, RegistrationActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
            finish();
        });

        sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);

        // Save Trip Button
        btnSaveTrip = findViewById(R.id.btnSaveTrip);

        btnSaveTrip.setOnClickListener(v -> {
            createTrip();
            btnSaveTrip.setVisibility(View.VISIBLE);
            TripDao tripDao = new TripDao(this);
            Trip savedTrip = tripDao.insertTrip(trip);
        });

        // Database setup
        DatabaseHelper.getInstance(this);

        // Edit Code
        edtExpenses = findViewById(R.id.editTextNumber);
        edtNotes = findViewById(R.id.edtNotes);
        edtDestination = findViewById(R.id.edtDestination);
        edtStartDate = findViewById(R.id.editTextDate);
        edtEndDate = findViewById(R.id.editTextDate2);

        // TextView Code
        txtExpenses = findViewById(R.id.txtSubTot);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotalExpenses = findViewById(R.id.txtTot);
    }

    Double expenses = 0.0;

    public void createTrip(){
        // Trip Planning Code //

        // Destination Input
        String destination = edtDestination.getText().toString();
        if (destination.isEmpty()) {
            Toast.makeText(this,"Please enter a Destination", Toast.LENGTH_LONG).show();
            return;
        }

        // Start Date Input
        String sD = edtStartDate.getText().toString();
        Long startDate = null;

        if (!edtStartDate.getText().toString().isEmpty()){
            try {
                startDate = Long.parseLong(sD);
            } catch (Exception e) {
                Toast.makeText(this, "Enter a valid format: yyyy/mm/dd", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Please enter a Start Date", Toast.LENGTH_LONG).show();
            return;
        }

        // End Date Input
        String eD = edtEndDate.getText().toString();
        Long endDate = null;

        if (!edtEndDate.getText().toString().isEmpty()) {
            try{
                endDate = Long.parseLong(eD);
            } catch (Exception e) {
                Toast.makeText(this, "Enter a valid format: yyyy/mm/dd", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(this, "Please enter an End Date", Toast.LENGTH_LONG).show();
            return;
        }

        // Notes Input
        String notes = edtNotes.getText().toString();
        if (notes.isEmpty()){
            Toast.makeText(this, "Please enter a Note", Toast.LENGTH_LONG).show();
            return;
        }

        // Expenses Input
        if (!edtExpenses.getText().toString().isEmpty()){
            try {
                if (expenses <= 0) {
                    expenses = Double.parseDouble(edtExpenses.getText().toString());
                }
            } catch (Exception e){
                Toast.makeText(this, "Please enter an expense", Toast.LENGTH_LONG).show();
                return;
            }

            if (expenses < 0){
                Toast.makeText(this, "Please enter a positive expense", Toast.LENGTH_LONG).show();
                return;
            }
        } else{
            Toast.makeText(this, "Please enter an expense", Toast.LENGTH_LONG).show();
            return;
        }

        Double totalExpenses = checkDiscount(expenses, 1);

        // Trip Creation
        trip = new Trip(destination, startDate, endDate, notes, totalExpenses);
    }

    public Double checkDiscount(Double Expense, Integer ID){
        Double totExpense = Expense;

//        for (User u:
//             arrUsers) {
//            if (u.getUserID() == ID){
//                if (u.getTotalTrips() >= 3) {
//                    totExpense = Expense * 0.10;
//                    updateText(Expense, totExpense, true);
//                } else{
//                    updateText(Expense, totExpense, false);
//                }
//            }
//        }

        Integer totaltrips = sharedPreferences.getInt("trips", 0);

        if (totaltrips >= 3) {
            totExpense = Expense * 0.90;
            updateText(Expense, totExpense, true);
        } else{
            updateText(Expense, totExpense, false);
        }

        return totExpense;
    }

    public void checkDiscountLive(Double Expense, Integer ID){
        Double totExpense = Expense;

//        for (User u:
//                arrUsers) {
//            if (u.getUserID() == ID && u.getTotalTrips() >= 3) {
//                totExpense = Expense * 0.90;
//                updateText(Expense, totExpense, true);
//            }
//        }

        Integer totaltrips = sharedPreferences.getInt("trips", 0);

        if (totaltrips >= 3) {
            totExpense = Expense * 0.90;
            updateText(Expense, totExpense, true);
        }

        updateText(Expense, totExpense, false);
    }

    private void updateText(Double Ex, Double finEx, Boolean Discount){
        txtExpenses.setText("Subtotal: R" + Ex.toString());
        txtTotalExpenses.setText("Total: R" + finEx.toString());

        if (Discount){
            txtDiscount.setText("Discount: 10%");
        } else{
            txtDiscount.setText("Discount: Not Applied");
        }
    }

    private void updateText(Double Ex, Double finEx){
        txtExpenses.setText("Subtotal: R" + Ex);
        txtTotalExpenses.setText("Total: R" + finEx);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        exCat = arrTripType[pos];

        if (!exCat.equals("Choose activities") && !act.contains(exCat)){
            act.add(exCat);
        }

        for (String a :
                act) {
            switch (a) {
                case "sightseeing":
                    expenses += 400;
                    break;
                case "hiking":
                    expenses += 600;
                    break;
                case "dining":
                    expenses += 900;
                    break;
                case "museum tours":
                    expenses += 1500;
                    break;
                default:
                    break;
            }
        }

        if (!edtExpenses.getText().toString().isEmpty()) {
            try {
                expenses += Double.parseDouble(edtExpenses.getText().toString());
            } catch (NumberFormatException ignored) {}
        }

        checkDiscountLive(expenses, 1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}