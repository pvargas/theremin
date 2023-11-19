package com.quadbytes.theremin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MotionEvent;
//import android.support.v7.app.AppCompatActivity;


import com.quadbytes.theremin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Used to load the 'theremin' library on application startup.
    static {
        System.loadLibrary("theremin");
    }

    private native void toggleSound(boolean soundOn);

    private native void startEngine();

    private native void stopEngine();
    private native void changePitch(float multiplier);

    private ActivityMainBinding binding;

    Button toggleButton;
    Boolean soundOn = false;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
        startEngine();
        toggleButton = (Button) findViewById(R.id.toggleButton);
        this.toggleButton.setOnClickListener(v -> {
            if (soundOn) {
                toggleSound(false);
                soundOn = false;
                Log.d("BUTTONS", "User turned sound off");
            } else {
                toggleSound(true);
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
        stopEngine();
        super.onDestroy();
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float sensorVal = event.values[0];
        Log.d("BUTTONS", "sensorVal: " + sensorVal);
        // Do something with this sensor data.
        changePitch(sensorVal);
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
}