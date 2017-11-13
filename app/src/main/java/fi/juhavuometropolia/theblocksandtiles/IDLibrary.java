package fi.juhavuometropolia.theblocksandtiles;


import java.util.HashMap;

/**
 * Created by JuhaVuokko on 3.7.2017.
 */

public class IDLibrary {

    private HashMap<String,Integer> idHashMap;

    public IDLibrary(){

        idHashMap = new HashMap<>();

        int blackredbox = R.drawable.blackredbox;
        String blackredboxS = "blackredbox";
        idHashMap.put(blackredboxS,blackredbox);

        int blackbluebox = R.drawable.blackbluebox;
        String blackblueboxS = "blackbluebox";
        idHashMap.put(blackblueboxS,blackbluebox);

        int blackgreenbox = R.drawable.blackgreenbox;
        String blackgreenboxS = "blackgreenbox";
        idHashMap.put(blackgreenboxS,blackgreenbox);

        int dirt = R.drawable.dirt;
        String dirtS = "dirt";
        idHashMap.put(dirtS,dirt);

        int empty = R.drawable.empty;
        String emptyS = "empty";
        idHashMap.put(emptyS,empty);

        int stone = R.drawable.stone;
        String stoneS = "stone";
        idHashMap.put(stoneS,stone);
    }

    public int getHashValue(String key){
        return idHashMap.get(key);
    }
}
