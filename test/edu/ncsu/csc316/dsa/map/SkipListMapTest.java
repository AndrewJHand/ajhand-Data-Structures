/**
 * 
 */
package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests for SkipListMap
 * @author Andrew Hand
 */
public class SkipListMapTest {

	/**
	 * integer, string map
	 */
    private SkipListMap<Integer, String> map;
    /**
     * student, integer map
     */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        
        assertEquals(1, map.size());
        
        assertNull(map.put(2, "string2"));
        assertEquals("string3", map.put(3,  "string1"));
        assertEquals("string1", map.put(3,  "string3"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(4, "string4"));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        
        
        assertEquals("string1", map.get(1));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.get(7));
    }

    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertEquals("SkipListMap[\n-INF -> +INF\n]", map.toFullString());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        
        
        assertEquals("string3", map.remove(3));
        assertEquals("string1", map.remove(1));
        assertEquals("string5", map.remove(5));
        assertNull(map.remove(6));
        assertEquals("string4", map.remove(4));
        assertEquals("string2", map.remove(2));
        assertNull(map.remove(1));
        assertTrue(map.isEmpty());
        
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertTrue(studentMap.isEmpty());
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s1, 1));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s4, 4));
        assertFalse(studentMap.isEmpty());
        assertEquals(5, studentMap.size());
        
        
        assertEquals(studentMap.get(s2).toString(), "2");
        
        Iterator<Student> it = studentMap.iterator();
        assertEquals(it.next(), s5);
        assertEquals(it.next(), s3);
        assertEquals(it.next(), s4);
        assertEquals(it.next(), s1);
        assertEquals(it.next(), s2);
        assertFalse(it.hasNext());
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students
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
        
        assertEquals(it.next().toString(), "1");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "2");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "3");
        assertTrue(it.hasNext());
        assertEquals(it.next().toString(), "4");
        assertTrue(it.hasNext());
        
        Exception e1 = assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals("The remove operation is not supported yet.", e1.getMessage());
        
        assertEquals(it.next().toString(), "5");
        assertFalse(it.hasNext());
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
        assertTrue(it.hasNext());
        
        
    }

}
