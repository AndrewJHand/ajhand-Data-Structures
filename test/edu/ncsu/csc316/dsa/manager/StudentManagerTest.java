package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;
/**
 * Test class for StudentManager
 * @author Andrew Hand
 */
public class StudentManagerTest {

	/**
	 * private student manager to test with default sorter
	 */
	private StudentManager sm;
	/**
	 * Student manager to test with idSorter
	 */
	private StudentManager idSorter;
	/**
	 * Student manager to test with gpa sorter
	 */
	private StudentManager gpaSorter;
	/**
	 * Private string to prevent errors
	 */
	private String ascendingPath = "input/student_ascendingID.csv";
	/**
	 * Setup and initialize before testing
	 */
	@Before
	public void setUp() {
		StudentIDComparator sample = new StudentIDComparator();
		Sorter<Student> s = new InsertionSorter<Student>(sample);

		sm = new StudentManager(ascendingPath);
		idSorter = new StudentManager("input/student_descendingID.csv", s);
		gpaSorter = new StudentManager("input/student_randomOrder.csv", new InsertionSorter<Student>(new StudentGPAComparator()));
	}
	/**
	 * test the sort with original default sorting
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	/**
	 * test sorting by id
	 */
	@Test
	public void testidSort() {
		Student[] sorted = idSorter.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Ara", sorted[1].getFirst());
		assertEquals("Lacie", sorted[2].getFirst());
		assertEquals("Idalia", sorted[3].getFirst());
		assertEquals("Evelin", sorted[4].getFirst());
		assertEquals("Lewis", sorted[5].getFirst());
		assertEquals("Alicia", sorted[6].getFirst());
		assertEquals("Tyree", sorted[7].getFirst());
		assertEquals("Loise", sorted[8].getFirst());
		assertEquals("Roxann", sorted[9].getFirst());
		assertEquals("Nichole", sorted[10].getFirst());
		assertEquals("Charlene", sorted[11].getFirst());
		assertEquals("Shanti", sorted[12].getFirst());
		assertEquals("Cristine", sorted[13].getFirst());
		assertEquals("Tanner", sorted[14].getFirst());
		assertEquals("Dante", sorted[15].getFirst());
	}
	/**
	 * test sorting by gpa
	 */
	@Test
	public void testgpaSort() {
		Student[] sorted = gpaSorter.sort();
		assertEquals("Nichole", sorted[0].getFirst());
		assertEquals("Alicia", sorted[1].getFirst());
		assertEquals("Charlene", sorted[2].getFirst());
		assertEquals("Cristine", sorted[3].getFirst());
		assertEquals("Dante", sorted[4].getFirst());
		assertEquals("Lacie", sorted[5].getFirst());
		assertEquals("Idalia", sorted[6].getFirst());
		assertEquals("Ara", sorted[7].getFirst());
		assertEquals("Loise", sorted[8].getFirst());
		assertEquals("Tanner", sorted[9].getFirst());
		assertEquals("Amber", sorted[10].getFirst());
		assertEquals("Lewis", sorted[15].getFirst());
		assertEquals("Shanti", sorted[14].getFirst());
		assertEquals("Evelin", sorted[13].getFirst());
		assertEquals("Tyree", sorted[12].getFirst());
		assertEquals("Roxann", sorted[11].getFirst());
	}
	
}
