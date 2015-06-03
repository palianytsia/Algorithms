package edu.stanford.algo.sorting;

import edu.stanford.algo.sorting.InsertionSort;

public class InsertionSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	InsertionSort.sort(array);
    }

}
