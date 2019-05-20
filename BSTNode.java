//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P2: BST and Balanced Search Tree AVL
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
// Files:           AVL.java, AVLTest.java, BST.java, BSTADT.java, BSTNode.java
//                  BSTTest.java, DataStructureADT.java, DataStructureADTTest.java
//                  DuplicatedKeyException.java, IllegalNullKeyException.java,
//                  KeyNotFoundException.java, SearchTreeADT.java
//
// Lecturer's Name: Debra Deplar
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
// Known Bugs: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// package level access means that all classes in the package can access directly.
//
// Classes that use this type:  <TODO, list which if any classes use this type>
class BSTNode<K,V> {
	
	K key;
	V value;
	BSTNode<K,V> left;
	BSTNode<K,V> right;
	int balanceFactor;
	int height;
	

	/**
	 * @param key
	 * @param value
	 * @param leftChild
	 * @param rightChild
	 */
	BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
		this.key = key;
		this.value = value;
		this.left = leftChild;
		this.right = rightChild;
		this.height = 0;
		this.balanceFactor = 0;
	}
	
	BSTNode(K key, V value) { this(key,value,null,null); }

	
}
