package edu.princeton.algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int emptyBlockIndex;
    private final int[][] blocks;

    /**
     * construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
	this.blocks = new int[blocks.length][blocks.length];
	int emptyBlockIndex = -1;
	for (int i = 0; i < blocks.length; i++) {
	    for (int j = 0; j < blocks.length; j++) {
		this.blocks[i][j] = blocks[i][j];
		if (blocks[i][j] == 0) {
		    emptyBlockIndex = i * blocks.length + j;
		}
	    }
	}

	this.emptyBlockIndex = emptyBlockIndex;
    };

    /**
     * board dimension N
     */
    public int dimension() {
	return blocks.length;
    };

    /**
     * number of blocks out of place
     */
    public int hamming() {
	int hamming = 0;
	for (int actualI = 0; actualI < blocks.length; actualI++) {
	    for (int actualJ = 0; actualJ < blocks.length; actualJ++) {
		if (blocks[actualI][actualJ] != 0) {
		    int expectedI = (blocks[actualI][actualJ] - 1) / blocks.length;
		    int expectedJ = (blocks[actualI][actualJ] - 1) % blocks.length;
		    if (actualI != expectedI || actualJ != expectedJ) {
			hamming++;
		    }
		}
	    }
	}
	return hamming;
    };

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
	int manhattan = 0;
	for (int actualI = 0; actualI < blocks.length; actualI++) {
	    for (int actualJ = 0; actualJ < blocks.length; actualJ++) {
		if (blocks[actualI][actualJ] != 0) {
		    int expectedI = (blocks[actualI][actualJ] - 1) / blocks.length;
		    int expectedJ = (blocks[actualI][actualJ] - 1) % blocks.length;
		    manhattan += Math.abs(actualI - expectedI) + Math.abs(actualJ - expectedJ);
		}
	    }
	}
	return manhattan;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
	return hamming() == 0;
    };

    /**
     * a board that is obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {
	if (emptyBlockIndex >= blocks.length) {
	    return swap(0, 1);
	}
	return swap(blocks.length, blocks.length + 1);
    };

    private Board swap(int x, int y) {
	int[][] copy = new int[blocks.length][blocks.length];
	for (int i = 0; i < blocks.length; i++) {
	    for (int j = 0; j < blocks.length; j++) {
		copy[i][j] = blocks[i][j];
	    }
	}

	int xI = x / blocks.length;
	int xJ = x % blocks.length;
	int yI = y / blocks.length;
	int yJ = y % blocks.length;
	int tmp = copy[xI][xJ];
	copy[xI][xJ] = copy[yI][yJ];
	copy[yI][yJ] = tmp;

	return new Board(copy);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Board other = (Board) obj;
	if (!Arrays.deepEquals(blocks, other.blocks))
	    return false;
	return true;
    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
	List<Board> neighbours = new LinkedList<Board>();
	if (emptyBlockIndex % blocks.length != 0) {
	    neighbours.add(swap(emptyBlockIndex - 1, emptyBlockIndex));
	}
	if (emptyBlockIndex - blocks.length >= 0) {
	    neighbours.add(swap(emptyBlockIndex - blocks.length, emptyBlockIndex));
	}
	if ((emptyBlockIndex + 1) % blocks.length != 0) {
	    neighbours.add(swap(emptyBlockIndex + 1, emptyBlockIndex));
	}
	if (emptyBlockIndex + blocks.length < blocks.length * blocks.length) {
	    neighbours.add(swap(emptyBlockIndex + blocks.length, emptyBlockIndex));
	}
	return neighbours;
    };

    /**
     * string representation of this board (in the output format specified below)
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(String.valueOf(blocks.length));
	sb.append("\n");
	for (int i = 0; i < blocks.length; i++) {
	    for (int j = 0; j < blocks.length; j++) {
		sb.append(blocks[i][j]).append(" ");
	    }
	    if (i < blocks.length - 1) {
		sb.append("\n");
	    }
	}
	return sb.toString();
    };

    public static void main(String[] args) {
	// Board goalBoard = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, {
	// 7, 8, 0 } });
	// System.out.println("Board:\n" + goalBoard);
	// System.out.println("Dimension: " + goalBoard.dimension());
	// System.out.println("Size: " + goalBoard.size);
	// System.out.println("Empty block index: " +
	// goalBoard.emptyBlockIndex);
	// System.out.println("Is goal: " + goalBoard.isGoal());
	// System.out.println("Hamming distance: " + goalBoard.hamming());
	// System.out.println("Manhattan distance: " + goalBoard.manhattan());
	// System.out.println("Twin:\n" + goalBoard.twin());
	// for (Board neighbor : goalBoard.neighbors()) {
	// System.out.println("Neighbor:\n" + neighbor);
	// }
	//
	// System.out.println("\n");

	Board testBoard = new Board(new int[][] { { 0, 1 }, { 2, 3 } });
	System.out.println("Board:\n" + testBoard);
	System.out.println("Dimension: " + testBoard.dimension());
	System.out.println("Empty block index: " + testBoard.emptyBlockIndex);
	System.out.println("Is goal: " + testBoard.isGoal());
	System.out.println("Hamming distance: " + testBoard.hamming());
	System.out.println("Manhattan distance: " + testBoard.manhattan());
	System.out.println("Twin:\n" + testBoard.twin());
	for (Board neighbor : testBoard.neighbors()) {
	    System.out.println("Neighbor:\n" + neighbor);
	}
    }
}
