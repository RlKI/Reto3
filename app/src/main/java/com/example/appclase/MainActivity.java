package com.example.appclase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int currentImageIndex = 0;
    private ImageView image;
    private List<Bitmap> imagesList = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
    }


    public void click1(View view) {
        Toast.makeText(this, "Tomar foto", Toast.LENGTH_LONG).show();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void clickPrev(View view) {
        if (imagesList.size() > 1) {
            currentImageIndex--;
            if (currentImageIndex < 0) {
                currentImageIndex = imagesList.size() - 1;
            }
            image.setImageBitmap(imagesList.get(currentImageIndex));
        }
    }

    public void clickNext(View view) {
        if (imagesList.size() > 1) {
            currentImageIndex++;
            if (currentImageIndex > imagesList.size() - 1) {
                currentImageIndex = 0;
            }
            image.setImageBitmap(imagesList.get(currentImageIndex));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "Here is your picture", Toast.LENGTH_LONG).show();

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagesList.add(imageBitmap);
            currentImageIndex = imagesList.size() - 1;
            image.setImageBitmap(imageBitmap);
        }
    }
}
