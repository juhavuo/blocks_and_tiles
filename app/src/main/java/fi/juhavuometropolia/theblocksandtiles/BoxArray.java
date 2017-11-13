package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import java.util.ArrayList;

/**
 * Created by JuhaVuokko on 8.6.2017.
 */

public class BoxArray{

    private ArrayList<Box> boxlist;
    private int length;
    private Context context;
    private int indexOfTouched;

    public BoxArray(Context context, int length) {
        boxlist = new ArrayList<>();
        this.length = length;
        this.context = context;
        this.indexOfTouched = 0;
    }

    public void addBox(float x, float y, int bitmapID) {
        Box addedBox = new Box(x, y, length, length, bitmapID);
        addedBox.createBitmap(context);
        boxlist.add(addedBox);
    }

    public Box getBox(int index){
        return boxlist.get(index);
    }

    public void drawBoxes(Canvas c) {
        for (int i = 0; i < boxlist.size(); ++i) {
            boxlist.get(i).drawBitmap(c);
        }

    }

    public boolean touchingBox(float touchX, float touchY) {
        boolean touchingBox = false;

        for (int i = 0; i < boxlist.size(); ++i) {
            if (boxlist.get(i).touched(touchX, touchY)) {
                touchingBox = true;
                indexOfTouched = i;
                break;
            }
        }

        return touchingBox;
    }

    public Box giveCopyOfTouchedBox(){

        float x = boxlist.get(indexOfTouched).getX();
        float y = boxlist.get(indexOfTouched).getY();
        int bitmapID = boxlist.get(indexOfTouched).getBitmapID();
        Box copiedBox = new Box(x,y,this.length,this.length,bitmapID);
        copiedBox.createBitmap(this.context);
        return copiedBox;

    }

    /*
    public void moveTouchedBox(float xNew, float yNew) {
        boxlist.get(indexOfTouched).changePosition(xNew, yNew);
    }*/

    public int getLength() {
        return this.length;
    }

    public ArrayList<Box> getBoxlist(){
        return boxlist;
    }

    public int getSize(){
        return this.boxlist.size();
    }

    public String toString(){
        String printedString = "";

        for (int i = 0; i < boxlist.size(); ++i){
            printedString += boxlist.get(i).toString();

            if(i<(boxlist.size()-1)){
                printedString += "\n";
            }
        }

        return printedString;
    }

    /*
    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }



    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Object createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };
    */
}
