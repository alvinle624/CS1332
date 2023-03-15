import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Your implementation of a LinkedDeque.
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
public class LinkedDeque<T> {

    // Do not add new instance variables or modify existing ones.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the front of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert"
                    + "null element into data structure.");
        } else {
            if (size == 0) {
                LinkedNode<T> addedNode = new LinkedNode<T>(data);

                this.head = addedNode;
                this.tail = addedNode;

                size = size + 1;
            } else {
                LinkedNode<T> addedNode = new LinkedNode<T>(data,
                        null, this.head);

                this.head.setPrevious(addedNode);
                this.head = addedNode;

                size = size + 1;
            }
        }
    }

    /**
     * Adds the element to the back of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null"
                    + "element into data structure.");
        } else {
            if (size == 0) {
                LinkedNode<T> addedNode = new LinkedNode<T>(data);

                this.head = addedNode;
                this.tail = addedNode;

                size = size + 1;
            } else {
                LinkedNode<T> addedNode = new LinkedNode<T>(data,
                        this.tail, null);

                this.tail.setNext(addedNode);
                this.tail = addedNode;

                size = size + 1;
            }
        }

    }

    /**
     * Removes and returns the first element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to remove.");
        } else {
            T data = this.head.getData();

            this.head.getNext().setPrevious(null);
            this.head = this.head.getNext();

            size = size - 1;
            return data;
        }
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to remove.");
        } else {
            T data = this.tail.getData();

            this.tail.getPrevious().setNext(null);
            this.tail = this.tail.getPrevious();

            size = size - 1;
            return data;
        }
    }

    /**
     * Returns the first data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to get from front.");
        } else {
            return this.head.getData();
        }
    }

    /**
     * Returns the last data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("There is no elements"
                    + "in the data structure to get from back.");
        } else {
            return this.tail.getData();
        }
    }

    /**
     * Returns the head node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
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
}
