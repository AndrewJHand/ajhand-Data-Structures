package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @param <E> is the generic element of the class
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {
	/**
	 * the comparator that should be used depending on the element
	 */
	private Comparator<E> comparator;
	/**
	 * default constructor creates default comparator
	 */
	public InsertionSorter() {
		super();
		setComparator(new NaturalOrder());
	}
	/**
	 * Constructor to create a different comparator
	 * @param comp is the comparator to set to
	 */
	public InsertionSorter(Comparator<E> comp) {
		super(comp);
		setComparator(comp);
	}
	/**
	 * method sets the comparator if it is not null
	 * @param comparator is the comparator to set to
	 */
	private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }
	
	/**
	 * method that uses insertion sorting to sort an array of elements
	 * @param data is the array of elements to sort
	 */
	public void sort(E[] data) {
		for(int x = 1; x < data.length; x++) {
			int pos = x;
			for(int y = x - 1; y >= 0; y--) {
				if(comparator.compare(data[y], data[pos]) > 0) {
					E temp = data[pos];
					data[pos] = data[y];
					data[y] = temp;
					pos = y;
				}
			}
		}
	}
	
	private class NaturalOrder implements Comparator<E> {
	    public int compare(E first, E second) {
	        return ((Comparable<E>) first).compareTo(second);
	    }
	}
}
