package com.algorithms.sorting;

public class QuickSort {

    public static void sort(int[] array) {
	sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to) {
	if (from < to) {
	    int pivotIndex = partition(array, from, to);
	    sort(array, from, pivotIndex - 1);
	    sort(array, pivotIndex + 1, to);
	}
    }

    private static int partition(int[] array, int from, int to) {
	int pivot = array[from];
	int newPivotIndex = from;
	for (int j = from + 1; j <= to; j++) {
	    if (array[j] <= pivot) {
		ArrayUtils.swap(array, j, newPivotIndex + 1);
		newPivotIndex++;
	    }
	}
	ArrayUtils.swap(array, from, newPivotIndex);
	return newPivotIndex;
    }

}
