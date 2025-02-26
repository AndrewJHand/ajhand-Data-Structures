package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {

	/**
	 * Map to test
	 */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        assertEquals("string3", map.put(3, "string1"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertNull(map.put(2, "string2"));
        assertEquals(2, map.size());
        assertEquals("UnorderedLinkedMap[2, 3]", map.toString());
        assertNull(map.put(1, "string3"));
        assertEquals(3, map.size());
        assertEquals("UnorderedLinkedMap[1, 2, 3]", map.toString());
        assertEquals("string1", map.put(3, "string4"));
        assertEquals("UnorderedLinkedMap[3, 1, 2]", map.toString());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.get(0));
        assertNull(map.get(-10));
        assertNull(map.get(10));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        
//        assertNull(map.put(null, "string6"));
//        assertEquals(map.get(null), "string6");
//        assertNull(map.put(6, null));
//        assertEquals(map.get(6), null);
//        assertEquals(map.size(), 7);
//        assertEquals(null, map.remove(6));
//        assertEquals(map.size(), 6);
//        assertEquals("string6", map.remove(null));
//        assertEquals(map.size(), 5);
//        assertNull(map.put(null, null));
//        assertEquals(map.get(null), null);
//        assertEquals(null, map.get(6));
        
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.remove(-1));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertNull(map.get(6));
        assertEquals("string3", map.remove(3));
        assertNull(map.get(3));
        assertEquals("string4", map.remove(4));
        assertNull(map.get(3));
        assertNull(map.get(4));
        assertEquals("string1", map.remove(1));
        assertEquals("string5", map.remove(5));
        assertEquals("string2", map.remove(2));
        assertNull(map.get(5));
        assertNull(map.get(2));
        assertNull(map.get(-1));
        assertNull(map.remove(2));
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        
        Iterator<Integer> it = map.iterator();
        
        
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals(it.next().toString(), "1");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "4");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "2");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "5");
        assertTrue(it.hasNext());
        
        Exception e1 = assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals("The remove operation is not supported yet.", e1.getMessage());
        
        assertEquals(it.next().toString(), "3");
        assertFalse(it.hasNext());
        //assertEquals(it.iterator());
        
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> it = map.values().iterator();
        
        assertEquals(it.next().toString(), "string1");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "string4");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "string2");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "string5");
        assertTrue(it.hasNext());
        
        Exception e1 = assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals("The remove operation is not supported yet.", e1.getMessage());
        
        assertEquals(it.next().toString(), "string3");
        assertFalse(it.hasNext());
        
    }
}