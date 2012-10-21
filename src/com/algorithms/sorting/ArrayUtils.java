package com.algorithms.sorting;

import java.util.List;

public class ArrayUtils {

    /**
     * Swaps two elements of an array.
     * 
     * @param array
     * The array to swap elements in.
     * 
     * @param a
     * Index of the first element to swap.
     * 
     * @param b
     * Index of the second element to swap.
     */
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * Swaps two elements of a list.
     * 
     * @param array
     * The list to swap elements in.
     * 
     * @param a
     * Index of the first element to swap.
     * 
     * @param b
     * Index of the second element to swap.
     */
    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
