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


import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * must be generic type - should be able to instantiate with String, Integer, 
 * Float, etc.
 * must implement operations described in the provided HashTableADT<K, V> 
 *interface in an efficient way (O(1)).
 *may use any of the following for internal data structure(s) as you like: 
 *arrays, ArrayList, LinkedList, a new node type, etc
 *must NOT USE Java's HashMap or TreeMap types (ask if you are not sure)
 *must handle edge or corner cases (first, last, empty, full)
 // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
 * 
 * @author Mudit Joshi
 *
 * @param <K>
 * @param <V>
 * 
 * 
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	
	private ArrayList<K> tableKey; 
	
	private ArrayList<V> Tablevalue; 
	
	
	private ArrayList<K> nextTableKey; 
	private boolean[] nextTableRemove; 
	
	private double BufferValue; 
	private int currSize; 
	
	private ArrayList<V> nextTableValue;
	
	private boolean[] tableKeyRemove; 
	
	private int currload; 
	private int size;
	 
	
	
	/**
	 * Constructor
	 * @param initialCapacity
	 * @param loadFactorThreshold
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		if(initialCapacity <= 0) {
			throw new IllegalArgumentException();
		}
		this.currSize = initialCapacity;
		this.BufferValue = loadFactorThreshold;
		this.currload = 0;
		this.size = 0;
		this.tableKey = new ArrayList<K>(Collections.nCopies(currSize, null));
		this.Tablevalue = new ArrayList<V>(Collections.nCopies(currSize, null));
		this.tableKeyRemove = new boolean[currSize];
	}
	
	/**
	 * Default Constructor
	 */
	public HashTable() {
		this.currSize = 97;
		this.BufferValue = 0.7;
		this.currload = 0;
		this.size = 0;  
		this.tableKey = new ArrayList<K>(Collections.nCopies(currSize, null));
		this.Tablevalue = new ArrayList<V>(Collections.nCopies(currSize, null));
		this.tableKeyRemove = new boolean[currSize];
	}
	
	/**
	 * 
	 * The goal for your HashTable is to build a searchable data structure
	 *  that achieves constant time O(1) for lookup, insert, and delete
	 *   operations with comparable performance to Java's built-in TreeMap type.
	 *   
	 *   // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
	 * 
	 */
    private void newSize() { 
    	int newSize = this.currSize * 2 + 1;
    	int newIndex = 0; 
    	
    	this.nextTableKey = new ArrayList<K>(Collections.nCopies(newSize, null));
    	this.nextTableValue = new ArrayList<V>(Collections.nCopies(newSize, null));
    	this.nextTableRemove = new boolean[newSize];
    	//rehash
    	for(int i = 0; i < currSize;  i++) {
    		if(this.tableKey.get(i)!=null) {
    			K keytemp = this.tableKey.get(i);
        		V valuetemp = this.Tablevalue.get(i);
        		boolean removetemp = this.tableKeyRemove[i];
        		newIndex = newIndex(keytemp.hashCode() % newSize);
        		this.nextTableKey.add(newIndex, keytemp);
            	this.nextTableValue.add(newIndex, valuetemp);
            	this.nextTableRemove[newIndex] = removetemp; 
    		}
    	}
    	
    	//set table 
    	this.tableKey = this.nextTableKey;
    	this.Tablevalue = this.nextTableValue;
    	this.tableKeyRemove = this.nextTableRemove;
    	//set property
    	this.currSize = newSize;
    }
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     *  // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     *
     * 
     * @return
     */
    private boolean Threshold(){ 
    	if(getLoadFactor() > getLoadFactorThreshold()){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * 
     * @param key
     * @param newhashIndex
     * @return
     */
    private int newIndex(K key, int newhashIndex) {  
    	int Index = newhashIndex; 
    	while(Index != newhashIndex - 1) {
    		if(this.tableKey.get(Index) == null) {
    			return Index;
    		}
    		else if(this.tableKey.get(Index) == key) {
    			if(tableKeyRemove[Index] == true) {
    				tableKeyRemove[Index] = false;
    				return Index;
    			}
    		}
    		else if(Index == this.currSize) {
    			//wrapping
    			Index = 0;
    		}
    		//linear probing
    		Index++;
    	}
    	return -1;
    }
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * 
     * 
     * @param newkey
     * @param newValue
     * @param newhashIndex
     * @return
     */
    private boolean insertTable(K newkey, V newValue,  int newhashIndex) { 
    	this.tableKey.add(newhashIndex, newkey);
    	this.Tablevalue.add(newhashIndex, newValue);
    	return true;
    } 
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * 
     * @param newkey
     * @param newhashIndex
     * @return
     */
    private int checkPointer(K newkey, int newhashIndex) {  
    	int Index = newhashIndex; 
    	while(Index != newhashIndex - 1 ){
    		if(tableKey.get(Index) == newkey) {
    			if(tableKeyRemove[Index] == true) {
    				tableKeyRemove[Index] = false;
    				return Index;
    			}
    			else {
    				//duplicate
        			return -1;
    			}
    		}
    		else if(tableKey.get(Index) == null) {
    			return Index;
    		}
    		else if(Index == this.currSize) {
    			Index = 0;
    		}
    		Index++;
    	}
    	return -1;
    }
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * 
     * @param newhashIndex
     * @return
     */
    private int newIndex(int newhashIndex) {  
    	int Index = newhashIndex; 
    	while(Index != newhashIndex - 1) {
    		if(this.nextTableKey.get(Index) == null) {
    			return Index;
    		}
    		else if(Index == currSize) {
    			//wrapping
    			Index = 0;
    		}
    		else {
    			//linear probing
    			Index++;
    		}
    	}
    	return -1;
    }
    
    /**
     *  *must handle resizing (we will set the initial capacity "table size" and 
	*add enough to cause re-sizing)
	*must include a constructor with the following signature:  public HashTable
	*(int initialCapacity, double loadFactor)
	*must document design choices that you make for hashing and collision resolution
	*in your HashTable implementation (see comments and document there)
	*
	* // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * @param newkey
     * @return
     */
    private boolean checkIndex(K newkey) {  
    	int newhashIndex = Math.abs(newkey.hashCode() % currSize); 
    	int i = newhashIndex;
    	while(i != newhashIndex - 1) {
    		if(tableKey.get(i) == newkey) {
    			return true;
    		}
    		else if(tableKey.get(i) == null) {
    			return false;
    		}
    		
    		if(i == currSize) {
    			i = 0;
    		}
    		i++;
    	}
    	return false;
    	
    }
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     *  // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * 
     * @param newkey
     * @return
     */
    private V keyValue(K newkey) {  
    	int newhashIndex = Math.abs(newkey.hashCode() % currSize); 
    	int i = newhashIndex;
    	while(i != newhashIndex - 1) {
    		if(tableKey.get(i) == newkey) {
    			if(tableKeyRemove[i] == true) {
    				return null;
    			}
    			else {
    				return Tablevalue.get(i);
    			}
    		}
    		else if(tableKey.get(i) == null) {
    			return null;
    		}
    		
    		if(i == currSize) {
    			i = 0;
    		}
    		i++;
    	}
    	return null;
    } 
    
    /**
     * 
     *  *must handle resizing (we will set the initial capacity "table size" and 
     *add enough to cause re-sizing)
     *must include a constructor with the following signature:  public HashTable
     *(int initialCapacity, double loadFactor)
     *must document design choices that you make for hashing and collision resolution
     *in your HashTable implementation (see comments and document there)
     *
     *  // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     * 
     */
    @Override
    public V get(K newkey) throws IllegalNullKeyException, KeyNotFoundException{
    	if(newkey == null) {
    		throw new IllegalNullKeyException();
    	}
    	V keyValue = keyValue(newkey); 
    	if(keyValue == null) {
    		throw new KeyNotFoundException();
    	}
    	else {
    		return keyValue;
    	}
    }
     
   /**
    * 
    *  *must handle resizing (we will set the initial capacity "table size" and 
    *add enough to cause re-sizing)
    *must include a constructor with the following signature:  public HashTable
    *(int initialCapacity, double loadFactor)
    *must document design choices that you make for hashing and collision resolution
    *in your HashTable implementation (see comments and document there)
    *
    * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
    * 
    */
	@Override
    public void insert(K newkey, V newvalue) throws IllegalNullKeyException, DuplicateKeyException{
    	//check key valid
    	if(newkey == null) {
    		throw new IllegalNullKeyException();
    	}
    	int index =newkey.hashCode()% this.currSize;
    	if(index<0) {
    		index = -index;
    	}
    	//check duplicate
    	int newhashIndex = checkPointer(newkey,index); 
    	if( newhashIndex == -1) {
    		throw new DuplicateKeyException();
    	}
    	int hashIndex2 = newIndex(newkey,index);
    	//put KV pair in table
    	if(insertTable(newkey,newvalue,hashIndex2)) {
    		//Successfully inserted
    		this.currload++;
    		this.size++;
    	}
    	//check  load factor
    	if(Threshold()) {
    		newSize();
    	}
    } 
    
	/**
	 * 
	 *  *must handle resizing (we will set the initial capacity "table size" and 
	 *add enough to cause re-sizing)
	 *must include a constructor with the following signature:  public HashTable
	 *(int initialCapacity, double loadFactor)
	 *must document design choices that you make for hashing and collision resolution
	 *in your HashTable implementation (see comments and document there)
	 *
	 *  // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
	 * 
	 */
    @Override
    public boolean remove(K newkey) throws IllegalNullKeyException{
    	if(newkey == null) {
    		throw new IllegalNullKeyException();
    	}
    	if(!checkIndex(newkey)) {
    		return false;
    	}
    	int hashIndex =  Math.abs(newkey.hashCode() % this.currSize);
    	int Index = hashIndex; 
    	while( Index != hashIndex -1) {
    		if(this.tableKey.get(Index) == newkey) {
    			
    			if(this.tableKeyRemove[Index] == true) {
    				return false;
    			}
    			else {
        			this.tableKeyRemove[Index] = true;
        			this.size--;
        			return true;
    			}
    		}
    		else if(this.tableKey.get(Index) == null) {
    			return false;
    		}
    		else if(Index == this.currSize) {
    			Index = 0;
    		}
    		Index++;
    	}
    	return false;
    }
    
    /**
     *  *must handle resizing (we will set the initial capacity "table size" and 
 *add enough to cause re-sizing)
 *must include a constructor with the following signature:  public HashTable
 *(int initialCapacity, double loadFactor)
 *must document design choices that you make for hashing and collision resolution
 *in your HashTable implementation (see comments and document there)
 *
 *  // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     */
    @Override
    public int numKeys() {
    	return this.size;
    }
    
    /**
     *  *must handle resizing (we will set the initial capacity "table size" and 
 *add enough to cause re-sizing)
 *must include a constructor with the following signature:  public HashTable
 *(int initialCapacity, double loadFactor)
 *must document design choices that you make for hashing and collision resolution
 *in your HashTable implementation (see comments and document there)
 *
 *  // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     */
    @Override
    public double getLoadFactor() {
    	return (double)this.currload/this.currSize;
    }
	
    
     /**
      *  *must handle resizing (we will set the initial capacity "table size" and 
 *add enough to cause re-sizing)
 *must include a constructor with the following signature:  public HashTable
 *(int initialCapacity, double loadFactor)
 *must document design choices that you make for hashing and collision resolution
 *in your HashTable implementation (see comments and document there)
 *
 * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
      */
	@Override
    public int getCapacity() {
    	return this.currSize;
    }

	/**
	 *  *must handle resizing (we will set the initial capacity "table size" and 
 *add enough to cause re-sizing)
 *must include a constructor with the following signature:  public HashTable
 *(int initialCapacity, double loadFactor)
 *must document design choices that you make for hashing and collision resolution
 *in your HashTable implementation (see comments and document there)
 *
 * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
	 */
	@Override
    public double getLoadFactorThreshold() {
    	return this.BufferValue;
    }
	
	
	/**
	 * *must handle resizing (we will set the initial capacity "table size" and 
 *add enough to cause re-sizing)
 *must include a constructor with the following signature:  public HashTable
 *(int initialCapacity, double loadFactor)
 *must document design choices that you make for hashing and collision resolution
 *in your HashTable implementation (see comments and document there) 
 *
 * // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
	 */
	@Override
    public int getCollisionResolution() {
    	return 1;
    }
}
