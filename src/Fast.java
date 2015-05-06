import java.util.Arrays;

public class Fast {

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

	for (Point p : points) {
	    Point[] pointsInSlopeOrder = Arrays.copyOf(points, points.length);
	    Arrays.sort(pointsInSlopeOrder, p.SLOPE_ORDER);

	    int fromIndex = 0;
	    int toIndex = 1;
	    double currentSlope = p.slopeTo(pointsInSlopeOrder[0]);
	    while (toIndex < pointsInSlopeOrder.length) {
		double nextSlope = p.slopeTo(pointsInSlopeOrder[toIndex]);
		if (currentSlope != nextSlope) {
		    if (toIndex - fromIndex >= 3 && pointsInSlopeOrder[fromIndex].compareTo(p) > 0) {
			output(p, Arrays.copyOfRange(pointsInSlopeOrder, fromIndex, toIndex));
		    }
		    fromIndex = toIndex;
		    currentSlope = nextSlope;
		}
		toIndex++;
	    }

	    if (toIndex - fromIndex >= 3 && pointsInSlopeOrder[fromIndex].compareTo(p) > 0) {
		output(p, Arrays.copyOfRange(pointsInSlopeOrder, fromIndex, toIndex));
	    }
	}
    }

    private static void output(Point p, Point... other) {
	p.drawTo(other[other.length - 1]);
	StringBuilder sb = new StringBuilder(p.toString());
	for (int i = 0; i < other.length; i++) {
	    sb.append(" -> ");
	    sb.append(other[i].toString());
	}
	System.out.println(sb.toString());
    }
}
