package edu.princeton.algo;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque is a generalization of a stack and a queue that supports inserting and removing items from either the front or the back of the
 * data structure.
 * 
 * <p>
 * Internally uses a linked list to store the data.
 * </p>
 * 
 * @see <a href="http://coursera.cs.princeton.edu/algs4/assignments/queues.html">Programming Assignment 2: Randomized Queues and Deques</a> from
 *      "Algorithms, Part I" course by <i>Kevin Wayne</i> and <i>Robert Sedgewick</i> for detailed data structure description.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 * 
 * @param <Item>
 *            - type of the items to be stored in a deque.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int size = 0;

    /**
     * Checks whether this deque is empty.
     * 
     * @return true if this deque is empty, false otherwise.
     */
    public boolean isEmpty() {
	return size == 0;
    }

    /**
     * Retrieves the size of this deque.
     * 
     * @return the number of items on this deque.
     */
    public int size() {
	return size;
    }

    /**
     * Inserts the given item at the front of this deque.
     * 
     * @param item
     *            - item to insert to this deque, can't be null.
     * 
     * @throws NullPointerException
     *             on attempt to insert null to this deque.
     */
    public void addFirst(Item item) {
	if (item == null) {
	    throw new NullPointerException("Null value cannot be inserted to the deque.");
	}
	if (isEmpty()) {
	    head = new Node<Item>(item);
	    tail = head;
	} else {
	    Node<Item> oldHead = head;
	    head = new Node<Item>(item);
	    head.next = oldHead;
	    oldHead.previous = head;
	}
	size++;
    }

    /**
     * Inserts the given item at the end of this deque.
     * 
     * @param item
     *            - item to insert to this deque.
     * @throws NullPointerException
     *             on attempt to insert null to this deque.
     */
    public void addLast(Item item) {
	if (item == null) {
	    throw new NullPointerException("Null value cannot be inserted to the deque.");
	}
	if (isEmpty()) {
	    tail = new Node<Item>(item);
	    head = tail;
	} else {
	    Node<Item> oldTail = tail;
	    tail = new Node<Item>(item);
	    tail.previous = oldTail;
	    oldTail.next = tail;
	}
	size++;
    }

    /**
     * Deletes and returns the item at the front of this deque.
     * 
     * @return the head of this deque.
     * 
     * @throws NoSuchElementException
     *             if this deque has no items.
     */
    public Item removeFirst() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Cannot remove item from an empty deque.");
	}
	Item item = head.value;
	if (size == 1) {
	    head = null;
	    tail = null;
	} else {
	    head = head.next;
	    head.previous = null;
	}
	size--;
	return item;
    }

    /**
     * Deletes and returns the item at the end of this deque.
     * 
     * @return the tail of this deque.
     * 
     * @throws NoSuchElementException
     *             if this deque has no items.
     */
    public Item removeLast() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Cannot remove item from an empty deque.");
	}
	Item item = tail.value;
	if (size == 1) {
	    head = null;
	    tail = null;
	} else {
	    tail = tail.previous;
	    tail.next = null;
	}
	size--;
	return item;
    }

    /**
     * Returns an iterator over the items in order from front to end. The returned iterator <strong>does not support</strong> <code>remove()</code> operation.
     * 
     * @return an iterator over this deque.
     */
    @Override
    public Iterator<Item> iterator() {
	return new LinkedListIterator<Item>(head);
    }

    /**
     * A basic component of a linked list, which in turn is an underlying data structure for the deque.
     * 
     * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
     * 
     * @param <Item>
     *            - type of the values that linked list built with this nodes can hold.
     */
    private static class Node<Item> {

	final Item value;

	Node<Item> next = null;
	Node<Item> previous = null;

	/**
	 * Constructs new linked list node with a given value.
	 * 
	 * @param value
	 *            - a value to be stored in a linked list.
	 */
	Node(Item value) {
	    this.value = value;
	}
    }

    /**
     * Provides a way to iterate over items in a deque, which internally is backed up by a linked list.
     * 
     * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
     * 
     * @param <Item>
     *            - type of the items that can be traversed with this iterator.
     */
    private static class LinkedListIterator<Item> implements Iterator<Item> {

	Node<Item> next;

	/**
	 * Given a pointer to linked list's head item constructs a new iterator over this linked list.
	 * 
	 * @param head
	 *            - first item in the linked list, can't be null.
	 * @NullPointerException if the given pointer to linked list's head is null.
	 */
	LinkedListIterator(Node<Item> head) {
	    if (head == null) {
		throw new NullPointerException();
	    }
	    this.next = head;
	}

	@Override
	public boolean hasNext() {
	    return next != null;
	}

	@Override
	public Item next() {
	    if (!hasNext()) {
		throw new NoSuchElementException();
	    }
	    Item item = next.value;
	    next = next.next;
	    return item;
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
