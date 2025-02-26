package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Class to test StudentIDComparator
 * @author Andrew Hand
 */
public class StudentIDComparatorTest {

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
	 * Private StudentIDComparator to create in setUp
	 */
	private StudentIDComparator comparator;

	/**
	 * Setup to initialize variables
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}
	/**
	 * test the compare method in IDComparator
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertEquals(0, comparator.compare(sOne, sOne));
		assertEquals(0, comparator.compare(sTwo, sTwo));
		assertTrue(comparator.compare(sOne, sThree) < 0);
		assertTrue(comparator.compare(sOne, sFour) < 0);
		assertTrue(comparator.compare(sOne, sFive) < 0);
		assertTrue(comparator.compare(sThree, sOne) > 0);
		assertTrue(comparator.compare(sFour, sOne) > 0);
		assertTrue(comparator.compare(sFive, sOne) > 0);
		
	}


}
