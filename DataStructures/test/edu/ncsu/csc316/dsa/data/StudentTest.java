package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * test the student class
 * @author Andrew Hand
 */
public class StudentTest {

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
	 * Private initializer
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Teaching test
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}
	/**
	 * Teaching test
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}
	/**
	 * Teaching test
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}
	/**
	 * Teaching test
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	/**
	 * Teaching test
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}
	/**
	 * my test
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	/**
	 * method to test get credit hours
	 */
	@Test
	public void testCreditHours() {
		assertEquals(sOne.getCreditHours(), 1);
	}
	
	/**
	 * my test
	 */
	@Test
	public void testEquals() {
		assertTrue(sFour.equals(sFour));
		assertFalse(sOne.equals(sFive));
	}
	/**
	 * my test
	 */
	@Test
	public void testToString() {
		assertEquals("Student ThreeFirst ThreeLast has a gpa= 3.0, their unityID= threeUnityID.", sThree.toString());
	}
	
}
