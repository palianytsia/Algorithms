package edu.stanford.algo.sorting;

/**
 * Showcase implementation of <strong>Bubble sort</strong>.
 * 
 * <p>
 * Bubble sort, sometimes incorrectly referred to as sinking sort, is a simple sorting algorithm that works by repeatedly stepping through the list to be
 * sorted, comparing each pair of adjacent items and swapping them if they are in the wrong order. The pass through the list is repeated until no swaps are
 * needed, which indicates that the list is sorted. The algorithm gets its name from the way smaller elements "bubble" to the top of the list. Because it only
 * uses comparisons to operate on elements, it is a comparison sort. Although the algorithm is simple, most other algorithms are more efficient for sorting
 * large lists.
 * </p>
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class BubbleSort {

    /**
     * Sorts the array ascending using Bubble sort algorithm.
     * 
     * @param array
     *            - array to be sorted.
     */
    public static void sort(int[] array) {
	int comparisons = 0;
	if (array.length > 1) {
	    boolean sorted = false;
	    do {
		sorted = true;
		for (int i = 0; i < array.length - 1; i++) {
		    if (array[i] > array[i + 1]) {
			sorted = false;
			ArrayUtils.swap(array, i, i + 1);
		    }
		    comparisons++;
		}
	    } while (!sorted);
	}
	System.out.println("Array of " + array.length + " elements sorted via Bubble sort with " + comparisons + " comparisons.");
    }
}
