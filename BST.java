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
//                  BSTTest.java, DataStructureADT.java, 
//					DataStructureADTTest.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  protected BSTNode<K, V> root; // root of tree
  protected int numKeys; // keys in bst -- raw size

  /**
   * def construct for class -- root null -- size 0
   */
  public BST() {
    root = null;
    numKeys = 0;
  }
  
  /**
   * just return the height via helper
   */
  @Override
  public int getHeight() {
    return getHeightHelper(root);
  }

  /**
   * recursively travel thru down thru the tree -- and get the biggest one
   * 
   * @param curr -- node you're starting on
   * @return the height from curr node down
   */
  protected int getHeightHelper(BSTNode<K, V> node) {
    if (node == null) {
      return 0;
    }

    int leftnode = getHeightHelper(node.left);

    int rightnode = getHeightHelper(node.right);

    if (rightnode > leftnode) {
      return rightnode + 1;
    } else {
      return leftnode + 1;
    }
  }
  
  

  /**
   * call the preo helper root - left - right
   */
  @Override
  public List<K> getPreOrderTraversal() {
    List<K> outputnode = new ArrayList<K>(); // keys go here
    PreO(root, outputnode); // help call
    return outputnode;
  }
  
  
  /**
   * man this really is a tough one. i just cant wrap my head around it
   */
  @Override
  public K getKeyAtRoot() {
    return root.key; // ITS JUST A VARIABLE
  }

  /**
   * search for a key -- if not found -- null
   * if found -- snag the key from getnode
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> pointnode = getNode(root, key);
    if (pointnode == null) {
      throw new KeyNotFoundException();
    }
    return pointnode.left.key;
  }

  /**
   * same as above -- but RIGHT
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> nodechecker = getNode(root, key);
    if (nodechecker == null) {
      throw new KeyNotFoundException();
    }
    return nodechecker.right.key;
  }


  /**
   * helper to traverse BST
   * 
   * @param curr -- current node
   * @param arr  -- array to be filled w keys
   */
  protected void PreO(BSTNode<K, V> currentnode, List<K> array) {
    array.add(currentnode.key); // add starter
    if (currentnode.left != null) // recursive search L pop @ null leaf
      PreO(currentnode.left, array);
    if (currentnode.right != null) // recursive search R
      PreO(currentnode.right, array);
  }



  /**
   * traverse inorder L -- root -- R
   */
  @Override
  public List<K> getInOrderTraversal() {
    List<K> tempnode = new ArrayList<K>();
    IOHelper(root, tempnode);
    return tempnode;
  }

  /**
   * io helper method for traversal
   * 
   * @param curr -- current node passed in
   * @param arr  -- array to fill
   */
  protected void IOHelper(BSTNode<K, V> currentnode, List<K> array) {
    if (currentnode.left != null)
      IOHelper(currentnode.left, array);
    array.add(currentnode.key);
    if (currentnode.right != null)
      IOHelper(currentnode.right, array);
  }

  /**
   * basic insert method -- checks for exc then calls helper
   */
  @Override
  public void insert(K keynode, V keyvalue) throws  DuplicateKeyException,IllegalNullKeyException {
    BSTNode<K, V> inputnode = new BSTNode<K, V>(keynode, keyvalue); 
    if (root == null) { // empty case
      root = inputnode;
      this.numKeys++;
    } else if (keynode == null) { // key exc
      throw new IllegalNullKeyException();
    } else if (contains(keynode)) { // dup exc
      throw new DuplicateKeyException();
    } else {
      insertHelp(root, inputnode); // proper insert -- call the handicap 
      this.numKeys++;
    }

  }

  /**
   * helper to deal with arrangement
   * 
   * @param curr -- current node - helps recursion
   * @param input -- node to be inserted
   * @throws IllegalNullKeyException -- key param null
   * @throws DuplicateKeyException -- key already inserted
   */
  protected void insertHelp(BSTNode<K, V> currentnode, BSTNode<K, V> inputnode)
      throws IllegalNullKeyException, DuplicateKeyException {

    if (inputnode.key.compareTo(currentnode.key) < 0) { // traverse left
      if (currentnode.left == null) {
    	  currentnode.left = inputnode;
      } else {
        insertHelp(currentnode.left, inputnode); // recur
      }
    } else { // traverse right
      if (currentnode.right == null) {
    	  currentnode.right = inputnode;
      } else {
        insertHelp(currentnode.right, inputnode); // recur
      }
    }
  } // method close

  /**
   * core removal method -- check exc then hit helper
   */
  @Override
  public boolean remove(K keynode) throws  KeyNotFoundException, IllegalNullKeyException {

    if (keynode == null) { // exc null key param
      throw new IllegalNullKeyException();
    }
    if (!(contains(keynode))) { // key aint there bro
      throw new KeyNotFoundException();
    }
    if (root == null) { // null root - nothing to remove
      return false;
    } else { // call helper -- no break points yet
      root = removeHelper(root, keynode);
      numKeys--;
      return true;
    }
  }
  
  /**
   * L - R - Root
   */
  @Override
  public List<K> getPostOrderTraversal() {
    List<K> tempnode = new ArrayList<K>();
    PostO(root, tempnode);
    return tempnode;
  }

  /**
   * helper to do post order iteration
   * 
   * @param curr -- current node
   * @param arr  -- array for keys post traverse
   */
  protected void PostO(BSTNode<K, V> currentnode, List<K> array) {
    if (currentnode.left != null)
      PostO(currentnode.left, array);
    if (currentnode.right != null)
      PostO(currentnode.right, array);
    array.add(currentnode.key);
  }

  /**
   * level order call -- use queue to simplify the arr building
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    List<K> leveledList = new ArrayList<K>();
    if (root == null)
      return leveledList;
    else {
      Queue<BSTNode<K, V>> BSTree = new LinkedList<BSTNode<K, V>>(); // create queue here
      BSTree.add(root);
      while (!BSTree.isEmpty()) { // iterate thru the bst
        BSTNode<K, V> temp = BSTree.poll();
        leveledList.add(temp.key);
        if (temp.left != null) // check leaves
          BSTree.add(temp.left);
        if (temp.right != null)
          BSTree.add(temp.right);
      }
      return leveledList;
    }
  }

  /**
   * get method just using getNode
   */
  @Override
  public V get(K keynode) throws  KeyNotFoundException,IllegalNullKeyException {
    if (keynode == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> pointernode = getNode(root, keynode);
    if (pointernode == null) {
      throw new KeyNotFoundException();
    }
    return pointernode.value;
  }

  /**
   * use getnode to check if bst has a certain key
   */
  @Override
  public boolean contains(K keynode) throws IllegalNullKeyException {
    if (keynode == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> recievenode = getNode(this.root, keynode); // recursive call
    if (recievenode != null) {
      return true;
    } else {
      return false;
    }
  }
  

  /**
   * helps out the remove method -- its the recusive aspect
   * 
   * @param curr -- current node - tracked thru recursion   
   * @param keyDeleted -- key to be deleted
   * @return -- node that you're deleting -- its for the recursion
   * @throws KeyNotFoundException -- key aint there bro
   */
  protected BSTNode<K, V> removeHelper(BSTNode<K, V> node, K keynoderemoved)
      throws KeyNotFoundException {

    if (node == null) {
      return node;
    }
    if (node.key.compareTo(keynoderemoved) > 0) {
      node.left = removeHelper(node.left, keynoderemoved); // recur left
    } else if (node.key.compareTo(keynoderemoved) < 0) {
      node.right = removeHelper(node.right, keynoderemoved); // recur right
    } else {
      // 1 child
      if (node.right == null) {
        return node.left; 
      } else if (node.left == null) {
        return node.right;
        // 2 child
      } else if (node.right != null && node.left != null) {
        K pointernode = inOrderSucessor(node.right).key;
        node.key = pointernode;
        node.right = removeHelper(node.right, node.key);
      }
    }
    return node;
  }

  /**
   * find the in order successor to help replacing a 2 child case
   * 
   * @param node - node youre finding successor of
   * @return - return the successor
   */
  protected BSTNode<K, V> inOrderSucessor(BSTNode<K, V> Node) {
    if (Node.left == null)
      return Node; // pop @ bottom left leaf
    if (Node.left != null)
      return inOrderSucessor(Node.left); // recur left after going right once
    return null;
  }



  /**
   * recorsive getnode method to traverse and search through a bst
   * 
   * @param current -- current location in bst
   * @param key -- key to be searched for
   * @return -- node found -- null if not found
   */
  protected BSTNode<K, V> getNode(BSTNode<K, V> node, K keynode) {
    if (node == null) {
      return null;
    }
    if (node.key.compareTo(keynode) == 0) { // found the node
      return node;
    }
    if (node.key.compareTo(keynode) > 0) { // traversal recursions
      return getNode(node.left, keynode);
    } else if (node.key.compareTo(keynode) < 0) {
      return getNode(node.right, keynode);
    }

    else {
      return null;
    }
  }

  /**
   * you literally just return the variable. its not hard
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }




}
