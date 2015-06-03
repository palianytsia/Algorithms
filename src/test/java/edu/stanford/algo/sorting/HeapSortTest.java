package edu.stanford.algo.sorting;

import edu.stanford.algo.sorting.HeapSort;

public class HeapSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	HeapSort.sort(array);
    }

}
