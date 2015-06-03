package edu.stanford.algo.structures.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class DirectedGraph extends AbstractGraph {

    /**
     * Creates new <DirectedGraph> instance based on its description stored in a plain text data file.
     * 
     * <p>
     * The data file should be in the following format:
     * <ul>
     * <li>The first line indicates the number of vertices and edges, respectively.</li>
     * <li>Each subsequent line describes an edge (the first two numbers are its tail and head, respectively) and its length (the third number).</li>
     * <li>E.g. <br>
     * [number_of_nodes] [number_of_edges] <br>
     * [edge 1 node 1] [edge 1 node 2] [edge 1 cost] <br>
     * [edge 2 node 1] [edge 2 node 2] [edge 2 cost]</li>
     * </ul>
     * </p>
     * 
     * @param filePath
     *            - path to the data files describing the graph to create.
     * 
     * @throws FileNotFoundException
     *             - if there is no graph data file at the specified path.
     */
    public static DirectedGraph fromFile(String filePath) throws FileNotFoundException {
	File dataFile = new File(filePath);
	Scanner scanner = new Scanner(dataFile);
	DirectedGraph g = new DirectedGraph();

	// number of vertices
	int n = scanner.nextInt();
	// number of edges
	int m = scanner.nextInt();

	g.addVertices(n);
	for (int i = 0; i < m; i++) {
	    int source = scanner.nextInt();
	    int destination = scanner.nextInt();
	    int length = scanner.nextInt();
	    g.addEdge(source, destination, length);
	}
	scanner.close();
	return g;
    }

    /**
     * Default constructor. Creates an empty directed graph with no vertices and no edges.
     */
    public DirectedGraph() {

    }

    /**
     * Copy constructor. Creates a new <code>DirectedGraph</code> instance which is an exact copy of a given one.
     * 
     * @param g
     *            - the directed graph to copy.
     */
    public DirectedGraph(DirectedGraph g) {
	super(g);
    }

    @Override
    public Set<Edge> getIncomingEdges(int vertex) {
	Set<Edge> adjacentEdges = vertices.get(vertex);
	Set<Edge> incomingEdges = new HashSet<Edge>();
	for (Edge e : adjacentEdges) {
	    if (e.getVertexB() == vertex) {
		incomingEdges.add(e);
	    }
	}
	return incomingEdges;
    }

    @Override
    public Set<Edge> getOutgoingEdges(int vertex) {
	Set<Edge> adjacentEdges = vertices.get(vertex);
	Set<Edge> outgoingEdges = new HashSet<Edge>();
	for (Edge e : adjacentEdges) {
	    if (e.getVertexA() == vertex) {
		outgoingEdges.add(e);
	    }
	}
	return outgoingEdges;
    }

    @Override
    public boolean hasEdge(int vertexA, int vertexB) {
	Set<Edge> adjacentEdges = vertices.get(vertexA);
	if (adjacentEdges != null) {
	    for (Edge e : adjacentEdges) {
		if (e.getVertexA() == vertexA && e.getVertexB() == vertexB) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public boolean hasPath(int vertexA, int vertexB) {
	// TODO: implement
	throw new UnsupportedOperationException("Not implemented");
    }

}
