package edu.princeton.algo;

import java.util.Arrays;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdDraw;

public class Brute {

    public static void main(String[] args) {
	StdDraw.setXscale(0, 32768);
	StdDraw.setYscale(0, 32768);

	In in = new In(args[0]);
	Point[] points = new Point[in.readInt()];
	for (int i = 0; i < points.length; i++) {
	    int x = in.readInt();
	    int y = in.readInt();
	    points[i] = new Point(x, y);
	    points[i].draw();
	}
	in.close();

	Arrays.sort(points);

	for (int k = 0; k < points.length; k++) {
	    for (int l = k + 1; l < points.length; l++) {
		for (int m = l + 1; m < points.length; m++) {
		    for (int n = m + 1; n < points.length; n++) {
			if (isStraightLine(points[k], points[l], points[m], points[n])) {
			    System.out.println(toString(points[k], points[l], points[m], points[n]));
			    points[k].drawTo(points[n]);
			}
		    }
		}
	    }
	}
    }

    private static String toString(Point... segment) {
	StringBuilder sb = new StringBuilder(segment[0].toString());
	for (int i = 1; i < segment.length; i++) {
	    sb.append(" -> ");
	    sb.append(segment[i].toString());
	}
	return sb.toString();
    }

    private static boolean isStraightLine(Point... segment) {
	double slope = segment[0].slopeTo(segment[1]);
	for (int i = 2; i < segment.length; i++) {
	    if (segment[0].slopeTo(segment[i]) != slope) {
		return false;
	    }
	}
	return true;
    }
}
