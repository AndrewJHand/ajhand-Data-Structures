package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Andrew Hand
 *
 */
public class ArrayBasedListTest {

	/**
	 * abstract list that will be an array based list
	 */
    private List<String> list;

    /** test list 
     * 
     */
    private List<List<String>> list2;
    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
        list2 = new ArrayBasedList<List<String>>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        list.add(0, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        list.add(0, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("one", list.get(2));
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        list.addLast("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        list.addLast("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list2.add(0, list);
        assertEquals("three", list2.get(0).set(2, "help"));
        assertEquals("help", list2.get(0).get(2));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.last());
    	list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertEquals("one", list.last());
        list.addLast("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertEquals("two", list.last());
        list.addLast("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertEquals("three", list.last());
        
        
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	
    	list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.first());
    	list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertEquals("one", list.first());
        list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("two", list.first());
        list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("three", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        
        // Use accessor methods to check that the list is correct
        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        assertTrue(it.hasNext());
        assertEquals("three", it.next());
        it.remove();
        assertEquals(list.get(2), "four");
        assertTrue(it.hasNext());
        assertEquals("four", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try{
            it.remove();
                     
        } catch(Exception e) {
        	fail("Nothing should have thrown");  
        }
        
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        list.add(0, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        list.add(0, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("two", list.get(1));
        
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
        
        assertEquals("two", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("one", list.remove(1));
        assertEquals(1, list.size());
        assertEquals("three", list.remove(0));
        assertEquals(0, list.size());
        
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.removeFirst());
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        list.add(0, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        list.add(0, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("two", list.get(1));
        
        assertEquals("three", list.removeFirst());
        assertEquals(2, list.size());
        assertEquals("two", list.removeFirst());
        assertEquals(1, list.size());
        assertEquals("one", list.removeFirst());
        assertEquals(0, list.size());
        
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeFirst());
        
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.removeLast());
    	list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        list.add(0, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        list.add(0, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("two", list.get(1));
        
        assertEquals("one", list.removeLast());
        assertEquals(2, list.size());
        assertEquals("two", list.removeLast());
        assertEquals(1, list.size());
        assertEquals("three", list.removeLast());
        assertEquals(0, list.size());
        
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeLast());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "string"));
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        list.add(0, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        list.add(0, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("two", list.get(1));
        
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "string"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "string"));
        
        assertEquals(list.set(0, "string"), "three");
        assertEquals("string", list.get(0));
        assertEquals(list.set(2, "string2"), "one");
        assertEquals("string2", list.last());
        assertEquals(list.set(1, "string3"), "two");
        assertEquals("string3", list.get(1));
    }
}