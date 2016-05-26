package com.jk.accelerometertest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private final static Integer SENSOR_DELAY_CUSTOM = 800000; // This should be set to highest possible value tolerable to your applicaiton to conserve battery.
    private final static String TAG = "MainActivity";

    public void createText(Integer view, String text){
        LinearLayout ll = (LinearLayout) findViewById(view);
        TextView tv = new TextView(this);
        tv.setText(text);
        ll.addView(tv);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float accX = event.values[0];
        float accY = event.values[1];
        float accZ = event.values[2];

        //pos gravity threshold
        float XlimMax = 10.0f;
        float YlimMax = 10.0f;
        float ZlimMax = 10.0f;

        //neg gravity threshold
        float XlimMin = -10.0f;
        float YlimMin = -10.0f;
        float ZlimMin = -10.0f;

        if(accX > XlimMax || accX < XlimMin) {
            Log.d(TAG, "X:"+accX);
            createText(R.id.myVerticalContainer1, "X:"+accX);
        }

        if(accY > YlimMax || accY < YlimMin) {
            Log.d(TAG, "Y:"+accY);
            createText(R.id.myVerticalContainer2, "Y:"+accY);
        }

        if(accZ > ZlimMax || accZ < ZlimMin) {
            Log.d(TAG, "Z:"+accZ);
            createText(R.id.myVerticalContainer3, "Z:"+accZ);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate!");

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            // Success! There's a accelerometer.
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else {
            // Failure! No accelerometer.
            Log.d(TAG, "No Accelerometer found");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SENSOR_DELAY_CUSTOM);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(this);
    }

}

