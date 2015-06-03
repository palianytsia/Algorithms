package edu.princeton.algo;

import java.util.Comparator;

import edu.princeton.stdlib.StdDraw;

/**
 * An immutable data type for points in the plane.
 * 
 * @see <a
 *      href="http://coursera.cs.princeton.edu/algs4/assignments/collinear.html">Programming
 *      Assignment 3: Pattern Recognition</a> from "Algorithms, Part I" course
 *      by <i>Kevin Wayne</i> and <i>Robert Sedgewick</i> for detailed data type
 *      description.
 * 
 * @author Ivan Palianytsia <a
 *         href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 * 
 */
public class Point implements Comparable<Point> {

	/**
	 * Compares points by the slopes they make with the invoking point (x0, y0).
	 * 
	 * <p>
	 * Formally, the point (x1, y1) is less than the point (x2, y2) if and only
	 * if the slope
	 * <code>(y1 − y0) / (x1 − x0)<code> is less than the slope <code>(y2 − y0) / (x2 −
	 * x0)</code>.
	 * </p>
	 */
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
		@Override
		public int compare(Point a, Point b) {
			return Double.compare(Point.this.slopeTo(a), Point.this.slopeTo(b));
		}
	};

	private final int x;
	private final int y;

	/**
	 * Creates a new point with the given coordinates (x, y).
	 * 
	 * @param x
	 *            - abscissa, perpendicular distance of a point from the y -
	 *            axis.
	 * @param y
	 *            - ordinate, perpendicular distance of a point from the y -
	 *            axis.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Compares this point with the specified point for lexicographical order.
	 * <p>
	 * Compares points by their y-coordinates, breaking ties by their
	 * x-coordinates. Formally, the invoking point (x0, y0) is less than the
	 * argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and
	 * x0 < x1.
	 * </p>
	 * 
	 * @param other
	 *            - point to compare this point with.
	 * 
	 * @return -1 if this point comes before the argument point in
	 *         lexicographical order, 1 if it comes after the argument point and
	 *         0 if it is the same point.
	 */
	@Override
	public int compareTo(Point other) {
		if (this.y < other.y) {
			return -1;
		}
		if (other.y < this.y) {
			return 1;
		}
		return Integer.compare(this.x, other.x);
	}

	/**
	 * Plots this point to standard drawing.
	 */
	public void draw() {
		StdDraw.point(x, y);
	}

	/**
	 * Draws line between this point and another point to standard drawing.
	 * 
	 * @param other
	 *            - point to connect to this point with a line in standard
	 *            drawing.
	 */
	public void drawTo(Point other) {
		StdDraw.line(this.x, this.y, other.x, other.y);
	}

	/**
	 * Calculates slope between this point and another point.
	 * 
	 * <p>
	 * Slope between the invoking point (x0, y0) and the argument point (x1, y1)
	 * is calculated by the formula <code>(y1 − y0) / (x1 − x0)</code>.
	 * </p>
	 * <p>
	 * Slope of a horizontal line segment is positive zero; slope of a vertical
	 * line segment is positive infinity; slope of a degenerate line segment
	 * (between a point and itself) is negative infinity.
	 * </p>
	 * 
	 * @param other
	 *            - point to calculate the slope to.
	 * 
	 * @return slope between this point and another point.
	 */
	public double slopeTo(Point other) {
		if (this.compareTo(other) == 0) {
			return Double.NEGATIVE_INFINITY;
		}
		if (this.x == other.x) {
			return Double.POSITIVE_INFINITY;
		}
		if (this.y == other.y) {
			return 0d;
		}
		return (other.y - this.y) / (double) (other.x - this.x);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}