package fi.juhavuometropolia.theblocksandtiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements View.OnTouchListener, Runnable {

    private int width, height;

    private GameView gameView;
    private Box movedBox;
    //private BoxArray boxArray;
    private boolean canIRun, touchIsOn;
    private XMLHandler xmlHandler;
    private Hammer hammer;
    private boolean boxplacingtime;
    private float[] modechooserCoordinates;
    private BoxGrid boxGridChoose, boxGridBuild;
    private Toolbar toolbar;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent receivingIntent = getIntent();
        int screenwidth = receivingIntent.getIntExtra("screenwidth",0);
        int screenheight = receivingIntent.getIntExtra("screenheight",0);

        Bitmap emptymap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.empty);
        Bitmap choosermap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.boxplacingtime);

        xmlHandler = new XMLHandler(PlayActivity.this,R.raw.drawingresources);
        // boxArray = xmlHandler.loadArray("boxarray1");
        ArrayList<Bitmap> maplist = xmlHandler.loadBitmapArray("boxarray1");
        int length = xmlHandler.getBitmapSize("boxarray1");
        int gridwidth = screenwidth/length;
        int gridheight = 5;
        int upperX = (screenwidth%length)/2;
        int upperY = 50;

        Log.i(MainActivity.TAG,"Before creating boxgrids");

        boxGridChoose = new BoxGrid(gridwidth,gridheight,upperX,upperY,length, emptymap);
        boxGridChoose.addBoxes(maplist);
        boxGridBuild = new BoxGrid(gridwidth,gridheight,upperX,upperY,length, emptymap);

        int toolbarX = upperX;
        int toolbarY = upperY+(gridheight)*length;
        toolbar = new Toolbar(PlayActivity.this,toolbarX,toolbarY,length,gridwidth,emptymap,choosermap);
        movedBox = null;


        modechooserCoordinates = xmlHandler.getCoordinates("modechooser");


        Log.i(MainActivity.TAG,"Before gameview initialization");


        gameView = new GameView(this,length,screenwidth,screenheight);
        setContentView(gameView);
        gameView.setOnTouchListener(this);


        canIRun = false;
        thread = null;
        touchIsOn = false;
        boxplacingtime = true;

        //gameView.gatherBoxes(boxes);


    }

    @Override
    protected void onResume(){
        super.onResume();
        resume();

    }

    @Override
    protected void onPause(){
        super.onPause();
        pause();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(toolbar.isInArea(x,y)){
                toolbar.setAction(x,y);
                int actioncode = toolbar.getActionCode();
                Log.i(MainActivity.TAG,"actioncode: " +actioncode);
                if(actioncode == 0){
                    boxplacingtime = !boxplacingtime;
                }
            }

            if(boxplacingtime){
                if(boxGridBuild.isInArea(x,y)){
                    boxGridBuild.putBitmapToPosition(toolbar.getChosenBitmap(),x,y);
                }
            }else{
                if(boxGridChoose.isInArea(x,y)){
                    toolbar.changeBitmap(boxGridChoose.getBitmapInPosition(x,y));
                }
            }

        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(boxplacingtime){
                if(boxGridBuild.isInArea(x,y)){
                    boxGridBuild.putBitmapToPosition(toolbar.getChosenBitmap(),x,y);
                }
            }

        }
        if(event.getAction() == MotionEvent.ACTION_UP){

        }

        return true;
    }

    @Override
    public void run() {
        while(canIRun){
            try{
                thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            gameView.drawToSurface(toolbar,boxGridChoose,boxGridBuild,boxplacingtime);
        }
    }

    public void pause(){
        canIRun = false;
        boolean running = true;
        while(running) {
            try {
                thread.join();
                running = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume(){
        canIRun = true;
        thread = new Thread(this);
        thread.start();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        Log.i(MainActivity.TAG,"onSaveInstance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstaceState){

        super.onRestoreInstanceState(savedInstaceState);
        Log.i(MainActivity.TAG,"onRestoreInstance");
    }

    @Override
    public void onBackPressed(){
        savePreferences();
        super.onBackPressed();


    }

    public void savePreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);


    }
}
