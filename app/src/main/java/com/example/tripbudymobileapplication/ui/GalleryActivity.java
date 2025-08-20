package com.example.tripbudymobileapplication.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
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
import com.example.tripbudymobileapplication.database.DatabaseHelper;
import com.example.tripbudymobileapplication.database.dao.MemoryDao;
import com.example.tripbudymobileapplication.database.model.Memory;
import com.example.tripbudymobileapplication.utils.BackgroundMusicManager;

import java.util.ArrayList;
import java.util.List;

import androidx.gridlayout.widget.GridLayout;

public class GalleryActivity extends AppCompatActivity {

    private ImageButton btnTrips, btnHome, btnAddMem, btnViewMemory, btnAccount;
    private List<Memory> memories = new ArrayList<>();

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

        // Database setup
        DatabaseHelper.getInstance(this);

        // Background music to play
        BackgroundMusicManager.play(this);

        // Populate memories
        MemoryDao memoryDao = new MemoryDao(this);
        memories = memoryDao.getAllMemories();

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
            container.setOnClickListener(v -> {
                Intent intent = new Intent(GalleryActivity.this, MemoryPlayerActivity.class);
                intent.putExtra("imgPath", m.getImgPath());
                intent.putExtra("mp3Path", m.getMp3Path());
                intent.putExtra("caption", m.getCaption());
                startActivity(intent);
            });

            // Load the image
            Bitmap bitmap = BitmapFactory.decodeFile(m.getImgPath());
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        BackgroundMusicManager.stop();
    }
}