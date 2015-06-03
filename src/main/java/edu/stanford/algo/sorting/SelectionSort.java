package com.algorithms.sorting;

/**
 * Showcase implementation of <strong>Selection sort</strong>.
 * 
 * <p>
 * In computer science, a selection sort is a sorting algorithm, specifically an in-place comparison sort. It has O(n^2) time complexity, making it inefficient
 * on large lists, and generally performs worse than the similar insertion sort. Selection sort is noted for its simplicity, and also has performance advantages
 * over more complicated algorithms in certain situations, particularly where auxiliary memory is limited.
 * </p>
 * 
 * The algorithm works as follows:
 * <ul>
 * <li>Find the minimum value in the list;</li>
 * <li>Swap it with the value in the first position;</li>
 * <li>Repeat the steps above for the remainder of the list (starting at the second position and advancing each time).</li>
 * </ul>
 * 
 * <p>
 * Effectively, the list is divided into two parts: the sublist of items already sorted, which is built up from left to right and is found at the beginning, and
 * the sublist of items remaining to be sorted, occupying the remainder of the array.
 * </p>
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class SelectionSort {

    /**
     * Sorts the array ascending using Selection sort algorithm.
     * 
     * @param array
     *            - array to be sorted.
     */
    public static void sort(int[] array) {
	long comparisons = 0;
	for (int i = 0; i < array.length - 1; i++) {
	    comparisons += select(array, i);
	}
	System.out.println("Array of " + array.length + " elements sorted via Selection sort with " + comparisons + " comparisons.");
    }

    /**
     * Selects an element into a given position in order to sort the given array, assuming that elements coming before this position are already sorted.
     * 
     * @param array
     *            - array being sorted.
     * 
     * @param position
     *            - position to select the element for, elements before this position should be already sorted.
     * 
     * @return number of comparisons made during selecting an element for the given position.
     */
    private static long select(int[] array, int position) {
	long comparisons = 0;
	int min = position;
	for (int i = position + 1; i < array.length; i++) {
	    if (array[i] < array[min]) {
		min = i;
	    }
	    comparisons++;
	}
	ArrayUtils.swap(array, min, position);
	return comparisons;
    }

}
