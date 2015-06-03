package edu.stanford.algo.structures;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import edu.stanford.algo.sorting.ArrayUtils;
import edu.stanford.algo.structures.BinarySearchTree;

public class BinarySearchTreeTest {

    @Test
    public void testBinarySearchTreeFromArray() {
	for (int repetiton = 0; repetiton < 50; repetiton++) {
	    int[] array = ArrayUtils.randomArray(1, 20, 1, 50);
	    Arrays.sort(array);
	    BinarySearchTree bst = new BinarySearchTree(array);
	    Assert.assertArrayEquals(array, bst.toArray());
	}
    }

    @Test
    public void testContains() {
	int[] array = new int[10];
	for (int i = 0; i < array.length; i++) {
	    array[i] = i + 1;
	}
	BinarySearchTree bst = new BinarySearchTree(array);
	Assert.assertTrue(bst.contains(1));
	Assert.assertTrue(bst.contains(5));
	Assert.assertTrue(bst.contains(6));
	Assert.assertTrue(bst.contains(10));
	Assert.assertFalse(bst.contains(0));
	Assert.assertFalse(bst.contains(11));
    }

    @Test
    public void testDelete() {
	for (int repetiton = 0; repetiton < 50; repetiton++) {
	    int[] array = ArrayUtils.randomArray(1, 20, 1, 50);
	    Arrays.sort(array);
	    int deleteIndex = (int) (Math.random() * (array.length - 1));

	    BinarySearchTree bst = new BinarySearchTree(array);
	    bst.delete(array[deleteIndex]);
	    int[] bstArray = bst.toArray();
	    Assert.assertEquals(array.length, bstArray.length + 1);
	    if (deleteIndex < bstArray.length) {
		Assert.assertEquals(array[deleteIndex + 1], bstArray[deleteIndex]);
	    }
	    assertSorted(bst.toArray());
	}
    }

    @Test
    public void testSelect() {
	for (int repetiton = 0; repetiton < 50; repetiton++) {
	    int[] array = ArrayUtils.randomArray(1, 20, 1, 50);
	    Arrays.sort(array);
	    int selectIndex = (int) (Math.random() * (array.length - 1));

	    BinarySearchTree bst = new BinarySearchTree(array);
	    Assert.assertEquals(array[selectIndex], bst.select(selectIndex + 1));
	}
    }

    @Test
    public void testInsert() {
	int[] array = new int[10];
	for (int i = 0; i < array.length; i++) {
	    array[i] = i + 1;
	}
	BinarySearchTree bst = new BinarySearchTree(array);
	int oldSize = bst.size();
	int numInserted = 5;
	for (int i = 0; i < numInserted; i++) {
	    bst.insert((int) (Math.random() * 10));
	}
	Assert.assertEquals(oldSize + numInserted, bst.size());
	assertSorted(bst.toArray());
    }

    @Test
    public void testMax() {
	for (int repetiton = 0; repetiton < 50; repetiton++) {
	    int[] array = ArrayUtils.randomArray(1, 20, 1, 50);
	    Arrays.sort(array);
	    BinarySearchTree bst = new BinarySearchTree(array);
	    Assert.assertEquals(array[array.length - 1], bst.max());
	}
    }

    @Test
    public void testMin() {
	for (int repetiton = 0; repetiton < 50; repetiton++) {
	    int[] array = ArrayUtils.randomArray(1, 20, 1, 50);
	    Arrays.sort(array);
	    BinarySearchTree bst = new BinarySearchTree(array);
	    Assert.assertEquals(array[0], bst.min());
	}
    }

    private void assertSorted(int[] array) {
	for (int i = 0; i < array.length - 1; i++) {
	    Assert.assertTrue(array[i] <= array[i + 1]);
	}
    }
}
