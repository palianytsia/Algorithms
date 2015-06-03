package edu.stanford.algo.greedy;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import edu.stanford.algo.greedy.Prim;
import edu.stanford.algo.structures.graph.Edge;
import edu.stanford.algo.structures.graph.UndirectedGraph;

public class PrimTest {

    private final String dataFileLocation = getClass().getResource("prim.txt").getPath();

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
