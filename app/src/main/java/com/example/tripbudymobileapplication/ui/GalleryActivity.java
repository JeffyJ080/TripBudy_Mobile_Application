package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;
import com.example.tripbudymobileapplication.model.Memory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GalleryActivity extends AppCompatActivity {

    private ImageButton btnTrips, btnHome, btnAddMem, btnViewMemory, btnAccount;
    private ArrayList<Memory> memories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
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

        btnViewMemory.setOnClickListener(v -> {
            Intent in = new Intent(this, GalleryActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        btnAccount.setOnClickListener(v -> {
            Intent in = new Intent(this, RegistrationActivity.class);
            startActivity(in);
            overridePendingTransition(0, 0);
        });

        // Populate memories
        // TODO: Database code here

        // Gallery Code
        GridLayout galleryGrid = findViewById(R.id.glGallery);

        for (Memory m :
                memories) {
            // Container for each memory
            LinearLayout container = new LinearLayout(this);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setGravity(Gravity.CENTER);
            container.setPadding(8, 8, 8, 8);

            // Imageview
            ImageView view = new ImageView(this);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Load the image
            File file = new File(getFilesDir(), m.getImgPath());
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bitmap != null){
                view.setImageBitmap(bitmap);
            } else{
                view.setImageResource(R.drawable.placeholder_foreground);
            }

            // Caption textview
            TextView text = new TextView(this);
            text.setText(m.getCaption());
            text.setGravity(Gravity.CENTER);
            text.setTextSize(14);

            // Layout parameters for image
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            int sizeInDP = 150;
            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, sizeInDP, getResources().getDisplayMetrics());
            params.width = px;
            params.height = px;
            view.setLayoutParams(params);

            // Add views to container
            container.addView(view);
            container.addView(text);

            // Add container to galleryGrid
            galleryGrid.addView(container);
        }
    }
}