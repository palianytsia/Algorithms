package com.algorithms.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public abstract class AbstractGraph implements Graph {

    private int lastInsertId = 0;

    protected final Set<Edge> edges = new HashSet<Edge>();
    protected final Map<Integer, Set<Edge>> vertices = new HashMap<Integer, Set<Edge>>();

    /**
     * Default constructor. Creates an empty graph with no vertices and no edges.
     */
    public AbstractGraph() {

    };

    /**
     * Copy constructor. Creates a new <code>Graph</code> instance which is an exact copy of a given one.
     * 
     * @param g
     *            - the graph to copy.
     */
    public AbstractGraph(AbstractGraph g) {
        lastInsertId = g.lastInsertId;
        for (Edge e : g.edges) {
            edges.add(new Edge(e));
        }
        for (Integer v : g.getVertices()) {
            vertices.put(v, new HashSet<Edge>(g.vertices.get(v)));
        }
    }

    @Override
    public void addEdge(int vertexA, int vertexB) {
        addEdge(vertexA, vertexB, 1);
    }

    @Override
    public void addEdge(int vertexA, int vertexB, int length) {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) {
            throw new IllegalArgumentException("One of the vertices: " + vertexA + ", " + vertexB
                    + " is not contained by this graph");
        }
        Edge e = new Edge(vertexA, vertexB, length);
        edges.add(e);
        vertices.get(vertexA).add(e);
        vertices.get(vertexB).add(e);
    }

    @Override
    public int addVertex() {
        lastInsertId++;
        vertices.put(lastInsertId, new HashSet<Edge>());
        return lastInsertId;
    }

    @Override
    public int addVertices(int n) {
        for (int i = 0; i < n; i++) {
            addVertex();
        }
        return lastInsertId;
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        List<Integer> vertices = new ArrayList<Integer>(getVertices());
        Collections.sort(vertices);
        int n = vertices.size();
        int[][] matrix = new int[n + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            Integer v = vertices.get(j - 1);
            matrix[0][j] = matrix[j][0] = v;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || hasEdge(i, j)) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    @Override
    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public int getNumEdges() {
        return edges.size();
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public Set<Integer> getVertices() {
        return Collections.unmodifiableSet(vertices.keySet());
    }

    @Override
    public boolean hasVertex(int vertex) {
        return vertices.containsKey(vertex);
    }

    @Override
    public boolean removeEdge(Edge e) {
        if (edges.remove(e)) {
            vertices.get(e.getVertexA()).remove(e);
            vertices.get(e.getVertexB()).remove(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(int vertex) {
        Set<Edge> adjacentEdges = vertices.get(vertex);
        if (adjacentEdges != null) {
            for (Edge e : adjacentEdges) {
                removeEdge(e);
            }
        }
        return vertices.remove(vertex) != null;
    }

}
