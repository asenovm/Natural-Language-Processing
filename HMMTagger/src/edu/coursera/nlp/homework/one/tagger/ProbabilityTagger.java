package edu.coursera.nlp.homework.one.tagger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import edu.coursera.nlp.homework.one.access.FileReader;
import edu.coursera.nlp.homework.one.access.ReadCallback;
import edu.coursera.nlp.homework.one.model.TaggedWord;

public class ProbabilityTagger implements ReadCallback {

	/**
	 * {@value}
	 */
	private static final String SEPARATOR_WORD = " ";

	private final Map<TaggedWord, Integer> taggedWords;

	private final Map<String, Integer> tags;

	private class InputReader implements ReadCallback {

		@Override
		public void onLineRead(final String line) {
			System.out.println("line is " + line);
		}

	}

	public ProbabilityTagger() {
		taggedWords = new HashMap<TaggedWord, Integer>();
		tags = new HashMap<String, Integer>();
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

	@Override
	public void onLineRead(String line) {
		final String[] tokens = line.split(SEPARATOR_WORD);

		if (tokens.length >= 2) {
			final String word = tokens[0];
			final String tag = tokens[1];

			tagWord(word, tag);
			countTag(tag);
		}
	}

	public void tag(final File file) throws FileNotFoundException {
		final FileReader reader = new FileReader();
		reader.read(file, new InputReader());
	}
}
