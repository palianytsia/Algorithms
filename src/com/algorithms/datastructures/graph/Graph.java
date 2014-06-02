package com.algorithms.datastructures.graph;

import java.util.Set;

/**
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public interface Graph {

    /**
     * Connects vertices A and B by a new edge of length 1.
     * 
     * <p>
     * If A and B are already connected, a new edge (parallel to already existing ones) will be added. For undirected graphs adding and edge (A, B) is
     * equivalent to adding edge (B, A), so {@link #hasEdge(int, int)} method will return true for any order of arguments.
     * </p>
     * 
     * @param vertexA
     *            - id of the source vertex.
     * @param vertexB
     *            - id of the destination vertex.
     * 
     * @throws IllegalArgumentException
     *             If this graph doesn't contain the vertex with id specified for a source or a destination vertex.
     */
    public void addEdge(int vertexA, int vertexB);

    /**
     * Connects vertices A and B by a new edge of length 1.
     * 
     * <p>
     * If A and B are already connected, a new edge (parallel to already existing ones) will be added. For undirected graphs adding and edge (A, B) is
     * equivalent to adding edge (B, A), so {@link #hasEdge(int, int)} method will return true for any order of arguments.
     * </p>
     * 
     * @param vertexA
     *            - id of the source vertex.
     * @param vertexB
     *            - id of the destination vertex.
     * 
     * @throws IllegalArgumentException
     *             If this graph doesn't contain the vertex with id specified for a source or a destination vertex.
     */
    public void addEdge(int vertexA, int vertexB, int length);

    /**
     * Appends a new vertex to this graph.
     * 
     * @return Identifier of a newly added vertex.
     */
    public int addVertex();

    /**
     * Gets an adjacency matrix of this graph.
     * 
     * <p>
     * The adjacency matrix is represented as array of integers, where first row and first column contain vertices
     * identifiers, and for every i,j > 0, matrix[i][j] contains 1 if there is an edge from vertex with id specified in
     * matrix[i][0] to vertex with id specified in matrix[0][j], 0 otherwise. It is assumed that each vertex is
     * reachable from itself even with no edges, so for every k > 0 matrix[k][k] = 1.
     * </p>
     * 
     * <p>
     * For example, an adjacency matrix of a undirected graph containing 4 vertices, connected with each other as a
     * rectangle looks as follows:
     * </p>
     * 
     * <table>
     * <tr><td>0</td><td>1</td><td>2</td><td>3</td><td>4</td></tr>
     * <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>0</td></tr>
     * <tr><td>2</td><td>1</td><td>1</td><td>0</td><td>1</td></tr>
     * <tr><td>3</td><td>1</td><td>0</td><td>1</td><td>1</td></tr>
     * <tr><td>4</td><td>0</td><td>1</td><td>1</td><td>1</td></tr>
     * </table>
     * 
     * @return int array representing adjacency matrix of this graph.
     */
    public int[][] getAdjacencyMatrix();

    /**
     * Retrieves set of edges contained by this graph.
     * 
     * @return Set of edges.
     */
    public Set<Edge> getEdges();

    /**
     * Retrieves the incoming edges for a given vertex. For the undirected graph the set of incoming edges is exactly the same as the set of outgoing edges.
     * 
     * @param vertex
     *            - id of the vertex to retrieve incoming edges for.
     * 
     * @return Set of incoming edges (if any), empty set (if there are no incoming edges) or null (if graph doesn't contain a vertex with such id).
     */
    public Set<Edge> getIncomingEdges(int vertex);

    /**
     * Retrieves number of edges contained by this graph.
     * 
     * @return number of edges.
     */
    public int getNumEdges();

    /**
     * Retrieves number of vertices contained by this graph.
     * 
     * @return number of vertices.
     */
    public int getNumVertices();

    /**
     * Retrieves the outgoing edges for a given vertex. For the undirected graph the set of outgoing edges is exactly the same as the set of outgoing edges.
     * 
     * @param vertex
     *            - id of the vertex to retrieve incoming edges for.
     * 
     * @return Set of outgoing edges (if any), empty set (if there are no outgoing edges) or null (if graph doesn't contain a vertex with such id).
     */
    public Set<Edge> getOutgoingEdges(int vertex);

    /**
     * Retrieves set of vertices contained by this graph.
     * 
     * @return Set of vertices.
     */
    public Set<Integer> getVertices();

    /**
     * Checks whether there is an edge between two vertices of this graph.
     * 
     * @param vertexA
     *            - id of the source vertex.
     * @param vertexB
     *            - id of the destination vertex.
     * 
     * @return True if there is an edge from A to B (for undirected graphs - if A and B are adjacent), false otherwise.
     */
    public boolean hasEdge(int vertexA, int vertexB);

    /**
     * Check whether there is a path from vertex A to vertex B.
     * 
     * @param vertexA
     *            - id of the source vertex.
     * @param vertexB
     *            - id of the destination vertex.
     * 
     * @return True if there exists a path from A to B, false otherwise.
     */
    public boolean hasPath(int vertexA, int vertexB);

    /**
     * Checks whether a particular vertex belongs to this graph.
     * 
     * @param vertexId
     *            - id of a vertex to check.
     * 
     * @return True if this graph contains the given vertex, false otherwise.
     */
    public boolean hasVertex(int vertexId);

    /**
     * Removes an edge from this graph.
     * 
     * @param e
     *            - an edge to be deleted, if this graph does not contain such an edge, then "remove" operation is skipped.
     * 
     * @return True if this graph contained the given edge, false otherwise.
     */
    public boolean removeEdge(Edge e);

    /**
     * Removes a vertex together with all its adjacent edges from this graph.
     * 
     * @param vertex
     *            - id of the vertex to be deleted, if this graph does not contain a vertex with such an id, then "remove" operation is skipped.
     * 
     * @return True if this graph contained the given vertex, false otherwise.
     */
    public boolean removeVertex(int vertex);
}
