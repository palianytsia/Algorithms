import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private Set<Point2D> points = new TreeSet<Point2D>();

    /** is the set empty? */
    public boolean isEmpty() {
	return points.isEmpty();
    }

    /** number of points in the set */
    public int size() {
	return points.size();
    }

    /** add the point to the set (if it is not already in the set) */
    public void insert(Point2D p) {
	if (p != null && !contains(p)) {
	    points.add(p);
	}
    }

    /** does the set contain point p? */
    public boolean contains(Point2D p) {
	return points.contains(p);
    }

    /** draw all points to standard draw */
    public void draw() {
	for (Point2D point : points) {
	    point.draw();
	}
    }

    /** all points that are inside the rectangle */
    public Iterable<Point2D> range(RectHV rect) {
	List<Point2D> range = new LinkedList<Point2D>();
	for (Point2D point : points) {
	    if (rect.contains(point)) {
		range.add(point);
	    }
	}
	return range;
    }

    /** a nearest neighbour in the set to point p; null if the set is empty */
    public Point2D nearest(Point2D p) {
	Point2D nearest = null;
	double minDistance = Double.POSITIVE_INFINITY;
	for (Point2D point : points) {
	    double distance = p.distanceTo(point);
	    if (distance < minDistance) {
		minDistance = distance;
		nearest = point;
	    }
	}
	return nearest;
    }
}