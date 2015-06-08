package edu.princeton.algo;

/**
 * Percolation system that helps to address following problems:
 * <ul>
 * <li>Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that
 * the composite system is an electrical conductor?</li>
 * <li>Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the
 * oil to gush through to the surface)?</li>
 * </ul>
 * 
 * @see <a href="http://coursera.cs.princeton.edu/algs4/assignments/percolation.html">Programming Assignment 1: Percolation</a> from "Algorithms, Part I" course
 *      by <i>Kevin Wayne</i> and <i>Robert Sedgewick</i> for detailed problem description.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 * 
 */
public class Percolation {

    private final int N;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF union;
    private final WeightedQuickUnionUF topUnion;

    /**
     * Create a new percolation system using an N-by-N grid of sites. Initially all the sites are blocked.
     * 
     * @param N
     *            - size of percolation grid, must be greater than zero
     * 
     * @throws IllegalArgumentExcetion
     *             if the given size is equal to or less than zero.
     */
    public Percolation(int N) {
	if (N <= 0) {
	    throw new IllegalArgumentException("Invalid size of percolation grid:" + N + ".");
	}
	this.N = N;
	this.grid = new boolean[N][N];
	this.union = new WeightedQuickUnionUF(N * N + 2);
	this.topUnion = new WeightedQuickUnionUF(N * N + 1);
    }

    /**
     * Checks whether a given site (row i, column j) is full.
     * 
     * <p>
     * A full site is an open site that can be connected to an open site in the top row via a chain of neighbouring (left, right, up, down) open sites.
     * </p>
     * <p>
     * By convention, the row and column indices i and j are integers between 1 and N, where (1, 1) is the upper-left site.
     * </p>
     * 
     * @param i
     *            - row index of the site to check
     * @param j
     *            - column index of the site to check
     * @return true if the given site is full, false otherwise.
     * 
     * @throws IndexOutOfBoundsException
     *             if any of the given indexes is outside the prescribed range.
     */
    public boolean isFull(int i, int j) {
	if (i < 1 || i > N) {
	    throw new IndexOutOfBoundsException("Row index out of bounds: " + i);
	}
	if (j < 1 || j > N) {
	    throw new IndexOutOfBoundsException("Column index out of bounds: " + j);
	}

	// site is full if and only if it is an open site and is connected to a
	// virtual top site
	return isOpen(i, j) && topUnion.connected(0, getSequenceNumber(i, j));
    }

    /**
     * Checks whether a given site (row i, column j) is open.
     * 
     * <p>
     * By convention, the row and column indices i and j are integers between 1 and N, where (1, 1) is the upper-left site.
     * </p>
     * 
     * @param i
     *            - row index of the site to check
     * @param j
     *            - column index of the site to check
     * @return true if the given site is open, false otherwise.
     * 
     * @throws IndexOutOfBoundsException
     *             if any of the given indexes is outside the prescribed range.
     */
    public boolean isOpen(int i, int j) {
	if (i < 1 || i > N) {
	    throw new IndexOutOfBoundsException("Row index out of bounds: " + i);
	}
	if (j < 1 || j > N) {
	    throw new IndexOutOfBoundsException("Column index out of bounds: " + j);
	}
	return grid[i - 1][j - 1];
    }

    /**
     * Opens site (row i, column j), if it is not yet open.
     * 
     * <p>
     * By convention, the row and column indices i and j are integers between 1 and N, where (1, 1) is the upper-left site.
     * </p>
     * 
     * @param i
     *            - row index of the site to open
     * @param j
     *            - column index of the site to open
     * 
     * @throws IndexOutOfBoundsException
     *             if any of the given indexes is outside the prescribed range.
     */
    public void open(int i, int j) {
	if (i < 1 || i > N) {
	    throw new IndexOutOfBoundsException("Row index out of bounds: " + i);
	}
	if (j < 1 || j > N) {
	    throw new IndexOutOfBoundsException("Column index out of bounds: " + j);
	}
	if (grid[i - 1][j - 1]) {
	    // System.out.printf("Site (%d, %d) is already opened.%n", i, j);
	    return;
	}

	grid[i - 1][j - 1] = true;

	int sequenceNumber = getSequenceNumber(i, j);

	// connect newly opened site to its opened neighbours
	if (i > 1 && isOpen(i - 1, j)) {
	    union.union(getSequenceNumber(i - 1, j), sequenceNumber);
	    topUnion.union(getSequenceNumber(i - 1, j), sequenceNumber);
	}
	if (i < N && isOpen(i + 1, j)) {
	    union.union(getSequenceNumber(i + 1, j), sequenceNumber);
	    topUnion.union(getSequenceNumber(i + 1, j), sequenceNumber);
	}
	if (j > 1 && isOpen(i, j - 1)) {
	    union.union(getSequenceNumber(i, j - 1), sequenceNumber);
	    topUnion.union(getSequenceNumber(i, j - 1), sequenceNumber);
	}
	if (j < N && isOpen(i, j + 1)) {
	    union.union(getSequenceNumber(i, j + 1), sequenceNumber);
	    topUnion.union(getSequenceNumber(i, j + 1), sequenceNumber);
	}

	// special treatment for top and bottom row sites, in order to connect
	// them to virtual neighbours
	if (i == 1) {
	    union.union(0, sequenceNumber);
	    topUnion.union(0, sequenceNumber);
	}
	if (i == N) {
	    union.union(N * N + 1, sequenceNumber);
	}

    }

    /**
     * Checks whether this system in its current state percolates.
     * 
     * <p>
     * The system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row
     * and that process fills some open site on the bottom row.
     * </p>
     * 
     * @return true if this system percolates, false otherwise.
     */
    public boolean percolates() {
	// system percolates if virtual top and bottom are connected
	return union.connected(0, N * N + 1);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (int i = 1; i <= N; i++) {
	    for (int j = 1; j <= N; j++) {
		if (!isOpen(i, j)) {
		    sb.append("\u2B1A");
		} else if (isFull(i, j)) {
		    sb.append("\u2B1B");
		} else {
		    sb.append("\u2B1C");
		}
		sb.append(" ");
	    }
	    sb.append('\n');
	}
	return sb.toString();
    }

    /**
     * Converts two-dimensional index into sequence number for usage in union data structure.
     * <p>
     * For example, in a 5x5 grid the site in the third row and second column has sequence number 12 (5 + 5 + 2).
     * </p>
     */
    private int getSequenceNumber(int i, int j) {
	return N * (i - 1) + j;
    }
}
