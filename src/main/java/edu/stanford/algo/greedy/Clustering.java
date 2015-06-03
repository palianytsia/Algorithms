package edu.stanford.algo.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import edu.stanford.algo.structures.graph.Edge;
import edu.stanford.algo.structures.graph.Graph;

/**
 * Clustering is an important form of unsupervised learning (i.e., extracting patterns from unlabeled data). This class demonstrates how Kruskal's MST algorithm
 * suggests flexible and useful greedy approaches to clustering problems.
 * 
 * @see <a href="https://class.coursera.org/algo2-2012-001">Algorithms: Design and Analysis, Part 2</a> by Tim Roughgarden for detailed algorithm description,
 *      analysis and pseudo code.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class Clustering {

    /**
     * Given the graph G, discovers the predefined number of clusters with maximum possible spacing using greedy clustering algorithm that is derivative from
     * Kruskal's MST algorithm. The a
     * 
     * @param g
     *            - graph to compute clustering for.
     * 
     * @param k
     *            - desired number of clusters.
     * 
     * @return the set of clusters.
     */
    public static Set<Cluster> findClusters(Graph g, int k) {
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

	// Initialisation step 2: put each point in its own cluster and create mapping between vertices and clusters
	// they belong to.
	Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>(g.getNumVertices());
	for (Integer v : g.getVertices()) {
	    Cluster cluster = new Cluster(g);
	    cluster.addVertex(v);
	    clusters.put(v, cluster);
	}

	// Until k clusters left, merge the clusters with smallest spacing
	int numClusters = g.getNumVertices();
	while (numClusters > k) {
	    Edge e = edges.remove();
	    Cluster clusterA = clusters.get(e.getVertexA());
	    Cluster clusterB = clusters.get(e.getVertexB());
	    // If vertices are not already in the same cluster - merge clusters
	    if (!clusterA.equals(clusterB)) {
		// To reduce the number of cluster reassigments we want bigger cluster to merge the smaller one.
		Cluster smallerCluster;
		Cluster biggerCluster;
		if (clusterA.size() >= clusterB.size()) {
		    smallerCluster = clusterB;
		    biggerCluster = clusterA;
		} else {
		    smallerCluster = clusterA;
		    biggerCluster = clusterB;
		}
		biggerCluster.merge(smallerCluster);
		for (Integer vertex : smallerCluster.getVertices()) {
		    clusters.put(vertex, biggerCluster);
		}
		numClusters--;
	    }
	}

	return new HashSet<Cluster>(clusters.values());
    }

    /**
     * Compute the spacing property for the given set of clusters. Spacing is equal to the length of the shortest edge that joins different clusters.
     * 
     * @param clusters
     *            - set of cluster to compute spacing for.
     * 
     * @return the real number or plus infinity if there are no edges joining different clusters.
     */
    public static Double getSpacing(Set<Cluster> clusters) {
	Double maxSpacing = Double.POSITIVE_INFINITY;
	for (Cluster a : clusters) {
	    for (Cluster b : clusters) {
		Double distance = a.distanceTo(b);
		if (distance > 0 && distance < maxSpacing) {
		    maxSpacing = distance;
		}
	    }
	}
	return maxSpacing;
    }

}
