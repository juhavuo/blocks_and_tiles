package fi.juhavuometropolia.theblocksandtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class ChooserActivity extends AppCompatActivity implements View.OnClickListener{

    private Button save1Button, save2Button, save3Button;
    private int screenwidth, screenheight;
    private String fileName1, fileName2, fileName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        fileName1 = "blocksSave1";
        fileName2 = "blocksSave2";
        fileName3 = "blocksSave3";

        save1Button = (Button)findViewById(R.id.save1Button);
        save2Button = (Button)findViewById(R.id.save2Button);
        save3Button = (Button)findViewById(R.id.save3Button);

        save1Button.setOnClickListener(this);
        save2Button.setOnClickListener(this);
        save3Button.setOnClickListener(this);

    }

    @Override
    protected void onStart(){
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenheight = displayMetrics.heightPixels;
        screenwidth = displayMetrics.widthPixels;

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        File file;
        String fileName = "";

        switch (v.getId()){
            case R.id.save1Button:
                fileName = fileName1;
                break;
            case R.id.save2Button:
                fileName = fileName2;
                break;
            case R.id.save3Button:
                fileName = fileName3;
                break;
        }

        if(id == R.id.save1Button || id == R.id.save2Button || id == R.id.save3Button){

            file = new File(getApplicationContext().getFilesDir(),fileName);
            try{
                if(!file.exists()){
                    file.createNewFile();
                    Log.i(MainActivity.TAG,"File was created.");
                }
            }catch (IOException ioe){
                Log.i(MainActivity.TAG,ioe.toString());
            }

            Intent playIntent = new Intent(this,PlayActivity.class);
            playIntent.putExtra("screenwidth",screenwidth);
            playIntent.putExtra("screenheight",screenheight);
            startActivity(playIntent);
        }

    }
}