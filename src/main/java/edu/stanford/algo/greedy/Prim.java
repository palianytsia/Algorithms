package edu.stanford.algo.greedy;

import java.util.HashSet;
import java.util.Set;

import edu.stanford.algo.structures.graph.Edge;
import edu.stanford.algo.structures.graph.UndirectedGraph;

/**
 * Prim's minimum spanning tree algorithm.
 * 
 * @see <a href="https://class.coursera.org/algo2-2012-001">Algorithms: Design and Analysis, Part 2</a> by Tim Roughgarden for detailed algorithm description,
 *      analysis and pseudo code.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 * 
 */
public class Prim {

    /**
     * Computes the minimum spanning tree for the given graph using Prim's greedy algorithm.
     * 
     * @param g
     *            - graph to compute minimum spanning tree for; must be connected for algorithm to succeed.
     * 
     * @return set of edges representing the minimum spanning tree or null if graph is not connected.
     */
    public static Set<Edge> getMinimumSpanningTree(UndirectedGraph g) {
	Set<Edge> mst = new HashSet<Edge>();
	Set<Integer> spannedVertices = new HashSet<Integer>();
	spannedVertices.add(g.getVertices().iterator().next());
	while (spannedVertices.size() < g.getNumVertices()) {
	    Edge cheapestEdge = null;
	    for (Integer vertex : spannedVertices) {
		for (Edge e : g.getOutgoingEdges(vertex)) {
		    if (!spannedVertices.contains(e.getOtherVertex(vertex)) && (cheapestEdge == null || e.getLength() < cheapestEdge.getLength())) {
			cheapestEdge = e;
		    }
		}
	    }
	    if (cheapestEdge == null) {
		return null;
	    }
	    mst.add(cheapestEdge);
	    spannedVertices.add(cheapestEdge.getVertexA());
	    spannedVertices.add(cheapestEdge.getVertexB());
	}
	return mst;
    }

}
