package ueb19;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("all")
public class DoppeltVerketteteListeTest {


    @Test
    public void testSizeEmptyList() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        assertEquals(0, list.size());
    }

    @Test
    public void testSizeNonEmptyList() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(5);
        list.add(10);
        list.add(15);
        assertEquals(3, list.size());
    }

    @Test
    public void testSizeAfterRemove() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(5);
        list.add(10);
        list.add(15);
        list.remove(Integer.valueOf(10));
        assertEquals(2, list.size());
    }


    @Test
    public void testIsEmptyEmptyList() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        assertTrue(list.isEmpty());
    }


    @Test
    public void testIsEmptyNonEmptyList() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(5);
        assertFalse(list.isEmpty());
    }


    @Test
    public void testIsEmptyAfterClear() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(5);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContainsExistingElement() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(5);
        list.add(10);
        list.add(15);
        assertTrue(list.contains(10));
    }

    @Test
    public void testContainsNonExistingElement() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(5);
        list.add(10);
        list.add(15);
        assertFalse(list.contains(20));
    }

    @Test
    public void testToArray() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add("B");
        list.add("C");

        String[] array = new String[3];
        array = list.toArray(array);

        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }


    @Test
    public void testToArrayEmptyList() {
        List<Integer> list = new DoppeltVerketteteListe<>();

        Integer[] array = new Integer[0];
        array = list.toArray(array);

        assertEquals(0, array.length);
    }


    @Test
    public void testToArrayWrongCast(){
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add("B");
        List maliciousList = list;
        maliciousList.add(3);

        String array [] = new String[3];
        assertThrows(ArrayStoreException.class, () -> list.toArray(array));
    }

    @Test
    public void testToArraySmallerArray() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] array = new Integer[2];
        array = list.toArray(array);

        assertEquals(1, array[0].intValue());
        assertEquals(2, array[1].intValue());
    }

    @Test
    public void testAdd() {
        List<String> list = new DoppeltVerketteteListe<>();
        assertTrue(list.add("A"));
        assertTrue(list.add("B"));
        assertTrue(list.add("C"));
        assertEquals(3, list.size());
    }


    @Test
    public void testAddAtIndex() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add("C");
        list.add(1, "B");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    public void testAddNullElement() {
        List<String> list = new DoppeltVerketteteListe<>();
        assertThrows(NullPointerException.class, () -> {
            list.add(null);
        });
        assertTrue(list.add("A"));

        assertEquals("A", list.get(0));
    }

    @Test
    public void testRemoveExistingElement() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    public void testRemoveNonExistingElement() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertFalse(list.remove(Integer.valueOf(4)));
        assertEquals(3, list.size());
    }

    @Test
    public void testAddAll() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        List<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        assertTrue(list.addAll(collection));
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }


    @Test
    public void testAddAllEmptyCollection() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        List<Integer> collection = new DoppeltVerketteteListe<>();
        assertFalse(list.addAll(collection));
        assertEquals(0, list.size());
    }


    @Test
    public void testAddAllNullCollection() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        assertFalse(list.addAll(null));
        assertEquals(0, list.size());
    }


    @Test
    public void testClear() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }


    @Test
    public void testClearEmptyList() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGet() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.get(1);
    }

    @Test
    public void testSet() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.set(1, "X"));
        assertEquals("X", list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetInvalidIndex() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.set(1, "X");
    }

    @Test
    public void testAddElementAtIndex() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add("C");
        list.add(1, "B");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementInvalidIndex() {
        List<String> list = new DoppeltVerketteteListe<>();
        list.add("A");
        list.add(2, "B");
    }

    @Test
    public void testRemoveAtIndex() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.remove(1).intValue());
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.remove(1);
    }

    @Test
    public void testIndexOfExistingElement() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.indexOf(2));
    }

    @Test
    public void testIndexOfNonExistingElement() {
        List<Integer> list = new DoppeltVerketteteListe<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testIndexOfNullElement() {
        List<String> list = new DoppeltVerketteteListe<>();
        assertThrows(NullPointerException.class, () -> list.add(null));
        list.add("A");
        assertEquals(0, list.indexOf("A"));
    }
}
