package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import java.util.ArrayList;

/**
 * Created by JuhaVuokko on 1.6.2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;


    private float hammerX, hammerY, modechooserX, modechooserY;
    private Bitmap hammeroff, hammeron, modechoosermap;




    public GameView(Context context, int boxLength , int width, int height) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        Log.i(MainActivity.TAG,"Gameview created, dimensions:" + width + "," + height);


    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    public void drawToSurface(Toolbar toolbar, BoxGrid available, BoxGrid placeable, boolean boxplacingtime) {

        if(holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawARGB(255, 240, 227, 153);


            /*
            if(hammertime) {
                canvas.drawBitmap(hammeron, hammerX, hammerY, null);
            }else{
                canvas.drawBitmap(hammeroff, hammerX, hammerY, null);
            }*/
            toolbar.drawToolbar(canvas);
            if(boxplacingtime) {
                placeable.drawGrid(canvas);
            }else{
                available.drawGrid(canvas);
            }




            /*for (int i = 0; i < boxes.size(); ++i) {
                Box box = boxes.get(i);
                canvas.drawBitmap(box.getBitmap(), box.getX(), box.getY(), null);
            }*/
            holder.unlockCanvasAndPost(canvas);
        }


    }






}
