package com.algorithms.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HuffmanCoderTest {

    private final Map<Character, Float> alphabet = new HashMap<Character, Float>();

    private HuffmanCoder coder;

    private final Map<String, String> encodedStrings = new HashMap<String, String>();

    {
	alphabet.put('a', 0.32f);
	alphabet.put('b', 0.25f);
	alphabet.put('c', 0.2f);
	alphabet.put('d', 0.18f);
	alphabet.put('e', 0.05f);

	encodedStrings.put("babe", "101110010");
	encodedStrings.put("dad", "01111011");
	encodedStrings.put("decade", "0110100011011010");
    }

    @Before
    public void setUp() throws Exception {
	coder = new HuffmanCoder(alphabet);
    }

    @Test
    public void testDecode() {
	for (Entry<String, String> entry : encodedStrings.entrySet()) {
	    Assert.assertEquals(entry.getKey(), coder.decode(entry.getValue()));
	}
    }

    @Test
    public void testEncode() {
	for (Entry<String, String> entry : encodedStrings.entrySet()) {
	    Assert.assertEquals(entry.getValue(), coder.encode(entry.getKey()));
	}
    }

    @Test
    public void testGetEvarageEncodingLength() {
	Assert.assertTrue(2.23 == coder.getEvarageEncodingLength());
    }

}
