package edu.stanford.algo.greedy;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanCoder {

    private class MetaCharacter {

	private final List<Character> characters = new LinkedList<Character>();
	private float overallProbability = 0;

	public final void addAll(Iterable<Character> characters) {
	    for (Character character : characters) {
		addCharacter(character);
	    }
	}

	public final void addCharacter(Character character) {
	    characters.add(character);
	    overallProbability += alphabet.get(character);
	}

	@Override
	public String toString() {
	    return "MetaCharacter [" + characters + ", " + overallProbability + "]";
	}

    }

    private final Map<Character, Float> alphabet;

    private final float averageEncodingLength;

    private final Map<Character, String> codes;

    /**
     * Generates Huffman codes for the given alphabet.
     * 
     * @param alphabet
     *            - map representing the alphabet to encode, where keys are the characters of the target alphabet, which can be arbitrary objects, and values
     *            are the probabilities of the character appearance in the string to encode (char frequencies); can't be null.
     * 
     * @throws NullPointerException
     *             - if <code>null</code> is given instead of valid alphabet.
     * 
     * @return map, where characters are mapped to their codes.
     */
    public HuffmanCoder(Map<Character, Float> alphabet) {
	if (alphabet == null) {
	    throw new NullPointerException("Expected valid alphabet, null given.");
	}

	this.alphabet = Collections.unmodifiableMap(new HashMap<Character, Float>(alphabet));

	Map<Character, String> codes = new HashMap<Character, String>(alphabet.size());
	if (alphabet.size() == 2) {
	    int i = 0;
	    for (Character c : alphabet.keySet()) {
		codes.put(c, String.valueOf(i));
		i++;
	    }
	} else {
	    PriorityQueue<MetaCharacter> metaAlphabet = new PriorityQueue<MetaCharacter>(alphabet.size(), new Comparator<MetaCharacter>() {
		@Override
		public int compare(MetaCharacter a, MetaCharacter b) {
		    return Float.compare(a.overallProbability, b.overallProbability);
		}
	    });

	    for (Character character : alphabet.keySet()) {
		codes.put(character, "");
		MetaCharacter metaCharacter = new MetaCharacter();
		metaCharacter.addCharacter(character);
		metaAlphabet.add(metaCharacter);
	    }

	    while (metaAlphabet.size() > 1) {
		MetaCharacter a = metaAlphabet.remove();
		MetaCharacter b = metaAlphabet.remove();
		MetaCharacter c = new MetaCharacter();
		c.addAll(a.characters);
		c.addAll(b.characters);
		for (Character character : a.characters) {
		    codes.put(character, "0" + codes.get(character));
		}
		for (Character character : b.characters) {
		    codes.put(character, "1" + codes.get(character));
		}
		metaAlphabet.add(c);
	    }
	}

	float averageEncodingLength = 0;
	for (Character c : alphabet.keySet()) {
	    averageEncodingLength += alphabet.get(c) * codes.get(c).length();
	}

	this.codes = Collections.unmodifiableMap(codes);
	this.averageEncodingLength = averageEncodingLength;
    }

    public String decode(String sequence) {
	StringBuilder decoded = new StringBuilder("");
	while (sequence.length() != 0) {
	    boolean successfulDecoding = false;
	    for (Entry<Character, String> entry : codes.entrySet()) {
		if (sequence.startsWith(entry.getValue())) {
		    decoded.append(entry.getKey());
		    sequence = sequence.substring(entry.getValue().length());
		    successfulDecoding = true;
		    break;
		}
	    }
	    if (!successfulDecoding) {
		throw new IllegalArgumentException("Encoding sequence is either corrupted or invalid and cannot be decoded.");
	    }
	}
	return decoded.toString();
    }

    public String encode(String sequence) {
	StringBuilder encoded = new StringBuilder("");
	for (char c : sequence.toCharArray()) {
	    String code = codes.get(c);
	    if (code == null) {
		throw new IllegalArgumentException("Sequence contains a character, that does not belong to the alphabet this coder is based on: " + c);
	    }
	    encoded.append(code);
	}
	return encoded.toString();
    }

    public Map<Character, String> getCodes() {
	return codes;
    }

    public float getEvarageEncodingLength() {
	return averageEncodingLength;
    }

}
