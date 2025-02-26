package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class SplayTreeMapTest {

	/**
	 * the map to search with
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(15, "15");
        tree.put(30, "30");
        assertEquals(30, (int)tree.root().getElement().getKey());
        tree.put(45, "45");
        assertEquals(tree.size(), 3);
        assertEquals(45, (int)tree.root().getElement().getKey());
        assertEquals(30, (int)tree.left(tree.root()).getElement().getKey());
        assertNull(tree.right(tree.root()).getElement());
        tree.put(20, "20");
        assertEquals(20, (int)tree.root().getElement().getKey());
        assertEquals(45, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(35, "35");
        assertEquals(35, (int)tree.root().getElement().getKey());
        assertEquals(45, (int)tree.right(tree.root()).getElement().getKey());
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	tree.put(15, "15");
        tree.put(30, "30");
        tree.put(20, "20");
        tree.put(35, "35");
        tree.put(45, "45");
        assertEquals(5, tree.size());
        assertEquals("45", tree.get(45));
        assertEquals("30", tree.get(30));
        assertNull(tree.get(12));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	tree.put(15, "15");
        tree.put(30, "30");
        tree.put(45, "45");
        tree.put(20, "20");
        tree.put(35, "35");
        assertEquals("20", tree.remove(20));
        assertEquals(30, (int)tree.root().getElement().getKey());
        assertEquals("30", tree.remove(30));
        assertEquals(35, (int)tree.root().getElement().getKey());
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
}