/**
 * NamedEntityRecognizer
 */
package com.neeraj.nlpexample;

import edu.stanford.nlp.ie.regexp.RegexNERSequenceClassifier;

/**
 * NamedEntityRecognizer Class. Recognize the NamedEntities and tags to each
 * word. Using StanFord NLP RegexNER. Uses a user defined RegexNER pattern
 * 'Regexner Pattern.txt'.
 * 
 * @author neeraj
 *
 */
public class NamedEntityRecognizer {

	/**
	 * NamedEntityRecognizer Class contains function 'applyNer()'
	 */

	/**
	 * RegexNERSequenceClassifier object.
	 */
	private RegexNERSequenceClassifier classifier = null;

	/**
	 * Default Constructor. Assigns RegexNERSequenceClassifier to 'classifier'.
	 */
	public NamedEntityRecognizer() {
		this.classifier = new RegexNERSequenceClassifier("resources/Regexner Pattern.txt", true, false, null);
	}

	/**
	 * Performs RegexNER.
	 * 
	 * @param line
	 *            Question String.
	 * @return NER tagged String.
	 */
	public String applyNer(String line) {

		return classifier.classifyToString(line);

	}

}
