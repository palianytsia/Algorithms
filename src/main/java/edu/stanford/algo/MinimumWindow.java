package edu.stanford.algo;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Find substring of minimum size (minimum window) that contains given set of characters.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia</a>
 * 
 */
public class MinimumWindow {

    /**
     * Computes minimum window using brute force search: tries every possible substring of length from m to n, where m is number of characters in the query and
     * n is number of characters in the string.
     * 
     * <p>
     * The complexity of this search method is {@code O(n^3)}: it walks through at most {@code n*(n-1)/2} subtrings that altogether contain
     * {@code (n^3 + 3*n^2 + 2*n) / 6} characters. The worst running time is approached when query consists of a single character and string does not contain
     * this character.
     * </p>
     * 
     * @param string
     *            - string to perform search in.
     * @param query
     *            - set of characters, the desired substring has to contain.
     * @return minimum size substring that contains given set of characters or null when there is no such substring.
     */
    public static String exhaustiveSearch(String string, Set<Character> query) {
	if (string.length() < query.size()) {
	    return null;
	}
	int substrings = 0;
	int totalLength = 0;
	for (int candidateLength = query.size(); candidateLength <= string.length(); candidateLength++) {
	    for (int i = 0; i <= string.length() - candidateLength; i++) {
		String candidate = string.substring(i, i + candidateLength);
		substrings++;
		totalLength += candidate.length();
		if (contains(candidate, query)) {
		    System.out.println("Walked through " + substrings + " substring(s) of total length " + totalLength + ".");
		    return candidate;
		}
	    }
	}
	System.out.println("Walked through " + substrings + " substring(s) of total length " + totalLength + ".");
	return null;
    }

    /**
     * Computes minimum window using inverted indices.
     * 
     * <p>
     * The complexity of this search method is {@code O(n*log(m))}: the preprocessing step (creation of inverted indices) takes {@code O(n)} operations and
     * computing minimum window using inverted indices takes {@code O(n*log(m))} operations, where m is number of characters in the query and n is number of
     * characters in the string. The worst running time is approached when string consists only with characters that are given as quer, so all the characters
     * from the string are reflected in the index.
     * </p>
     * 
     * @param string
     *            - string to perform search in.
     * @param query
     *            - set of characters, the desired substring has to contain.
     * @return minimum size substring that contains given set of characters or null when there is no such substring.
     */
    public static String indexedSearch(String string, Set<Character> query) {
	if (string.length() < query.size()) {
	    return null;
	}
	Map<Character, List<Integer>> invertedIndex = new HashMap<Character, List<Integer>>();
	for (int i = 0; i < string.length(); i++) {
	    char c = string.charAt(i);
	    if (query.contains(c)) {
		if (!invertedIndex.containsKey(c)) {
		    invertedIndex.put(c, new LinkedList<Integer>());
		}
		invertedIndex.get(c).add(i);
	    }
	}
	if (invertedIndex.size() < query.size()) {
	    return null;
	}
	SortedSet<Integer> minWindow = minWindow(invertedIndex);
	return string.substring(minWindow.first(), minWindow.last() + 1);
    }

    public static void main(String[] args) {
	String string = "Find minimum size substring that contains given set of characters.";
	Set<Character> chars = new HashSet<Character>();
	chars.add('a');
	chars.add('c');
	chars.add('i');
	System.out.println(exhaustiveSearch(string, Collections.unmodifiableSet(chars)));
	System.out.println(indexedSearch(string, Collections.unmodifiableSet(chars)));
    }

    /**
     * Checks whether the given string contains given set of characters .
     * 
     * @param string
     *            - string to check.
     * 
     * @param chars
     *            - set of characters to be looked up in the string.
     * @return true if the given string contains given set of characters, false otherwise.
     */
    private static boolean contains(String string, Set<Character> chars) {
	Set<Character> copySet = new HashSet<Character>(chars);
	for (char c : string.toCharArray()) {
	    copySet.remove(c);
	    if (copySet.isEmpty()) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Given an inverted index finds the smallest window containing all the characters. Order of appearance does not matter.
     * 
     * @param invertedIndex
     *            - map where each character points to sorted list of its positions in the string; each character with in the index must appear in the source
     *            string at least once (i.e. must have non-empty list of positions).
     * @return positions of characters that belong to minimum window.
     */
    private static SortedSet<Integer> minWindow(Map<Character, List<Integer>> invertedIndex) {
	SortedMap<Integer, Iterator<Integer>> currentWindow = new TreeMap<Integer, Iterator<Integer>>();
	int entriesProcessed = 0;
	for (List<Integer> list : invertedIndex.values()) {
	    Iterator<Integer> iterator = list.iterator();
	    currentWindow.put(iterator.next(), iterator);
	    entriesProcessed++;
	}
	SortedSet<Integer> minWindow = new TreeSet<Integer>(currentWindow.keySet());
	int minWindowLength = minWindow.last() - minWindow.first();

	Iterator<Integer> iterator = currentWindow.remove(currentWindow.firstKey());
	while (iterator.hasNext()) {
	    currentWindow.put(iterator.next(), iterator);
	    int currentWindowLength = currentWindow.lastKey() - currentWindow.firstKey();
	    if (currentWindowLength < minWindowLength) {
		minWindow = new TreeSet<Integer>(currentWindow.keySet());
		minWindowLength = currentWindowLength;
	    }
	    iterator = currentWindow.remove(currentWindow.firstKey());
	    entriesProcessed++;
	}
	System.out.println("Walked through " + entriesProcessed + " entries in inverted index.");
	return minWindow;
    }

}
