package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	/**
	 * private comparator to know how to compare the element E
	 */
    private Comparator<E> comparator;
    
    /**
     * Default constructor sets the comparator = to NaturalOrder
     */
    public SelectionSorter() {
    	setComparator(new NaturalOrder());
    }
    /**
     * One arg constructor sets the comparator to the argument
     * @param comparator is the comparator to set to
     */
    public SelectionSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   

    /**
     * Method to sort using selection sort
     * @param data is the array of elements to sort
     */
    public void sort(E[] data) {
        for(int x = 0; x < data.length; x++) {
        	int min = x;
        	for(int y = x + 1; y < data.length; y++) {
        		if(comparator.compare(data[y], data[min]) < 0) {
        			min = y;
        		}
        	}
        	if(x != min) {
        		E temp = data[x];
        		data[x] = data[min];
        		data[min] = temp;
        	}
        }
    }
    /**
     * Inner class to be the natural comparator
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
}