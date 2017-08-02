package com.example.aakashb.demo_cameraapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here, we are making a folder named picFolder to store
        // pics taken by the camera using this application.


        Button capture = (Button) findViewById(R.id.cameraButton);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise.
                count++;
                String file = count + ".jpg";
                File newfile = new File(dir, file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                }


                Uri outputFileUri = FileProvider.getUriForFile(v.getContext() ,((Activity) v.getContext()).getPackageName(), newfile);



                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("ResultOK", String.valueOf(RESULT_OK));
            Log.d("CameraDemo", "Pic saved");
        }
    }
}
