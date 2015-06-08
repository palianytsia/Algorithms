package edu.princeton.algo;

import org.junit.Test;

public class BruteTest {

    @Test
    public void test6() {
	String path = getClass().getResource("input6.txt").toString();
	Brute.main(new String[] { path });
    }

    @Test
    public void test8() {
	String path = getClass().getResource("input8.txt").toString();
	Brute.main(new String[] { path });
    }

}
