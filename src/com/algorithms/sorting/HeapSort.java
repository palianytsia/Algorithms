package com.algorithms.sorting;

import com.algorithms.datastructures.Heap;

public class HeapSort {

    /**
     * Sorts the array ascending using Heap data structure.
     * 
     * @param array
     *            - array to be sorted.
     */
    public static void sort(int[] array) {
	Heap heap = new Heap(array);
	for (int i = 0; i < array.length; i++) {
	    array[i] = heap.extractMin();
	}
    }

}
