package edu.coursera.nlp.homework.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

public class WordReplacer {

	/**
	 * {@value}
	 */
	private static final String SEPARATOR_WORD = " ";

	public void replaceWords(final File source, final File destination,
			final Set<String> words, final String replacement)
			throws FileNotFoundException {
		final Scanner scanner = new Scanner(source);
		final PrintWriter writer = new PrintWriter(destination);
		while (scanner.hasNext()) {
			final String line = scanner.nextLine();
			final String word = line.split(SEPARATOR_WORD)[0];
			if (words.contains(word)) {
				writer.write(line.replace(word, replacement));
			} else {
				writer.write(line);
			}
			writer.write("\n");
		}
		writer.close();
	}
}
