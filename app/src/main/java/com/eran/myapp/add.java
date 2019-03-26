package com.eran.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class add extends AppCompatActivity {
    Button selectImage;
    private ImageView imageView;
    private int REQUEST_CODE=1;
    Uri imageuri;
    private static final int PICK_IMAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

       selectImage=(Button)findViewById(R.id.selectImage);

        imageView=(ImageView)findViewById(R.id.imageView);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"הוספת תמונה"),REQUEST_CODE);
            }
        });

        /*imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE );

            }
        });*/

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK )
        {
          imageuri =data.getData();
            try{
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
