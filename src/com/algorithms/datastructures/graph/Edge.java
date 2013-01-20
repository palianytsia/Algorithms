package com.algorithms.datastructures.graph;

/**
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class Edge {

    private double length = 0;

    private final int vertexA;
    private final int vertexB;

    Edge(Edge e) {
        this.vertexA = e.vertexA;
        this.vertexB = e.vertexB;
        this.length = e.length;
    }

    Edge(int vertexA, int vertexB, double length) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public int getVertexA() {
        return vertexA;
    }

    public int getVertexB() {
        return vertexB;
    }

    public boolean isAdjacentTo(int vertex) {
        return vertexA == vertex || vertexB == vertex;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Edge [" + vertexA + " ->" + vertexB + ",length=" + length + "]";
    }

}
