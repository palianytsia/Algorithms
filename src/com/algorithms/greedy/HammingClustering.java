package com.algorithms.greedy;

import java.util.BitSet;

/**
 * The clustering algorithm for a MUCH bigger graph. So big, in fact, that the distances (i.e., edge costs) are only
 * defined implicitly, rather than being provided as an explicit list.
 * <p>
 * The distance between two nodes u and v in this problem is defined as the Hamming distance--- the number of differing
 * bits --- between the two nodes' labels. For example, the Hamming distance between the 24-bit label "
 * <code>0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1</code>" and the e 24-bit label "
 * <code>0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1</code>" is 3 (since they differ in the 3rd, 7th, and 21st
 * bits).
 * </p>
 * 
 * TODO: improve this algorithm with greedy approach.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class HammingClustering {

    /**
     * Computes the largest value of k such that there is a k-clustering with the desired spacing. That is, how many
     * clusters are needed to ensure that no pair of nodes with all but <code>desired spacing - 1</code> bits in common
     * get split into different clusters.
     * 
     * @param labels
     *            - list of bit labels associated with each node (id of the corresponding node is label index + 1).
     * 
     * @param spacing
     *            - the desired spacing.
     * 
     * @return the maximum possible number of clusters that preserves the desired spacing.
     */
    public static int getMaxNumberOfClusters(BitSet[] labels, int spacing) {
        if (labels == null || labels.length == 0) {
            throw new IllegalArgumentException();
        }
        if (spacing <= 0) {
            throw new IllegalArgumentException();
        }
        if (spacing == 1) {
            return labels.length;
        }
        BitSet clustered = new BitSet(labels.length);
        int numClusters = 0;
        while (clustered.cardinality() < labels.length) {
            numClusters++;
            int i = clustered.nextClearBit(0);
            markClustered(i, clustered, labels, spacing);
        }
        return numClusters;
    }

    /**
     * Marks the i-th node clustered and recurse on all its unclustered neighbours that are closer than allowed spacing.
     * 
     * @param i
     *            - initial node to add.
     * 
     * @param clustered
     *            - BitSet saying what is already clustered (k-th bit means k-th node is already clustered).
     * 
     * @param labels
     *            - nodes labels.
     * 
     * @param spacing
     *            - desired spacing.
     */
    private static void markClustered(int i, BitSet clustered, BitSet[] labels, int spacing) {
        clustered.set(i);
        BitSet label = labels[i];
        for (int k = clustered.nextClearBit(0); k < labels.length; k = clustered.nextClearBit(k + 1)) {
            BitSet intersection = new BitSet(label.size());
            intersection.or(label);
            intersection.xor(labels[k]);
            if (intersection.cardinality() < spacing) {
                markClustered(k, clustered, labels, spacing);
            }
        }
    }
}
