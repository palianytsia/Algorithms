package com.algorithms.greedy;

import java.io.FileNotFoundException;
import java.util.SortedMap;

import junit.framework.Assert;

import org.junit.Test;

import com.algorithms.datastructures.graph.DirectedGraph;
import com.algorithms.datastructures.graph.UndirectedGraph;

public class DijkstraTest {

    private final String dataFileLocation = "test\\data\\greedy\\dijkstra.txt";

    @Test
    public void testDirectedGraphShortestPath() {
        try {
            DirectedGraph g = DirectedGraph.fromFile(dataFileLocation);
            SortedMap<Integer, Double> distances = Dijkstra.shortestPath(g, 1);
            Assert.assertEquals(10d, distances.get(2));
            Assert.assertEquals(21d, distances.get(7));
            Assert.assertEquals(39d, distances.get(9));
            System.out.println(distances);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testUndirectedGraphShortestPath() {
        try {
            UndirectedGraph g = UndirectedGraph.fromFile(dataFileLocation);
            SortedMap<Integer, Double> distances = Dijkstra.shortestPath(g, 1);
            Assert.assertEquals(8d, distances.get(2));
            Assert.assertEquals(19d, distances.get(7));
            Assert.assertEquals(30d, distances.get(9));
            System.out.println(distances);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

}
