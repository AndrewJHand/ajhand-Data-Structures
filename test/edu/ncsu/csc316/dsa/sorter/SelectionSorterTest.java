package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
/**
 * Test class for selection sorter
 * @author Andrew Hand
 */
public class SelectionSorterTest {

	/**
	 * Private integer array to test with integers
	 */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/**
	 * Private integer array to test with integers
	 */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/**
	 * Private integer array to test with integers
	 */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/**
	 * private selection sorter
	 */
	private SelectionSorter<Integer> integerSorter;
	/**
	 * private selection sorter
	 */
	private SelectionSorter<Student> studentSorterID;
	/**
	 * private selection sorter
	 */
	private SelectionSorter<Student> studentSorterGPA;
	
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
	 * Setup to initialize variables before testing
	 */
	@Before
	public void setUp() {
		StudentIDComparator iDCompare = new StudentIDComparator();
		StudentGPAComparator gPACompare = new StudentGPAComparator();
		integerSorter = new SelectionSorter<Integer>();
		studentSorterID = new SelectionSorter<Student>(iDCompare);
		studentSorterGPA = new SelectionSorter<Student>(gPACompare);
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * integer tests
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals((Integer)1, dataAscending[0]);
		assertEquals((Integer)2, dataAscending[1]);
		assertEquals((Integer)3, dataAscending[2]);
		assertEquals((Integer)4, dataAscending[3]);
		assertEquals((Integer)5, dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals((Integer)1, dataDescending[0]);
		assertEquals((Integer)2, dataDescending[1]);
		assertEquals((Integer)3, dataDescending[2]);
		assertEquals((Integer)4, dataDescending[3]);
		assertEquals((Integer)5, dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals((Integer)1, dataRandom[0]);
		assertEquals((Integer)2, dataRandom[1]);
		assertEquals((Integer)3, dataRandom[2]);
		assertEquals((Integer)4, dataRandom[3]);
		assertEquals((Integer)5, dataRandom[4]);
		
		Integer[] dup = {2, 1, 4, 6, 1};
		integerSorter.sort(dup);
		assertEquals((Integer)1, dup[0]);
		assertEquals((Integer)1, dup[1]);
		assertEquals((Integer)2, dup[2]);
		assertEquals((Integer)4, dup[3]);
		assertEquals((Integer)6, dup[4]);
		
		Integer[] single = {2};
		integerSorter.sort(single);
		assertEquals((Integer)2, single[0]);
		
		Integer[] two = {4, 2};
		integerSorter.sort(two);
		assertEquals((Integer)2, two[0]);
		assertEquals((Integer)4, two[1]);
	}

	/**
	 * Tests I made for students
	 */
	@Test
	public void testSortStudent() {
		Student[] students = {sTwo, sThree, sOne, sFive, sFour};
		
		studentSorterID.sort(students);
		assertEquals(sOne, students[0]);
		assertEquals(sTwo, students[1]);
		assertEquals(sThree, students[2]);
		assertEquals(sFour, students[3]);
		assertEquals(sFive, students[4]);
		
		studentSorterGPA.sort(students);
		assertEquals(sFive, students[0]);
		assertEquals(sFour, students[1]);
		assertEquals(sThree, students[2]);
		assertEquals(sTwo, students[3]);
		assertEquals(sOne, students[4]);
		
	}
}
