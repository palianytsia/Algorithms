package edu.stanford.algo;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import edu.stanford.algo.Selection;
import edu.stanford.algo.sorting.ArrayUtils;

public class SelectionTest {

    @Test
    public void testMedianOfThree() {
	int[] array = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
	for (int i = 0; i < array.length; i++) {
	    for (int j = 0; j < array.length; j++) {
		for (int k = 0; k < array.length; k++) {
		    int[] sortedArray = { array[i], array[j], array[k] };
		    Arrays.sort(sortedArray);
		    Assert.assertEquals(sortedArray[1], Selection.median(array[i], array[j], array[k]));
		}
	    }
	}
    }

    @Test
    public void testSelectIthOrderStatistic() {
	for (int n = 0; n < 50; n++) {
	    int[] array = ArrayUtils.randomArray(1, 20, 1, 50);
	    int i = 1 + (int) (Math.random() * array.length);
	    int ithOrderStatistic = Selection.select(array, i);
	    Arrays.sort(array);
	    Assert.assertEquals(array[i - 1], ithOrderStatistic);
	}
    }

}
