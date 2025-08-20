package com.example.tripbudymobileapplication.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tripbudymobileapplication.R;

import java.io.IOException;

public class MemoryPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Boolean isPlaying = true;
    private ImageButton playPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memory_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView fullScreenImage = findViewById(R.id.fullScreenImage);
        TextView memoryCaption = findViewById(R.id.memoryCaption);
        playPauseButton = findViewById(R.id.btnMediaControl);

        // Get data from intent
        String imgPath = getIntent().getStringExtra("imgPath");
        String mp3Path = getIntent().getStringExtra("mp3Path");
        String caption = getIntent().getStringExtra("caption");

        // Load image
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        if (bitmap != null) {
            fullScreenImage.setImageBitmap(bitmap);
        } else {
            fullScreenImage.setImageResource(R.drawable.placeholder_foreground);
        }

        // Set caption
        memoryCaption.setText(caption);

        // Play music
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(mp3Path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Pause/Play toggle
        playPauseButton.setOnClickListener(v -> {
            if (mediaPlayer != null){
                if (isPlaying){
                    mediaPlayer.pause();
                    playPauseButton.setImageResource(R.drawable.media_player_play);
                } else{
                    mediaPlayer.start();
                    playPauseButton.setImageResource(R.drawable.media_player_pause);
                }
                isPlaying = !isPlaying;
            }
        });

        // Close on image tap
        fullScreenImage.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}