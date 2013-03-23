package edu.coursera.nlp.homework.one.tagger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

	/**
	 * {@value}
	 */
	private static final String WORD_RARE = "_RARE_";

	private final Map<TaggedWord, Integer> taggedWords;

	private final Map<String, Integer> tags;

	private class TagHelper {

		private final String rareWordTag;

		public TagHelper() {
			rareWordTag = getTagInternal(WORD_RARE);
		}

		public String getTagFor(final String word) {
			final String tag = getTagInternal(word);
			return tag == null ? rareWordTag : tag;
		}

		private String getTagInternal(final String word) {
			double maxProbability = 0;
			String tag = null;

			for (final TaggedWord each : taggedWords.keySet()) {
				final String currentWord = each.getWord();
				final String currentTag = each.getTag();
				final double currentProbability = (double) taggedWords
						.get(each) / tags.get(currentTag);
				if (currentWord.equals(word)
						&& maxProbability < currentProbability) {
					maxProbability = currentProbability;
					tag = currentTag;
				}
			}
			return tag;
		}
	}

	private class InputReader implements ReadCallback {

		private final TagHelper helper;

		private PrintWriter writer;

		public InputReader() {
			helper = new TagHelper();
			try {
				writer = new PrintWriter("../data/gene.dev.out");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onLineRead(final String line) {
			if (line.isEmpty()) {
				writer.write("\n");
				writer.flush();
				return;
			}

			final String tag = helper.getTagFor(line);
			writer.write(line);
			writer.write(" ");
			writer.write(tag);
			writer.write("\n");

			writer.flush();
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
