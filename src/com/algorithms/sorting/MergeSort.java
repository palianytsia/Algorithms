package com.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    /**
     * Sorts the array ascending using Merge sort algorithm.
     * 
     * @param array
     * Array to be sorted.
     */
    public static void sort(int[] array) {
        if (array.length > 1) {

            // divide input array on two subproplems
            int middle = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, middle);
            int[] right = Arrays.copyOfRange(array, middle, array.length);

            // sort subarrays
            sort(left);
            sort(right);

            // and merge them back to the original array
            int l = 0;
            int r = 0;
            for (int i = 0; i < array.length; i++) {
                if (r == right.length || (l < left.length && left[l] < right[r])) {
                    array[i] = left[l];
                    l++;
                }
                else {
                    array[i] = right[r];
                    r++;
                }
            }
        }
    }

}
