package com.algorithms.datastructures.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UndirectedGraph {

	private final Set<Edge> edges = new HashSet<Edge>();
	private final Map<Integer, Set<Edge>> vertices = new HashMap<Integer, Set<Edge>>();

	public Edge addEdge(int vertexA, int vertexB) {
		return addEdge(vertexA, vertexB, 1);
	}

	public Edge addEdge(int vertexA, int vertexB, int cost) {
		Edge e = new Edge(vertexA, vertexB, cost);
		edges.add(e);
		vertices.get(vertexA).add(e);
		vertices.get(vertexB).add(e);
		return e;
	}

	public void addVertex(int vertex) {
		vertices.put(vertex, new HashSet<Edge>());
	}

	public Set<Edge> getEdges() {
		return Collections.unmodifiableSet(edges);
	}

	public Set<Integer> getVertices() {
		return Collections.unmodifiableSet(vertices.keySet());
	}

	public boolean hasEdge(int vertexA, int vertexB) {
		for (Edge e: vertices.get(vertexA)) {
			if (e.isAdjacentTo(vertexB)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPath(int vertexA, int vertexB) {
		// TODO: implement
		throw new RuntimeException("Not implemented");
	}

	public boolean hasVertex(int vertex) {
		return vertices.containsKey(vertex);
	}

	public void removeEdge(Edge e) {
		edges.remove(e);
		vertices.get(e.getVertexA()).remove(e);
		vertices.get(e.getVertexB()).remove(e);
	}

	public void removeVertex(int vertex) {
		Set<Edge> adjacentEdges = vertices.get(vertex);
		edges.removeAll(adjacentEdges);
		vertices.remove(vertex);
	}

}
