package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * Source Data Structures and Algorithms in Java:
 * Goodrich, Michael, T. et al. Data Structures and Algorithms in Java. Available from: 
 * North Carolina State University, (6th Edition). Wiley Global Education US, 2014.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
	/**
	 * Method to run an in order operation of the tree and return an iterable
	 * of the leaves
	 * @return an iterable with the order that inOrder would return
	 */
	public Iterable<Position<E>> inOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }

	/**
	 * Method to help run an in order operation of the tree and return an iterable
	 * of the leaves
	 * @param p is the position of the current node
	 * @param traversal is the iterable to add to
	 */
    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
        if(left(p) != null) {
        	inOrderHelper(left(p), traversal);
        }
        if(p.getElement() != null) {
        	traversal.add(p);
        }
        if(right(p) != null) {
        	inOrderHelper(right(p), traversal);
        }
    }
    
   
    
    @Override
    public int numChildren(Position<E> p) {
        int count = 0;
        if(left(p) != null) {
        	count++;
        }
        if(right(p) != null) {
        	count++;
        }
        return count;
    }
    /**
     * Method checks a position to find the sibling
     * @param p is the position to check
     * @return the position of the sibling or null if there is none
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if(parent == null) {
        	return null;
        }
        if(p == left(parent)) {
        	return right(parent);
        }
        else {
        	return left(parent);
        }
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}