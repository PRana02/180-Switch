package com.example.text_to_speech;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class upload extends AppCompatActivity implements View.OnClickListener  {

    Button Upload;
    TextView file;
    public static final int PICK_FILE_REQUEST = 234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(getApplicationContext(), "Welcome to File Upload", Toast.LENGTH_SHORT).show();
        Upload = (Button) findViewById(R.id.button6);
        Upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==Upload)
        {
            Intent i = new Intent();
            i.setType("text/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select a file"), PICK_FILE_REQUEST);
        }
    }
}