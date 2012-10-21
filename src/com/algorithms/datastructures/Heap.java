package com.algorithms.datastructures;

import java.util.ArrayList;
import java.util.List;

import com.algorithms.sorting.ArrayUtils;

/**
 * <p>This is a demonstration of how the heap works. Heap is a data structure that offers a fast way to do repeated minimum computation for the contained
 * elements. The synonymous name for the "heap" is "priority queue".</p>
 * 
 * <p>Generally heap can contain any elements associated with the keys that are comparable (i.e. applicable for minimum computation), but this demo heap can
 * contain only integers which at the same moment are the keys.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class Heap {

    private final List<Integer> storage = new ArrayList<Integer>();

    /**
     * Constructs an empty heap.
     */
    public Heap() {

    }

    /**
     * <p>Constructs a new heap containing all the elements from the given array.</p>
     * 
     * <p>Running time of this operation is O(log(n)), where <code>n</code> denotes the number of elements in the array, which is faster than to create an empty
     * and then call {@link #insert(Comparable) insert} for each element in turn.</p>
     * 
     * @param array
     * The array containing the elements to initialize the heap with.
     */
    public Heap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            storage.add(array[i]);
        }
        for (int i = storage.size() / 2 - 1; i >= 0; i--) {
            bubbleDown(storage, i);
        }
    }

    /**
     * <p>Retrieves the minimum element (as defined by Comparator) and removes it from the heap. If there are several minimum elements, an arbitrary one of them
     * is removed.</p>
     * 
     * <p>Running time of this operation is <code>O(log(n))</code>, where <code>n</code> denotes the number of elements in the heap.</p>
     * 
     * @return Newly removed element.
     */
    public Integer extractMin() {
        if (storage.size() == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        Integer min = storage.get(0);

        // Move last leaf to be new root
        storage.set(0, storage.get(storage.size() - 1));
        storage.remove(storage.size() - 1);

        // Bubble-Down until heap property has been restored
        bubbleDown(storage, 0);

        return min;
    }

    /**
     * <p>Adds a new element to the heap</p>
     * 
     * <p>Running time of this operation is O(log(n)), where <code>n</code> denotes the number of elements in the heap.</p>
     * 
     * @param element
     * Element to add to the heap.
     */
    public void insert(int element) {
        storage.add(element);

        // Bubble-Up element until heap property is restored
        bubbleUp(storage, storage.size() - 1);
    }

    public int size() {
        return storage.size();
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    private void bubbleDown(List<Integer> list, int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int min = index;
        if (left < storage.size() && storage.get(min).compareTo(storage.get(left)) > 0) {
            min = left;
        }
        if (right < storage.size() && storage.get(min).compareTo(storage.get(right)) > 0) {
            min = right;
        }
        if (index != min) {
            ArrayUtils.swap(list, index, min);
            bubbleDown(list, min);
        }
    }

    private void bubbleUp(List<Integer> list, int index) {
        if (index > 0) {
            int parent = getParentIndex(index);
            if (storage.get(parent).compareTo(storage.get(index)) > 0) {
                ArrayUtils.swap(storage, index, parent);
                bubbleUp(list, parent);
            }
        }
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

}
