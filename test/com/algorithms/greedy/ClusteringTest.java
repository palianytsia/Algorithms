package com.algorithms.greedy;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.algorithms.datastructures.graph.Graph;
import com.algorithms.datastructures.graph.UndirectedGraph;

public class ClusteringTest {

    private Graph g;

    private final String dataFileLocation = "test\\data\\greedy\\clustering1.txt";

    @Before
    public void setUp() throws Exception {
        g = UndirectedGraph.fromFile(dataFileLocation);
    }

    @Test
    public void testClustering() {
        Set<Cluster> clusters = Clustering.findClusters(g, 4);

        Assert.assertEquals(4, clusters.size());

        int totalSize = 0;
        for (Cluster cluster : clusters) {
            totalSize += cluster.size();
        }
        Assert.assertEquals(g.getNumVertices(), totalSize);

        Double maxSpacing = Clustering.getSpacing(clusters);
        Assert.assertEquals(0, maxSpacing.compareTo(106d));
    }

}
