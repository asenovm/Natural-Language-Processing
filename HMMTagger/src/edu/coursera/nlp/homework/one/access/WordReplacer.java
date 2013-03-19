package edu.coursera.nlp.homework.one.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;


public class WordReplacer implements ReadCallback {

	/**
	 * {@value}
	 */
	@SuppressWarnings("unused")
	private static final String TAG = WordReplacer.class.getSimpleName();

	/**
	 * {@value}
	 */
	private static final String SEPARATOR_WORD = " ";

	private final Set<String> words;

	private final String replacement;

	private final PrintWriter writer;

	public WordReplacer(final File destination, final Set<String> words,
			final String replacement) throws FileNotFoundException {
		this.words = words;
		this.replacement = replacement;
		this.writer = new PrintWriter(destination);
	}

	private void replaceWords(final String line) {
		final String[] tokens = line.split(SEPARATOR_WORD);
		final String word = tokens[0];

		if (words.contains(word)) {
			final String tag = tokens[1];
			writer.write(replacement);
			writer.write(SEPARATOR_WORD);
			writer.write(tag);
		} else {
			writer.write(line);
		}
		writer.write("\n");
		writer.flush();
	}

	@Override
	public void onLineRead(String line) {
		replaceWords(line);
	}

}
