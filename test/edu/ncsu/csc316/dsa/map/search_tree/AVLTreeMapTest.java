package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 * Used the textbook Data Structures and Algorithms in Java
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class AVLTreeMapTest {

	/**
	 * the search tree to test
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1, "one");
        tree.put(2, "two");
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(3, "three");
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(4, "four");
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(8, "eight");
        assertEquals(8, (int)(tree.right(tree.right(tree.root()))).getElement().getKey());
        tree.put(7, "seven");
        assertEquals(7, (int)(tree.left(tree.right(tree.root())).getElement().getKey()));
        tree.put(5, "five");
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
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
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(5, "five");
        assertEquals("five", tree.get(5));
        assertEquals(tree.get(2), "two");
        assertNull(tree.get(6));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        tree.put(44, "44");
        tree.put(17, "17");
        tree.put(62, "62");
        tree.put(32, "32");
        tree.put(50, "50");
        tree.put(78, "78");
        tree.put(48, "48");
        tree.put(54, "54");
        tree.put(88, "88");
        assertEquals("32", tree.remove(32));
        assertEquals("62", tree.root().getElement().getValue());
        assertEquals("62", tree.remove(62));
        assertEquals("50", tree.root().getElement().getValue());
        tree.toString();
        tree.entrySet();
        
        Iterator<Map.Entry<Integer, String>> it = tree.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(17, (int)(entry.getKey()));
        assertEquals("17", (String)(entry.getValue()));
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
}