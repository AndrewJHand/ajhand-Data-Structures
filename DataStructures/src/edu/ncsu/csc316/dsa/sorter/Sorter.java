package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Andrew Hand
 * @param <E> is the generic element to sort
 */
public interface Sorter<E> {
	/**
	 * method that should be in every sorter
	 * @param data is the array of elements to sort
	 */
	void sort(E[] data);
}
