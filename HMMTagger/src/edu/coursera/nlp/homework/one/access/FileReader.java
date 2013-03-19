package edu.coursera.nlp.homework.one.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {

	public void read(final File file, final ReadCallback callback)
			throws FileNotFoundException {
		final Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			final String line = scanner.nextLine();
			callback.onLineRead(line);
		}
	}
}
