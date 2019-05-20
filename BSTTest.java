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

import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * 
 * Checks BST
 * 
 * @author Mudit Joshi
 *
 */
public class BSTTest extends DataStructureADT {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @Override
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructure = bst = createInstance();
    dataStructure2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */

  @Override
  @AfterEach
  void tearDown() throws Exception {
    dataStructure = bst = null;
    dataStructure2 = bst2 = null;
  }

  /*
   * (non-Javadoc)
   *
   * @see DataStructureADTTest#createInstance()
   */
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /*
   * (non-Javadoc)
   *
   * @see DataStructureADTTest#createInstance2()
   */
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each of the four
   * traversal orders.
   */
  @Test
  void empty_orders() {
    try {

      List<String> Order = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + Order);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(Order, inOrder);
      assertEquals(Order, preOrder);
      assertEquals(Order, postOrder);
      assertEquals(Order, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }
  


  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   *
   * Insert order: 20-10-30 In-Order traversal order: 10-20-30
   */
  @Test
  void check_inOrderbalanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(10); // L
      Order.add(20); // V
      Order.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }



  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   *
   * Insert order: 10-20-30 Level-Order traversal order: 10-20-30
   */
  @Test
  void check_levelOrderbalanced_insert_order() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected levelOrder 20 10 20
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(10); // V
      Order.add(20); // L
      Order.add(30); // R

      // GET LEVEL-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }


  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for each of the four
   * traversal orders.
   */
  @Test
  void check_traversalsinsert_one() {

    try {

      List<Integer> Order = new ArrayList<Integer>();
      Order.add(10);
      bst2.insert(10, "ten");
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + Order);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(Order, inOrder);
      assertEquals(Order, preOrder);
      assertEquals(Order, postOrder);
      assertEquals(Order, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }

  }


  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   *
   * Insert order: 10-20-30 In-Order traversal order: 10-20-30
   */
  @Test
  void check_balanced_insert_orders() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(10); // L
      Order.add(20); // R
      Order.add(30); // V

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }


  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   *
   * Insert order: 10-20-30 Pre-Order traversal order: 10-20-30
   */
  @Test
  void checkbalanced_insertorder() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected postOrder 10 30 20
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(10); // L
      Order.add(20); // R
      Order.add(30); // V

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }

  }
  
  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   *
   * Insert order: 20-10-30 Pre-Order traversal order: 20-10-30
   */
  @Test
  void check_preOrderbalanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected preOrder 20 10 30
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(20); // V
      Order.add(10); // L
      Order.add(30); // R

      // GET PRE-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }
  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   *
   * Insert order: 20-10-30 Post-Order traversal order: 10-30-20
   */
  @Test
  void check_postOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected postOrder 10 30 20
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(10); // L
      Order.add(30); // R
      Order.add(20); // V

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   *
   * Insert order: 10-20-30 Post-Order traversal order: 30-20-10
   */
  @Test
  void checkbalanced_insert_order() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected postOrder 30 20 10
      List<Integer> Order = new ArrayList<Integer>();
      Order.add(30); // L
      Order.add(20); // R
      Order.add(10); // V

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(Order, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }

  }



}

