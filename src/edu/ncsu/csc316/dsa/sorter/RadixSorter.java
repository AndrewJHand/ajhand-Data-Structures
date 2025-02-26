package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @author Andrew Hand
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Method should sort an element array using radix algorithm
	 * @param data is the array of elements to sort.
	 */
	@Override
	public void sort(E[] data) {
		int largest = 0;
		for(int x = 0; x < data.length; x++) {
			largest = Math.max(largest, data[x].getId());
		}
		int digits = (int) Math.ceil(Math.log(largest + 1) / Math.log(10));
		
		int pos = 1;
		
		for(int x = 1; x <= digits; x++) {
			int[] b = new int[10];
			for(int y = 0; y < data.length; y++) {
				
				b[(data[y].getId() / pos) % 10] = b[(data[y].getId() / pos) % 10] + 1;
		}
		
			for(int y = 1; y < 10; y++) {
				b[y] = b[y - 1] + b[y];
			}
			
			@SuppressWarnings("unchecked")
			E[] array = (E[]) new Identifiable[data.length];
			
			for(int y = data.length - 1; y >= 0; y--) {
				array[b[(data[y].getId() / pos) % 10] - 1] = data[y];
				b[(data[y].getId() / pos) % 10] = b[(data[y].getId() / pos) % 10] - 1;
			}
			
			for(int y = 0; y < data.length; y++) {
				data[y] = array[y];
			}
			pos = pos * 10;
		}
	}
	
}
