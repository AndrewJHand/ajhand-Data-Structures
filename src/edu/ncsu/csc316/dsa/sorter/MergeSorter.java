package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;


/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	
    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
    	super();
        
    }

    /**
     * Method sorts using merge sort recursively
     * should change the given array to a sorted version with the same elements
     * @param data is the array to sort
     */
	@Override
	public void sort(E[] data) {
		mergeSort(data);
	}
	
	/**
     * Method sorts using merge sort recursively
     * should change the given array to a sorted version with the same elements
     * @param data is the array to sort
     * @return E[] data but sorted
     */
	public E[] mergeSort(E[] data) {
		if(data.length < 2) {
			return data;
		}
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		E[] sorted = merge(left, right, data);
		return sorted;
		
	}
    /**
     * Method merges the left and right arrays into the T array
     * @param left is the array containing the sorted left half
     * @param right is the array containing the sorted right half
     * @param t is the array that should contain the merged sorted output of left and right
     * @return t but sorted
     */
    public E[] merge(E[] left, E[] right, E[] t) {
    	int leftIndex = 0;
    	int rightIndex = 0;
    	while(leftIndex + rightIndex < t.length) {
    		if(rightIndex == right.length || (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)) {
    			t[leftIndex + rightIndex] = left[leftIndex];
    			leftIndex++;
    		}
    		else {
    			t[leftIndex + rightIndex] = right[rightIndex];
    			rightIndex++;
    		}
    	}
    	return t;
    }
}