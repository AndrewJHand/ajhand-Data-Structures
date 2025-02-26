package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
/**
 * Test class for CountingSorter
 * @author Andrew Hand
 */
public class CountingSorterTest {
	/**
	 * Student one private to prevent errors
	 */
	private Student sOne;
	/**
	 * Student two private to prevent errors
	 */
	private Student sTwo;
	/**
	 * Student three private to prevent errors
	 */
	private Student sThree;
	/**
	 * Student four private to prevent errors
	 */
	private Student sFour;
	/**
	 * Student five private to prevent errors
	 */
	private Student sFive;
	/**
	 * Private CountingSorter to create in setUp
	 */
	private CountingSorter<Student> sorter;
	/**
	 * Method to initialize private variables/objects
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	/**
	 * Method to test sorting
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * Tests I created to test the counting sort
	 */
	@Test
	public void otherTestSortStudent() {
		Student[] original = {sOne, sFive, sFour, sTwo, sThree};
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
}
