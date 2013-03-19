package edu.coursera.nlp.homework.one.tagger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import edu.coursera.nlp.homework.one.CountReader;
import edu.coursera.nlp.homework.one.access.FileReader;
import edu.coursera.nlp.homework.one.access.WordReplacer;

public class Tagger {

	/**
	 * {@value}
	 */
	public static final String TAG = Tagger.class.getSimpleName();

	/**
	 * {@value}
	 */
	private static final String FILE_TRAINING_UPDATED = "../data/gene.train.updated";

	/**
	 * {@value}
	 */
	private static final String FILE_COUNT = "../data/gene.count";

	/**
	 * {@value}
	 */
	private static final String FILE_TRAINING = "../data/gene.train";

	/**
	 * {@value} 
	 */
	private static final String SYMBOL_RARE = "_RARE_";

	public static void main(String[] main) {
		final Tagger tagger = new Tagger();
		tagger.tag();
	}

	public void tag() {
		try {
			updateRareWords();
			computeTagProbabilities();
		} catch (IOException ex) {
			ex.printStackTrace();
			Logger.getAnonymousLogger().throwing(TAG, "main", ex);
		}
	}

	private void computeTagProbabilities() throws FileNotFoundException {
		final EmissionComputer computer = new EmissionComputer(new File(
				FILE_TRAINING_UPDATED));
	}

	private void updateRareWords() throws FileNotFoundException {
		final File trainingData = new File(FILE_TRAINING);
		final File countData = new File(FILE_COUNT);
		final File destinationFile = new File(FILE_TRAINING_UPDATED);

		final FileReader reader = new FileReader();

		final CountReader countReader = new CountReader();
		reader.read(countData, countReader);

		final WordReplacer replacer = new WordReplacer(destinationFile,
				countReader.getRareWords(), SYMBOL_RARE);
		reader.read(trainingData, replacer);
	}
}
