package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Andrew Hand
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Method to sort an element by the Counting sort algorithm
	 * @param data is the array of elements to sort
	 */
	public void sort(E[] data) {
		int min = data[0].getId();
		int max = data[0].getId();
		
		for(int x = 0; x < data.length; x++) {
			min = Math.min(data[x].getId(), min);
			max = Math.max(data[x].getId(), max);
		}
		
		int range = max - min + 1;
		
		int[] b = new int[range];
		
		for(int x = 0; x < data.length; x++) {
			b[data[x].getId() - min] = b[data[x].getId() - min] + 1;
		}
		
		for(int x = 1; x < range; x++) {
			b[x] = b[x - 1] + b[x];
		}
		
		@SuppressWarnings("unchecked")
		E[] array = (E[]) new Identifiable[data.length];
		
		for(int x = data.length - 1; x >= 0; x--) {
			array[b[data[x].getId() - min] - 1] = data[x];
			b[data[x].getId() - min] = b[data[x].getId() - min] - 1;
		}
		for(int x = 0; x < data.length; x++) {
			data[x] = array[x];
		}
	}
	

}
