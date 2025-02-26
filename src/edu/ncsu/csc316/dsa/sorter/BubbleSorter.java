package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
/**
 * Bubble sorter class to run the bubble sort algorithm
 * @author Andrew Hand
 * @param <E> is the element to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	
	
	/**
	 * default constructor creates default comparator
	 */
	public BubbleSorter() {
		super();
	}
	/**
	 * constructor creates default comparator
	 * @param comp is the comparator to set to
	 */
	public BubbleSorter(Comparator<E> comp) {
		super(comp);
	}
	
	
	
	/**
	 * Sort method should sort elements by the bubble sort algorithm
	 */
	@Override
	public void sort(E[] data) {
		boolean work = true;
		while(work) {
			work = false;
			for(int x = 1; x < data.length; x++) {
				if(compare(data[x], data[x - 1]) < 0) {
					E temp = data[x - 1];
					data[x - 1] = data[x];
					data[x] = temp;
					work = true;
				}
			}
		}
		
	}

	
}
