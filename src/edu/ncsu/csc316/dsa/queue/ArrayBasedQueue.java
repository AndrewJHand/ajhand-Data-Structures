package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

    /**
     * Internal array to store the data within the queue
     */
    private E[] data;

    /**
     * A reference to the index of the first element in the queue
     */
    private int front;

    /**
     * A reference to the index immediately after the last element in the queue
     */
    private int rear;

    /**
     * The number of elements stored in the queue
     */
    private int size;

    /**
     * The initial default capacity of the internal array that stores the data
     */
    private static final int DEFAULT_CAPACITY = 0;

    /**
     * A number to keep track of the capacity for dequeue;
     */
    private int capacity = 0;
    /**
     * Constructs a new array-based queue with the given initialCapcity for the
     * array
     * 
     * @param initialCapacity the initial capacity to use when creating the initial
     *                        array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity) {
        data = (E[]) (new Object[initialCapacity]);
        size = 0;
        capacity = initialCapacity;
    }

    /**
     * Constructs a new array-based queue with the default initial capacity for the
     * array
     */
    public ArrayBasedQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * To ensure amortized O(1) cost for adding to the array-based queue, use the
     * doubling strategy on each resize. Here, we add +1 after doubling to handle
     * the special case where the initial capacity is 0 (otherwise, 0*2 would still
     * produce a capacity of 0).
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
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) (new Object[newCapacity]);
            int current = front;
            for(int x = 0; x < oldCapacity; x++) {
            	newData[x] = data[current];
            	current = (current + 1) % oldCapacity;
            }
            data = newData;
            front = 0;
            rear = oldCapacity;
            capacity = newCapacity;
            // Remember that we cannot copy an array the same way we do
            // when resizing an array-based list since we do not want to
            // "break" the elements in the queue since there may be wrap-around
            // at the end of the array
        }
    }

    /**
     * method adds an element to the end of the queue
     * changes the size and rear of the queue
     * @param element is the element to add
     */
	@Override
	public void enqueue(E element) {
		ensureCapacity(size + 1);
		data[(rear) % capacity] = element;
		rear = (rear + 1) % capacity;
		size++;
	}

	/**
	 * Method removes and returns the first element in the queue
	 * @return element at the front of the queue
	 * @throws NoSuchElementException if size == 0
	 */
	@Override
	public E dequeue() {
		if(size <= 0) {
			throw new NoSuchElementException();
		}
		E removed = data[front];
		data[front] = null;
		if(capacity > 1) {
		front++;
		}
		size--;
		return removed;
	}

	/**
	 * Method returns the first element in the queue
	 * @return element at the front of the queue
	 * @throws NoSuchElementException if there is no elements in the queue
	 */
	@Override
	public E front() {
		if(size <= 0) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	/**
	 * Method returns the number of elements in the queue
	 * @return size, the number of elements in the queue
	 */
	@Override
	public int size() {
		return size;
	}

}   