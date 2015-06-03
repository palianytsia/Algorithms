package edu.princeton.algo;

import org.junit.Test;

public class FastTest {

	@Test
	public void test6() {
		String path = getClass().getResource("input6.txt").toString();
		Fast.main(new String[] { path });
	}
	
	@Test
	public void test8() {
		String path = getClass().getResource("input8.txt").toString();
		Fast.main(new String[] { path });
	}

}
