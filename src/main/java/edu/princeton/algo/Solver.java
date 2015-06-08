package edu.princeton.algo;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

public class Solver {
    private static class SearchNode implements Comparable<SearchNode> {
	private final Board board;
	private final SearchNode previous;
	private final int moves;

	SearchNode(Board board, SearchNode previous) {
	    this.board = board;
	    this.previous = previous;
	    this.moves = previous != null ? previous.moves + 1 : 0;
	}

	@Override
	public int compareTo(SearchNode other) {
	    int thisPriority = this.board.manhattan() + this.moves;
	    int otherPriority = other.board.manhattan() + other.moves;
	    return thisPriority - otherPriority;
	}

	public boolean isSolution() {
	    return board.isGoal();
	}

	public List<SearchNode> moves() {
	    List<SearchNode> moves = new LinkedList<SearchNode>();
	    for (Board neighbor : board.neighbors()) {
		if (previous == null || !previous.board.equals(neighbor)) {
		    moves.add(new SearchNode(neighbor, this));
		}
	    }
	    return moves;
	}

	public List<Board> trace() {
	    LinkedList<Board> trace = new LinkedList<Board>();
	    SearchNode node = this;
	    while (node != null) {
		trace.addFirst(node.board);
		node = node.previous;
	    }
	    return trace;
	}
    }

    private SearchNode solution = null;

    /** find a solution to the initial board (using the A* algorithm) */
    public Solver(Board board) {
	MinPQ<SearchNode> gameTree = new MinPQ<SearchNode>();
	gameTree.insert(new SearchNode(board, null));

	MinPQ<SearchNode> twinGameTree = new MinPQ<SearchNode>();
	twinGameTree.insert(new SearchNode(board.twin(), null));

	while (solution == null) {
	    SearchNode searchNode = gameTree.delMin();
	    if (searchNode.isSolution()) {
		solution = searchNode;
	    } else {
		for (SearchNode move : searchNode.moves()) {
		    gameTree.insert(move);
		}
	    }

	    SearchNode twinSearchNode = twinGameTree.delMin();
	    if (twinSearchNode.isSolution()) {
		// no possible solution
		break;
	    } else {
		for (SearchNode move : twinSearchNode.moves()) {
		    twinGameTree.insert(move);
		}
	    }
	}
    };

    /** is the initial board solvable? */
    public boolean isSolvable() {
	return solution != null;
    };

    /** min number of moves to solve initial board; -1 if unsolvable */
    public int moves() {
	if (solution == null) {
	    return -1;
	}
	return solution.moves;
    };

    /** sequence of boards in a shortest solution; null if unsolvable */
    public Iterable<Board> solution() {
	if (solution == null) {
	    return null;
	}
	return solution.trace();
    };

    /** solve a slider puzzle */
    public static void main(String[] args) {

	// create initial board from file
	In in = new In(args[0]);
	int N = in.readInt();
	int[][] blocks = new int[N][N];
	for (int i = 0; i < N; i++)
	    for (int j = 0; j < N; j++)
		blocks[i][j] = in.readInt();
	Board initial = new Board(blocks);

	// solve the puzzle
	Solver solver = new Solver(initial);

	// print solution to standard output
	if (!solver.isSolvable())
	    StdOut.println("No solution possible");
	else {
	    StdOut.println("Minimum number of moves = " + solver.moves());
	    for (Board board : solver.solution())
		StdOut.println(board);
	}
    }
}
