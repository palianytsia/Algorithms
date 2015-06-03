package edu.stanford.algo.greedy;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.BitSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import edu.stanford.algo.greedy.Cluster;
import edu.stanford.algo.greedy.Clustering;
import edu.stanford.algo.greedy.HammingClustering;
import edu.stanford.algo.structures.graph.Graph;
import edu.stanford.algo.structures.graph.UndirectedGraph;

public class ClusteringTest {

    private final URL dataFile1 = getClass().getResource("clustering1.txt"); 
    private final URL dataFile2 = getClass().getResource("clustering2.txt");

    @Test
    public void testClustering() {
	try {
	    Graph g = UndirectedGraph.fromFile(dataFile1.getPath());
	    Set<Cluster> clusters = Clustering.findClusters(g, 4);

	    Assert.assertEquals(4, clusters.size());

	    int totalSize = 0;
	    for (Cluster cluster : clusters) {
		totalSize += cluster.size();
	    }
	    Assert.assertEquals(g.getNumVertices(), totalSize);

	    Double maxSpacing = Clustering.getSpacing(clusters);
	    Assert.assertEquals(0, maxSpacing.compareTo(106d));
	} catch (FileNotFoundException e) {
	    Assert.fail(e.getMessage());
	}
    }

    @Test
    public void testGetMaxNumberOfClusters() {
	try {
	    File dataFile = new File(dataFile2.getPath());
	    Scanner scanner = new Scanner(dataFile);

	    // number of nodes
	    int n = scanner.nextInt();
	    // number of bit per label
	    int k = scanner.nextInt();

	    BitSet[] labels = new BitSet[n];

	    for (int i = 0; i < n; i++) {
		BitSet label = new BitSet(k);
		for (int j = 0; j < k; j++) {
		    byte bit = scanner.nextByte();
		    if (bit == 1) {
			label.set(j);
		    }
		}
		labels[i] = label;
	    }

	    scanner.close();

	    int maxNumberOfClusters = HammingClustering.getMaxNumberOfClusters(labels, 3);
	    Assert.assertEquals(16508, maxNumberOfClusters);

	} catch (FileNotFoundException e) {
	    Assert.fail(e.getMessage());
	}
    }

}
