package edu.stanford.algo.sorting;

import edu.stanford.algo.sorting.QuickSort;

public class QuickSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	QuickSort.sort(array);
    }

}
