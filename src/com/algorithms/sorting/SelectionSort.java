package com.algorithms.sorting;

/**
 * Showcase implementation of <strong>Selection sort</strong>.
 * 
 * <p>
 * In computer science, a selection sort is a sorting algorithm, specifically an in-place comparison sort. It has O(n^2)
 * time complexity, making it inefficient on large lists, and generally performs worse than the similar insertion sort.
 * Selection sort is noted for its simplicity, and also has performance advantages over more complicated algorithms in
 * certain situations, particularly where auxiliary memory is limited.
 * </p>
 * 
 * The algorithm works as follows:
 * <ul>
 * <li>Find the minimum value in the list;</li>
 * <li>Swap it with the value in the first position;</li>
 * <li>Repeat the steps above for the remainder of the list (starting at the second position and advancing each time).</li>
 * </ul>
 * 
 * <p>
 * Effectively, the list is divided into two parts: the sublist of items already sorted, which is built up from left to
 * right and is found at the beginning, and the sublist of items remaining to be sorted, occupying the remainder of the
 * array.
 * </p>
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class SelectionSort {

    /**
     * Sorts the array ascending using Selection sort algorithm.
     * 
     * @param array
     *            - array to be sorted.
     */
    public static void sort(int[] array) {
        int comparisons = 0;
        if (array.length > 1) {
            int begin = 0;
            while (begin < array.length - 1) {
                int min = begin;
                for (int i = begin; i < array.length; i++) {
                    if (array[i] < array[min]) {
                        min = i;
                    }
                    comparisons++;
                }
                if (min != begin) {
                    ArrayUtils.swap(array, min, begin);
                }
                begin++;
            }
        }
        System.out.println("Array of " + array.length + " elements sorted via Selection sort with " + comparisons
                + " comparisons.");
    }

}
