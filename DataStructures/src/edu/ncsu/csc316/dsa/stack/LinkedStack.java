package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }

	@Override
	public void push(E element) {
		list.addLast(element);
		
	}

	/**
	 * Method removes and returns the element at the top of the stack
	 * @return element that is removed
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if(size() <= 0) {
			throw new EmptyStackException();
		}
		
		return list.removeLast();
	}

	/**
	 * Method returns the element at the top of the stack
	 * @return element that is at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E top() {
		if(size() <= 0) {
			throw new EmptyStackException();
		}
		return list.last();
	}

	/**
	 * Method to get the number of elements in the stack
	 * @return size, the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}
    
    
}