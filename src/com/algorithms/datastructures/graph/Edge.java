package com.algorithms.datastructures.graph;

public final class Edge {

    private final int cost;
    private final int vertexA;
    private final int vertexB;

    public Edge(int vertexA, int vertexB, int cost) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
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
