package edu.coursera.nlp.homework.one;

import java.io.IOException;
import java.util.logging.Logger;

public class Tagger {

	public static final String TAG = Tagger.class.getSimpleName();

	public static void main(String[] main) {
		final CountReader reader = new CountReader();
		try {
			reader.read("../gene.count");
			final Iterable<String> rareWords = reader.getRareWords();
			for (final String word : rareWords) {
				System.out.println("word is " + word);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			Logger.getAnonymousLogger().throwing(TAG, "main", ex);
		}
	}
}
