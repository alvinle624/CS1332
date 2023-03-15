import java.util.*;

/**
 * Your implementation of a LinearProbingHashMap.
 *
 * @author Alvin Le
 * @version 1.0
 * @userid 903715899
 * @GTID ah307
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class LinearProbingHashMap<K, V> {

    /**
     * The initial capacity of the LinearProbingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /**
     * The max load factor of the LinearProbingHashMap
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    // Do not add new instance variables or modify existing ones.
    private LinearProbingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new LinearProbingHashMap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public LinearProbingHashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new LinearProbingHashMap.
     *
     * The backing array should have an initial capacity of initialCapacity.
     *
     * You may assume initialCapacity will always be positive.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public LinearProbingHashMap(int initialCapacity) {
        table = new LinearProbingMapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the array would violate the max load factor if the data was
     * added. For example, let's say the array is of length 5 and the current
     * size is 3 (LF = 0.6). For this example, assume that no elements are
     * removed in between steps. If another entry is attempted to be added,
     * before doing anything else, you should check whether (3 + 1) / 5 = 0.8
     * is larger than the max LF. It is, so you would trigger a resize before
     * you even attempt to add the data or figure out if it's a duplicate. Be
     * careful to consider the differences between integer and double
     * division when calculating load factor.
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key   the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot"
                    + "insert a key value pair with null object.");
        } else {
            double loadFactor = (this.size + 1) / ((double) this.table.length);

            if (loadFactor > MAX_LOAD_FACTOR) {
                this.resizeBackingTable((2 * this.table.length) + 1);
            }

            V oldValue = null;
            int index = Math.abs(key.hashCode() % table.length);
            int delIndex = -1;
            int i = 0;
            boolean con = true;

            if (this.size < 1) {
                table[index] = new LinearProbingMapEntry<K, V>(key, value);
                this.size = size + 1;

            } else {
                while (con && i <= this.size) {
                    if (table[index] == null && delIndex == -1) {
                        table[index] = new LinearProbingMapEntry<K, V>(key,
                                value);
                        this.size = size + 1;
                        con = false;

                    } else {
                        if (table[index] == null && delIndex != -1) {
                            table[delIndex].setKey(key);
                            table[delIndex].setValue(value);
                            this.size = size + 1;
                            con = false;

                        } else {
                            if (table[index].getKey().equals(key)
                                    && !table[index].isRemoved()) {
                                oldValue = table[index].getValue();
                                table[index].setValue(value);
                                con = false;

                            } else {
                                if (table[index].isRemoved()
                                        && delIndex == -1) {
                                    delIndex = index;
                                    index = (index + 1) % table.length;

                                } else {
                                    if (!table[index].isRemoved()) {
                                        i = i + 1;
                                    }
                                    index = (index + 1) % table.length;
                                }
                            }
                        }
                    }
                }
            }
          return oldValue;
        }
    }

    /**
     * Removes the entry with a matching key from map by marking the entry as
     * removed.
     *
     * @param key the key to remove
     * @return the value previously associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot return null key.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("Key"
                        + "Value pair does not exist.");
            } else {
                int i = 0;
                int index = Math.abs(key.hashCode() % table.length);
                boolean con = true;
                V rValue = null;

                while (con && i < this.size) {
                    if (table[index] == null) {
                        con = false;
                    } else {
                        if (table[index].getKey().equals(key)
                                && !table[index].isRemoved()) {
                            table[index].setRemoved(true);
                            rValue = table[index].getValue();
                            con = false;
                        } else {
                            if (!table[index].isRemoved()) {
                                i = i + 1;
                            }
                            index = (index + 1) % table.length;
                        }
                    }
                }

                if (rValue == null) {
                    throw new NoSuchElementException("Key"
                            + "Value pair does not exist.");
                } else {
                    this.size = size - 1;
                    return rValue;
                }
            }
        }
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get null key.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("Key value pair not found.");
            } else {
                int i = 0;
                int index = Math.abs(key.hashCode() % table.length);
                boolean con = true;
                V fValue = null;

                while (con && i < this.size) {
                    if (table[index] == null) {
                        con = false;
                    } else {
                        if (table[index].getKey().equals(key)
                                && !table[index].isRemoved()) {
                            fValue = table[index].getValue();
                            con = false;
                        } else {
                            if (!table[index].isRemoved()) {
                                i = i + 1;
                            }
                            index = (index + 1) % table.length;
                        }
                    }
                }

                if (fValue == null) {
                    throw new NoSuchElementException("Key"
                            + "value pair is not in the HashMap.");
                } else {
                    return fValue;
                }
            }
        }
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("HashMap"
                    + "cannot contain null key.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("HashMap"
                        + "is empty.");
            } else {
                int i = 0;
                int index = Math.abs(key.hashCode() % table.length);
                boolean con = true;
                boolean found = false;

                while (con && i < this.size) {
                    if (table[index] == null) {
                        con = false;
                    } else {
                        if (table[index].getKey().equals(key)
                                && !table[index].isRemoved()) {
                            con = false;
                            found = true;
                        } else {
                            if (!table[index].isRemoved()) {
                                i = i + 1;
                            }
                            index = (index + 1) % table.length;
                        }
                    }
                }

                return found;
            }
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * Use java.util.HashSet.
     *
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> nSet = new HashSet<K>(this.size);

        for (int i = 0; i < table.length; i = i + 1) {
            if (table[i] != null && !table[i].isRemoved()) {
                nSet.add(table[i].getKey());
            }
        }

        return nSet;
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use java.util.ArrayList or java.util.LinkedList.
     *
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> nList = new ArrayList<V>(size);

        for (int i = 0; i < table.length; i = i + 1) {
            if (table[i] != null && !table[i].isRemoved()) {
                nList.add(table[i].getValue());
            }
        }

        return nList;
    }

    /**
     * Resize the backing table to length.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     * You should NOT copy over removed elements to the resized backing table.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new array.
     *
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    public void resizeBackingTable(int length) {
        if (length < this.size) {
            throw new IllegalArgumentException("Cannot create"
                    + "table with a capacity less"
                    + "then the total number of elements.");
        } else {
           LinearProbingMapEntry<K, V>[] nTable = new
                   LinearProbingMapEntry[length];
           int j = 0;

           for (int i = 0; i < table.length && j < this.size; i = i + 1) {
               boolean con = true;
               if (table[i] != null && !table[i].isRemoved()) {
                   int k = 0;
                   while (con && k < nTable.length) {
                       int index = Math.abs((table[i].getKey().hashCode()
                               + k) % nTable.length);
                       if (nTable[index] == null) {
                           nTable[index] = table[i];
                           con = false;
                       } else {
                           k = k + 1;
                       }
                   }
                   j = j + 1;
               }
           }

           this.table = nTable;
        }
    }

    /**
     * Clears the map.
     *
     * Resets the table to a new array of the INITIAL_CAPACITY and resets the
     * size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.table = new LinearProbingMapEntry[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the table of the map
     */
    public LinearProbingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the map
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
