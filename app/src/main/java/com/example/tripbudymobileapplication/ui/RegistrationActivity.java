package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;
import com.example.tripbudymobileapplication.database.DatabaseHelper;

public class RegistrationActivity extends AppCompatActivity {

    private ImageButton btnTrips, btnHome, btnAddMem, btnViewMemory, btnAccount;
    private SharedPreferences sharedPreferences;
    String email, username;
    private TextView txt1, txt2;
    private Integer trips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);

        // Database setup
        DatabaseHelper.getInstance(this);

        // Login code
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtEmail = findViewById(R.id.edtEmail);

        if (!sharedPreferences.getBoolean("loggedin", false)) {
            // Activity setup
            edtUsername.setVisibility(View.VISIBLE);
            edtEmail.setVisibility(View.VISIBLE);
            txt1.setVisibility(View.VISIBLE);
            txt2.setVisibility(View.VISIBLE);
            btnLogin.setText("Login");

            // Login logic
            btnLogin.setOnClickListener(v -> {
                if (edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    email = edtEmail.getText().toString();
                }

                if (edtUsername.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    username = edtUsername.getText().toString();
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("email", email);
                editor.putBoolean("loggedin", true);
                editor.putInt("trips", trips); // TODO: Database Code
                editor.apply();

                btnHome.callOnClick();
            });
        } else{
            // Activity setup
            edtUsername.setVisibility(View.INVISIBLE);
            edtEmail.setVisibility(View.INVISIBLE);
            txt1.setVisibility(View.INVISIBLE);
            txt2.setVisibility(View.INVISIBLE);

            // Actual logout logic
            btnLogin.setText("Logout");
            btnLogin.setOnClickListener(v -> logout());
        }

    }

    private void logout(){
        if (sharedPreferences.getBoolean("loggedin", true)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("loggedin", false);
            editor.apply();

            btnHome.callOnClick();
        }
    }
}