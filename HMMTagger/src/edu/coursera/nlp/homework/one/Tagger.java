package edu.coursera.nlp.homework.one;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

public class Tagger {

	/**
	 * {@value}
	 */
	private static final String FILE_TRAINING_UPDATED = "../gene.train.updated";

	/**
	 * {@value}
	 */
	private static final String FILE_COUNT = "../gene.count";

	/**
	 * {@value}
	 */
	private static final String FILE_TRAINING = "../gene.train";

	/**
	 * {@value}
	 */
	public static final String TAG = Tagger.class.getSimpleName();

	/**
	 * {@value} 
	 */
	private static final String SYMBOL_RARE = "_RARE_";

	public static void main(String[] main) {

		try {
			final File trainingData = new File(FILE_TRAINING);
			final File countData = new File(FILE_COUNT);
			final File destinationFile = new File(FILE_TRAINING_UPDATED);

			final CountReader reader = new CountReader(countData);
			final WordReplacer replacer = new WordReplacer();

			final Set<String> rareWords = reader.getRareWords();
			replacer.replaceWords(trainingData, destinationFile, rareWords,
					SYMBOL_RARE);
		} catch (IOException ex) {
			Logger.getAnonymousLogger().throwing(TAG, "main", ex);
		}
	}
}
