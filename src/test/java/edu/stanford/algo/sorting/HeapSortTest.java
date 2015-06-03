package com.algorithms.sorting;

public class HeapSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	HeapSort.sort(array);
    }

}
