package edu.stanford.algo.sorting;

import edu.stanford.algo.sorting.MergeSort;

public class MergeSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	MergeSort.sort(array);
    }

}
