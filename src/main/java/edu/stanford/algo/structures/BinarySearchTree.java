package edu.stanford.algo.structures;

import java.util.NoSuchElementException;

/**
 * Binary search tree (BST) is a data structure that act like a sorted array (e.g. supports fast min/max/median
 * retrieval, in-order traversal, etc.), but additionally supports fast insert and deletion of elements.
 *
 * <p>
 * Operations supported by this BST:
 * </p>
 * <ul>
 * <li>contains</li>
 * <li>heapify</li>
 * <li>insert/delete</li>
 * <li>min/max</li>
 * <li>output in sorted order</li>
 * <li>rank</li>
 * <li>select</li>
 * </ul>
 *
 * <p>
 * <strong>Careful!</strong> This implementation of subtree is guaranteed to be balanced only after it is created.
 * Insert and delete operation do not maintain the balanced invariant, which means after a number of insert/delete the
 * complexity of search tree operations will increase from O(log(n)) to O(log(height)) where height is the distance
 * between root and last level of the tree.
 * </p>
 *
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 *
 */
public class BinarySearchTree {

    private Node root = null;
    private int size = 0;

    /**
     * Creates a new BST that consists only of root node with a given value.
     *
     * @param rootValue
     *            - the value of the root node in a created tree.
     */
    public BinarySearchTree(int rootValue) {
	this.root = new Node(rootValue);
	this.size = 1;
    }

    /**
     * Creates a new BST from sorted array.
     *
     * <p>
     * Complexity of this operation is O(n).
     * </p>
     *
     * @param values
     *            - values to be inserted to the BST, must be in sorted order.
     */
    public BinarySearchTree(int[] values) {
	this.root = subtree(values, 0, values.length - 1, null);
	this.size = values.length;
    }

    /**
     * Searches for a given value in this BST and returns tree if this value was found.
     *
     * <p>
     * Complexity of this operation is O(log(n)) if this BST is balanced and O(log(height)) if not.
     * </p>
     *
     * @param value
     *            - value to search for.
     *
     * @return true if tree contains the given value, false otherwise.
     */
    public boolean contains(int value) {
	if (root == null) {
	    return false;
	}
	return search(value, root) != null;
    }

    /**
     * Finds and removes the element from this BST having the given value. If this BST does not contain the given value,
     * nothing happens.
     *
     * <p>
     * Complexity of this operation is O(log(n)) if this BST is balanced and O(log(height)) if not.
     * </p>
     *
     * @param value
     *            - value to be removed from the tree. If tree contains several such values, only one of them is
     *            removed.
     *
     * @return true if this BST was modified as a result of delete operation, false otherwise.
     */
    public boolean delete(int value) {
	if (size == 0) {
	    return false;
	}
	Node node = search(value, root);
	if (node == null) {
	    return false;
	}
	cutOut(node);
	// maintain size invariant
	Node ancestor = node.parent;
	while (ancestor != null) {
	    ancestor.subtreeSize--;
	    ancestor = ancestor.parent;
	}
	size--;
	return true;
    }

    /**
     * Inserts a new value to this BST.
     *
     * <p>
     * Complexity of this operation is O(log(n)) if this BST is balanced and O(log(height)) if not.
     * </p>
     *
     * @param value
     *            - value to be inserted.
     */
    public void insert(int value) {
	Node node = new Node(value);
	if (root == null) {
	    root = node;
	} else {
	    insert(root, node);
	}
	size++;
    }

    /**
     * Finds the minimum value stored in this BST.
     *
     * <p>
     * Complexity of this operation is O(log(n)) if this BST is balanced and O(log(height)) if not.
     * </p>
     *
     * @return maximum value stored in this BST.
     *
     *
     * @throws NoSuchElementException
     *             if this BST is empty.
     */
    public int max() {
	if (root == null) {
	    throw new NoSuchElementException("Maximum does not exist for empty BST.");
	}
	return max(root).value;
    }

    /**
     * Finds the minimum value stored in this BST.
     *
     * <p>
     * Complexity of this operation is O(log(n)) if this BST is balanced and O(log(height)) if not.
     * </p>
     *
     * @return minimum value stored in this BST.
     *
     * @throws NoSuchElementException
     *             if this BST is empty.
     */
    public int min() {
	if (root == null) {
	    throw new NoSuchElementException("Minimum does not exist for empty BST.");
	}
	return min(root).value;
    }

    public int rank() {
	throw new UnsupportedOperationException("Not yet supported by current implementation");
    }

    public int select(int i) {
	if (i <= 0 || i > size) {
	    throw new IllegalArgumentException("Order statistic must be a positive number between 1 and BST size.");
	}
	return select(root, i).value;
    }

    /**
     * @return number of elements in this BST.
     */
    public int size() {
	return size;
    }

    /**
     * Converts this BST to sorted array (output in sorted order operation).
     *
     * <p>
     * Complexity of this operation is O(n).
     * </p>
     *
     * @return array containing values stored in this BST in sorted order.
     */
    public int[] toArray() {
	int[] array = new int[size];
	if (root != null) {
	    fill(array, 0, root);
	}
	return array;
    }

    @Override
    public String toString() {
	return toString(root);
    }

    private void cutOut(Node node) {
	if (node.leftChild == null && node.rightChild == null) {
	    // node to be deleted is leaf
	    if (node.parent == null) {
		root = null;
	    } else {
		node.parent.removeChild(node);
	    }
	} else if (node.leftChild == null) {
	    // node to be deleted has only left child - the child assumes the position of deleted node
	    node.rightChild.parent = node.parent;
	    if (node.parent == null) {
		root = node.rightChild;
	    } else {
		node.parent.replaceChild(node, node.rightChild);
	    }

	} else if (node.rightChild == null) {
	    // node to be deleted has only right child - the child assumes the position of deleted node
	    node.leftChild.parent = node.parent;
	    if (node.parent == null) {
		root = node.leftChild;
	    } else {
		node.parent.replaceChild(node, node.leftChild);
	    }
	} else {
	    // node to be deleted has both children - it has to be swapped with its predecessor prior to deletion
	    Node predecessor = predecessor(node);
	    swapValues(node, predecessor);
	    cutOut(predecessor);
	}
    }

    private int fill(int[] array, int i, Node node) {
	if (node.leftChild != null) {
	    i = fill(array, i, node.leftChild);
	}
	array[i] = node.value;
	i++;
	if (node.rightChild != null) {
	    i = fill(array, i, node.rightChild);
	}
	return i;
    }

    private void insert(Node parent, Node child) {
	if (child.value <= parent.value) {
	    if (parent.leftChild != null) {
		insert(parent.leftChild, child);
	    } else {
		parent.leftChild = child;
		child.parent = parent;
	    }
	} else {
	    if (parent.rightChild != null) {
		insert(parent.rightChild, child);
	    } else {
		parent.rightChild = child;
		child.parent = parent;
	    }
	}
	parent.subtreeSize++;
    }

    /**
     * Finds a node having max value in the subtree with root in the given node.
     *
     * @param root
     *            - root of the subtree to find maximum in.
     *
     * @return Node with max value (the right most node in the subtree).
     */
    private Node max(Node root) {
	Node maxNode = root;
	while (maxNode.rightChild != null) {
	    maxNode = maxNode.rightChild;
	}
	return maxNode;
    }

    /**
     * Finds a node having min value in the subtree with root in the given node.
     *
     * @param root
     *            - root of the subtree to find minimum in.
     *
     * @return Node with min value (the left most node in the subtree).
     */
    private Node min(Node root) {
	Node minNode = root;
	while (minNode.leftChild != null) {
	    minNode = minNode.leftChild;
	}
	return minNode;
    }

    private Node predecessor(Node node) {
	if (node.leftChild != null) {
	    return max(node.leftChild);
	}
	Node predecessor = null;
	Node parent = node.parent;
	while (parent != null && predecessor == null) {
	    if (parent.value <= node.value) {
		predecessor = parent;
	    }
	}
	return predecessor;
    }

    private Node search(int value, Node start) {
	if (value < start.value) {
	    if (start.leftChild != null) {
		return search(value, start.leftChild);
	    }
	    return null;
	}
	if (value > start.value) {
	    if (start.rightChild != null) {
		return search(value, start.rightChild);
	    }
	    return null;
	}
	return start;
    }

    private Node select(Node root, int i) {
	int rootOrder = 1 + (root.leftChild != null ? root.leftChild.subtreeSize : 0);
	if (rootOrder > i) {
	    return select(root.leftChild, i);
	}
	if (rootOrder < i) {
	    return select(root.rightChild, i - rootOrder);
	}
	return root;
    }

    private Node subtree(int[] values, int from, int to, Node parent) {
	int middle = (from + to) / 2;
	Node root = new Node(values[middle]);
	root.parent = parent;
	root.subtreeSize = to - from + 1;
	if (from <= middle - 1) {
	    root.leftChild = subtree(values, from, middle - 1, root);

	}
	if (middle + 1 <= to) {
	    root.rightChild = subtree(values, middle + 1, to, root);
	}
	return root;
    }

    @SuppressWarnings("unused")
    private Node successor(Node node) {
	if (node.rightChild != null) {
	    return min(node.rightChild);
	}
	Node successor = null;
	Node parent = node.parent;
	while (parent != null && successor == null) {
	    if (parent.value >= node.value) {
		successor = parent;
	    }
	}
	return successor;
    }

    private void swapValues(Node a, Node b) {
	int temp = a.value;
	a.value = b.value;
	b.value = temp;
    }

    private String toString(Node start) {
	if (start == null) {
	    return "null";
	}
	String str = String.valueOf(start.value);
	if (start.leftChild != null || start.rightChild != null) {
	    str += "(" + toString(start.leftChild) + "," + toString(start.rightChild) + ")";
	}
	return str;
    }

    /**
     * A building block for BST implementation.
     *
     * <p>
     * Except for value, stores the following information in order to assist BST operations:
     * </p>
     * <ul>
     * <li>left child pointer</li>
     * <li>right child pointer</li>
     * <li>parent pointer</li>
     * <li>size of subtree with root in this node</li>
     * </ul>
     *
     * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
     */
    private static class Node {
	private Node leftChild = null;
	private Node rightChild = null;
	private Node parent = null;

	private int value = 0;
	private int subtreeSize = 1;

	public Node(int value) {
	    this.value = value;
	}

	public void removeChild(Node child) {
	    if (child.equals(leftChild)) {
		leftChild = null;
	    } else if (child.equals(rightChild)) {
		rightChild = null;
	    }
	}

	public void replaceChild(Node oldChild, Node newChild) {
	    if (oldChild.equals(leftChild)) {
		leftChild = newChild;
	    } else if (oldChild.equals(rightChild)) {
		rightChild = newChild;
	    }
	}

	@Override
	public String toString() {
	    return "Node [" + value + "]";
	}
    }
}
