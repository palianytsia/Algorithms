import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from
 * items in the data structure.
 * 
 * @see <a href="http://coursera.cs.princeton.edu/algs4/assignments/queues.html">Programming Assignment 2: Randomized
 *      Queues and Deques</a> from "Algorithms, Part I" course by <i>Kevin Wayne</i> and <i>Robert Sedgewick</i> for
 *      detailed data structure description.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 * 
 * @param <Item>
 *            - type of the items to be stored in a queue.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * The capacity (amount of space allocated) of a queue will never shrink lower this value.
     */
    private static final int MIN_SIZE = 1;

    @SuppressWarnings("unchecked")
    private Item[] items = (Item[]) new Object[MIN_SIZE];;

    private int size = 0;;

    /**
     * Deletes and returns a random item from this queue.
     * 
     * @return a random item just removed from this queue.
     * 
     * @throws NoSuchElementException
     *             if this queue has no items.
     */
    public Item dequeue() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Queue is empty.");
	}
	swap(items, size - 1, randomIndex());
	Item item = items[--size];
	if (size > MIN_SIZE && 4 * size <= items.length) {
	    resize(items.length / 2);
	} else {
	    items[size] = null;
	}
	return item;
    };

    /**
     * Inserts the given item to this queue.
     * 
     * @param item
     *            - a new item to add to this queue, can't be null.
     * 
     * @throws NullPointerException
     *             on attempt to insert null to this queue.
     */
    public void enqueue(Item item) {
	if (item == null) {
	    throw new NullPointerException("Null value cannot be inserted to the queue.");
	}
	if (size == items.length) {
	    resize(items.length * 2);
	}
	items[size++] = item;
    }

    /**
     * Checks whether this dequeue is empty.
     * 
     * @return true if this queue is empty, false otherwise.
     */
    public boolean isEmpty() {
	return size == 0;
    }

    /**
     * Returns an independent iterator over the items in random order. The returned iterator <strong>does not
     * support</strong> <code>remove()</code> operation.
     * 
     * @return an iterator over this queue.
     */
    @Override
    public Iterator<Item> iterator() {
	@SuppressWarnings("unchecked")
	Item[] array = (Item[]) new Object[size];
	for (int i = 0; i < size; i++) {
	    array[i] = items[i];
	}
	shuffle(array);
	return new ArrayIterator<Item>(array);
    }

    /**
     * Returns (but not removes) a random item from this queue.
     * 
     * @return a random item from this queue.
     * 
     * @throws NoSuchElementException
     *             if this queue has no items.
     */
    public Item sample() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Queue is empty.");
	}
	return items[randomIndex()];
    };

    /**
     * Retrieves the size of this queue.
     * 
     * @return the number of items on the queue.
     */
    public int size() {
	return size;
    }

    /**
     * Generates a random integer from 0 (inclusive) to size of the queue (exclusive), which then can be used as an
     * index of random item.
     * 
     * @return an index of a random item.
     */
    private int randomIndex() {
	return StdRandom.uniform(size);
    };

    /**
     * Changes size of the underlying array in order to hold more items or to save space.
     * 
     * @param newSize
     *            - new size for the underlying array, can't be lower than the number of items already on this queue.
     * @throws IllegalArgumentException
     *             if the new physical size of the internal storage is lower than logical size of this queue.
     */
    private void resize(int newSize) {
	if (newSize < size) {
	    throw new IllegalArgumentException("New size (" + newSize + ") is not sufficient to hold all the items ("
		    + size + ")");
	}

	@SuppressWarnings("unchecked")
	Item[] newArray = (Item[]) new Object[newSize];
	for (int i = 0; i < size; i++) {
	    if (items[i] != null) {
		newArray[i] = items[i];
	    }
	}
	items = newArray;
    };

    /**
     * Reorders items in the given array in random order.
     * 
     * @param array
     *            - array to shuffle items in.
     */
    private static void shuffle(Object[] array) {
	for (int i = 0; i < array.length; i++) {
	    int r = StdRandom.uniform(i + 1);
	    swap(array, i, r);
	}
    }

    /**
     * Exchanges the values between two array cells.
     * 
     * @param array
     *            - array to swap items in.
     * 
     * @param i
     *            - index of the first cell.
     * 
     * @param j
     *            - index of the second cell.
     */
    private static void swap(Object[] array, int i, int j) {
	if (i != j) {
	    Object object = array[i];
	    array[i] = array[j];
	    array[j] = object;
	}
    }

    /**
     * Provides a way to iterate over items in a randomized queue, which internally is backed up by an array.
     * 
     * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
     * 
     * @param <Item>
     *            - type of the items that can be traversed with this iterator.
     */
    private static class ArrayIterator<Item> implements Iterator<Item> {

	private final Item[] array;
	private int next = 0;

	/**
	 * Constructs a new iterator over the given array.
	 * 
	 * @param array
	 *            - array to create iterator over, can't be null.
	 * @NullPointerException if null is given instead of an array.
	 */
	ArrayIterator(Item[] array) {
	    if (array == null) {
		throw new NullPointerException();
	    }
	    this.array = array;
	}

	@Override
	public boolean hasNext() {
	    return next < array.length;
	}

	@Override
	public Item next() {
	    if (!hasNext()) {
		throw new NoSuchElementException();
	    }
	    return array[next++];
	}

	/**
	 * Unsupported operation, an exception is thrown by a call to this method.
	 * 
	 * @throws UnsupportedOperationException
	 *             whenever this method is called.
	 */
	@Override
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
}
