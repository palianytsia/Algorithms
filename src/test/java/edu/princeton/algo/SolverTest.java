package edu.princeton.algo;

import org.junit.Test;

public class SolverTest {

    @Test
    public void test4x4puzzle() {
	String path = getClass().getResource("puzzle04.txt").toString();
	Solver.main(new String[] { path });
    }

    @Test
    public void testUnsolvable() {
	String path = getClass().getResource("puzzle-unsolvable3x3.txt").toString();
	Solver.main(new String[] { path });
    }

}
