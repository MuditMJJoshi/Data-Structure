//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P3: HashTable and Test HashTableADT
//
// Author:          Mudit Joshi
//
// Course:          CS 400 2019
//
// Lecture:         Lec 001
//
// Email:           mjoshi6@wisc.edu 
//
// Due Date:        03/14/2019
//
// Files:           HashTable.java
//					HashTableTest.java
//					
//
// Lecturer's Name: Debra Deplar
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Random;



/**
 * Add other fields that will be used by multiple tests
 * Add code that runs before each test method
 *  * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
 * 
 * @author Mudit Joshi
 *
 */
public class HashTableTest {

/**
 *  * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
 * @throws Exception
 */
    @Before
    public void setUp() throws Exception {

    }
    
    /**
     *  * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
     * @throws Exception
     */
    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {

    }
    
    
    /**
     * Tests that the hashTable resizes properly after crossing the loadFactorThreshhold
     *  * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
     */
    @Test
    public void Insert_Many() { 
        try {
            HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer, String>();
            for (int i = 0; i < 1000; i++) {
                htIntegerKey.insert(i, "");
            }

            if (htIntegerKey.numKeys() != 1000)
                fail("numKeys not how the inserted key");



        } catch (IllegalNullKeyException e) {
            fail("NOt throw this exception");

        } catch (DuplicateKeyException e) {
            fail("Not throw this exception");
        }
    }

    /**
     * Tests that a HashTable returns an integer code indicating which collision resolution strategy
     * is used.
     *
     * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
     */
    @Test
    public void Collision_scheme() { 
        HashTableADT Key = new HashTable<Integer, String>(); 
        int scheme = Key.getCollisionResolution();
        if (scheme < 1 || scheme > 9)
            fail("Collision Resolution with 1-9");
    }
    

    /**
     *  * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
     */
    @Test
    public void Insert_Resize() { 
        try {

            HashTableADT<Integer, String> Key = new HashTable<Integer, String>();

            Key.insert(8, ""); 
            Key.insert(5, "");
            Key.insert(6, "");
            Key.insert(7, "");
            Key.insert(10, "");

            if (Key.numKeys() != 5) {
                fail("Wrong Size"
                    + Key.numKeys());
            }

        } catch (IllegalNullKeyException e) {

        } catch (DuplicateKeyException e) {
            System.out.println("Duplicate Values");

        }

    }
    
    /*
     * Tests that get is able to grab the appropriate value (string) given a key
     *  * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
     * binary search trees
     */
    @Test
    public void Get_keys() { 
        try {
            HashTableADT<Integer, String> Key = new HashTable<Integer, String>();

            Key.insert(8, ""); // 1 
            Key.insert(5, ""); // 2
            Key.insert(6, ""); // 3
            Key.insert(7, ""); // 4
            Key.insert(10, ""); // 5
            Key.insert(11, "Hello"); // 6


            if (!Key.get(11).equals("Hello")) {
                fail("value " + Key.get(11));

            }


        } catch (IllegalNullKeyException e) {
            fail("Not throw this exception");

        } catch (DuplicateKeyException e) {
            fail("Not throw this exception");
        } catch (KeyNotFoundException e) {
            fail("Exception was thrown");

        }

    }

    /**
     * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws
     *  IllegalNullKeyException
     *   * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic 
     *   probe 3 OPEN ADDRESSING: double
     * hashing 4 CHAINED BUCKET: array list of array lists 5 CHAINED 
     * BUCKET: array list of linked
     * lists 6 CHAINED BUCKET: array list of binary search trees 7 
     * CHAINED BUCKET: linked list of
     * array lists 8 CHAINED BUCKET: linked list of linked lists 9 
     * CHAINED BUCKET: linked list of of
     * binary search trees
     */
    @Test
    public void IllegalNullKey() { 
        try {
            HashTableADT Key = new HashTable<Integer, String>(); 
            Key.insert(null, null);
            fail("Insert null key"); 
        } catch (IllegalNullKeyException e) {
            /* expected */ } catch (Exception e) {
            fail("Not throw exception " + e.getClass().getName()); 
        }
    }

    
    /**
     * Tests that the table stays at the correct number of keys after removing
     *  and inserting various
     * keys
     * 
     */
    @Test
    public void Remove_many() { 
        try {

            HashTableADT<Integer, String> Key = new HashTable<Integer, String>(); 
            
            for (int i = 0; i < 1000; i++) {
                Key.insert(i, "");
            }

            for (int i = 500; i < 700; i++) {
                Key.remove(i);
            }
            
            System.out.println(Key.numKeys());
            
            if (Key.numKeys() != 800) {
                fail("Wrong keys inserted and removed: "
                    + Key.numKeys());

            }

        } catch (IllegalNullKeyException e) {

        } catch (DuplicateKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

   
}

