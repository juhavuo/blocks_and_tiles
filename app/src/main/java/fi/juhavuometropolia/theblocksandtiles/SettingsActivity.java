package fi.juhavuometropolia.theblocksandtiles;

import android.provider.Settings;
import android.provider.Settings.System;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar brightnessSB;
    private int brightness;
    private TextView brightnessValuetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        brightnessSB = (SeekBar) findViewById(R.id.brightnessBar);
        brightnessSB.setMax(255);

        brightnessValuetv = (TextView) findViewById(R.id.brightnessValuetv);

        try {
            brightness = System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        }catch (Exception e){
            brightness = -1;
        }

        String brightnessInString ="" + brightness;
        brightnessValuetv.setText(brightnessInString);
        brightnessSB.setProgress(brightness);
        brightnessSB.setOnSeekBarChangeListener(this);



    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        brightnessValuetv.setText("changing...");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        brightnessValuetv.setText("starting..");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        brightnessValuetv.setText("stopping..");
    }
}
