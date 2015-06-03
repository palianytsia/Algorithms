package com.algorithms;

import java.io.FileNotFoundException;
import java.util.SortedMap;

import org.junit.Assert;
import org.junit.Test;

import com.algorithms.datastructures.graph.DirectedGraph;

public class JonsonTest {

    private final String graphFileLocation = "test\\data\\jonson.txt";
    private final String negativeCycleGraphFileLocation = "test\\data\\jonson-negative-cycle.txt";

    @Test
    public void testAllPairsShortestPaths() {
        Double minShortestPathDistance = Double.POSITIVE_INFINITY;
        try {
            DirectedGraph g = DirectedGraph.fromFile(graphFileLocation);
            SortedMap<Integer, SortedMap<Integer, Double>> allPairsShortestPaths = Jonson.allPairsShortestPaths(g);
            if (allPairsShortestPaths != null) {
                for (Integer u : allPairsShortestPaths.keySet()) {
                    SortedMap<Integer, Double> uDistances = allPairsShortestPaths.get(u);
                    for (Integer v : uDistances.keySet()) {
                        Double distance = uDistances.get(v);
                        if (distance < minShortestPathDistance) {
                            minShortestPathDistance = distance;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(19 == minShortestPathDistance);
    }

    @Test
    public void testNegativeCycleDetection() {
        try {
            DirectedGraph g = DirectedGraph.fromFile(negativeCycleGraphFileLocation);
            SortedMap<Integer, SortedMap<Integer, Double>> allPairsShortestPaths = Jonson.allPairsShortestPaths(g);
            Assert.assertNull(allPairsShortestPaths);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }
}
