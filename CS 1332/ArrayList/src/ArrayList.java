import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Alvin Le
 * @version 1.0
 * @userid ah307
 * @GTID 903715899
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null into data structure.");
        } else {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Data structure doesn't have an index less than 0.");
            } else if (index > size) {
                throw new IndexOutOfBoundsException("Index is greater then the size of data structure");
            } else if (index == backingArray.length && size == backingArray.length) {
                T[] tempArray = (T[]) new Object[backingArray.length * 2];

                for (int i = 0; i < backingArray.length; i = i + 1) {
                    tempArray[i] = backingArray[i];
                }

                tempArray[backingArray.length] = data;
                backingArray = tempArray;
                size = size + 1;
            } else {
                if (size == backingArray.length) {
                    T[] tempArray = (T[]) new Object[backingArray.length * 2];

                    for (int i = 0; i < index; i = i + 1) {
                        tempArray[i] = backingArray[i];
                    }
                    tempArray[index] = data;
                    for (int i = index + 1; i < backingArray.length + 1; i = i + 1) {
                        tempArray[i] = backingArray[i - 1];
                    }

                    backingArray = tempArray;
                    size = size + 1;
                } else {
                    if (backingArray[0] == null) {
                        backingArray[0] = data;
                        size = size + 1;
                    } else if (index == size) {
                        backingArray[index] = data;
                        size = size + 1;
                    } else {
                        int i = 0;
                        T temp = backingArray[index];
                        T temp2;

                        while (backingArray[i] != null && i <= index) {
                            if (i == index) {
                                backingArray[i] = data;
                                temp2 = backingArray[i + 1];
                                backingArray[i + 1] = temp;
                                temp = temp2;
                            }
                            i = i + 1;
                        }

                        i = index + 1;

                        while (backingArray[i] != null && i < backingArray.length - 1) {
                            temp2  = backingArray[i + 1];
                            backingArray[i + 1] = temp;
                            temp = temp2;

                            i = i + 1;
                        }
                        size = size + 1;
                    }
                }
            }
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null into data structure.");
        } else {
            if (backingArray[0] == null) {
                backingArray[0] = data;
                size = size + 1;
            } else {
                if (size == backingArray.length) {
                    T[] tempArray = (T[]) new Object[backingArray.length * 2];

                    tempArray[0] = data;

                    for (int i = 1; i < backingArray.length + 1; i = i + 1) {
                        tempArray[i] = backingArray[i - 1];
                    }

                    backingArray = tempArray;
                    size = size + 1;
                } else {
                    int i = 0;
                    T temp = backingArray[i];
                    T temp2;
                    backingArray[i] = data;

                    while (backingArray[i] != null && i < backingArray.length - 1) {
                        temp2 = backingArray[i + 1];
                        backingArray[i + 1] = temp;
                        temp = temp2;

                        i = i + 1;
                    }

                    size = size + 1;
                }
            }
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null into data structure.");
        } else {
            if (size == backingArray.length) {
                T[] tempArray = (T[]) new Object[backingArray.length * 2];

                for (int i = 0; i < backingArray.length; i = i + 1) {
                    tempArray[i] = backingArray[i];
                }

                tempArray[backingArray.length] = data;
                backingArray = tempArray;
                size = size + 1 ;
            } else {
                backingArray[size] = data;
                size = size + 1;
            }
        }
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Data structure cannot have a negative index.");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index is greater then the available size of data structure.");
        } else {
            if (index == size - 1) {
                T removedObj = backingArray[index];
                backingArray[index] = null;
                size = size - 1;
                return removedObj;
            } else {
                T removedObj = backingArray[index];
                int i = index;

                while (backingArray[i] != null && i < size) {
                    backingArray[i] = backingArray[i + 1];
                    i = i + 1;
                }

                size = size - 1;
                return removedObj;
            }
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("The arraylist is empty.");
        } else {
            T removedObj = backingArray[0];
            int i = 0;

            while (backingArray[i] != null && i < size + 1) {
                backingArray[i] = backingArray[i + 1];
                i = i + 1;
            }

            size = size - 1;
            return removedObj;
        }
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T removedObj = backingArray[size - 1];
        backingArray[size - 1] = null;
        size = size - 1;

        return removedObj;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Data structure cannot have a negative index.");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index is greater then the available size of data structure.");
        } else {
            return backingArray[index];
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
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
     * Returns the size of the list.
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
