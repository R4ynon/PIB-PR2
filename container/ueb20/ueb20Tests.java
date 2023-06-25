package ueb20;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SuppressWarnings("all")

public class ueb20Tests {
    /*
    @Before
    public void beforeEach(){
        Producer prod = new Producer();
        Consumer con = new Consumer();
    }
    */
    @Test
    public void testInsertIntoMap(){
        Producer prod = new Producer();
        Consumer con = new Consumer();
        con.insertIntoChecksummMap(3);
        HashMap<Integer, Integer> map = con.getChecksummCountDifferentOccMap();
        int help = map.get(3);
        assertEquals(1,help );
    }

    @Test
    public void testInstertIntoMapMulti(){
        Producer prod = new Producer();
        Consumer con = new Consumer();
        con.insertIntoChecksummMap(3);
        con.insertIntoChecksummMap(3);
        con.insertIntoChecksummMap(3);
        HashMap<Integer, Integer> map = con.getChecksummCountDifferentOccMap();
        int help = map.get(3);
        assertEquals(3,help );
    }
    @Test
    public void testNumberOfOcc(){
        Producer prod = new Producer();
        Consumer con = new Consumer();
        con.insertIntoChecksummMap(3);
        con.insertIntoChecksummMap(3);
        con.insertIntoChecksummMap(3);
        HashMap<Integer, Integer> map = con.getChecksummCountDifferentOccMap();
        int help = map.get(3);
        assertEquals(3,help );

    }

    @Test
    public void testNumberOfOccFalse(){
        Producer prod = new Producer();
        Consumer con = new Consumer();
        con.insertIntoChecksummMap(3);
        con.insertIntoChecksummMap(3);
        con.insertIntoChecksummMap(3);
        HashMap<Integer, Integer> map = con.getChecksummCountDifferentOccMap();
        int help = map.get(3);
        assertFalse(help == 4);
    }

}
