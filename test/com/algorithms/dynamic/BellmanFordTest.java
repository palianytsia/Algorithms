package com.algorithms.dynamic;

import java.io.FileNotFoundException;
import java.util.SortedMap;

import junit.framework.Assert;

import org.junit.Test;

import com.algorithms.datastructures.graph.DirectedGraph;

public class BellmanFordTest {

    private final String graphFileLocation = "test\\data\\dynamic\\bellman-ford.txt";
    private final String negativeCostGraphFileLocation = "test\\data\\dynamic\\bellman-ford-negative-cost.txt";
    private final String negativeCycleGraphFileLocation = "test\\data\\dynamic\\bellman-ford-negative-cycle.txt";

    @Test
    public void testNegativeCostShortestPath() {
        try {
            DirectedGraph g = DirectedGraph.fromFile(negativeCostGraphFileLocation);
            SortedMap<Integer, Double> distances = BellmanFord.shortestPath(g, 1);
            Assert.assertEquals(5d, distances.get(2));
            Assert.assertEquals(11d, distances.get(7));
            Assert.assertEquals(29d, distances.get(9));
            System.out.println(distances);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNegativeCycleDetection() {
        try {
            DirectedGraph g = DirectedGraph.fromFile(negativeCycleGraphFileLocation);
            SortedMap<Integer, Double> distances = BellmanFord.shortestPath(g, 1);
            Assert.assertNull(distances);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testShortestPath() {
        try {
            DirectedGraph g = DirectedGraph.fromFile(graphFileLocation);
            SortedMap<Integer, Double> distances = BellmanFord.shortestPath(g, 1);
            Assert.assertEquals(10d, distances.get(2));
            Assert.assertEquals(21d, distances.get(7));
            Assert.assertEquals(39d, distances.get(9));
            System.out.println(distances);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

}
