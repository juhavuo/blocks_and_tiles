package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by JuhaVuokko on 18.7.2017.
 */

public class Toolbar {

    private float upperX, upperY;
    private int boxAmount, actionCode, lengthOfBox, chosenBitmap;
    private Bitmap[] bitmaps;
    private boolean[] markers;
    private Context context;
    private Paint paintOfChosen, paintOfUnchosen;

    public Toolbar(Context context, float xUp, float yUp, int boxlength, int amountOfSpaces, Bitmap empty, Bitmap chooser){
        this.context = context;
        upperX = xUp;
        upperY = yUp;
        boxAmount = amountOfSpaces;
        bitmaps = new Bitmap[boxAmount];
        markers = new boolean[boxAmount-1];
        for(int i = 0; i< markers.length; ++i){
            markers[i] = false;
        }
        markers[0] = true;
        chosenBitmap = 1;
        paintOfChosen = new Paint();
        paintOfChosen.setColor(0xff11ff11);
        paintOfUnchosen = new Paint();
        paintOfUnchosen.setColor(0xffffffff);


        bitmaps[0] = chooser;
        for (int i = 1; i < bitmaps.length;++i){
            bitmaps[i] = empty;
        }

        lengthOfBox = boxlength;
        actionCode = -1;
    }

    public void changeBitmap(Bitmap bitmap){
       bitmaps[chosenBitmap] = bitmap;
    }

    public void resetActionCode(){
        actionCode = -1;
    }

    public void setAction(float x, float y){
        if(isInArea(x,y)){
            actionCode = (int)(x-upperX)/lengthOfBox;
            if(actionCode != 0){
                for(int i = 0; i< markers.length; ++i){
                    markers[i] = false;
                }
                markers[actionCode-1] = true;
                chosenBitmap = actionCode;

            }
        }
    }

    public boolean isInArea(float x, float y){
        if(x > upperX && x < (upperX + lengthOfBox*bitmaps.length) && y > upperY && y < (upperY + lengthOfBox)){
            return true;
        }else{
            return false;
        }
    }

    public float getXCoordinateOfBitmap(int index){
        return upperX+index*lengthOfBox;
    }

    public int getActionCode(){
        return actionCode;
    }

    public Bitmap getChosenBitmap(){
        return bitmaps[chosenBitmap];
    }



    public void drawToolbar(Canvas c){
        for (int i = 0; i < bitmaps.length; ++i){
            c.drawBitmap(bitmaps[i],getXCoordinateOfBitmap(i),upperY,null);
        }
        for (int i = 0; i< markers.length; ++i){
            if(markers[i] == true){
                c.drawRect(upperX+(i+1)*lengthOfBox,upperY+lengthOfBox,upperX+(i+2)*lengthOfBox,upperY+2*lengthOfBox,paintOfChosen);
            }else {
                c.drawRect(upperX+(i+1)*lengthOfBox,upperY+lengthOfBox,upperX+(i+2)*lengthOfBox,upperY+2*lengthOfBox,paintOfUnchosen);
            }
        }
    }


}
