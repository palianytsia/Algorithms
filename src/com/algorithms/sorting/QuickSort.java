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
	int lessThanPivot = from;
	int partitioned = from;
	while (partitioned < to) {
	    partitioned++;
	    if (array[partitioned] <= pivot) {
		lessThanPivot++;
		ArrayUtils.swap(array, partitioned, lessThanPivot);
	    }
	}
	ArrayUtils.swap(array, from, lessThanPivot);
	return lessThanPivot;
    }

}
