package edu.stanford.algo.structures;

import java.util.Arrays;

import edu.stanford.algo.sorting.ArrayUtils;

/**
 * This is a demonstration of how the heap works. Heap is a data structure that offers a fast way to do repeated minimum computation for the contained elements.
 * The synonymous name for the "heap" is "priority queue".
 * 
 * <p>
 * Generally heap can contain any elements associated with the keys that are comparable (i.e. applicable for minimum computation), but this demo heap can
 * contain only integers which at the same moment are the keys.
 * </p>
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class Heap {

    private static final float LOAD_FACTOR = 0.75f;

    private int size = 0;

    public int[] storage;

    /**
     * Constructs an empty heap.
     */
    public Heap() {
	size = 100;
	storage = new int[size];
    }

    /**
     * Constructs a new heap containing all the elements from the given array.
     * 
     * <p>
     * Running time of this operation is O(log(n)), where <code>n</code> denotes the number of elements in the array, which is faster than to create an empty
     * and then call {@link #insert(Comparable) insert} for each element in turn.
     * </p>
     * 
     * @param array
     *            - the array containing the elements to initialize the heap with.
     */
    public Heap(int[] array) {
	if (array.length < 50) {
	    storage = new int[100];
	} else {
	    storage = new int[array.length * 2];
	}
	size = array.length;
	for (int i = 0; i < size; i++) {
	    storage[i] = array[i];
	}
	for (int i = size / 2 - 1; i >= 0; i--) {
	    bubbleDown(i);
	}
    }

    /**
     * Deletes an element from the heap. If there are several such elements, an arbitrary one of them is removed.
     * 
     * @param element
     *            - element to delete from the heap.
     */
    public void delete(int element) {
	int index = find(element, 0);
	if (index != -1) {
	    // Move last leaf to deleted element position
	    storage[index] = storage[size - 1];
	    size--;

	    // Bubble-Down new element until heap property has been restored
	    bubbleDown(index);
	}
    }

    /**
     * Retrieves the minimum element and removes it from the heap. If there are several minimum elements, an arbitrary one of them is removed.
     * 
     * <p>
     * Running time of this operation is <code>O(log(n))</code>, where <code>n</code> denotes the number of elements in the heap.
     * </p>
     * 
     * @return Newly removed element.
     */
    public int extractMin() {
	if (size == 0) {
	    throw new IllegalStateException("Heap is empty.");
	}
	int min = storage[0];

	// Move last leaf to be new root
	storage[0] = storage[size - 1];
	size--;

	// Bubble-Down new root until heap property has been restored
	bubbleDown(0);

	return min;
    }

    /**
     * Adds a new element to the heap.
     * 
     * <p>
     * Running time of this operation is O(log(n)), where <code>n</code> denotes the number of elements in the heap.
     * </p>
     * 
     * @param element
     *            - element to add to the heap.
     */
    public void insert(int element) {

	// If storage is loaded on more than 75% - increase its size twice
	if ((float) size / storage.length > LOAD_FACTOR) {
	    storage = Arrays.copyOf(storage, storage.length * 2);
	}

	// Insert new element
	storage[size] = element;
	size++;

	// Bubble-Up new element until heap property is restored
	bubbleUp(size - 1);
    }

    /**
     * @return Number of elements in this heap.
     */
    public int size() {
	return size;
    }

    @Override
    public String toString() {
	return storage.toString();
    }

    private void bubbleDown(int index) {
	int left = getLeftChildIndex(index);
	int right = getRightChildIndex(index);
	int min = index;
	if (left < size && storage[min] > storage[left]) {
	    min = left;
	}
	if (right < size && storage[min] > storage[right]) {
	    min = right;
	}
	if (index != min) {
	    ArrayUtils.swap(storage, index, min);
	    bubbleDown(min);
	}
    }

    private void bubbleUp(int index) {
	if (index > 0) {
	    int parent = getParentIndex(index);
	    if (storage[parent] > storage[index]) {
		ArrayUtils.swap(storage, index, parent);
		bubbleUp(parent);
	    }
	}
    }

    private int find(int element, int startIndex) {
	if (element < storage[startIndex]) {
	    return -1;
	}
	if (element == storage[startIndex]) {
	    return startIndex;
	}

	int leftSubtreeSearch = find(element, getLeftChildIndex(startIndex));
	if (leftSubtreeSearch != -1) {
	    return leftSubtreeSearch;
	}

	int rightSubtreeSearch = find(element, getRightChildIndex(startIndex));
	if (rightSubtreeSearch != -1) {
	    return rightSubtreeSearch;
	}

	return -1;
    }

    private int getParentIndex(int childIndex) {
	return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
	return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
	return 2 * parentIndex + 2;
    }

}
