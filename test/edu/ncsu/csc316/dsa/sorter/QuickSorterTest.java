/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the quick sorter
 * @author Andrew Hand
 */
public class QuickSorterTest {

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
	 * Private Bubble Sorter to create in setUp by ID
	 */
	private QuickSorter<Student> studentSorterID;
	/**
	 * Private Bubble Sorter to create in setUp by GPA
	 */
	private QuickSorter<Student> studentSorterGPA;
	
	/**
	 * initalize private variables before testing
	 */
	@Before
	public void setUp() {
		
		StudentIDComparator iDCompare = new StudentIDComparator();
		StudentGPAComparator gPACompare = new StudentGPAComparator();
		studentSorterID = new QuickSorter<Student>(iDCompare);
		studentSorterGPA = new QuickSorter<Student>(gPACompare);
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}
	/**
	 * test sorting by gpa and by id
	 */
	@Test
	public void testSortStudent() {
		QuickSorter<Student> naturalSorter = new QuickSorter<Student>();
		QuickSorter<Student> firstConstr = new QuickSorter<Student>();
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
		
		naturalSorter.sort(students);
		assertEquals(sFive, students[0]);
		assertEquals(sFour, students[1]);
		assertEquals(sOne, students[2]);
		assertEquals(sThree, students[3]);
		assertEquals(sTwo, students[4]);
		
		firstConstr.sort(students);
		assertEquals(sFive, students[0]);
		assertEquals(sFour, students[1]);
		assertEquals(sOne, students[2]);
		assertEquals(sThree, students[3]);
		assertEquals(sTwo, students[4]);
		
		
	}

}
