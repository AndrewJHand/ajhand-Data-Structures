package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {

	/**
	 * List to test across all tests
	 */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	assertEquals(null, list.first());
    	
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
      
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(null, list.last());
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        assertEquals(list.last().getElement(), "two");
        assertEquals(second.getElement(), "two");
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.first().getElement(), "one");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.last().getElement(), "three");
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals(second.getElement(), "two");
        assertEquals(list.first().getElement(), "two");
        assertEquals(list.last().getElement(), "one");
        
        Position<String> third = list.addFirst("three");
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        assertEquals(third.getElement(), "three");
        assertEquals(list.first().getElement(), "three");
        assertEquals(list.after(third).getElement(), "two");
        assertEquals(list.last().getElement(), "one");
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        assertEquals(list.last().getElement(), "two");
        assertEquals(second.getElement(), "two");
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.first().getElement(), "one");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.last().getElement(), "three");
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertNull(list.before(first));
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.before(second).getElement(), "one");
        assertEquals(list.last().getElement(), "two");
        assertNull(list.before(first));
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.before(second).getElement(), "one");
        assertEquals(list.before(third).getElement(), "two");
        assertEquals(list.last().getElement(), "three");
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertNull(list.after(first));
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(second));
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.after(second).getElement(), "three");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(third));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertNull(list.after(first));
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addBefore(first, "two");
        assertEquals(2, list.size());
        assertEquals(second.getElement(), "two");
        assertEquals(list.before(first).getElement(), "two");
        assertEquals(list.first().getElement(), "two");
        assertNull(list.after(first));
        
        Position<String> third = list.addBefore(second, "three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.before(second).getElement(), "three");
        assertEquals(list.after(second).getElement(), "one");
        assertEquals(list.first().getElement(), "three");
        assertNull(list.after(first));
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertNull(list.after(first));
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addAfter(first, "two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(second));
        
        Position<String> third = list.addAfter(second, "three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.after(second).getElement(), "three");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(third));
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertNull(list.after(first));
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(second));
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.after(second).getElement(), "three");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(third));
        
        assertEquals("two", list.set(second, "second"));
        assertEquals("second", second.getElement());
        assertEquals(list.after(first).getElement(), "second");
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertNull(list.after(first));
        assertEquals(1, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.first().getElement(), "one");
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first.getElement(), "one");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(second));
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third.getElement(), "three");
        assertEquals(list.after(second).getElement(), "three");
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        assertNull(list.after(third));
        
        assertEquals("three", list.remove(third));
        assertEquals(2, list.size());
        assertEquals(list.after(first).getElement(), "two");
        assertEquals(list.first().getElement(), "one");
        
        assertEquals("two", list.remove(list.after(first)));
        assertEquals(1, list.size());
        assertEquals(list.first().getElement(), "one");
        
        assertEquals("one", list.remove(first));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
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
        Position<String> first = list.addLast("one");
        assertEquals(first.getElement(), "one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.first().getElement());
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        it.remove();
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        
    }

}