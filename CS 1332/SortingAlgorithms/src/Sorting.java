import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Queue;
import java.util.List;

/**
 * Your implementation of various sorting algorithms.
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
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("arr and comparator can't be null.");
        } else {
            for (int i = 1; i < arr.length; i = i + 1) {
                T temp = arr[i];
                int j = i - 1;

                while (j >= 0 && comparator.compare(arr[j], temp) > 0) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }

                arr[j + 1] = temp;
            }
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("arr and comparator can't be null.");
        } else {
            boolean swapM = true;
            int start = 0;
            int end = arr.length - 2;

            while (swapM) {
                swapM = false;

                for (int i = start; i < end; i = i + 1) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        T temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swapM = true;
                    }
                }

                if (swapM) {
                    for (int i = end; i >= start; i = i - 1) {
                        if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                            T temp = arr[i];
                            arr[i] = arr[i + 1];
                            arr[i + 1] = temp;
                            swapM = true;
                        }
                    }
                }

                end = end - 1;
                start = start + 1;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("arr and comparator can't be null.");
        } else {
            int len = arr.length;

            if (len < 2) {
                return;
            }

            int mid = len / 2;
            T[] left = (T[]) new Object[mid];
            T[] right = (T[]) new Object[len - mid];

            for (int i = 0; i < mid; i++)  {
                left[i] = arr[i];
            }

            for (int i = mid; i < len; i++) {
                right[i - mid] = arr[i];
            }

            mergeSort(left, comparator);
            mergeSort(right, comparator);

            int lIndex = 0;
            int rIndex = 0;
            int currIndex = 0;

            while (lIndex < mid && rIndex < len - mid)    {
                if (comparator.compare(left[lIndex], right[rIndex]) < 0) {
                    arr[currIndex] = left[lIndex];
                    lIndex = lIndex + 1;
                } else  {
                    arr[currIndex] = right[rIndex];
                    rIndex = rIndex + 1;
                }
                currIndex = currIndex + 1;
            }

            while (lIndex < mid)    {
                arr[currIndex] = left[lIndex];
                currIndex = currIndex + 1;
                lIndex = lIndex + 1;
            }

            while (rIndex < len - mid)    {
                arr[currIndex] = right[rIndex];
                currIndex = currIndex + 1;
                rIndex = rIndex + 1;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("arr, comparator and rand cannot be null.");
        } else {
            quickR(arr, comparator, rand, 0, arr.length - 1);
        }
    }

    /**
     * Recursive quick sort helper.
     *
     * @param arr array of type T
     * @param first int start
     * @param last int end
     * @param rand Random object
     * @param comparator Comparator object
     * @param <T> data type
     */
    private static <T> void quickR(T[] arr, Comparator<T> comparator,
                                   Random rand, int first, int last) {
        if (last > first) {
            int pivotR = rand.nextInt(last - first) + first;
            int pIndex = split(arr, comparator, first, last, pivotR);

            quickR(arr, comparator, rand, first, pIndex - 1);
            quickR(arr, comparator, rand, pIndex + 1, last);
        }
    }

    /**
     * Splits up array
     *
     * @param arr array of type T
     * @param first int start
     * @param last int end
     * @param pIndex int pivot index
     * @param comparator Comparator object
     * @param <T> data type
     * @return int
     */
    private static <T> int split(T[] arr, Comparator<T> comparator,
                                 int first, int last, int pIndex) {
        T pivot = arr[pIndex];
        int index = first;
        T temp = arr[pIndex];
        arr[pIndex] = arr[last];
        arr[last] = temp;

        for (int i = first; i < last; i = i + 1) {
            if (comparator.compare(arr[i], pivot) <= 0) {
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index = index + 1;
            }
        }

        temp = arr[last];
        arr[last] = arr[index];
        arr[index] = temp;
        return index;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort null array.");
        } else {
            LinkedList<Integer>[] buckets = new LinkedList[19];

            for (int i = 0; i < buckets.length; i = i + 1) {
                buckets[i] = new LinkedList<Integer>();
            }

            boolean con = true;
            int dTracker = 1;

            while (con) {
                con = false;

                for (int i = 0; i < arr.length; i = i + 1) {
                    if (arr[i] >= 0) {
                        int added = arr[i] / dTracker;

                        if (added != 0) {
                            con = true;
                        }

                        buckets[(added % 10) + 9].add(arr[i]);
                    } else {
                        int added = Math.abs(arr[i]) / dTracker;

                        if (added != 0) {
                            con = true;
                        }

                        buckets[added % 10].add(arr[i]);
                    }

                }

                dTracker = dTracker * 10;
                int i = 0;

                for (LinkedList<Integer> bucket : buckets) {
                    if (bucket != null) {
                        for (int num : bucket) {
                            arr[i] = num;
                            i = i + 1;
                        }
                        bucket.clear();
                    }
                }

            }

        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot sort null list.");
        } else {
            Queue<Integer> pQ = new PriorityQueue<Integer>(data);
            int[] sorted = new int[data.size()];

            for (int i = 0; i < data.size(); i = i + 1) {
                sorted[i] = pQ.remove();
            }

            return sorted;
        }
    }
}
