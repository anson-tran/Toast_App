package com.trananson.toast_app;

import android.util.Log;
import android.content.Context;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView v1, v2, v3, v4;
    View.OnClickListener listener;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SeekBar seekbar;
    int viewId;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = findViewById(R.id.firstView);
        v2 = findViewById(R.id.secondView);
        v3 = findViewById(R.id.thirdView);
        v4 = findViewById(R.id.fourthView);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView x = (TextView)v;
                viewId = x.getId();
                text = x.getText().toString();
                text = text.substring(0, text.length()-1);
                int currentCount = sharedPreferences.getInt(""+viewId, 0);
                currentCount++;
                editor.putInt(""+viewId, currentCount);
                editor.apply();
                Toast toast = Toast.makeText(getApplicationContext(),text + " has been clicked " + currentCount + " times!",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        };
        v1.setOnClickListener(listener);
        v2.setOnClickListener(listener);
        v3.setOnClickListener(listener);
        v4.setOnClickListener(listener);
        seekbar = findViewById(R.id.simpleSeekBar);
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                v1.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

