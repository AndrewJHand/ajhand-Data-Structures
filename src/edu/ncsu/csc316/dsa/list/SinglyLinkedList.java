package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Andrew Hand
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    /**
     * Method adds an element to the singly linked list at given index
     * @param index is the index to add to
     * @param value is the element to add
     * @throws IndexOutOfBoundsException if the desired index is out of bounds
     */
    public void add(int index, E value) {
    	if(index > size || index < 0) {
    		throw new IndexOutOfBoundsException();
    	}
    	if(size == 0) {
    		front = new LinkedListNode<E>(value);
    		tail = front;
    	}
    	else if(size == 1 && index == 1) {
    		front.next = new LinkedListNode<E>(value);
    		tail = front.next;
    	}
    	else if(index < size && index != 0) {
    		LinkedListNode<E> temp = front;
    		for(int x = 0; x < index - 1; x++) {
    			temp = temp.next;
    		}
    		temp.next = new LinkedListNode<E>(value, temp.next);
    	}
    	else if(index == size) {
    		tail.next = new LinkedListNode<E>(value);
    		tail = tail.next;
    	}
    	else if(index == 0) {
    		front = new LinkedListNode<E>(value, front);
    	}
    	this.size++;
    }
    
    /**
	 * getter method for an element at a given index
	 * @param index is the desired index to access
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if the desired index is out of bounds
	 */
	@Override
	public E get(int index) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(index == size - 1) {
			return tail.getElement();
		}
		LinkedListNode<E> temp = front;
		for(int x = 0; x < index; x++) {
			temp = temp.next;
		}
		return temp.getElement();
	}
	/**
	 * Method should remove an element at the desire index, and return the removed element
	 * throws exception if the index is out of bounds
	 * changes tail/front if needed
	 * @param index is the desired index to access
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if the desired index is out of bounds
	 */
	@Override
	public E remove(int index) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0 && size == 1) {
			E removed = front.getElement();
			front = null;
			tail = null;
			size--;
			return removed;
		}
		else if(index == 0) {
			E removed = front.getElement();
			front = front.next;
			size--;
			return removed;
		}
		LinkedListNode<E> temp = front;
		for(int x = 0; x < index - 1; x++) {
			temp = temp.next;
		}
		E removed = temp.getNext().getElement();
		temp.next = temp.next.next;
		if(index == size - 1) {
			tail = temp;
		}
		this.size--;
		return removed;
	}

	/**
	 * Method should set an index to be a specified element, and return the old element at the given index
	 * @param index is the index to set to
     * @param element is the element to set
     * @return E element that was previously at the index
     * @throws IndexOutOfBoundsException if the desired index is out of bounds
     * 
	 */
	@Override
	public E set(int index, E element) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			E changed = front.getElement();
			front.element = element;
			return changed;
		}
		if(index == size - 1) {
			E changed = tail.getElement();
			tail.element = element;
			return changed;
		}
		LinkedListNode<E> temp = front;
		for(int x = 0; x < index - 1; x++) {
			temp = temp.next;
		}
		E changed = temp.next.getElement();
		temp.next.element = element;
		return changed;
	}

	/**
	 * getter method for size
	 * @return size, the number of elements in the array
	 */
	@Override
	public int size() {
		return this.size;
	}
	/**
	 * Method to create an element iterator
	 */
	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
    
    /**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
    	if(size == 0) {
    		front = new LinkedListNode<E>(element);
    		tail = front;
    	}
    	else {
    		tail.next = new LinkedListNode<E>(element);
            tail = tail.next;
    	}
        size++;
    }

private static class LinkedListNode<E> {
    
	 /**
	  * Element of the list node (value)
	  */
	 private E element;
	 /**
	  * next list node that the current one points to
	  */
    private LinkedListNode<E> next;
	 /**
	  * Constructor with one parameter
	  * @param element is the element to set element equal to
	  */
       public LinkedListNode(E element) {
			this.element = element;
			this.next = null;
		}
		/**
		 * Constructor with two parameters sets the element, and the next list node
		 * @param element is the element of this list node
		 * @param next is the next list node to connect to
		 */
       public LinkedListNode(E element, LinkedListNode<E> next) {
       	this.element = element;
			this.next = next;
       }
       /**
        * helper method to get the element of the list node
        * @return element, the element of the list node
        */
       public E getElement() {
    	   return element;
       }
       /**
        * Method to get the next node
        * @return next, the next node
        */
       public LinkedListNode<E> getNext(){
    	   return next;
       }
   }

private class ElementIterator implements Iterator<E> {
    /**
     * Keep track of the next node that will be processed
     */
    private LinkedListNode<E> current;
    
    
    /**
     * Construct a new element iterator where the cursor is initialized 
     * to the beginning of the list.
     */
    public ElementIterator() {
        current = new LinkedListNode<E>(null, front);
    }

    @Override
    public boolean hasNext() {
        if(current.getNext() != null && current.getNext().getElement() != null) {
        	return true;
        }
        return false;
    }

    @Override
    public E next() {
        if(current.getNext() == null || current.getNext().getElement() == null) {
        	throw new NoSuchElementException();
        }
        else {
        	if(current.getElement() == null) {
        		current = current.next;
        		E previous = current.getElement();
        		return previous;
        	}
        	current = current.next;
        	E previous = current.getElement();
        	return previous;
        }
    }
     
    @Override    
    public void remove() {
	    // DO NOT CHANGE THIS METHOD
        throw new UnsupportedOperationException(
            "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
    }
}
}
