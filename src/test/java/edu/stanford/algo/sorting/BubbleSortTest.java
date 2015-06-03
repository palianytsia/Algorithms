package edu.stanford.algo.sorting;

import edu.stanford.algo.sorting.BubbleSort;

public class BubbleSortTest extends SortTest {

    @Override
    protected void sort(int[] array) {
	BubbleSort.sort(array);
    }

}
