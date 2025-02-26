package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Andrew Hand
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/**
	 * The Student's First Name
	 */
	private String first;
	/**
	 * The Student's Last Name.
	 */
	private String last;
	/**
	 * The Student's Id.
	 */
	private int id;
	/**
	 * The Student's number of credit hours.
	 */
	private int creditHours;
	/**
	 * The Student's GPA
	 */
	private double gpa;
	/**
	 * The Student's unity Id.
	 */
	private String unityID;
	/**
	 * Constructor creates a student with the given parameters
	 * @param first is the student's first name
	 * @param last is the student's last name
	 * @param id is the student's id
	 * @param creditHours is the student's number of credit hours
	 * @param gpa is the student's GPA
	 * @param unityID is the student's unityID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}
	
	
	/**
	 * Getter method for the first name of a student
	 * @return the first name of a student
	 */
	public String getFirst() {
		return first;
	}


	/**
	 * Setter method for a student's first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}


	/**
	 * Getter method for a studen't last name
	 * @return the last name of the student
	 */
	public String getLast() {
		return last;
	}


	/**
	 * Setter method for a student's last name
	 * @param last the last name to set
	 */
	public void setLast(String last) {
		this.last = last;
	}


	/**
	 * Getter method for a student's Id
	 * @return the id of the student
	 */
	public int getId() {
		return id;
	}


	/**
	 * Setter method for the student's Id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Getter method for a student's credit hours
	 * @return the creditHours of the student
	 */
	public int getCreditHours() {
		return creditHours;
	}


	/**
	 * Setter method for a student
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}


	/**
	 * Getter method for a students gpa
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}


	/**
	 * Setter method for a student's gpa
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	/**
	 * Getter method for a student's unity id
	 * @return the unityID of the student
	 */
	public String getUnityID() {
		return unityID;
	}


	/**
	 * Setter method for a student's unity id
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * The compareTo method for student
	 * @param o is the student to compare with
	 * @return number greater than 1 if o is "greater" than this, or number less than 1 if o is "less" than this
	 */
	@Override
	public int compareTo(Student o) {
		if(this.getLast().compareTo(o.getLast()) != 0) {
			return this.getLast().compareTo(o.getLast());
		}
		else if(this.getFirst().compareTo(o.first) != 0) {
			return this.getFirst().compareTo(o.getFirst());
		}
		else {
			return this.getUnityID().compareTo(o.getUnityID());
		}
	}

	/**
	 * HashCode for the students
	 */
	@Override
	public int hashCode() {
		return Objects.hash(first, last, unityID);
	}

	/**
	 * Equals method to check if two student's first, last, and unityID are the same
	 * @return true or false if they are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(first, other.first) && Objects.equals(last, other.last)
				&& Objects.equals(unityID, other.unityID);
	}


	@Override
	public String toString() {
		return "Student " + first + " " + last + " has a gpa= " + gpa + ", their unityID= " + unityID + ".";
	}


}
