package fi.juhavuometropolia.theblocksandtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playButton, settingsButton;
    public static final String TAG = "fi.juhavuometropolia.SC";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "MainActivity onCreate");

        playButton = (Button) findViewById(R.id.playButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);




        playButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

    }





    public void onClick(View v){

        switch (v.getId()){
            case R.id.playButton:
                Intent chooserIntent = new Intent(this,ChooserActivity.class);
                startActivity(chooserIntent);
                break;
            case R.id.settingsButton:
                Intent settingsIntent = new Intent(this,SettingsActivity.class);
                startActivity(settingsIntent);
                break;

        }
    }
}
