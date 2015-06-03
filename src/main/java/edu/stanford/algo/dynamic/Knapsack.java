package edu.stanford.algo.dynamic;

public class Knapsack {

    public static class Item {

	private final int value;

	private final int weight;

	public Item(int value, int weight) {
	    this.value = value;
	    this.weight = weight;
	}

    }

    private final int capacity;

    public Knapsack(int capacity) {
	this.capacity = capacity;
    }

    public int pack(Item[] items) {
	int[][] a = new int[items.length + 1][capacity + 1];
	for (int i = 1; i <= items.length; i++) {
	    for (int x = 0; x <= capacity; x++) {
		if (x >= items[i - 1].weight) {
		    a[i][x] = Math.max(a[i - 1][x], a[i - 1][x - items[i - 1].weight] + items[i - 1].value);
		} else {
		    a[i][x] = a[i - 1][x];
		}
	    }
	}
	return a[items.length][capacity];
    }

}
