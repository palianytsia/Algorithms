package com.algorithms.sorting;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public abstract class SortTest {

    @Test
    public void testDegenerate() {
        try {
            sort(new int[] {});
            sort(new int[] { 0 });
        }
        catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testEven() {
        test(2);
        test(1000);
    }

    @Test
    public void testHuge() {
        test(100000);
    }

    @Test
    public void testOdd() {
        test(3);
        test(999);
    }

    protected abstract void sort(int[] array);

    private int[] getRandomArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (length * Math.random());
        }
        return array;
    }

    private void test(int length) {
        int[] test = getRandomArray(length);
        int[] verification = test.clone();
        sort(test);
        Arrays.sort(verification);
        Assert.assertArrayEquals(verification, test);
    }

}
