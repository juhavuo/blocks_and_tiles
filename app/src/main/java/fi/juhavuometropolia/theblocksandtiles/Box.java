package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JuhaVuokko on 6.6.2017.
 */

public class Box implements Parcelable {

    private float x,y;
    private int width, height, bitmapID;
    private Bitmap bitmap;
    private Context context;

    public Box(Parcel input){
        this.x = input.readFloat();
        this.y = input.readFloat();
        this.width = input.readInt();
        this.height = input.readInt();
        this.bitmapID = input.readInt();

    }

    public Box(float x, float y, int width, int height, int bitmapID){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bitmapID = bitmapID;

    }

    public void createBitmap(Context context){
        this.bitmap = BitmapFactory.decodeResource(context.getResources(),this.bitmapID);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void changePosition(float xNew, float yNew){
        x = xNew;
        y = yNew;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getBitmapID(){
        return this.bitmapID;
    }

    public void drawBitmap(Canvas c){

        c.drawBitmap(this.getBitmap(), this.x, this.y, null);
    }

    public boolean touched(float touchX, float touchY){

        boolean touched = false;

        if((touchX>this.x&&touchX<this.x+this.width)&&(touchY>this.y&&touchY<this.y+this.height)){
            touched = true;
        }

        return touched;
    }

    public String toString(){
        return "box at (" + this.x +"," + this.y +"), bitmapID:" + bitmapID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(this.y);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.bitmapID);
    }

    public static final Parcelable.Creator<Box> CREATOR = new Parcelable.Creator<Box>(){

        @Override
        public Box createFromParcel(Parcel source) {
            return new Box(source);
        }

        @Override
        public Box[] newArray(int size) {
            return new Box[size];
        }
    };
}
