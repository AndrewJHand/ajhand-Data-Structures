package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Andrew Hand
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    /**
     * Add method adds a given element at the desired index
     * @param index is the index to add to
     * @param element is the element to add
     * @throws IndexOutOfBoundsException if the desired index is out of bounds
     */
	@Override
	public void add(int index, E element) {
		ensureCapacity(size + 1);
		checkIndexForAdd(index);
		
		if(index == size) {
			data[index] = element;
		}
		else {
			E temp = data[index];
			data[index] = element;
			for(int x = size; x > index + 1; x--) {
				data[x] = data[x - 1];
			}
			data[index + 1] = temp;
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

		checkIndex(index);
		return data[index];
	}
	/**
	 * Method should remove an element at the desire index, and return the removed element
	 * throws exception if the index is out of bounds
	 * @param index is the desired index to access
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if the desired index is out of bounds
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		if(size == 1) {
			E element = data[index];
			this.size--;
			data[index] = null;
			return element;
		}
		else if(index == size - 1) {
			E element = data[index];
			data[index] = null;
			this.size--;
			return element;
		}
		E element = data[index];
		for(int x = index; x < size - 1; x++) {
			data[x] = data[x + 1];
		}
		data[size - 1] = null;
		this.size--;
		return element;
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
		checkIndex(index);
		E temp = data[index];
		data[index] = element;
		return temp;
	}

	/**
	 * getter method for size
	 * @return size, the number of elements in the array
	 */
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
    
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    private class ElementIterator implements Iterator<E> {
    	/**
    	 * int to keep track of the current element
    	 */
        private int position;
        /**
         * boolean should be set to true if next() is run, and false if remove() is run
         */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            position = -1;
        }
        /**
         * Checks if there is a next element by running next
         * Returns false if there is an exception thrown
         * Returns true if the is an element thrown
         */
        @Override
        public boolean hasNext() {
            try {
            	if(data[position + 1] != null) {
            		return true;
            	}
            	throw new ArrayIndexOutOfBoundsException();
            }
            catch(ArrayIndexOutOfBoundsException e){
            	return false;
            }
        }

        /**
         * Method advances the iterator, and returns the new element
         * sets removeOK to true if there is an element
         * @return element that is next
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public E next() {
        	if(position + 1 >= size) {
        		throw new NoSuchElementException();
        	}
        	try {
            if(data[position + 1] != null) {
            	position++;
            	removeOK = true;
            	return data[position];
            }
            return null;
        	}
        	catch(ArrayIndexOutOfBoundsException e){
            	throw new NoSuchElementException();
            }
        }
        
        /**
         * method removes the current element in the iterator
         * Must have removeOK = true
         * Sets removeOK = false if element is removed
         */
        @Override
        public void remove() {
            if(removeOK) {
            	ArrayBasedList.this.remove(position);
            	removeOK = false;
            	position--;
            }
            else {
            	throw new IllegalStateException();
            }
        }
    }
}