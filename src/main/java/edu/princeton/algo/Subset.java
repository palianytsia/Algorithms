import java.util.Collection;
import java.util.Iterator;

/**
 * @see <a href="http://coursera.cs.princeton.edu/algs4/assignments/queues.html">Programming Assignment 2: Randomized
 *      Queues and Deques</a> from "Algorithms, Part I" course by <i>Kevin Wayne</i> and <i>Robert Sedgewick</i> for
 *      detailed description.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 */
public class Subset {

    /**
     * This class contains only static method, so no need to instantiate it.
     */
    private Subset() {

    }

    /**
     * Takes a command-line integer k; reads in a sequence of N strings from standard input using StdIn.readString();
     * and prints out exactly k of them, uniformly at random. Each item from the sequence can be printed out at most
     * once.
     * 
     * @param args
     *            - program arguments where <code>args[0]</code> is size of the subset.
     */
    public static void main(String[] args) {
	int k = Integer.parseInt(args[0]);
	if (k <= 0) {
	    return;
	}

	final RandomizedQueue<String> queue = new RandomizedQueue<String>();
	int n = 0;
	while (!StdIn.isEmpty()) {
	    n++;
	    String element = StdIn.readString();
	    if (queue.size() == k) {
		if (StdRandom.uniform() < 1d * k / n) {
		    queue.dequeue();
		    queue.enqueue(element);
		}
	    } else {
		queue.enqueue(element);
	    }
	}
	while (!queue.isEmpty()) {
	    StdOut.println(queue.dequeue());
	}
    }

    /**
     * Creates a random subset of K items from the given collection.
     * 
     * @param k
     *            - size of the subset, 0 < k <= N, where N is the size of the full set.
     * @param fullSet
     *            - collection containing all the items.
     * @return random subset to iterate over.
     */
    public static Iterable<Integer> subset(int k, Collection<Integer> fullSet) {
	if (k <= 0 || k > fullSet.size()) {
	    throw new IllegalArgumentException();
	}

	RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
	int n = 0;
	Iterator<Integer> iter = fullSet.iterator();
	while (iter.hasNext()) {
	    n++;
	    if (queue.size() == k) {
		if (StdRandom.uniform() < 1d * k / n) {
		    queue.dequeue();
		    queue.enqueue(iter.next());
		} else {
		    iter.next();
		}
	    } else {
		queue.enqueue(iter.next());
	    }
	}

	return queue;
    }
}
