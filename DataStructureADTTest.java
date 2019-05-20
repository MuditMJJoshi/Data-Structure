//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P1: Implement and Test DataStructureADT
//
// Author:          Mudit Joshi
//
// Course:          CS 400 2019
//
// Lecture:         Lec 001
//
// Email:           mjoshi6@wisc.edu 
//
// Due Date:        02/07/2019
//
// Files:           TestDS_My must test DS_My
//					TestDS_Andy must test DS_Andy
//					TestDS_Deb must test DS_Deb
//					TestDSGautham must test DS_Gautham
//					TestDS_Katie must test DS_Katie
//					TestDS_MadanRaj must test DS_MadanRaj
//					TestDS_Niveditha must test DS_Niveditha
//					TestDS_Roshan must test DS_Roshan
//					TestDS_Sapan must test DS_Sapan
//					TestDS_Varun must test DS_Varun
//					TestDS_Vibhor must test DS_Vibhor
//					TestDS_Yash must test DS_Yash
//
// Lecturer's Name: Debra Deplar
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Mudit Joshi
 *
 *	TO TEST A DATA STRUCTURE CLASS:
 *	for each data structure class file you wish to test:
 *  	1. create a test class (like this one) 
 * 		2. edit the actual type being created (line 16)
 *   	 3. run this test class 
 *  	 4. OR, configure Eclipse project to run all tests
 *	 Eclipse: Run->Run Configurations->"Run All Tests..."
 *
 * 	the return type must be the name of the data structure class you are testing
 * 
 *	A data structure that can store at least 500 key,value pairs.
 *	 May not use any of Java's built-in Java collection types:
 *	 such as: List, ArrayList, LinkedList, etc...
 *	 May not add any public members (fields, methods, inner classes)
 *	 Wish to define an inner class
 *	for storing key and value as a pair
 *	such a class and its members should be "private"
 * 	Private Fields of the class
 * 	create field(s) here to store data pairs
 *
 * @param <T> The data Structure instance
 * 
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

	private T dataStructureInstance; //Declared Private data Structure instance

	protected abstract T createInstance(); //Declared instance of T
	
	/**
	 * 
	 * Declaring setUp Before Class
	 * 
	 * @throws Exception <E>
	 * 
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * 
	 * Declaring tear down after class
	 * 
	 * @throws Exception <E>
	 * 
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * 
	 * Declaring setUp 
	 * declares data structure instance
	 * 
	 * @throws Exception <E>
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance(); // declares data structure instance
	}
	
	/**
	 * 
	 * Declaring tear down
	 * declares data structure to null
	 * 
	 * @throws Exception <E>
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null; // declares data structure to null
	}
	
	/**
	 * 
	 * Declaring Test for get proper value should be returned
	 * print the fail error
	 * 
	 */
	@Test
	void test01() {
		dataStructureInstance.insert("W", "w");
		dataStructureInstance.insert("X", "x");
		dataStructureInstance.insert("Y", "y");
		if (dataStructureInstance.get("W").compareTo("w")!=0) {
			fail("Did not returned the proper value");
		}
	}

	/**
	 * 
	 * Declaring Test for after insert one size is on
	 * print the fail error
	 * 
	 */
	@Test
	void test02() {
		dataStructureInstance.insert("w", "w");
		if (dataStructureInstance.size() != 1) {
			fail("Size must be 01");
		}
	}

	/**
	 * 
	 * Declaring Test for remove returns false when key not present
	 * print the fail error
	 * 
	 * @throws Exception <E>
	 * 
	 */
	@Test
	void test03() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.insert("X", "X");
		dataStructureInstance.insert("Y", "Y");
		if (dataStructureInstance.remove("Z")) {
			fail("False returned since Key Z doesnt exist");
		}
	}

	/**
	 * 
	 * Declaring Test for contains true when key is present
	 * print the fail error
	 * 
	 */
	@Test
	void test04() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.insert("X", "X");
		dataStructureInstance.insert("Y", "Y");
		if (dataStructureInstance.contains("W")) {

		} else
			fail("True should returned contains method");
	}
	
	/**
	 * 
	 * Declaring Test for get null value should throw expcetion
	 * print the fail error
	 * 
	 * @throws Exception <E> RuntimeException should have been thrown
	 * 
	 */
	@Test
	void test05() {
		try {
			dataStructureInstance.get(null);
			fail("Runtime Exception be thrown");
		} catch (IllegalArgumentException e) {
			if (e.getMessage().compareTo("null key") != 0) {
				fail("Wrong Exception Message");
			}
		} catch (Exception e) {
			fail("Illegal Argument Exception was not thrown");
		}
	}
	
	/**
	 * 
	 * Declaring Test for get should not decrease size
	 * print the fail error
	 * 
	 */
	@Test
	void test06() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.get("W");
		if (dataStructureInstance.size() != 1) {
			fail("Size not be Changed");
		}
	}

	/**
	 * 
	 * Declaring Test for empty data structure
	 * print the fail error
	 * 
	 */
	@Test
	void test07() {
		if (dataStructureInstance.size() != 0)
			fail("Data Structure must be empty, but size=" + dataStructureInstance.size());
	}
	
	/**
	 * 
	 * Declaring Test for remove true when key is removed
	 * print the fail error
	 * 
	 */
	@Test
	void test08() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.insert("X", "W");
		dataStructureInstance.insert("Y", "Y");
		if (dataStructureInstance.remove("X")) {

		} else
			fail("Return true");
	}
	
	/**
	 * 
	 * Declaring Test for remove on empty Data Structure
	 * print the fail error
	 * 
	 */
	@Test
	void test09() {
		if (dataStructureInstance.remove("Z")) {
			fail("Return false");
		}
	}
	
	/**
	 * 
	 * Declaring Test for contains for null
	 * print the fail error
	 * 
	 */
	@Test
	void test10() {
		if (dataStructureInstance.contains(null)) {
			fail("False returned");
		}
	}
	
	/**
	 * 
	 * Declaring Test for after insert one remove one size is 0
	 * print the fail error
	 * 
	 */
	@Test
	void test11() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.remove("W");
		if (dataStructureInstance.size() != 0) {
			fail("Size not 0");
		}
	}

	/**
	 * 
	 * Declaring Test for remove exception when key is null
	 * print the fail error
	 * 
	 * @throws Exception <E> RuntimeException should have been thrown
	 * 
	 */
	@Test
	void test12() {
		try {

			dataStructureInstance.remove(null);
			fail("Throw exception");
		} catch (IllegalArgumentException e) {
			if (e.getMessage().compareTo("null key") != 0) {
				fail("Wrong Exception Message");
			}
		} catch (Exception e) {
			fail("False Exception Thrown");
		}
	}
	
	/**
	 * 
	 * Declaring Test for size updates correctly
	 * print the fail error
	 * 
	 */
	@Test
	void test13() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.insert("X", "X");
		dataStructureInstance.insert("Y", "Y");
		dataStructureInstance.remove("W");
		dataStructureInstance.insert("Z", "Z");
		dataStructureInstance.insert("A", "A");
		dataStructureInstance.remove("Y");
		dataStructureInstance.remove("Z");
		if (dataStructureInstance.size() != 2) {
			fail("Wrong Size");
		}
	}
	
	/**
	 * 
	 * Declaring Test for duplicate exception is thrown
	 * print the fail error
	 * 
	 * @throws Exception <E>
	 * 
	 */
	@Test
	void test14() {
		try {
			dataStructureInstance.insert("W", "W");
			dataStructureInstance.insert("X", "W");
			dataStructureInstance.insert("W", "Y");
			fail("Exception must be Thrown");
		} catch (RuntimeException e) {
			if (e.getMessage().compareTo("duplicate key") != 0) {
				fail("Wrong Exception thrown");
			}

		} catch (Exception e) {
			fail("Wrong Exception thrown");
		}
	}

	/**
	 * 
	 * Declaring Test for contains false when key isn't present
	 * print the fail error
	 * 
	 */
	@Test
	void test15() {
		dataStructureInstance.insert("W", "W");
		dataStructureInstance.insert("X", "W");
		dataStructureInstance.insert("Y", "Y");
		if (dataStructureInstance.contains("Z")) {
			fail("Should Return False");

		}
	}
	
	}
