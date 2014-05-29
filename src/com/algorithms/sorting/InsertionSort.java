package com.algorithms.sorting;

public class InsertionSort {

	/**
	 * Sorts the array ascending using Insertion sort algorithm.
	 * 
	 * @param array
	 *            - array to be sorted.
	 */
	public static void sort(int[] array) {
		long comparisons = 0;
		for (int j = 1; j < array.length; j++) {
			comparisons += insert(array, j);
		}
		System.out.println("Array of " + array.length
				+ " elements sorted via Insertion sort with " + comparisons
				+ " comparisons.");
	}

	private static long insert(int[] array, int index) {
		long comparisons = 0;
		for (int i = index; i > 0; i--) {
			comparisons++;
			if (array[i - 1] > array[i]) {
				ArrayUtils.swap(array, i - 1, i);
			}
			else {
				// element is in its correct position
				break;
			}
		}
		return comparisons;
	}

}
