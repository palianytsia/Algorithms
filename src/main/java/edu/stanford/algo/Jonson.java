package edu.stanford.algo;

import java.util.SortedMap;
import java.util.TreeMap;

import edu.stanford.algo.dynamic.BellmanFord;
import edu.stanford.algo.greedy.Dijkstra;
import edu.stanford.algo.structures.graph.DirectedGraph;
import edu.stanford.algo.structures.graph.Edge;

/**
 * Johnson's algorithm is a way to find the shortest paths between all pairs of vertices in a sparse directed graph. It allows some of the edge weights to be
 * negative numbers, but no negative-weight cycles may exist. It works by using the Bellman–Ford algorithm to compute a transformation of the input graph that
 * removes all negative weights, allowing Dijkstra's algorithm to be used on the transformed graph. It is named after Donald B. Johnson, who first published the
 * technique in 1977. [From Wikipedia, the free encyclopedia].
 * 
 * @see <a href="https://class.coursera.org/algo2-2012-001">Algorithms: Design and Analysis, Part 2</a> by Tim Roughgarden for detailed algorithm description,
 *      analysis and pseudo code.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class Jonson {

    public static SortedMap<Integer, SortedMap<Integer, Double>> allPairsShortestPaths(DirectedGraph g) {
	SortedMap<Integer, SortedMap<Integer, Double>> distances = new TreeMap<Integer, SortedMap<Integer, Double>>();

	// STEP 1: Form G' by adding a new vertex s and a new edge (s, v) with length 0 for each vertex v of G.
	DirectedGraph gPrime = new DirectedGraph(g);
	Integer s = gPrime.addVertex();
	for (Integer v : gPrime.getVertices()) {
	    if (s != v) {
		gPrime.addEdge(s, v, 0);
	    }
	}

	// STEP 2: Run Bellman-Ford on G' with source vertex s. If Bellman-Ford detects negative cost cycle in G' (which
	// must lay in G), halt and report this (return null), else for each vertex v of V define p_v = length of the
	// shortest s -> v path in G'.
	SortedMap<Integer, Double> pValues = BellmanFord.shortestPath(gPrime, s);
	if (pValues == null) {
	    return null;
	}

	// STEP 3: For each edge e = (u, v) of G define new cost c'_e = c_e + p_u - p_v
	for (Edge e : g.getEdges()) {
	    double cPrime = e.getLength() + pValues.get(e.getVertexA()) - pValues.get(e.getVertexB());
	    e.setLength(cPrime);
	}

	// STEP 4: For each vertex u of G: Run Dijkstra's algorithm in G, with edge lengths {c'_e}, with source vertex
	// u, to compute the shortest-path distance d'(u, v) for each v of G. Then compute the shortest-path distance
	// d(u, v) = d'(u, v) - p_u + p_v, which is correct shortest-path distance for initial edge costs c.
	for (Integer u : g.getVertices()) {
	    SortedMap<Integer, Double> uDistances = Dijkstra.shortestPath(g, u);
	    for (Integer v : uDistances.keySet()) {
		double d = uDistances.get(v) - pValues.get(u) + pValues.get(v);
		uDistances.put(v, d);
	    }
	    distances.put(u, uDistances);
	}

	// STEP 5: restore initial edge costs in G.
	for (Edge e : g.getEdges()) {
	    double c = e.getLength() - pValues.get(e.getVertexA()) + pValues.get(e.getVertexB());
	    e.setLength(c);
	}

	return distances;
    }

}
