import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 *
 * @author Alvin Le
 * @version 1.0q
 * @userid ah307
 * @GTID 903715899
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     * To initialize the backing array, create a Comparable array and then cast
     * it to a T array.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * size of the passed in ArrayList (not INITIAL_CAPACITY). Index 0 should
     * remain empty, indices 1 to n should contain the data in proper order, and
     * the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot"
                    + "create Heap out of null list");
        } else  {
            backingArray = (T[]) new Comparable[(2 * data.size()) + 1];

            for (int i = 0; i < data.size(); i = i + 1) {
                if (data.get(i) == null) {
                    throw new IllegalArgumentException("Element is null");
                } else {
                    backingArray[i + 1] = data.get(i);
                    this.size = size + 1;
                }
            }

            int start = this.size / 2;

            while (start >= 1) {
                backingArray = downHeap(backingArray, start);
                start = start - 1;
            }
        }
    }

    /**
     * Goes down thru the heap and switches if parent is larger than child.
     * @param arr array of T objects
     * @param index int
     * @return array of T objects
     */
    private T[] downHeap(T[] arr, int index) {
        T temp;
        boolean con = true;

        while (con && index < size) {
            if (arr[index * 2] == null
                    && arr[(index * 2) + 1] == null) {
                con = false;
            } else {
                if (arr[index * 2] != null
                        && arr[(index * 2) + 1] != null) {
                    if (arr[index].compareTo(arr[index * 2]) > 0
                            || arr[index].compareTo(arr[(index * 2) + 1]) > 0) {
                        if (arr[index * 2].compareTo(arr[(index * 2) + 1]) < 0) {
                            temp = arr[index];
                            arr[index] = arr[index * 2];
                            arr[index * 2] = temp;
                            index = index * 2;

                        } else {
                            temp = arr[index];
                            arr[index] = arr[(index * 2) + 1];
                            arr[(index * 2) + 1] = temp;
                            index = (index * 2) + 1;

                        }
                    } else {
                        con = false;
                    }
                } else {
                    if (arr[index * 2] != null) {
                        if (arr[index].compareTo(arr[index * 2]) > 0) {
                            temp = arr[index];
                            arr[index] = arr[index * 2];
                            arr[index * 2] = temp;
                            index = index * 2;

                        } else {
                            con = false;
                        }
                    } else {
                        if (arr[index].compareTo(arr[(index * 2) + 1]) > 0) {
                            temp = arr[index];
                            arr[index] = arr[(index * 2) + 1];
                            arr[(index * 2) + 1] = temp;
                            index = (index * 2) + 1;

                        } else {
                            con = false;
                        }
                    }
                }
            }
        }

        return arr;
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * The order property of the heap must be maintained after adding. You can
     * assume that no duplicate data will be passed in.
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot"
                    + "insert null element into heap.");
        } else {
            if (size == 0) {
                backingArray[1] = data;

                size = size + 1;
            } else if (size == backingArray.length - 1) {
                T[] temp = (T[]) new Comparable[backingArray.length * 2];

                for (int i = 1; i < backingArray.length; i = i + 1) {
                    temp[i] = backingArray[i];
                }

                temp = upHeap(temp, data, this.size + 1);
                backingArray = temp;

                size = size + 1;
            } else {
                backingArray = upHeap(backingArray, data, this.size + 1);
                size = size + 1;
            }
        }
    }

    /**
     * Goes up the array and switches the values if child is greater then parent.
     * @param array array that holds T objects
     * @param data T object
     * @param index int
     * @return array of T objects
     */
    private T[] upHeap(T[] array, T data, int index) {
        boolean con = true;
        T temp;
        array[index] = data;

        while (index != 1 && con) {
            if (array[index].compareTo(array[index / 2]) < 0) {
                temp = array[index];
                array[index] = array[index / 2];
                array[index / 2] = temp;

                index = index / 2;
            } else {
                con = false;
            }
        }

        return array;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * The order property of the heap must be maintained after removing.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
       if (this.size == 0) {
           throw new NoSuchElementException("There"
                   + "is no element to remove from heap.");
       } else {
           T removed = backingArray[1];
           backingArray[1] = backingArray[size];
           backingArray[size] = null;
           size = size - 1;

           backingArray = downHeap(backingArray, 1);
           return removed;
       }
    }

    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty.");
        } else {
            return backingArray[1];
        }
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
