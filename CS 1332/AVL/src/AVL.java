import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @userid ah307
 * @GTID 903715899
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot"
                    + "create AVL with null collection.");
        } else {
            Iterable<T> iterable = data;
            for (T element : iterable) {
                if (element == null) {
                    throw new IllegalArgumentException("Cannot"
                            + "insert null element into AVL.");
                } else {
                    if (size == 0) {
                        AVLNode<T> rootNode = new AVLNode<T>(element);
                        this.root = rootNode;
                        this.size = size + 1;
                    } else {
                        this.add(element);
                    }
                }
            }
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot"
                    + "insert null into the AVL.");
        } else {
            if (this.size == 0) {
                this.root = new AVLNode<T>(data);
                this.root.setHeight(0);
                this.root.setBalanceFactor(findHeight(root.getLeft())
                        - findHeight(root.getRight()));
                size = size + 1;
            } else {
                int dupTest = size + 1;
                AVLNode<T> tempNode = recursiveAdd(root, data);
                if (this.size == dupTest) {
                    this.root = tempNode;
                }
            }
        }
    }

    /**
     * Transverses and sets the AVL until a null branch
     * is reached where a AVLNode is created using the data.
     * Also set height and BF and rebalances AVL if needed.
     *
     * @param curr AVLNode of type T to be checked
     * @param data data to create a Node
     * @return AVLNode of type T
     */
    private AVLNode<T> recursiveAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            this.size = size + 1;
            return new AVLNode<T>(data);
        } else {
            if (data.compareTo(curr.getData()) < 0) {
                curr.setLeft(recursiveAdd(curr.getLeft(), data));
            } else if (data.compareTo(curr.getData()) > 0) {
                curr.setRight(recursiveAdd(curr.getRight(), data));
            }
            curr.setHeight(findHeight(curr));
            int leftH = -1;
            int rightH = -1;

            if (curr.getLeft() != null) {
                leftH = curr.getLeft().getHeight();
            }
            if (curr.getRight() != null) {
                rightH = curr.getRight().getHeight();
            }

            curr.setBalanceFactor(leftH - rightH);

            if (Math.abs(curr.getBalanceFactor()) > 1) {
                if (curr.getBalanceFactor() < -1) {
                    if (curr.getRight().getBalanceFactor() < 1) {
                        curr = leftRotation(curr);
                    } else {
                        curr.setRight(rightRotation(curr.getRight()));
                        curr = leftRotation(curr);
                    }
                } else {
                    if (curr.getLeft().getBalanceFactor() > -1) {
                        curr = rightRotation(curr);
                    } else {
                        curr.setLeft(leftRotation(curr.getLeft()));
                        curr = rightRotation(curr);
                    }
                }
            }

            return curr;
        }
    }

    /**
     * performs a left rotation on the AVL to rebalance.
     * @param node AVLNode of type T to be checked
     * @return AVLNode of type T
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);

        node.setHeight(findHeight(node));
        int leftN = -1;
        int rightN = -1;

        if (node.getLeft() != null) {
            leftN = node.getLeft().getHeight();
        }
        if (node.getRight() != null) {
            rightN = node.getRight().getHeight();
        }
        node.setBalanceFactor(leftN - rightN);

        temp.setHeight(findHeight(temp));
        int leftT = -1;
        int rightT = -1;

        if (temp.getLeft() != null) {
            leftT = temp.getLeft().getHeight();
        }
        if (temp.getRight() != null) {
            rightT = temp.getRight().getHeight();
        }
        temp.setBalanceFactor(leftT - rightT);

        return temp;
    }

    /**
     * performs a right rotation on the AVL to rebalance.
     * @param node AVLNode of type T to be checked
     * @return AVLNode of type T
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);

        node.setHeight(findHeight(node));
        int leftN = -1;
        int rightN = -1;

        if (node.getLeft() != null) {
            leftN = node.getLeft().getHeight();
        }
        if (node.getRight() != null) {
            rightN = node.getRight().getHeight();
        }
        node.setBalanceFactor(leftN - rightN);

        temp.setHeight(findHeight(temp));
        int leftT = -1;
        int rightT = -1;

        if (temp.getLeft() != null) {
            leftT = temp.getLeft().getHeight();
        }
        if (temp.getRight() != null) {
            rightT = temp.getRight().getHeight();
        }
        temp.setBalanceFactor(leftT - rightT);

        return temp;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null from AVL.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("AVL is empty.");
            } else if (this.size == 1) {
                T removedData = this.root.getData();
                this.root = null;

                size = size - 1;
                if (removedData.equals(data)) {
                    return removedData;
                } else {
                    throw new NoSuchElementException("Data is not in the AVL.");
                }
            } else {
                AVLNode<T> removed = new AVLNode<T>(this.root.getData());
                this.root = removeH(this.root, removed, data);

                if (removed.getData() == null) {
                    throw new NoSuchElementException("Data is not in the AVL.");
                } else {
                    return removed.getData();
                }
            }
        }
    }

    /**
     * Transverses the AVL to find the data to be removed.
     * @param curr AVLNode of type T to be checked
     * @param holder AVLNode of type T to hold removed data
     * @param data the data to check for the data to be removed
     * @return AVLNode of type T
     */
    private AVLNode<T> removeH(AVLNode<T> curr, AVLNode<T> holder, T data) {
        if (curr == null) {
            holder.setData(null);
            return null;
        } else {
            if (data.compareTo(curr.getData()) < 0) {
                curr.setLeft(removeH(curr.getLeft(), holder, data));
            } else if (data.compareTo(curr.getData()) > 0) {
                curr.setRight(removeH(curr.getRight(), holder, data));
            } else {
                holder.setData(curr.getData());
                size = size - 1;

                if (curr.getLeft() == null && curr.getRight() == null) {
                    curr = null;
                    return curr;
                } else {
                    if (curr.getLeft() != null && curr.getRight() != null) {
                        AVLNode<T> removeHolder = new AVLNode<T>(root.getData());
                        curr.setLeft(predecessorH(curr.getLeft(), removeHolder));
                        curr.setData(removeHolder.getData());
                    } else {
                        if (curr.getLeft() != null) {
                            return curr.getLeft();
                        } else {
                            return curr.getRight();
                        }
                    }
                }
            }
            curr.setHeight(findHeight(curr));
            int leftH = -1;
            int rightH = -1;

            if (curr.getLeft() != null) {
                leftH = curr.getLeft().getHeight();
            }
            if (curr.getRight() != null) {
                rightH = curr.getRight().getHeight();
            }
            curr.setBalanceFactor(leftH - rightH);

            if (Math.abs(curr.getBalanceFactor()) > 1) {
                if (curr.getBalanceFactor() < -1) {
                    if (curr.getRight().getBalanceFactor() < 1) {
                        curr = leftRotation(curr);
                    } else {
                        curr.setRight(rightRotation(curr.getRight()));
                        curr = leftRotation(curr);
                    }
                } else {
                    if (curr.getLeft().getBalanceFactor() > -1) {
                        curr = rightRotation(curr);
                    } else {
                        curr.setLeft(leftRotation(curr.getLeft()));
                        curr = rightRotation(curr);
                    }
                }
            }

            return curr;
        }
    }

    /**
     * Restructures the tree using the predecessor method.
     * @param curr AVLNode of type T to be checked
     * @param holder AVLNode of type T to hold removed data
     * @return AVLNode of type T
     */
    private AVLNode<T> predecessorH(AVLNode<T> curr, AVLNode<T> holder) {
        if (curr.getRight() == null) {
            if (curr.getLeft() == null) {
                holder.setData(curr.getData());
                return null;
            } else {
                holder.setData(curr.getData());
                return curr.getLeft();
            }
        } else {
            curr.setRight(predecessorH(curr.getRight(), holder));
            return curr;
        }
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get"
                    + "null data from AVL.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("AVL is empty.");
            } else if (this.size == 1) {
                if (data.equals(root.getData())) {
                    return this.root.getData();
                } else {
                    throw new NoSuchElementException("Data is not in the AVL.");
                }
            } else {
                AVLNode<T> found = new AVLNode<T>(root.getData());
                this.root = getH(this.root, found, data);

                if (found.getData() == null) {
                    throw new NoSuchElementException("Element"
                            + "is not in the AVL.");
                } else {
                    return found.getData();
                }
            }
        }
    }

    /**
     * Transverses the AVL to find the data.
     * @param curr AVLNode of type T to be checked
     * @param holder AVLNode of type T to hold found data
     * @param data the data to check for the data to be found
     * @return AVLNode of type T
     */
    private AVLNode<T> getH(AVLNode<T> curr, AVLNode<T> holder, T data) {
        if (curr == null) {
            holder.setData(null);
            return null;
        } else {
            if (data.compareTo(curr.getData()) == 0) {
                holder.setData(curr.getData());
                return curr;
            } else {
                if (data.compareTo(curr.getData()) < 0) {
                    curr.setLeft(getH(curr.getLeft(), holder, data));
                } else {
                    curr.setRight(getH(curr.getRight(), holder, data));
                }
                return curr;
            }
        }
    }



    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("AVL cannot contain null node.");
        } else {
            if (size == 0) {
                return false;
            } else if (size == 1) {
                return data.compareTo(this.root.getData()) == 0;
            } else {
                boolean result = false;
                result = containsH(this.root, data, result);
                return result;
            }
        }
    }

    /**
     * Transverses AVL to find and return if the data is found.
     * @param curr AVLNode of type T to be checked
     * @param data the data to check for the data to be found
     * @param result boolean value that is true if found and false otherwise
     * @return boolean
     */
    private boolean containsH(AVLNode<T> curr, T data, boolean result) {
        if (curr == null) {
            return false;
        } else {
            if (data.compareTo(curr.getData()) == 0) {
                return true;
            } else {
                if (data.compareTo(curr.getData()) < 0) {
                    result = containsH(curr.getLeft(), data, result);
                    return result;
                } else {
                    result = containsH(curr.getRight(), data, result);
                    return result;
                }
            }
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        } else if (size == 1) {
            return 0;
        } else {
            int hCount = findHeight(this.root);
            return hCount;
        }
    }

    /**
     * Recursively finds the max height of branches.
     * @param curr AVLNode of type T to be checked
     * @return int
     */
    private int findHeight(AVLNode<T> curr) {
        int num = 1;

        if (curr == null) {
            return num - 2;
        } else {
            return Math.max(findHeight(curr.getLeft()),
                    findHeight(curr.getRight())) + num;
        }
    }


    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the data in the deepest node. If there is more than one node
     * with the same deepest depth, return the rightmost (i.e. largest) node with
     * the deepest depth.
     *
     * Should be recursive.
     *
     * Must run in O(log n) for all cases.
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (this.root == null) {
            return null;
        } else {
            AVLNode<T> hold = new AVLNode<T>(root.getData());
            deepestH(this.root, hold);
            return hold.getData();
        }
    }

    /**
     * Recursively finds the deepest leaf.
     * @param curr AVLNode of type T to be checked
     * @param holder AVLNode of type T used to set data of deepest leaf
     */
    private void deepestH(AVLNode<T> curr, AVLNode<T> holder) {
        if (curr.getRight() == null && curr.getLeft() == null) {
            holder.setData(curr.getData());
        } else {
            if (curr.getRight() != null && curr.getLeft() != null) {
                if (curr.getRight().getHeight() >= curr.getLeft().getHeight()) {
                    deepestH(curr.getRight(), holder);
                } else {
                    deepestH(curr.getLeft(), holder);
                }
            } else {
                if (curr.getRight() != null) {
                    deepestH(curr.getRight(), holder);
                } else {
                    deepestH(curr.getLeft(), holder);
                }
            }
        }
    }

    /**
     * In BSTs, you learned about the concept of the successor: the
     * smallest data that is larger than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the successor of the data
     * passed in. There are 2 cases to consider:
     * 1: The right subtree is non-empty. In this case, the successor is the
     * leftmost node of the right subtree.
     * 2: The right subtree is empty. In this case, the successor is the lowest
     * ancestor of the node whose left subtree contains the data.
     *
     * The second case means the successor node will be one of the node(s) we
     * traversed left from to find data. Since the successor is the SMALLEST element
     * greater than data, the successor node is the lowest/last node
     * we traversed left from on the path to the data node.
     *
     * This should NOT be used in the remove method.
     *
     * Should be recursive.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *                    76
     *                  /    \
     *                34      90
     *                  \    /
     *                  40  81
     * successor(76) should return 81
     * successor(81) should return 90
     * successor(40) should return 76
     *
     * @param data the data to find the successor of
     * @return the successor of data. If there is no larger data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T successor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        } else {
            if (this.root == null) {
                throw new NoSuchElementException("AVL is empty.");
            } else {
                AVLNode<T> temp = new AVLNode<T>(this.root.getData());
                findingData(this.root, temp, data);
                T max = null;

                if (temp.getData().equals(findMax(this.root, max))) {
                    return null;
                } else {
                    return temp.getData();
                }
            }
        }

    }

    /**
     * finds if data is in the AVL while
     * tracking the successor in case of
     * not having a right subtree.
     * @param curr AVLNode of type T to be checked
     * @param holder AVLNode of type T to hold found data
     * @param data the data to check for the data to be found
     */
    private void findingData(AVLNode<T> curr, AVLNode<T> holder, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data is not in AVL.");
        } else {
            if (data.compareTo(curr.getData()) == 0) {
                if (curr.getRight() != null) {
                    successorR(curr.getRight(), holder);
                }
            } else {
                if (data.compareTo(curr.getData()) < 0) {
                    if (curr.getLeft() != null) {
                        holder.setData(curr.getData());
                    }
                    findingData(curr.getLeft(), holder, data);
                } else {
                    findingData(curr.getRight(), holder, data);
                }
            }
        }
    }

    /**
     * finds the successor in case of
     * valid right subtree.
     * @param curr AVLNode of type T to be checked
     * @param holder AVLNode of type T to hold found data
     */
    private void successorR(AVLNode<T> curr, AVLNode<T> holder) {
        if (curr.getLeft() == null) {
            holder.setData(curr.getData());
        } else {
            successorR(curr.getLeft(), holder);
        }
    }

    /**
     * finds the max data in the AVL.
     * @param curr AVLNode of type T to be checked
     * @param data the data that is used to hold the max
     * @return T
     */
    private T findMax(AVLNode<T> curr, T data) {
        if (curr.getRight() == null) {
            return curr.getData();
        } else {
            data = findMax(curr.getRight(), data);
        }

        return data;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
