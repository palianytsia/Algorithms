package com.algorithms;

import java.util.Arrays;

import com.algorithms.sorting.ArrayUtils;

public class Selection {

    /**
     * Selects median of three numbers.
     * 
     * @param a
     *            - first number.
     * @param b
     *            - second number.
     * @param c
     *            - third number.
     * 
     * @return median of the given numbers.
     */
    public static int median(int a, int b, int c) {
	if (a < b) {
	    if (c < a) {
		return a;
	    } else if (b < c) {
		return b;
	    }
	    return c;
	} else {// b < a
	    if (a < c) {
		return a;
	    } else if (c < b) {
		return b;
	    }
	    return c;
	}
    }

    /**
     * Select median of the given array. Median is <code>n/2</code> order statistic for arrays with even length, and is
     * <code>(n+1)/2</code> for arrays with odd length.
     * 
     * @param array
     *            - array to select median from. Can't be null or empty.
     * 
     * @return value of the median element of the given array.
     */
    public static int median(int[] array) {
	return select(array, Math.round(array.length / 2F));
    }

    /**
     * Selects i-th order statistic of the given array. Selects median of the array when i equals array's length / 2.
     * 
     * <p>
     * Uses randomised algorithm that has average complexity of O(n).
     * </p>
     * 
     * @param array
     *            - array to select order statistic from. Can't be null or empty.
     * @param i
     *            - order statistic to be selected. Must be between 1 and given array's length.
     * 
     * @return value of the i-th order statistic of the given array.
     */
    public static int select(int[] array, int i) {
	int[] copy = Arrays.copyOf(array, array.length);
	return rSelect(copy, 0, copy.length - 1, i);
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

    private static int rSelect(int[] array, int from, int to, int i) {
	if (from == to) {
	    assert i == 1;
	    return array[from];
	}
	int randomIndex = from + (int) (Math.random() * (to - from));
	ArrayUtils.swap(array, from, randomIndex);

	int pivotIndex = partition(array, from, to);
	int pivotOrder = pivotIndex - from + 1;
	if (i < pivotOrder) {
	    return rSelect(array, from, pivotIndex - 1, i);
	}
	if (i > pivotOrder) {
	    return rSelect(array, pivotIndex + 1, to, i - pivotOrder);
	}
	return array[pivotIndex];
    }
}
