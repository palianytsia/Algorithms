package com.algorithms.dynamic;

import java.util.SortedMap;
import java.util.TreeMap;

import com.algorithms.datastructures.graph.DirectedGraph;
import com.algorithms.datastructures.graph.Edge;

/**
 * The Bellman-Ford algorithm solves the single-source shortest-path problem. While slower than Dijkstra's algorithm, it accommodates negative edge lengths and
 * is also better suited for distributed implementations.
 * 
 * @see <a href="https://class.coursera.org/algo2-2012-001">Algorithms: Design and Analysis, Part 2</a> by Tim Roughgarden for detailed algorithm description,
 *      analysis and pseudo code.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class BellmanFord {

    public static SortedMap<Integer, Double> shortestPath(DirectedGraph g, int source) {
	final SortedMap<Integer, Double> previousDistances = new TreeMap<Integer, Double>();
	final SortedMap<Integer, Double> currentDistances = new TreeMap<Integer, Double>();
	currentDistances.put(source, 0d);
	for (Integer v : g.getVertices()) {
	    if (v != source) {
		currentDistances.put(v, Double.POSITIVE_INFINITY);
	    }
	}
	for (int i = 1; i <= g.getNumVertices(); i++) {
	    previousDistances.clear();
	    previousDistances.putAll(currentDistances);
	    currentDistances.clear();
	    for (Integer v : g.getVertices()) {
		Double currentDistance = Double.POSITIVE_INFINITY;
		Double previousDistance = previousDistances.get(v);
		if (previousDistance < currentDistance) {
		    currentDistance = previousDistance;
		}
		for (Edge e : g.getIncomingEdges(v)) {
		    Double alternativeDistance = previousDistances.get(e.getVertexA()) + e.getLength();
		    if (alternativeDistance < currentDistance) {
			currentDistance = alternativeDistance;
		    }
		}
		currentDistances.put(v, currentDistance);
	    }
	}
	if (!previousDistances.equals(currentDistances)) {
	    // there are negative cost cycles
	    return null;
	}
	return previousDistances;
    }

}
