package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class RedBlackTreeMapTest {

	/**
	 * the tree used to test
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(10, "10");
        assertEquals(10, (int)tree.root().getElement().getKey());
        tree.put(30, "30");
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(30, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(40, "40");
        assertEquals(30, (int)tree.root().getElement().getKey());
        assertEquals(40, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(25, "25");
        tree.put(28, "28");
        assertEquals(30, (int)tree.root().getElement().getKey());
        assertEquals(25, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        tree.put(5, "5");
        tree.put(4, "4");
        assertEquals(5, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        tree.put(55, "55");
        tree.put(45, "45");
        assertEquals(45, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(55, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(1, "1");
        tree.put(3, "3");
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
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
    	tree.put(10, "10");
    	tree.put(30, "30");
    	tree.put(40, "40");
    	tree.put(25, "25");
        tree.put(28, "28");
        tree.put(5, "5");
        tree.put(4, "4");
        tree.put(55, "55");
        tree.put(45, "45");
        tree.put(1, "1");
        tree.put(3, "3");
        assertEquals("45", tree.get(45));
        assertEquals("5", tree.get(5));
        assertEquals("10", tree.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(10, "10");
    	tree.put(30, "30");
    	tree.put(40, "40");
    	tree.put(25, "25");
        tree.put(28, "28");
        tree.put(5, "5");
        tree.put(4, "4");
        tree.put(55, "55");
        tree.put(45, "45");
        tree.put(1, "1");
        tree.put(3, "3");
        assertEquals("5", tree.remove(5));
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals("25", tree.remove(25));
        assertEquals(28, (int)tree.root().getElement().getKey());
        assertEquals(40, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals("30", tree.remove(30));
        tree.put(2, "2");
        tree.put(25, "25");
        tree.put(26, "26");
        tree.put(27, "27");
        tree.put(15, "15");
        tree.put(15, "15");
        tree.put(42, "42");
        tree.put(35, "35");
        tree.put(37, "37");
        tree.put(50, "50");
        tree.put(48, "48");
        tree.put(46, "46");
        tree.put(49, "49");
        tree.put(47, "47");
        tree.put(70, "70");
        tree.put(80, "80");
        tree.put(90, "90");
        tree.put(44, "44");
        assertEquals("44", tree.remove(44));
        assertEquals("42", tree.remove(42));
        assertEquals("46", tree.remove(46));
        assertEquals("70", tree.remove(70));
        
        //assertEquals("40", tree.remove(40));
        //assertEquals("25", tree.remove(25));
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    
}