package com.algorithms.greedy;

import java.io.FileNotFoundException;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.algorithms.datastructures.graph.Edge;
import com.algorithms.datastructures.graph.UndirectedGraph;

public class PrimTest {

    private final String dataFileLocation = "test\\data\\greedy\\prim.txt";

    @Test
    public void testGetMinimumSpanningTree() {
        try {
            UndirectedGraph g = UndirectedGraph.fromFile(dataFileLocation);
            Set<Edge> mst = Prim.getMinimumSpanningTree(g);
            Assert.assertNotNull(mst);
            Assert.assertFalse("Tree doesn't contain any edges", mst.isEmpty());
            Assert.assertEquals(-3612829L, getTreeCost(mst));
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    private long getTreeCost(Set<Edge> tree) {
        long cost = 0;
        for (Edge edge : tree) {
            cost += edge.getLength();
        }
        return cost;
    }

}
