package com.quadbytes.theremin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ToggleButton;
//import android.support.v7.app.AppCompatActivity;


import com.quadbytes.theremin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Used to load the 'theremin' library on application startup.
    static {
        System.loadLibrary("theremin");
    }

    private MainViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    Button soundToggleButton;
//    ToggleButton scaleToggleButton;
    Boolean soundOn = false;
    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        Sounds.startEngine();
        soundToggleButton = (Button) findViewById(R.id.soundToggleButton);
//        scaleToggleButton = (ToggleButton) findViewById(R.id.scaleToggleButton);
//        scaleToggleButton.setTextOff("Minor Scale");
//        scaleToggleButton.setTextOn("Major Scale");
//        scaleToggleButton.setChecked(scaleToggleButton.isChecked());
        this.soundToggleButton.setOnClickListener(v -> {
            if (soundOn) {
                Sounds.toggleSound(false);
                soundOn = false;
                Log.d("BUTTONS", "User turned sound off");
            } else {
                Sounds.toggleSound(true);
                soundOn = true;
                Log.d("BUTTONS", "User turned sound on");
            }
        });



        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onDestroy() {
        Sounds.stopEngine();
        super.onDestroy();
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float sensorVal = event.values[0];
//        Log.d("BUTTONS", "sensorVal: " + sensorVal);
        // Do something with this sensor data.
//        Sounds.changePitch(sensorVal);
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    // end sensor

    @Override
    public void onBackPressed() {
        finish();
    }
}