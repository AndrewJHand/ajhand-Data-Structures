package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<String> tree;
    private Position<String> one;
    private Position<String> two;
    private Position<String> three;
    private Position<String> four;
    private Position<String> five;
    private Position<String> six;
    private Position<String> seven;
    private Position<String> eight;
    private Position<String> nine;
    private Position<String> ten;
    

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
        
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals(tree.set(two, "new two"), "two");
        assertEquals(tree.set(one, "new one"), "one");
        assertEquals(tree.set(two, "old two"), "new two");
        assertEquals(tree.set(nine, "new nine"), "nine");
        
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(tree.size(), 10);
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(1, tree.numChildren(three));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(tree.parent(eight), four);
        assertEquals(tree.parent(two), one);
        assertEquals(tree.parent(one), null);
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(tree.sibling(two), three);
        assertEquals(tree.sibling(one), null);
        assertEquals(tree.sibling(seven), five);
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(eight));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(eight));
        assertFalse(tree.isLeaf(two));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertEquals(it.next(), one);
        assertEquals(it.next(), two);
        assertEquals(it.next(), six);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), five);
        assertEquals(it.next(), three);
        assertEquals(it.next(), four);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), nine);
        
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertEquals(it.next(), six);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), five);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), two);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), nine);
        assertEquals(it.next(), four);
        assertEquals(it.next(), three);
        assertEquals(it.next(), one);
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(it.next(), six);
        assertEquals(it.next(), two);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), five);
        assertEquals(it.next(), one);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), four);
        assertEquals(it.next(), nine);
        assertEquals(it.next(), three);
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	Iterator<Position<String>> it = tree.inOrder().iterator();
    	assertFalse(it.hasNext());
    	tree.toString();
    }
    
    /**
     * test the level order traversal behavior
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(it.next(), one);
        assertEquals(it.next(), two);
        assertEquals(it.next(), three);
        assertEquals(it.next(), six);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), four);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), five);
        assertEquals(it.next(), eight);
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals(it.next(), nine);
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	one = tree.addRoot("one");
    	two = tree.addLeft(one, "two");
        assertThrows(IllegalArgumentException.class, () -> three = tree.addLeft(one, "three"));
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	one = tree.addRoot("one");
    	two = tree.addRight(one, "two");
        assertThrows(IllegalArgumentException.class, () -> three = tree.addRight(one, "three"));
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.remove(two));
        assertEquals(tree.remove(seven), "seven");
        assertEquals(tree.remove(ten), "ten");
        assertTrue(tree.isLeaf(five));
    }
}