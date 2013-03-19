package edu.coursera.nlp.homework.one.tagger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.coursera.nlp.homework.one.model.TaggedWord;

public class EmissionComputer {

	/**
	 * {@value}
	 */
	private static final String SEPARATOR_WORD = " ";

	private final Map<TaggedWord, Integer> taggedWords;

	private final Map<String, Integer> tags;

	public EmissionComputer(final File trainingData)
			throws FileNotFoundException {
		taggedWords = new HashMap<TaggedWord, Integer>();
		tags = new HashMap<String, Integer>();
		readTags(trainingData);

		System.out.println("debug data start!!!!");

		for (final Map.Entry<TaggedWord, Integer> each : taggedWords.entrySet()) {
			System.out.println(each.getKey() + " " + each.getValue());
		}

		for (final Map.Entry<String, Integer> each : tags.entrySet()) {
			System.out.println(each.getKey() + " " + each.getValue());
		}

		System.out.println("debug data end!!!!!");
	}

	private void readTags(final File trainingData) throws FileNotFoundException {
		final Scanner scanner = new Scanner(trainingData);
		while (scanner.hasNextLine()) {
			final String line = scanner.nextLine();
			final String[] tokens = line.split(SEPARATOR_WORD);

			if (tokens.length >= 2) {
				final String word = tokens[0];
				final String tag = tokens[1];

				tagWord(word, tag);
				countTag(tag);
			}

		}
	}

	private void countTag(final String tag) {
		if (tags.containsKey(tag)) {
			tags.put(tag, tags.get(tag) + 1);
		} else {
			tags.put(tag, 1);
		}
	}

	private void tagWord(final String word, final String tag) {
		final TaggedWord taggedWord = new TaggedWord(word, tag);
		if (taggedWords.containsKey(taggedWord)) {
			taggedWords.put(taggedWord, taggedWords.get(taggedWord) + 1);
		} else {
			taggedWords.put(taggedWord, 1);
		}
	}
}
