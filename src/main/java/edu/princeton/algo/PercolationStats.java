package edu.princeton.algo;

import edu.princeton.stdlib.StdRandom;
import edu.princeton.stdlib.StdStats;

public class PercolationStats {

	/**
	 * Let stats[t] be the fraction of open sites in computational experiment t.
	 */
	private final double[] stats;

	/**
	 * Creates new sample of T computational experiments performed on an N-by-N
	 * percolation grid.
	 * 
	 * @param N
	 *            - size of percolation grid.
	 * @param T
	 *            - number of computational experiments.
	 */
	public PercolationStats(int N, int T) {
		if (N <= 0) {
			throw new IllegalArgumentException(
					"Invalid size of percolation grid:" + N + ".");
		}
		if (T <= 0) {
			throw new IllegalArgumentException(
					"Invalid number of computational experiments: " + T + ".");
		}
		this.stats = new double[T];
		for (int t = 0; t < T; t++) {
			Percolation p = new Percolation(N);
			int numOpened = 0;
			while (!p.percolates()) {
				int i = StdRandom.uniform(1, N + 1);
				int j = StdRandom.uniform(1, N + 1);
				if (!p.isOpen(i, j)) {
					p.open(i, j);
					numOpened++;
				}
			}
			stats[t] = (double) numOpened / (N * N);
		}
	}

	/**
	 * @return sample mean of percolation threshold.
	 */
	public double mean() {
		return StdStats.mean(stats);
	}

	/**
	 * @return sample standard deviation of percolation threshold.
	 */
	public double stddev() {
		return StdStats.stddev(stats);
	}

	/**
	 * @return lower bound of the 95% confidence interval.
	 */
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(stats.length);
	}

	/**
	 * @return upper bound of the 95% confidence interval.
	 */
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(stats.length);
	}

	/**
	 * Test client, which takes two command-line arguments N and T, creates new
	 * PercolationStats object parametrised by those variables and outputs the
	 * received statistic.
	 * 
	 * @param args
	 *            - args[0] is size of percolation grid, arg[1] is number of
	 *            experiments to run.
	 */
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats percolationStats = new PercolationStats(N, T);
		System.out.println("mean = " + percolationStats.mean());
		System.out.println("stddev = " + percolationStats.stddev());
		System.out.println("95% confidence interval = "
				+ percolationStats.confidenceLo() + ", "
				+ percolationStats.confidenceHi());

	}
}
