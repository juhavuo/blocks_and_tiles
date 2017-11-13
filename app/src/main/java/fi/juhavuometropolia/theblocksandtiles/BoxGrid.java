package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by JuhaVuokko on 15.7.2017.
 */

public class BoxGrid {

    private Bitmap[][] bitmapGrid;
    private Bitmap emptymap;
    private int xUp, yUp, boxLength, gridwidth, gridheight;

    public BoxGrid(int gridwidth, int gridheight, int upperX, int upperY, int boxL, Bitmap emptymap){

        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        bitmapGrid = new Bitmap[gridwidth][gridheight];
        xUp = upperX;
        yUp = upperY;
        boxLength = boxL;
        this.emptymap = emptymap;

        for(int i = 0; i < bitmapGrid.length;++i) {
            for (int j = 0; j < bitmapGrid[i].length; ++j) {
                bitmapGrid[i][j] = emptymap;
            }
        }


    }

    public void addBoxes(ArrayList<Bitmap> maplist){
        int index = 0;
        for(int j = 0; j < bitmapGrid[0].length;++j){
            for(int i = 0; i < bitmapGrid.length;++i){
                if(index < maplist.size()){
                    bitmapGrid[i][j] = maplist.get(index);
                    ++index;
                }
            }
        }

    }

    public Bitmap getBitmapInPosition(float x, float y){

        if(x > xUp && x < (xUp + boxLength*(bitmapGrid.length)) && y > yUp && y < (yUp + boxLength*(bitmapGrid[0].length))){
            int xCoordinate = (int)((x-xUp)/boxLength);
            int yCoordinate = (int)((y-yUp)/boxLength);
            return bitmapGrid[xCoordinate][yCoordinate];
        }else{
            return null;
        }
    }

    public void putBitmapToPosition(Bitmap mapToInsert, float x, float y){
        if(x > xUp && x < (xUp + boxLength*(bitmapGrid.length)) && y > yUp && y < (yUp + boxLength*(bitmapGrid[0].length))){
            int xCoordinate = (int)((x-xUp)/boxLength);
            int yCoordinate = (int)((y-yUp)/boxLength);
            bitmapGrid[xCoordinate][yCoordinate] = mapToInsert;
        }
    }

    public void drawGrid(Canvas c){
        for(int i = 0; i < bitmapGrid.length;++i) {
            for (int j = 0; j < bitmapGrid[i].length; ++j) {
                c.drawBitmap(bitmapGrid[i][j], xUp+i*boxLength, yUp+j*boxLength, null);
            }
        }
    }

    public boolean isInArea(float x, float y){
        if(x > xUp && x < (xUp + boxLength*gridwidth) && y > yUp && y < (yUp + boxLength*gridheight)){
            return true;
        }else{
            return false;
        }
    }
}
