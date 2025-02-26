package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	
	/**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

    /**
     * reference to which pivot selector we are using
     */
    private PivotSelector selector;

    
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = RANDOM_ELEMENT_SELECTOR;
        } else {
            this.selector = selector;
        }
    }
	
    /**
     * Method to run the quick sorting algorithm
     * should change data to become sorted using the quick sorting algorithm
     * @param data is the array to sort
     */
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
		setSelector(FIRST_ELEMENT_SELECTOR);
		quickSort(data, 0, data.length - 1);
		setSelector(LAST_ELEMENT_SELECTOR);
		quickSort(data, 0, data.length - 1);
		setSelector(MIDDLE_ELEMENT_SELECTOR);
		quickSort(data, 0, data.length - 1);
	}

	/**
	 * helper method to run quick sort recursively
	 * @param data is the array to sort
	 * @param low is the low index
	 * @param high is the high index
	 */
	private void quickSort(E[] data, int low, int high) {
		if(low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * helper method to run quick sort recursively
	 * @param data is the array to sort
	 * @param low is the low index
	 * @param high is the high index
	 * @return the pivot location
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * helper method to run quick sort recursively
	 * @param data is the array to sort
	 * @param low is the low index
	 * @param high is the high index
	 * @return an integer of the partition
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int separator = low;
		for(int x = low; x <= high - 1; x++) {
			if(compare(data[x], pivot) <= 0) {
				swap(data, separator, x);
				separator++;
			}
		}
		swap(data, separator, high);
		return separator;
	}
	/**
	 * Method to swap two elements in an array
	 * @param data is the array where the elements are stored
	 * @param index1 is the first index
	 * @param index2 is the second index
	 */
	private void swap(E[] data, int index1, int index2) {
		E temp = data[index2];
		data[index2] = data[index1];
		data[index1] = temp;
	}
	/**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class LastElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return (high + low) / 2;
        }
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class RandomElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
        	int range = high - low + 1;
            return (int)((Math.random() * range) + low);
        }
    }
}