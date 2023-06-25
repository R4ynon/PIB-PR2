package ueb20;

import java.util.HashMap;
import java.util.LinkedList;

public class Consumer {

    private HashMap<Integer, LinkedList<Double>> timestampsForCecksummMap ;

    private HashMap<Integer, Integer> checksummCountDifferentOccMap;
    int mapCapacity = 28;
    public Consumer(){
        this.checksummCountDifferentOccMap = new HashMap<Integer, Integer>(mapCapacity);
        // hier werden die timestamps gespeichert
        this.timestampsForCecksummMap = new HashMap<Integer, LinkedList<Double>>(mapCapacity);
        newHashMap();
    }

    public static int consume(int ran){
        //hier start mit systemnanotime
        int checksum = 0;
        while(0 != ran) {
            checksum = checksum + (ran % 10);
            ran = ran / 10;
        }
        // hier ende von system nanotime
        // hier die zeit in die timestampsmap an der stelle checksumm einfügen
        return checksum;
    }

    public void insertIntoChecksummMap(int checksumm){
        checksummCountDifferentOccMap.put(checksumm, checksummCountDifferentOccMap.get(checksumm) +1);
    }
    public int numberOfDifferentResults(){
        int count = 0;
        for(int i=0; i<= checksummCountDifferentOccMap.size(); i++){
            if(checksummCountDifferentOccMap.get(i) > 0){
                count++;
            }
        }
        return count;
    }

    public int numberOfOccurrences(int given){
        int count = checksummCountDifferentOccMap.get(given);
        return count;
    }

    public LinkedList<Integer> getCrossTotalsAscending(){
        LinkedList<Integer> crossTotalChecksummAscending = new LinkedList<>();
        for (int i = 0; i <= checksummCountDifferentOccMap.size(); i++){
            if(checksummCountDifferentOccMap.get(i) > 0){
                crossTotalChecksummAscending.add(checksummCountDifferentOccMap.get(i));
            }
        }
        return crossTotalChecksummAscending;
    }

    public LinkedList<Integer> getCrossTotalsDescending(){
        LinkedList<Integer> crossTotalChecksummDescending = new LinkedList<>();
        for (int i = checksummCountDifferentOccMap.size(); i >= 0; i--){
            if(checksummCountDifferentOccMap.get(i) > 0){
                crossTotalChecksummDescending.add(checksummCountDifferentOccMap.get(i));
            }
        }
        return crossTotalChecksummDescending;
    }

    public LinkedList<Double> getTimestampsForResult(int givenChecksumm){
        LinkedList<Double> timestampsForGivenCkecksumm = new LinkedList<>();
        timestampsForGivenCkecksumm = timestampsForCecksummMap.get(givenChecksumm);
        return timestampsForGivenCkecksumm;
    }


    public void newHashMap(){
        for(int i =0 ; i<=27 ; i++){
            checksummCountDifferentOccMap.put(i,0);
            timestampsForCecksummMap.put(i, new LinkedList<Double>());
        }
    }
}
