package com.algorithms.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class HeapTest {

	private int[] test = { 13, 12, 11, 9, 9, 8, 4, 4, 4 };

	@Test
	public void test() {
		Heap heap = new Heap(test);
		Assert.assertTrue(heap.size() == test.length);
		Assert.assertTrue(heap.extractMin() == 4);
		Assert.assertTrue(heap.size() == test.length - 1);
		heap.insert(-6);
		heap.insert(-4);
		heap.insert(4);
		Assert.assertTrue(heap.size() == test.length + 2);
		Assert.assertTrue(heap.extractMin() == -6);
		Assert.assertTrue(heap.extractMin() == -4);
		Assert.assertTrue(heap.size() == test.length);
		heap.delete(9);
		Assert.assertTrue(heap.size() == test.length - 1);
	}

}
