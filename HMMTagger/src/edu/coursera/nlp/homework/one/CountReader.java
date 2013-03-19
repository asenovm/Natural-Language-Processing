package edu.coursera.nlp.homework.one;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.coursera.nlp.homework.one.access.ReadCallback;

public class CountReader implements ReadCallback {

	/**
	 * {@value}
	 */
	private static final int THRESHOLD_RARE_WORD = 5;

	/**
	 * {@value}
	 */
	private static final String SEPARATOR_WORD = " ";

	/**
	 * {@value}
	 */
	private static final String TAG_WORD = "WORDTAG";

	private final Map<String, Integer> wordCounts;

	public CountReader() throws FileNotFoundException {
		wordCounts = new HashMap<String, Integer>();
	}

	public Set<String> getRareWords() {
		final Set<String> result = new HashSet<String>();
		for (final Entry<String, Integer> entry : wordCounts.entrySet()) {
			final String word = entry.getKey();
			final int occurrences = entry.getValue();
			if (occurrences < THRESHOLD_RARE_WORD) {
				result.add(word);
			}
		}
		return result;
	}

	private void countWordsInLine(final String line) {
		final String[] tokens = line.split(SEPARATOR_WORD);

		final String word = tokens[tokens.length - 1];
		final int occurrences = Integer.parseInt(tokens[0]);

		if (wordCounts.containsKey(word)) {
			wordCounts.put(word, wordCounts.get(word) + occurrences);
		} else {
			wordCounts.put(word, occurrences);
		}
	}

	@Override
	public void onLineRead(String line) {
		if (line.contains(TAG_WORD)) {
			countWordsInLine(line);
		}
	}

}
