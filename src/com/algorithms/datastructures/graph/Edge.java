package com.algorithms.datastructures.graph;

public class Edge {

    private final int length;
    private final int vertexA;
    private final int vertexB;

    Edge(int vertexA, int vertexB, int length) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.length = length;
    }

    public int getLength() {
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

}
