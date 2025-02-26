package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract class for all comparison sorters
 * @author Andrew Hand
 * @param <E> the generic type of the data
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * every child of this class should have a private comparator
	 */
    private Comparator<E> comparator;
    
    /**
     * The default constructor creates a comparator of the type natural order
     */
    public AbstractComparisonSorter() {
    	setComparator(null);
    }
    /**
     * one parameter constructor creates the comparator with the parameter
     * @param comparator is the comparator to set
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Helper method to set the comparator
     * @param comparator is the comparator to set
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * Inner class to have a default comparator
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * Default compare method
     * @param first is the first element to compare
     * @param second is the second element to compare
     * @return 0, 1, -1 if the elements are equal, greater than the other, or less than the other
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}
