/**
 * QuestionClassifier
 */
package com.neeraj.nlpexample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * QuestionClassifier Class. Identifies the type of each question as Who, What, When,
 * Affirmation and Unknown.
 * 
 * @author neeraj
 *
 */
public class QuestionClassifier {
	/**
	 * QuestionClassifier Class contains three methods 'start()', 'addToMap', 'display()', and
	 * 'writeToFile()'
	 */

	/**
	 * 'hmap' contains the result. Question types as Map Key and List of
	 * questions as Map Values.
	 */
	private HashMap<String, List<String>> hmap = null;

	/**
	 * Default Constructor. Assigns HashMap to 'hmap'.
	 */
	public QuestionClassifier() {
		this.hmap = new HashMap<String, List<String>>();
	}

	/**
	 * Function 'start()' performs the question type identification.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public void start() throws IOException {

		/* Creating PosTagger object */
		PosTagger tagger = new PosTagger();

		/* Creating NamedEntityRecognizer object */
		NamedEntityRecognizer ner = new NamedEntityRecognizer();

		/* Creating QuestionTypeConfirmer object */
		QuestionTypeConfirmer checkType = new QuestionTypeConfirmer();

		/*
		 * File Inputs: 
		 * 1. Sample-Input.txt : Some random questions and some cases 
		 * 	  not present in 'Dataset.label'. 
		 * 2. CleansedDataset.txt : Cleansed data from 'Dataset.label'. 
		 * 3. CleansedDataset_Plus.txt : Questions from both '1' and '2'.
		 * 
		 */

		/* Reading DataSet */
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("resources/CleansedDataset_Plus.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * Reading each question from file and performs identification. Results
		 * are added to 'hmap' using function 'addToMap()'.
		 */
		String line;
		while ((line = reader.readLine()) != null) {

			if (line.toLowerCase().contains("what")) { // Questions contains what.														

				/* POS Tagging the question */
				String taggedLine = tagger.tag(line);

				/* Checking POSTag for What. */
				if (taggedLine.contains("/WP")) {
					addToMap(line, "What");
				} else if (taggedLine.contains("/WDT")) { // Ambiguous What.

					/* Recognizing Named Entity of question */
					String nerLine = ner.applyNer(line);

					/* Checking for When Type in Ambiguous What. */
					if (nerLine.contains("/WHENTYPE") && checkType.checkWhenType(line, taggedLine, nerLine)) {
						addToMap(line, "When");
					} else { // Ambiguous What ( Which, Where types).
						addToMap(line, "Unknown");
					}

				}

			} else if (line.contains("Who")) { // Who type.
				addToMap(line, "Who");

			} else if (line.contains("When")) { // When type.
				addToMap(line, "When");

			} else if (line.toLowerCase().contains("where") || line.toLowerCase().contains("why")
					|| line.toLowerCase().contains("which") || line.toLowerCase().contains("how")) {
				addToMap(line, "Unknown"); // Rest as Unknown type.

			} else if (checkType.affirmationCheck(tagger.tag(line))) { // Affirmation Type.																		
				addToMap(line, "Affirmation");
			} else { // Unknown Type.
				addToMap(line, "Unknown");

			}

		}

	}

	/**
	 * Adding result to 'hmap'.
	 * 
	 * @param line
	 *            Question String.
	 * @param type
	 *            Question type.
	 */
	public void addToMap(String line, String type) {

		List<String> lines = new ArrayList<String>();
		if (hmap.get(type) != null)
			lines = hmap.get(type);
		lines.add(line);
		hmap.put(type, lines);
	}

	/**
	 * Printing Result in 'hmap'.
	 */
	public void display() {

		Set<Entry<String, List<String>>> set = hmap.entrySet();

		Iterator<Entry<String, List<String>>> i = set.iterator();

		while (i.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) i.next();

			@SuppressWarnings("unchecked")
			List<String> b = (List<String>) me.getValue();

			System.out.println("_____________ Type : " + me.getKey() + " ______________\n");
			b.forEach(x -> System.out.println(x));
			System.out.println("--------------------------------------------------\n\n");

		}

	}

	/**
	 * Writing result to a file.
	 * 
	 * @param path
	 *            path to the output file.
	 */
	public void writeToFile(String path) {

		PrintWriter writer = null;

		try {
			FileWriter write = new FileWriter(path);
			writer = new PrintWriter(write);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Entry<String, List<String>>> set = hmap.entrySet();
		Iterator<Entry<String, List<String>>> i = set.iterator();

		while (i.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) i.next();

			@SuppressWarnings("unchecked")
			List<String> b = (List<String>) me.getValue();

			writer.println("_____________ Type : " + me.getKey() + " ______________\n");

			for (String str : b) {
				writer.println(str);
			}

			writer.println("--------------------------------------------------\n\n");

		}

	}

}
