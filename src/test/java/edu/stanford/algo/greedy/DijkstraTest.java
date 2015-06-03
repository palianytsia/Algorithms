package edu.stanford.algo.greedy;

import java.io.FileNotFoundException;
import java.util.SortedMap;

import org.junit.Assert;
import org.junit.Test;

import edu.stanford.algo.greedy.Dijkstra;
import edu.stanford.algo.structures.graph.DirectedGraph;
import edu.stanford.algo.structures.graph.UndirectedGraph;

public class DijkstraTest {

    private final String dataFileLocation = getClass().getResource("dijkstra.txt").getPath();

    @Test
    public void testDirectedGraphShortestPath() {
	try {
	    DirectedGraph g = DirectedGraph.fromFile(dataFileLocation);
	    SortedMap<Integer, Double> distances = Dijkstra.shortestPath(g, 1);
	    Assert.assertTrue(distances.get(2) == 10);
	    Assert.assertTrue(distances.get(7) == 21);
	    Assert.assertTrue(distances.get(9) == 39);
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
	    Assert.assertTrue(distances.get(2) == 8);
	    Assert.assertTrue(distances.get(7) == 19);
	    Assert.assertTrue(distances.get(9) == 30);
	    System.out.println(distances);
	} catch (FileNotFoundException e) {
	    Assert.fail(e.getMessage());
	}
    }

}
