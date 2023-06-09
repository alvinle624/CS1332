import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * This is a basic set of unit tests for BST.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class BSTStudentTest {

    private static final int TIMEOUT = 200;
    private BST<Integer> tree;

    @Before
    public void setup() {
        tree = new BST<>();
    }




    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        assertEquals(3, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 2, tree.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> preorder = new ArrayList<>();
        preorder.add(3);
        preorder.add(0);
        preorder.add(1);
        preorder.add(2);
        preorder.add(8);
        preorder.add(4);
        preorder.add(6);
        preorder.add(5);
        preorder.add(7);

        // Should be [3, 0, 1, 2, 8, 4, 6, 5, 7]
        assertEquals(preorder, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> inorder = new ArrayList<>();
        inorder.add(0);
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);

        // Should be [0, 1, 2, 3, 4, 5, 6, 7, 8]
        assertEquals(inorder, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> postorder = new ArrayList<>();
        postorder.add(2);
        postorder.add(1);
        postorder.add(0);
        postorder.add(5);
        postorder.add(7);
        postorder.add(6);
        postorder.add(4);
        postorder.add(8);
        postorder.add(3);

        // Should be [2, 1, 0, 5, 7, 6, 4, 8, 3]
        assertEquals(postorder, tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(3);
        levelorder.add(0);
        levelorder.add(8);
        levelorder.add(1);
        levelorder.add(4);
        levelorder.add(2);
        levelorder.add(6);
        levelorder.add(5);
        levelorder.add(7);

        // Should be [3, 0, 8, 1, 4, 2, 6, 5, 7]
        assertEquals(levelorder, tree.levelorder());
    }



    @Test(timeout = TIMEOUT)
    public void testGetMaxDataPerLevel() {
        /*
              3
             / \
            1   4
               /
              2  
        */

        tree.add(3);
        tree.add(4);
        tree.add(1);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        expected.add(2);
        assertEquals(expected, tree.getMaxDataPerLevel());
    }

    private BST<Integer> bst;

    private Integer[] tree1 = {20, 30, 40, 50, 60};
    private Integer[] tree2 = {20, 15, 10, 5, 8};
    private Integer[] tree3 = {2};
    private Integer[] tree4 = {50, 30, 1, 85, 100, 40, 55, 80, 39, 86, 53, 38, 99};
    private Integer[] tree5 = {15, 20, 6, 16, 23};

    @Before
    public void setUp() throws Exception {
        bst = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertNull(bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        bst = new BST<>(Arrays.asList(tree1));

        Integer[] tree1Preorder = {20, 30, 40, 50, 60};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree1Preorder.length; i++) {
            assertEquals(tree1Preorder[i], pre.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }

        assertEquals(5, bst.size());

        Integer[] tree1Preorder = {20, 30, 40, 50, 60};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree1Preorder.length; i++) {
            assertEquals(tree1Preorder[i], pre.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }

        assertEquals(5, bst.size());

        Integer[] tree2Preorder = {20, 15, 10, 5, 8};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree2Preorder.length; i++) {
            assertEquals(tree2Preorder[i], pre.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }

        assertEquals(1, bst.size());

        Integer[] tree3Preorder = {2};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree3Preorder.length; i++) {
            assertEquals(tree3Preorder[i], pre.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }

        assertEquals(13, bst.size());

        Integer[] tree4Preorder = {50, 30, 1, 40, 39, 38, 85, 55, 53, 80, 100, 86, 99};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree4Preorder.length; i++) {
            assertEquals(tree4Preorder[i], pre.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }

        assertEquals(5, bst.size());

        assertEquals(Integer.valueOf(15), bst.getRoot().getData());
        assertEquals(Integer.valueOf(6), bst.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(20), bst.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(16),
                bst.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(23),
                bst.getRoot().getRight().getRight().getData());
        assertNull(bst.getRoot().getLeft().getLeft());
        assertNull(bst.getRoot().getLeft().getRight());
        assertNull(bst.getRoot().getRight().getLeft().getLeft());
        assertNull(bst.getRoot().getRight().getLeft().getRight());
        assertNull(bst.getRoot().getRight().getRight().getLeft());
        assertNull(bst.getRoot().getRight().getRight().getRight());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        bst.add(1);
        bst.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(13, bst.size());

        assertEquals(Integer.valueOf(85), bst.remove(85));
        Integer[] desired1 = {50, 30, 1, 40, 39, 38, 86, 55, 53, 80, 100, 99};
        assertArrayEquals(desired1, bst.preorder().toArray());
        assertEquals(12, bst.size());

        bst.remove(99);

        assertEquals(Integer.valueOf(30), bst.remove(30));
        Integer[] desired2 = {50, 38, 1, 40, 39, 86, 55, 53, 80, 100};
        assertArrayEquals(desired2, bst.preorder().toArray());
        assertEquals(10, bst.size());

        assertEquals(Integer.valueOf(86), bst.remove(86));
        Integer[] desired3 = {50, 38, 1, 40, 39, 100, 55, 53, 80};
        assertArrayEquals(desired3, bst.preorder().toArray());
        assertEquals(9, bst.size());

        assertEquals(Integer.valueOf(100), bst.remove(100));
        Integer[] desired4 = {50, 38, 1, 40, 39, 55, 53, 80};
        assertArrayEquals(desired4, bst.preorder().toArray());
        assertEquals(8, bst.size());

        assertEquals(Integer.valueOf(1), bst.remove(1));
        Integer[] desired5 = {50, 38, 40, 39, 55, 53, 80};
        assertArrayEquals(desired5, bst.preorder().toArray());
        assertEquals(7, bst.size());

        assertEquals(Integer.valueOf(38), bst.remove(38));
        Integer[] desired6 = {50, 40, 39, 55, 53, 80};
        assertArrayEquals(desired6, bst.preorder().toArray());
        assertEquals(6, bst.size());

        assertEquals(Integer.valueOf(50), bst.remove(50));
        Integer[] desired7 = {53, 40, 39, 55, 80};
        assertArrayEquals(desired7, bst.preorder().toArray());
        assertEquals(5, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveEquality() {
        BST<String> stringBST = new BST<>();
        stringBST.add(new String("a"));
        String toRemove = new String("a");
        String removed = stringBST.remove(toRemove);
        assertFalse(removed == toRemove);
        assertNull(stringBST.getRoot());
        assertEquals(0, stringBST.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        bst.add(5);
        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotPresent() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.remove(49);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(Integer.valueOf(50), bst.get(50));
        assertEquals(Integer.valueOf(100), bst.get(100));
        assertEquals(Integer.valueOf(30), bst.get(30));
        assertFalse(bst.get(1) == new Integer(1));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotPresent() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.get(98);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertTrue(bst.contains(50));
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(80));
        assertFalse(bst.contains(0));
        assertFalse(bst.contains(1000));
        assertFalse(bst.contains(56));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }

        Integer[] desired = {50, 30, 1, 40, 39, 38, 85, 55, 53, 80, 100, 86, 99};

        assertArrayEquals(desired, bst.preorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {15, 6, 20, 16, 23};
        assertArrayEquals(desired, bst.preorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {20, 30, 40, 50, 60};
        assertArrayEquals(desired, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {5, 8, 10, 15, 20};
        assertArrayEquals(desired, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(desired, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {1, 30, 38, 39, 40, 50, 53, 55, 80, 85, 86, 99, 100};
        assertArrayEquals(desired, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {6, 15, 16, 20, 23};
        assertArrayEquals(desired, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {60, 50, 40, 30, 20};
        assertArrayEquals(desired, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {8, 5, 10, 15, 20};
        assertArrayEquals(desired, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(desired, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {1, 38, 39, 40, 30, 53, 80, 55, 99, 86, 100, 85, 50};
        assertArrayEquals(desired, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {6, 16, 23, 20, 15};
        assertArrayEquals(desired, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {20, 30, 40, 50, 60};
        assertArrayEquals(desired, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {20, 15, 10, 5, 8};
        assertArrayEquals(desired, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(desired, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {50, 30, 85, 1, 40, 55, 100, 39, 53, 80, 86, 38, 99};
        assertArrayEquals(desired, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {15, 6, 20, 16, 23};
        assertArrayEquals(desired, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testOrderEmpty() {
        Integer[] desired = {};
        assertArrayEquals(desired, bst.preorder().toArray());
        assertArrayEquals(desired, bst.inorder().toArray());
        assertArrayEquals(desired, bst.postorder().toArray());
        assertArrayEquals(desired, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(13, bst.size());
        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        assertEquals(4, bst.height());
        bst.clear();

        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        assertEquals(4, bst.height());
        bst.clear();

        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        assertEquals(0, bst.height());
        bst.clear();

        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(4, bst.height());
        bst.clear();

        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        assertEquals(2, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDataPerLevelTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {20, 30, 40, 50, 60};
        assertArrayEquals(desired, bst.getMaxDataPerLevel().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDataPerLevelTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {20, 15, 10, 5, 8};
        assertArrayEquals(desired, bst.getMaxDataPerLevel().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDataPerLevelTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(desired, bst.getMaxDataPerLevel().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDataPerLevelTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {50, 85, 100, 86, 99};
        assertArrayEquals(desired, bst.getMaxDataPerLevel().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDataPerLevelTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {15, 20, 23};
        assertArrayEquals(desired, bst.getMaxDataPerLevel().toArray());
    }
}