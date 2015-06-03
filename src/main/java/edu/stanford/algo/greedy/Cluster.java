package edu.stanford.algo.greedy;

import java.util.HashSet;
import java.util.Set;

import edu.stanford.algo.structures.graph.Edge;
import edu.stanford.algo.structures.graph.Graph;

public class Cluster {

    private final Graph g;

    private final Set<Integer> vertices = new HashSet<Integer>();

    public Cluster(Graph g) {
	this.g = g;
    }

    public void addVertex(int vertexId) {
	vertices.add(vertexId);
    }

    public Double distanceTo(Cluster other) {
	if (this.equals(other)) {
	    return 0d;
	}
	Double distance = Double.POSITIVE_INFINITY;
	for (Edge e : getOutgoingEdges()) {
	    if ((other.vertices.contains(e.getVertexA()) || other.vertices.contains(e.getVertexB())) && e.getLength() < distance) {
		distance = e.getLength();
	    }
	}
	return distance;
    }

    public Set<Integer> getVertices() {
	return vertices;
    }

    public void merge(Cluster other) {
	if (!this.g.equals(other.g)) {
	    throw new IllegalArgumentException();
	}
	this.vertices.addAll(other.vertices);
    }

    public void removeVertex(int vertexId) {
	vertices.remove(vertexId);
    }

    public int size() {
	return vertices.size();
    }

    @Override
    public String toString() {
	return "Cluster [vertices=" + vertices + "]";
    }

    private Set<Edge> getOutgoingEdges() {
	Set<Edge> outgoingEdges = new HashSet<Edge>();
	for (Integer v : vertices) {
	    for (Edge e : g.getOutgoingEdges(v)) {
		if (!vertices.contains(e.getOtherVertex(v))) {
		    outgoingEdges.add(e);
		}
	    }
	}
	return outgoingEdges;
    }
}
