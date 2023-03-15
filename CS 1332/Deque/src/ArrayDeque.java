import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayDeque.
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
public class ArrayDeque<T> {

    /**
     * The initial capacity of the ArrayDeque.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 11;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayDeque.
     */
    public ArrayDeque() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.size = 0;
    }

    /**
     * Adds the element to the front of the deque.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current capacity. When resizing, copy elements to the
     * beginning of the new array and reset front to 0. After the resize and add
     * operation, the new data should be at index 0 of the array. Consider how
     * to do this efficiently.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null"
                    + "element into data structure.");
        } else {
            if (this.size == this.backingArray.length) {
                T[] tempArr = (T[]) new Object[this.backingArray.length * 2];

                tempArr[0] = data;

                for (int i = front; i < this.size + front; i = i + 1) {
                    if (i < this.backingArray.length) {
                        tempArr[(i + 1) - front] = this.backingArray[i];
                    } else {
                        int index = mod(i, this.backingArray.length);

                        tempArr[(i + 1) - front] = this.backingArray[index];
                    }
                }

                this.front = 0;
                size = size + 1;
                this.backingArray = tempArr;
            } else {
                int index = (front != 0) ? (front - 1)
                        : (this.backingArray.length - 1);

                this.backingArray[index] = data;
                this.front = index;
                size = size + 1;
            }
        }
    }

    /**
     * Adds the element to the back of the deque.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current capacity. When resizing, copy elements to the
     * beginning of the new array and reset front to 0.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null"
                    + "element into data structure.");
        } else {
            if (this.size == this.backingArray.length) {
                T[] tempArr = (T[]) new Object[this.backingArray.length * 2];

                for (int i = front; i < this.size + front; i = i + 1) {
                    if (i < this.backingArray.length) {
                        tempArr[i - front] = this.backingArray[i];
                    } else {
                        int index = mod(i, this.backingArray.length);

                        tempArr[i - front] = this.backingArray[index];
                    }
                }

                tempArr[this.size] = data;
                this.front = 0;
                size = size + 1;
                this.backingArray = tempArr;
            } else {
                int index = 0;

                if (front == 0) {
                    index = size;
                } else {
                    if (front + size >= backingArray.length) {
                        index = size + front - backingArray.length;
                    } else {
                        index = size + front;
                    }
                }

                this.backingArray[index] = data;
                size = size + 1;
            }
        }
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * Do not grow or shrink the backing array.
     *
     * If the deque becomes empty as a result of this call, do not reset
     * front to 0. Rather, modify the front index as if the deque did not become
     * empty as a result of this call.
     *
     * Replace any spots that you remove from with null. Failure to do so can
     * result in loss of points.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("There is no element"
                    + "in the data structure to remove.");
        } else {
            T data = this.backingArray[front];

            this.backingArray[front] = null;
            this.front = (front != this.backingArray.length - 1)
                    ? (front + 1) : 0;
            size = size - 1;

            return data;
        }
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * Do not grow or shrink the backing array.
     *
     * If the deque becomes empty as a result of this call, do not reset
     * front to 0.
     *
     * Replace any spots that you remove from with null. Failure to do so can
     * result in loss of points.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to remove.");
        } else {
            int index = 0;

            if (front == 0) {
                index = size - 1;
            } else {
                if (front + size > backingArray.length) {
                    index = size + front - (backingArray.length + 1);
                } else {
                    index = size + front - 1;
                }
            }

            T data = this.backingArray[index];
            this.backingArray[index] = null;
            size = size - 1;

            return data;
        }
    }

    /**
     * Returns the first data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the first data
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to get.");
        } else {
            return this.backingArray[front];
        }
    }

    /**
     * Returns the last data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the last data
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to get.");
        } else {
            int index = 0;

            if (front == 0) {
                index = size - 1;
            } else {
                if (front + size > backingArray.length) {
                    index = size + front - (backingArray.length + 1);
                } else {
                    index = size + front - 1;
                }
            }

            return this.backingArray[index];
        }
    }

    /**
     * Returns the backing array of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the deque
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the deque
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the smallest non-negative remainder when dividing index by
     * modulo. So, for example, if modulo is 5, then this method will return
     * either 0, 1, 2, 3, or 4, depending on what the remainder is.
     *
     * This differs from using the % operator in that the % operator returns
     * the smallest answer with the same sign as the dividend. So, for example,
     * (-5) % 6 => -5, but with this method, mod(-5, 6) = 1.
     *
     * Examples:
     * mod(-3, 5) => 2
     * mod(11, 6) => 5
     * mod(-7, 7) => 0
     *
     * This helper method is here to make the math part of the circular
     * behavior easier to work with.
     *
     * @param index  the number to take the remainder of
     * @param modulo the divisor to divide by
     * @return the remainder in its smallest non-negative form
     * @throws java.lang.IllegalArgumentException if the modulo is non-positive
     */
    private static int mod(int index, int modulo) {
        // DO NOT MODIFY THIS METHOD!
        if (modulo <= 0) {
            throw new IllegalArgumentException("The modulo must be positive");
        }
        int newIndex = index % modulo;
        return newIndex >= 0 ? newIndex : newIndex + modulo;
    }
}
