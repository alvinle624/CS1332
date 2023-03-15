import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot create BST with null collection.");
        } else {
            Iterable<T> iterable = data;
            for (T element : iterable) {
                if (element == null) {
                    throw new IllegalArgumentException("Cannot insert null element into BST.");
                } else {
                    if (size == 0) {
                        BSTNode<T> rootNode = new BSTNode<T>(element);
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
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null into the BST.");
        } else {
            if (this.size == 0) {
                this.root = new BSTNode<T>(data);
                size = size + 1;
            } else {
                this.root = recursiveAdd(root, data);
            }
        }
    }

    /**
     * Transverses and sets the BST until a null branch
     * is reached where a BSTNode is created using the data.
     *
     * @param curr BSTNode of type T to be checked
     * @param data data to create a Node
     * @return BSTNode of type T
     */
    private BSTNode<T> recursiveAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            this.size = size + 1;
            return new BSTNode<T>(data);
        } else {
            if (data.compareTo(curr.getData()) < 0) {
                curr.setLeft(recursiveAdd(curr.getLeft(), data));
            } else {
                curr.setRight(recursiveAdd(curr.getRight(), data));
            }
            return curr;
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null from BST.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("BST is empty.");
            } else if (this.size == 1) {
                T removedData = this.root.getData();
                this.root = null;

                size = size - 1;
                return removedData;
            } else {
                BSTNode<T> removed = new BSTNode<T>(this.root.getData());
                this.root = removeH(this.root, removed, data);

                if (removed.getData() == null) {
                    throw new NoSuchElementException("Data is not in the BST.");
                } else {
                    return removed.getData();
                }
            }

        }
    }

    /**
     * Transverses the BST to find the data to be removed.
     * @param curr BSTNode of type T to be checked
     * @param holder BSTNode of type T to hold removed data
     * @param data the data to check for the data to be removed
     * @return BSTNode of type T
     */
    private BSTNode<T> removeH(BSTNode<T> curr, BSTNode<T> holder, T data) {
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
                        BSTNode<T> removeHolder = new BSTNode<T>(this.root.getData());
                        curr.setRight(successorH(curr.getRight(), removeHolder));
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
            return curr;
        }
    }

    /**
     * Restructures the tree using the successor method.
     * @param curr BSTNode of type T to be checked
     * @param holder BSTNode of type T to hold removed data
     * @return BSTNode of type T
     */
    private BSTNode<T> successorH(BSTNode<T> curr, BSTNode<T> holder) {
        if (curr.getLeft() == null) {
            if(curr.getRight() == null) {
                holder.setData(curr.getData());
                return null;
            } else {
                holder.setData(curr.getData());
                return curr.getRight();
            }
        } else {
            curr.setLeft(successorH(curr.getLeft(), holder));
            return curr;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data from BST.");
        } else {
            if (this.size == 0) {
                throw new NoSuchElementException("BST is empty.");
            } else if (this.size == 1) {
                return this.root.getData();
            } else {
                BSTNode<T> found = new BSTNode<T>(root.getData());
                this.root = getH(this.root, found, data);

                if (found.getData() == null) {
                    throw new NoSuchElementException("Element is not in the BST.");
                } else {
                    return found.getData();
                }
            }
        }
    }

    /**
     * Transverses the BST to find the data.
     * @param curr BSTNode of type T to be checked
     * @param holder BSTNode of type T to hold found data
     * @param data the data to check for the data to be found
     * @return BSTNode of type T
     */
    private BSTNode<T> getH(BSTNode<T> curr, BSTNode<T> holder, T data) {
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
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("BST cannot contain null node.");
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
     * Transverses BST to find and return if the data is found.
     * @param curr BSTNode of type T to be checked
     * @param data the data to check for the data to be found
     * @param result boolean value that is true if data is found and false otherwise
     * @return boolean
     */
    private boolean containsH(BSTNode<T> curr, T data, boolean result) {
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
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the pre-order traversal of the tree
     */
    public List<T> preorder() {
        List<T> preoderList = new ArrayList<T>();
        preoderList = preorderR(this.root, preoderList);
        return preoderList;
    }

    /**
     * Helper method that creates and returns a preordered list.
     * @param curr BSTNode of type T to be checked
     * @param givenList List of type T that is the list to add elements to
     * @return List of type T
     */
    private List<T> preorderR(BSTNode<T> curr, List<T> givenList) {
        if (curr != null) {
            givenList.add(curr.getData());
            preorderR(curr.getLeft(), givenList);
            preorderR(curr.getRight(), givenList);
        }
        return givenList;
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the in-order traversal of the tree
     */
    public List<T> inorder() {
        List<T> inorderList = new ArrayList<T>();
        inorderList = inorderR(this.root, inorderList);
        return inorderList;
    }
    /**
     * Helper method that creates and returns an inordered list.
     * @param curr BSTNode of type T to be checked
     * @param givenList List of type T that is the list to add elements to
     * @return List of type T
     */
    private List<T> inorderR(BSTNode<T> curr, List<T> givenList) {
        if (curr != null) {
            inorderR(curr.getLeft(), givenList);
            givenList.add(curr.getData());
            inorderR(curr.getRight(), givenList);
        }
        return givenList;
    }
    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the post-order traversal of the tree
     */
    public List<T> postorder() {
        List<T> postorderList = new ArrayList<T>();
        postorderList = postorderR(this.root, postorderList);
        return postorderList;
    }
    /**
     * Helper method that creates and returns a postordered list.
     * @param curr BSTNode of type T to be checked
     * @param givenList List of type T that is the list to add elements to
     * @return List of type T
     */
    private List<T> postorderR(BSTNode<T> curr, List<T> givenList) {
        if (curr != null) {
            postorderR(curr.getLeft(), givenList);
            postorderR(curr.getRight(), givenList);
            givenList.add(curr.getData());
        }
        return givenList;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level-order traversal of the tree
     */
    public List<T> levelorder() {


        List<T> levelorderList = new ArrayList<T>();
        Queue<BSTNode<T>> levelorderQueue = new LinkedList<BSTNode<T>>();

        if (size != 0) {
            levelorderQueue.add(this.root);
            BSTNode<T> addedNode;

            while (levelorderQueue.isEmpty() == false) {
                addedNode = levelorderQueue.remove();
                levelorderList.add(addedNode.getData());

                if (addedNode != null) {
                    if (addedNode.getLeft() != null) {
                        levelorderQueue.add(addedNode.getLeft());
                    }
                    if (addedNode.getRight() != null) {
                        levelorderQueue.add(addedNode.getRight());
                    }
                }
            }
        }

        return levelorderList;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
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
     * @param curr
     * @return
     */
    private int findHeight(BSTNode<T> curr) {
        int num = 1;

        if (curr == null) {
            return num - 2;
        } else {
            return Math.max(findHeight(curr.getLeft()), findHeight(curr.getRight())) + num;
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.root = null;
        size = 0;
    }

    /**
     * Generates a list of the max data per level from the top to the bottom
     * of the tree. (Another way to think about this is to get the right most
     * data per level from top to bottom.)
     * 
     * This must be done recursively.
     *
     * This list should contain the last node of each level.
     *
     * If the tree is empty, an empty list should be returned.
     *
     * Ex:
     * Given the following BST composed of Integers
     *      2
     *    /   \
     *   1     4
     *  /     / \
     * 0     3   5
     * getMaxDataPerLevel() should return the list [2, 4, 5] - 2 is the max
     * data of level 0, 4 is the max data of level 1, and 5 is the max data of
     * level 2
     *
     * Ex:
     * Given the following BST composed of Integers
     *               50
     *           /        \
     *         25         75
     *       /    \
     *      12    37
     *     /  \    \
     *   11   15   40
     *  /
     * 10
     * getMaxDataPerLevel() should return the list [50, 75, 37, 40, 10] - 50 is
     * the max data of level 0, 75 is the max data of level 1, 37 is the
     * max data of level 2, etc.
     *
     * Must be O(n).
     *
     * @return the list containing the max data of each level
     */
    public List<T> getMaxDataPerLevel() {
        List<T> listOfMaxes = new ArrayList<T>();
        if (this.size == 0) {
            return listOfMaxes;
        } else if (this.size == 1) {
            listOfMaxes.add(this.root.getData());
            return listOfMaxes;
        } else {
            listOfMaxes = maxH(this.root, listOfMaxes, 0);
            return listOfMaxes;
        }
    }

    /**
     *  maxH helper.
     * @param curr
     * @param givenList
     * @param depth
     * @return
     */
    private List<T> maxH(BSTNode<T> curr, List<T> givenList, int depth) {
        if (curr != null) {
            if (givenList.size() == depth) {
                givenList.add(curr.getData());
            }
            maxH(curr.getRight(), givenList, depth + 1);
            maxH(curr.getLeft(), givenList, depth + 1);
        }
        return givenList;
    }

    /* public List<T> preorder() {
        List<T> preoderList = new ArrayList<T>();
        preoderList = preorderR(this.root, preoderList);
        return preoderList;
    }


//    private List<T> preorderR(BSTNode<T> curr, List<T> givenList) {
//        if (curr != null) {
//            givenList.add(curr.getData());
//            preorderR(curr.getLeft(), givenList);
//            preorderR(curr.getRight(), givenList);
//        }
//        return givenList;
//    }
     */

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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
