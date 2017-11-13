package fi.juhavuometropolia.theblocksandtiles;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.DocumentsContract;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by JuhaVuokko on 29.6.2017.
 */

public class XMLHandler {

    private Context context;
    private Resources res;
    private int fileID;
    private IDLibrary hashlibrary;
    private Element rootelement;

    public XMLHandler(Context context, int fileID){
        this.context = context;
        res = this.context.getResources();
        this.fileID = fileID;
        this.hashlibrary = new IDLibrary();

        try {
            InputStream inputStream = this.res.openRawResource(this.fileID);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            rootelement = doc.getDocumentElement();

        }catch (Exception e) {
            Log.i(MainActivity.TAG,e.toString());
        }
    }

    public float[] getCoordinates(String elementname){
        float[] coordinates = new float[2];
        NodeList list = rootelement.getElementsByTagName(elementname);
        Element element = (Element) list.item(0);
        float x = Float.parseFloat(element.getElementsByTagName("x").item(0).getTextContent());
        float y = Float.parseFloat(element.getElementsByTagName("y").item(0).getTextContent());

        coordinates[0] = x;
        coordinates[1] = y;

        return coordinates;
    }



    public BoxArray loadArray(String arrayName){


            Log.i(MainActivity.TAG, rootelement.getNodeName());
            NodeList boxarrayList = rootelement.getElementsByTagName("boxarray");
            int listlength = boxarrayList.getLength();
            Log.i(MainActivity.TAG, "Length of boxarraylist: " + listlength);
            Element arrayElement = null;

            for (int i = 0; i < boxarrayList.getLength(); ++i) {
                Node currentItem = boxarrayList.item(i);
                NamedNodeMap attributes = currentItem.getAttributes();
                for (int j = 0; j < attributes.getLength(); ++j) {
                    Node attributenode = attributes.item(j);
                    if (attributenode.getNodeValue().equals(arrayName)) {
                        arrayElement = (Element) currentItem;
                        break;
                    }
                }
            }

            if(arrayElement == null){
                return null;
            }else {
                int length = Integer.parseInt(arrayElement.getElementsByTagName("boxsize").item(0).getTextContent());
                Log.i(MainActivity.TAG,"Boxsize " + length);
                BoxArray boxArray = new BoxArray(this.context,length);
                NodeList boxlist = arrayElement.getElementsByTagName("box");
                Log.i(MainActivity.TAG,"boxlist " + boxlist.getLength());
                for(int i = 0; i < boxlist.getLength(); ++i){
                    Element boxelement =(Element) boxlist.item(i);
                    float x = Float.parseFloat(boxelement.getElementsByTagName("x").item(0).getTextContent());
                    float y = Float.parseFloat(boxelement.getElementsByTagName("y").item(0).getTextContent());
                    String bitmapString = boxelement.getElementsByTagName("bitmapID").item(0).getTextContent();
                    int bitmapID = hashlibrary.getHashValue(bitmapString);
                    boxArray.addBox(x, y, bitmapID);

                }
                return boxArray;
            }


    }
    public int getBitmapSize(String arrayName){

        NodeList boxarrayList = rootelement.getElementsByTagName("boxarray");

        Element arrayElement = null;

        for (int i = 0; i < boxarrayList.getLength(); ++i) {
            Node currentItem = boxarrayList.item(i);
            NamedNodeMap attributes = currentItem.getAttributes();
            for (int j = 0; j < attributes.getLength(); ++j) {
                Node attributenode = attributes.item(j);
                if (attributenode.getNodeValue().equals(arrayName)) {
                    arrayElement = (Element) currentItem;
                    break;
                }
            }
        }
        if(arrayElement != null) {
            return Integer.parseInt(arrayElement.getElementsByTagName("boxsize").item(0).getTextContent());
        }else {
            return 0;
        }
    }

    public ArrayList<Bitmap> loadBitmapArray(String arrayName){
        Log.i(MainActivity.TAG, rootelement.getNodeName());
        NodeList boxarrayList = rootelement.getElementsByTagName("boxarray");
        int listlength = boxarrayList.getLength();
        Log.i(MainActivity.TAG, "Length of boxarraylist: " + listlength);
        Element arrayElement = null;

        for (int i = 0; i < boxarrayList.getLength(); ++i) {
            Node currentItem = boxarrayList.item(i);
            NamedNodeMap attributes = currentItem.getAttributes();
            for (int j = 0; j < attributes.getLength(); ++j) {
                Node attributenode = attributes.item(j);
                if (attributenode.getNodeValue().equals(arrayName)) {
                    arrayElement = (Element) currentItem;
                    break;
                }
            }
        }

        if(arrayElement == null){
            return null;
        }else {
            int length = Integer.parseInt(arrayElement.getElementsByTagName("boxsize").item(0).getTextContent());
            Log.i(MainActivity.TAG,"Boxsize " + length);
            ArrayList<Bitmap> bitmapArray = new ArrayList<>();
            NodeList boxlist = arrayElement.getElementsByTagName("box");
            Log.i(MainActivity.TAG,"boxlist " + boxlist.getLength());
            for(int i = 0; i < boxlist.getLength(); ++i){
                Element boxelement =(Element) boxlist.item(i);

                String bitmapString = boxelement.getElementsByTagName("bitmapID").item(0).getTextContent();
                int bitmapID = hashlibrary.getHashValue(bitmapString);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),bitmapID);
                bitmapArray.add(bitmap);

            }
            return bitmapArray;
        }
    }

    public void saveArray(BoxArray arrayToSave){

    }
}
