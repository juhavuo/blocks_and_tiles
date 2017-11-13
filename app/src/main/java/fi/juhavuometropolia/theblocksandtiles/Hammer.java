package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by JuhaVuokko on 11.7.2017.
 */

public class Hammer {

    private float x,y;
    private int size;
    private Bitmap hammerOnBitmap, hammerOffBitmap, showedBitmap;
    private boolean hammertime;

    public Hammer(Context context,float x, float y){
        this.x = x;
        this.y = y;
        hammerOffBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.hammer);
        hammerOnBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.hammeron);
        showedBitmap = hammerOffBitmap;
        hammertime = false;
        size = 100;

    }

    public boolean getHammertime(){
        return hammertime;
    }

    public boolean hammerCheck(float xTouched, float yTouched){
        if(xTouched >= x && (xTouched <= x + size) && yTouched >= y && yTouched <= (y + size)){
            hammertime = !hammertime;
        }
        if (hammertime){
            showedBitmap = hammerOnBitmap;
        }else{
            showedBitmap = hammerOffBitmap;
        }

        return hammertime;
    }

    public Bitmap getShowedBitmap(){
        return showedBitmap;
    }
}
