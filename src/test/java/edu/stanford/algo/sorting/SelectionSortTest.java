package edu.stanford.algo.sorting;

import edu.stanford.algo.sorting.SelectionSort;

public class SelectionSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	SelectionSort.sort(array);
    }

}
