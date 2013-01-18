package com.algorithms.sorting;

public class InsertionSort {

    /**
     * Sorts the array ascending using Insertion sort algorithm.
     * 
     * @param array
     *            - array to be sorted.
     */
    public static void sort(int[] array) {
        int comparisons = 0;
        if (array.length > 1) {
            for (int j = 1; j < array.length; j++) {
                int i = j;
                while (i > 0 && array[i] < array[i - 1]) {
                    comparisons++;
                    ArrayUtils.swap(array, i, i - 1);
                    i--;
                }
            }
        }
        System.out.println("Array of " + array.length + " elements sorted via Insertion sort with " + comparisons
                + " comparisons.");
    }

}
