package com.algorithms.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.algorithms.datastructures.graph.UndirectedGraph;

public class Clustering {

	public static Set<Set<Integer>> findClusters(UndirectedGraph g, int numClusters) {
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for (Integer v: g.getVertices()) {
			Set<Integer> cluster = new HashSet<Integer>();
			cluster.add(v);
			map.put(v, cluster);
		}
		return null;
	}

}
