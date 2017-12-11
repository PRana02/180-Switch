package com.example.text_to_speech;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import static android.R.attr.name;

public class speech2text extends AppCompatActivity {

    private TextView res ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech2text);
        res = (TextView) findViewById(R.id.textView);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this,"Welcome to speech to text conversion",Toast.LENGTH_SHORT).show();

        }
        public void buttonclick(View view) {

            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            //i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");

            if(getIntent().resolveActivity(getPackageManager())!=null)
            {
                startActivityForResult(i,10);
            }
            else
            {
                Toast.makeText(this, "Nah Not possible", Toast.LENGTH_SHORT).show();
            }

        }

        public void onActivityResult(int request_code,int result_code,Intent i)
        {
            super.onActivityResult(request_code,result_code,i);

            switch(request_code)
            {
                case 10: if(result_code == RESULT_OK && i!=null)
                {
                    ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    res.setText(result.get(0));
                }
                break;
            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.mic:
                Intent t2s = new Intent(android.content.Intent.ACTION_VIEW);
                t2s.setData(Uri.parse("http://searchmobilecomputing.techtarget.com/definition/text-to-speech"));
                startActivity(t2s);
                return true;
            case R.id.help:
                Intent help = new Intent(android.content.Intent.ACTION_VIEW);
                help.setData(Uri.parse("http://searchmobilecomputing.techtarget.com/definition/text-to-speech"));
                startActivity(help);

                return true;
            case R.id.name:
                new AlertDialog.Builder(this).setTitle(R.string.nameinfo)
                        .setMessage(R.string.nInfo).setCancelable(false)
                        .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            case R.id.exit:
                new AlertDialog.Builder(this).setTitle(R.string.exit).setMessage(R.string.exitCon).setCancelable(true)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                System.exit(0);
                            }
                        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
//        return false;
    }
}
