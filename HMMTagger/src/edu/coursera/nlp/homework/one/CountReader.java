package edu.coursera.nlp.homework.one;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class CountReader {

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

	public CountReader() {
		wordCounts = new HashMap<String, Integer>();
	}

	public void read(final String filePath) throws IOException {
		final Scanner scanner = new Scanner(new File(filePath));

		while (scanner.hasNext()) {
			final String currentLine = scanner.nextLine();
			if (currentLine.contains(TAG_WORD)) {
				countWordsInLine(currentLine);
			}
		}
	}

	public Iterable<String> getRareWords() {
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

}