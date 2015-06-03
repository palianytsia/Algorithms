package com.algorithms.datastructures.graph;

public class GraphUtils {

    public static void printAdjencyMatrix(Graph g) {
	int[][] matrix = g.getAdjacencyMatrix();
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix.length; j++) {
		System.out.printf("%3d", matrix[i][j]);
	    }
	    System.out.println();
	}
    }

}
