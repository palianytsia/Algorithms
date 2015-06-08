package edu.stanford.algo.dynamic;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.stanford.algo.dynamic.Knapsack;
import edu.stanford.algo.dynamic.Knapsack.Item;

public class KnapsackTest {

	private final String dataFileLocation = getClass().getResource("knapsack1.txt").getPath();

    @Test
    public void testPack() {
	try {
	    File dataFile = new File(dataFileLocation);
	    Scanner scanner = new Scanner(dataFile);
	    int knapsackSize = scanner.nextInt();
	    int numberOfItems = scanner.nextInt();
	    Item[] items = new Item[numberOfItems];
	    for (int i = 0; i < numberOfItems; i++) {
		items[i] = new Item(scanner.nextInt(), scanner.nextInt());
	    }
	    scanner.close();
	    Knapsack knapsack = new Knapsack(knapsackSize);
	    System.out.println(knapsack.pack(items));
	} catch (FileNotFoundException e) {
	    fail("Cannot find knapsack data file: " + dataFileLocation);
	}
    }

}
