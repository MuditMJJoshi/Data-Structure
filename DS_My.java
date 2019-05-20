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

/**
 * 
 * A data structure that can store at least 500 key,value pairs.
 *
 * May not use any of Java's built-in Java collection types:
 * such as: List, ArrayList, LinkedList, etc...
 * 
 * May not add any public members (fields, methods, inner classes)
 * Wish to define an inner class
 *for storing key and value as a pair
 *such a class and its members should be "private"
 * Private Fields of the class
 * create field(s) here to store data pairs
 * 
 * @author Mudit
 *
 */
public class DS_My implements DataStructureADT {

	/**
	 * TO TEST A DATA STRUCTURE CLASS:
	 *
	 * for each data structure class file you wish to test:
	 * 
	 *   1. create a test class (like this one) 
	 *   
	 *    2. edit the actual type being created (line 16)
	 *    
	 *    3. run this test class 
	 *    
	 *     4. OR, configure Eclipse project to run all tests
	 *     
	 *  Eclipse: Run->Run Configurations->"Run All Tests..."
	 *  
	 *  the return type must be the name of the data structure class you are testing
	 *  
	 * @author Mudit
	 */
	public DS_My() {
		parent = null;
	}
	
	/**
	 * Private Declared Parent Node 
	 */
	private Node parent;
    
	/**
	 * Private Declared int size
	 */
	private int size = 0;
	
	/**
	 * 
	 * May not add any public members (fields, methods, inner classes)
	 * Wish to define an inner class
	 *for storing key and value as a pair
	 *such a class and its members should be "private"
	 * Private Fields of the class
	 * create field(s) here to store data pairs
	 * 
	 * @author Mudit
	 *
	 */
	private class Node {
		private Comparable key; // Declared Private Comparable Key
		private Object value; // Declared Private Object value
		private Node pointer; // Declared Private Node pointer

		private Node() {
			pointer = null;
		}

		private Node(Comparable _key, Object _val) {
			this.key = _key;
			this.value = _val;
			this.pointer = null;
		}
		
		private Node getNext() {
			return this.pointer;
		}
		
		private Comparable getKey() {
			return this.key;
		}

		private Object getVal() {
			return this.value;
		}

	}
    
	/**
	 * 
	 * Returns the number of elements in the data structure
	 * 
	 */
	@Override
	public int size() {
		return size;
	}
    
	/**
	 * 
	 * Returns true if the key is in the data structure
     * Returns false if key is null or not present
	 * 
	 * @param <K> The key must not be null and must be Comparable.
	 * @param <V> The data value associated with a given key.
	 */
	@Override
	public boolean contains(Comparable k) {
		if (k == null) {
			return false;
		}
		Node secondreferenceNode = parent;
		while (secondreferenceNode != null) {
			if (secondreferenceNode.getKey().compareTo(k) == 0) {
				return true;
			}
			secondreferenceNode = secondreferenceNode.getNext();
		}
		return false;
	}
	
	/**
	 * 
	 *	Returns the value associated with the specified key
     *	get - does not remove key or decrease size
     * 	If key is null, throws IllegalArgumentException("null key") 
	 * 
	 * @param <K> The key must not be null and must be Comparable.
	 * @param <V> The data value associated with a given key.
	 * 
	 */
	@Override
	public Object get(Comparable k) {
		if (k == null) {
			throw new IllegalArgumentException("null key");
		}

		Node thirdreferenceNode = parent;
		while (thirdreferenceNode != null) {
			if (thirdreferenceNode.getKey().compareTo(k) == 0) {
				return thirdreferenceNode.getVal();
			}
			thirdreferenceNode = thirdreferenceNode.getNext();
		}
		return null;
	}
	
	/**
	 * 
	 *If key is found, Removes the key from the data structure and decreases size
     * If key is null, throws IllegalArgumentException("null key") without decreasing size
     * If key is not found, returns false.
     * 
     * @param <K> The key must not be null and must be Comparable.
	 * @param <V> The data value associated with a given key.
	 * 
	 */
	@Override
	public boolean remove(Comparable k) {
		if (k == null) {
			throw new IllegalArgumentException("null key");
		} else if (parent == null) { // null list
			return false;
		} else if (parent.getKey().compareTo(k) == 0) {
			parent = parent.getNext();
			size--;
			return true;
		}

		Node lastNode = parent;
		Node referenceNode = lastNode.getNext();
		while (referenceNode != null) {
			if (referenceNode.getKey().compareTo(k) == 0) {
				lastNode.pointer = referenceNode.getNext();
				size--;
				return true;
			}
			lastNode = referenceNode;
			referenceNode = referenceNode.getNext(); // increment stuff
		}
		return false;
	}
	
	/**
	 * 
	 *Add the key,value pair to the data structure and increases size.
     * If key is null, throws IllegalArgumentException("null key");
     * If key is already in data structure, throws RuntimeException("duplicate key");
     * can accept and insert null values
     *
     * @param <K> The key must not be null and must be Comparable.
	 * @param <V> The data value associated with a given key.
	 * 
	 */
	@Override
	public void insert(Comparable k, Object v) {
		if (k == null) {
			throw new IllegalArgumentException("null key");
		}
		if (parent == null) {
			parent = new Node(k, v);
		} else {
			Node temporaryNode = parent;
			while (true) {
				if (temporaryNode.getKey().compareTo(k) == 0) {
					throw new RuntimeException("duplicate key");
				}
				if (temporaryNode.getNext() == null) {
					break;
				} else {
					temporaryNode = temporaryNode.getNext();
				}
			} // while close
			temporaryNode.pointer = new Node(k, v);
		}
		size++;
	}

}
