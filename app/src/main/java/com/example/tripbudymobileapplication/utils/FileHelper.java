package com.example.tripbudymobileapplication.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.tripbudymobileapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileHelper {

    public static String saveImageFromUri(Context context, Uri uri){
        String filename = "memories_" + UUID.randomUUID().toString() + ".jpg";
        File dir = new File(context.getFilesDir(), "memories");

        if (!dir.exists()){
            dir.mkdirs();
        }

        File outfile = new File(dir, filename);

        try (InputStream in = context.getContentResolver().openInputStream(uri);
             OutputStream out = new FileOutputStream(outfile)) {

            byte[] buffer = new byte[4096];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            return "memories/" + filename; // Relative path for DB
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
