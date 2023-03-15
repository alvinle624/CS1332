import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */

/* @author Alvin Le
 * @version 1.0
 * @userid ah307
 * @GTID 903715899
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null"
                    + "into data structure.");
        } else {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Data structure"
                        + "doesn't have an index less than 0.");
            } else if (index > size) {
                throw new IndexOutOfBoundsException("Index is greater"
                        + "then the size of data structure");
            } else {
                if (size == 0) {
                    DoublyLinkedListNode<T> addedNode = new
                            DoublyLinkedListNode<T>(data);

                    this.head = addedNode;
                    this.tail = addedNode;

                    size = size + 1;
                } else {
                    if (index == 0) {
                        DoublyLinkedListNode<T> addedNode = new
                                DoublyLinkedListNode<T>(data, null, this.head);

                        this.head.setPrevious(addedNode);
                        this.head = addedNode;
                        this.tail.setNext(null);

                        size = size + 1;
                    } else if (index == size) {
                        DoublyLinkedListNode<T> addedNode = new
                                DoublyLinkedListNode<T>(data, this.tail, null);

                        this.tail.setNext(addedNode);
                        this.tail = addedNode;
                        this.head.setPrevious(null);

                        size = size + 1;
                    } else {
                        int maybeOdd = this.size % 2;
                        int mid = (this.size / 2) + maybeOdd;

                        if (index <= mid) {
                            DoublyLinkedListNode<T> temp = this.head;

                            for (int i = 0; i < index; i = i + 1) {
                                if (i == index - 1) {
                                    DoublyLinkedListNode<T> addedNode = new
                                            DoublyLinkedListNode<T>(data,
                                            temp, temp.getNext());

                                    temp.setNext(addedNode);
                                    addedNode.getNext().setPrevious(addedNode);
                                    this.head.setPrevious(null);
                                    this.tail.setNext(null);

                                    size = size + 1;
                                }
                                temp = temp.getNext();
                            }
                        } else {
                            DoublyLinkedListNode<T> temp = this.tail;

                            for (int i = 0; i < this.size - index; i = i + 1) {
                                if (i == this.size - (index + 1)) {
                                    DoublyLinkedListNode<T> addedNode = new
                                            DoublyLinkedListNode<T>(data,
                                            temp.getPrevious(), temp);

                                    temp.setPrevious(addedNode);
                                    addedNode.getPrevious().setNext(addedNode);
                                    this.head.setPrevious(null);
                                    this.tail.setNext(null);

                                    size = size + 1;
                                }
                                temp = temp.getPrevious();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot"
                    + "insert null into data structure.");
        } else {
            if (size == 0) {
                DoublyLinkedListNode<T> addedNode = new
                        DoublyLinkedListNode<T>(data);

                this.head = addedNode;
                this.tail = addedNode;

                size = size + 1;
            } else {
                DoublyLinkedListNode<T> addedNode = new
                        DoublyLinkedListNode<T>(data, null, this.head);

                this.head.setPrevious(addedNode);
                this.head = addedNode;

                size = size + 1;
            }
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot"
                    + "insert null into data structure.");
        } else {
            if (size == 0) {
                DoublyLinkedListNode<T> addedNode = new
                        DoublyLinkedListNode<T>(data);

                this.head = addedNode;
                this.tail = addedNode;

                size = size + 1;
            } else {
                DoublyLinkedListNode<T> addedNode = new
                        DoublyLinkedListNode<T>(data, this.tail, null);

                this.tail.setNext(addedNode);
                this.tail = addedNode;
                this.head.setPrevious(null);

                size = size + 1;
            }
        }
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Data"
                    + "structure doesn't have an index less than 0.");
        } else if (index >= this.size) {
            throw new IndexOutOfBoundsException("There is no element at"
                    + "or greater then the size of the data structure.");
        } else {
            if (this.size == 1) {
                T data = this.head.getData();

                this.head.setNext(null);
                this.head.setPrevious(null);
                this.head = null;

                size = size - 1;
                return data;
            } else {
                if (index == 0) {
                    T data = this.head.getData();

                    this.head.getNext().setPrevious(null);
                    this.head.setNext(null);
                    this.head.setPrevious(null);
                    this.head = null;

                    size = size - 1;
                    return data;
                } else if (index == this.size - 1) {
                    T data = this.tail.getData();

                    this.tail.getPrevious().setNext(null);
                    this.tail.setNext(null);
                    this.tail.setPrevious(null);
                    this.tail = null;

                    size = size - 1;
                    return data;
                } else {
                    int ifOdd = this.size % 2;
                    int mid = (this.size / 2) + ifOdd;
                    DoublyLinkedListNode<T> removedNode;
                    T data = null;

                    if (index <= mid) {
                        DoublyLinkedListNode<T> temp = this.head;

                        for (int i = 0; i < index; i = i + 1) {
                            if (i == index - 1) {
                                removedNode = temp.getNext();

                                temp.setNext(temp.getNext().getNext());
                                temp.getNext().getPrevious().setNext(null);
                                temp.getNext().getPrevious().setPrevious(null);
                                temp.getNext().setPrevious(temp);

                                size = size - 1;
                                data = removedNode.getData();
                            }
                            temp = temp.getNext();
                        }
                    } else {
                        DoublyLinkedListNode<T> temp = this.tail;

                        for (int i = 0; i < this.size - index; i = i + 1) {
                            if (i == this.size - (index + 1)) {
                                removedNode = temp.getPrevious();

                                temp.setPrevious(temp.getPrevious()
                                        .getPrevious());
                                temp.getPrevious().getNext().setPrevious(null);
                                temp.getPrevious().getNext().setNext(null);
                                temp.getPrevious().setNext(temp);

                                size = size - 1;
                                data = removedNode.getData();
                            }
                            temp = temp.getPrevious();
                        }
                    }
                    return data;
                }
            }
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (this.size == 0) {
            throw new NoSuchElementException("Data structure is empty.");
        } else {
            T data = this.head.getData();

            this.head.getNext().setPrevious(null);
            this.head = this.head.getNext();

            size = size - 1;
            return data;
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
        if (this.size == 0) {
            throw new NoSuchElementException("Data structure is empty.");
        } else {
            T data = this.tail.getData();

            this.tail.getPrevious().setNext(null);
            this.tail = this.tail.getPrevious();

            size = size - 1;
            return data;
        }
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Data structure"
                    + "doesn't have an index less than 0.");
        } else if (index >= this.size) {
            throw new IndexOutOfBoundsException("There is no element at or"
                    + "greater then the size of the data structure.");
        } else {
            if (index == 0) {
                DoublyLinkedListNode<T> foundNode = this.head;

                return foundNode.getData();
            } else if (index == this.size - 1) {
                DoublyLinkedListNode<T> foundNode = this.tail;

                return foundNode.getData();
            } else {
                int ifOdd = this.size % 2;
                int mid = (this.size / 2) + ifOdd;
                T data = null;

                if (index <= mid) {
                    DoublyLinkedListNode<T> foundNode = this.head;

                    for (int i = 0; i < index; i = i + 1) {
                        foundNode = foundNode.getNext();

                        if (i == index - 1) {
                            data = foundNode.getData();
                        }
                    }
                } else {
                    DoublyLinkedListNode<T> foundNode = this.tail;

                    for (int i = 0; i < this.size - index; i = i + 1) {
                        foundNode = foundNode.getPrevious();

                        if (i == this.size - (index + 1)) {
                            data = foundNode.getData();
                        }
                    }
                }
                return data;
            }
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
        return this.size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert"
                    + "null into data structure.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("Element"
                        + "not found because data structure is empty.");
            } else {
                if (data.equals(this.tail.getData())) {
                    return removeFromBack();
                } else {
                    int index = 0;
                    DoublyLinkedListNode<T> foundNode = this.head;
                    T removedData = null;

                    for (int i = 0; i < this.size; i = i + 1) {
                        if (data.equals(foundNode.getData())) {
                            index = i;
                        }

                        foundNode = foundNode.getNext();
                    }
                    if (index != 0) {
                        removedData = removeAtIndex(index);
                    } else {
                        if (!data.equals(this.head.getData()) && index == 0) {
                            throw new NoSuchElementException("Element not"
                                    + "found in the data Structure.");
                        } else if (data.equals(this.head.getData())
                                && index == 0) {
                            removedData = removeFromFront();
                        }
                    }
                    return removedData;
                }
            }
        }
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        Object[] newArray = new Object[this.size];
        DoublyLinkedListNode<T> temp = this.head;

        if (this.size == 0) {
            return newArray;
        } else {
            for (int i = 0; i < this.size; i = i + 1) {
                newArray[i] = temp.getData();
                temp = temp.getNext();
            }
            return newArray;
        }
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
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
        // DO NOT MODIFY!
        return size;
    }
}
