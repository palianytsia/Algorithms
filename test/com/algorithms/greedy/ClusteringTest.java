package com.algorithms.greedy;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.algorithms.datastructures.graph.Graph;
import com.algorithms.datastructures.graph.UndirectedGraph;

public class ClusteringTest {

    private Graph g;

    private final String dataFileLocation = "test\\data\\clustering1.txt";

    @Before
    public void setUp() throws Exception {
        g = UndirectedGraph.fromFile(dataFileLocation);
    }

    @Test
    public void testFindClusters() {
        Clustering.findClusters(g, 4);
        Assert.fail("Not yet implemented.");
    }

}
