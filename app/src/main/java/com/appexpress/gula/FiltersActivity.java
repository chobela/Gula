package com.appexpress.gula;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FiltersActivity extends AppCompatActivity {

    private static final String TAG = "FiltersActivity";

    //widgets
    private Button mSave;
    private EditText mCity;
    private ImageView mBackArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        mSave = (Button) findViewById(R.id.btnSave);
        mCity = (EditText) findViewById(R.id.input_city);
        mBackArrow = (ImageView) findViewById(R.id.backArrow);

        init();

    }

    private void init(){

        getFilterPreferences();

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: saving...");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FiltersActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                Log.d(TAG, "onClick: city: " + mCity.getText().toString());
                editor.putString(getString(R.string.preferences_city), mCity.getText().toString());
                editor.commit();
                Toast.makeText(FiltersActivity.this, "Saved", Toast.LENGTH_SHORT).show();

            }
        });

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back.");
                finish();
            }
        });
    }

    private void getFilterPreferences(){
        Log.d(TAG, "getFilterPreferences: retrieving saved preferences.");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String city = preferences.getString(getString(R.string.preferences_city), "");

        mCity.setText(city);
    }
}
