package com.example.tripbudymobileapplication.ui;

import static android.content.Intent.ACTION_OPEN_DOCUMENT;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;
import com.example.tripbudymobileapplication.model.Memory;
import com.example.tripbudymobileapplication.model.unused.BudgetingActivity;
import com.example.tripbudymobileapplication.utils.FileHelper;

import java.io.File;

public class MemoryPostingActivity extends AppCompatActivity {

    private ImageButton btnTrips, btnHome, btnAddMem, btnBudget, btnAccount;
    private ActivityResultLauncher<Intent> pickImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memoryposting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Nav bar code
        btnHome = findViewById(R.id.btnHome);
        btnTrips = findViewById(R.id.btnTrips);
        btnAddMem = findViewById(R.id.btnNewMemory);
        btnBudget = findViewById(R.id.btnViewMem);
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

        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        handlePickedImage(uri);
                    }
                }
        );

        Button btnAddImage, btnSaveMemory;

        btnAddImage = findViewById(R.id.btnAddImage);
        btnAddImage.setOnClickListener(v -> openImagePicker());

        btnSaveMemory = findViewById(R.id.btnSaveMem);
        btnSaveMemory.setOnClickListener(v -> {

            EditText edtMemory = findViewById(R.id.edtMemory);
            String text = edtMemory.getText().toString();

            if (text.isEmpty() || selectedImagePath == null){
                Toast.makeText(this, "Add text and image", Toast.LENGTH_SHORT).show();
                return;
            }

            Memory memory = new Memory(
                    text
                    ,selectedImagePath
                    ,"selectedMP3Path" // TODO: You know...
                    ,System.currentTimeMillis()
            );

            // TODO: Add Database code here

            Toast.makeText(this, "Memory Saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

//    public void openSoundPicker(){
//        // Sound picker code
//        Intent intent = new Intent(ACTION_OPEN_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("")
//    }
//
//    String selectedMP3Path;
//
//    private void handlePickedSound(Uri uri){
//
//    }

    public void openImagePicker(){
        // Image Picker code
        Intent intent = new Intent(ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*"); // Any Image file

        pickImageLauncher.launch(intent);
    }

    String selectedImagePath;

    private void handlePickedImage(Uri uri) {
        String relativePath = FileHelper.saveImageFromUri(this, uri);
        if (relativePath != null) {
            // Save path temporarily (until user saves memory)
            selectedImagePath = relativePath;

            ImageView preview = findViewById(R.id.imageView);
            File file = new File(getFilesDir(), relativePath);

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            preview.setImageBitmap(bitmap);
        }
    }
}