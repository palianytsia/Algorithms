package com.algorithms.greedy;

import java.util.SortedMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.algorithms.datastructures.graph.DirectedGraph;
import com.algorithms.datastructures.graph.UndirectedGraph;

public class DijkstraTest {

    private DirectedGraph directedGraph = null;
    private UndirectedGraph undirectedGraph = null;

    private final String dataFileLocation = "test\\data\\greedy\\dijkstra.txt";

    @Before
    public void setUp() throws Exception {
        directedGraph = DirectedGraph.fromFile(dataFileLocation);
        undirectedGraph = UndirectedGraph.fromFile(dataFileLocation);
    }

    @Test
    public void testDirectedGraphShortestPath() {
        SortedMap<Integer, Double> distances = Dijkstra.shortestPath(directedGraph, 1);
        Assert.assertEquals(10d, distances.get(2));
        Assert.assertEquals(21d, distances.get(7));
        Assert.assertEquals(39d, distances.get(9));
        System.out.println(distances);
    }

    @Test
    public void testUndirectedGraphShortestPath() {
        SortedMap<Integer, Double> distances = Dijkstra.shortestPath(undirectedGraph, 1);
        Assert.assertEquals(8d, distances.get(2));
        Assert.assertEquals(19d, distances.get(7));
        Assert.assertEquals(30d, distances.get(9));
        System.out.println(distances);
    }

}
