package com.algorithms.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.algorithms.datastructures.graph.Edge;
import com.algorithms.datastructures.graph.Graph;

public class Clustering {

    public static Set<Set<Integer>> findClusters(Graph g, int k) {
        // Initialisation step 1: sort edges by their weight
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(g.getEdges().size(), new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                if (a.getLength() < b.getLength()) {
                    return -1;
                } else if (a.getLength() > b.getLength()) {
                    return 1;
                }
                return 0;
            }
        });
        edges.addAll(g.getEdges());

        // Initialisation step 2: put each point in its own cluster
        Map<Integer, Set<Integer>> clusters = new HashMap<Integer, Set<Integer>>();
        for (Integer v : g.getVertices()) {
            Set<Integer> cluster = new HashSet<Integer>();
            cluster.add(v);
            clusters.put(v, cluster);
        }

        // Until k clusters left, merge the clusters with smallest spacing
        int numClusters = clusters.size();
        while (numClusters > k) {
            Edge e = edges.remove();
            Set<Integer> clusterA = clusters.get(e.getVertexA());
            Set<Integer> clusterB = clusters.get(e.getVertexB());
            // If vertices are not already in the same cluster - merge clusters
            if (!clusterA.equals(clusterB)) {
                if (clusterA.size() > clusterB.size()) {
                    clusterA.addAll(clusterB);
                    for (Integer v : clusterB) {
                        clusters.put(v, clusterA);
                    }
                } else {
                    clusterB.addAll(clusterA);
                    for (Integer v : clusterA) {
                        clusters.put(v, clusterB);
                    }
                }
                numClusters--;
            }
        }

        return new HashSet<Set<Integer>>(clusters.values());
    }

}
