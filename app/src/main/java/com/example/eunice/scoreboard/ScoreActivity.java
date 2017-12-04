package com.example.eunice.scoreboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private Button homebutton,visitorbutton, save, clear;
    private TextView homeScreen, visitorScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        homebutton = (Button)findViewById(R.id.homebutton);
        visitorbutton = (Button)findViewById(R.id.visitorbutton);
        save = (Button)findViewById(R.id.save);
        clear = (Button)findViewById(R.id.clear);
        homeScreen = (TextView)findViewById(R.id.homescreen);
        visitorScreen = (TextView)findViewById(R.id.visitorscreen);

        homebutton.setOnClickListener(this);
        visitorbutton.setOnClickListener(this);
        save.setOnClickListener(this);
        clear.setOnClickListener(this);

        homeScreen.setText("00");
        visitorScreen.setText("00");
    }

    @Override
    public void onClick(View v) {
        String homeCurrentText = homeScreen.getText().toString();
        String visitorCurrentText = visitorScreen.getText().toString();

        switch (v.getId()) {

            case R.id.homebutton:
                int homeUpdate = (Integer.valueOf(homeCurrentText) + 1);
                homeScreen.setText(String.valueOf(homeUpdate));
                break;

            case R.id.visitorbutton:
                int visitorUpdate = (Integer.valueOf(visitorCurrentText) + 1);
                visitorScreen.setText(String.valueOf(visitorUpdate));
                break;

            case R.id.save:
                SharedPreferences sharedPreferences = getSharedPreferences("Scores", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("homeScores", homeCurrentText);
                editor.putString("visitorScores", visitorCurrentText);
                editor.apply();
                Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
                super.onStart();
                String homeScore = sharedPreferences.getString("homeScores", homeCurrentText);
                String visitorScore = sharedPreferences.getString("visitorScores", visitorCurrentText);
                homeScreen.setText(homeScore);
                visitorScreen.setText(visitorScore);
                break;
            case R.id.clear:
                homeScreen.setText("00");
                visitorScreen.setText("00");
                break;
            default:
                homeScreen.setText("00");
                visitorScreen.setText("00");
                break;

        }

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        homeScreen.setText(savedInstanceState.getString("homeScores"));
        visitorScreen.setText(savedInstanceState.getString("visitorScores"));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("homeScores", homeScreen.getText().toString());
        outState.putString("visitorScores", visitorScreen.getText().toString());

        super.onSaveInstanceState(outState);

    }
    }



