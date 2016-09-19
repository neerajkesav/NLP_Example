/**
 * PosTagger
 */
package com.neeraj.nlpexample;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * PosTagger Class. Part of Speech Tagging of a String 'line'. Using StanFord
 * NLP MaxentTagger.
 * 
 * @author neeraj
 *
 */
public class PosTagger {

	/**
	 * PosTagger Class contains function 'tag()'.
	 */

	/**
	 * MaxentTagger object.
	 */
	private MaxentTagger tagger = null;

	/**
	 * Default Constructor. Assigns MaxentTagger to 'tagger'.
	 */
	public PosTagger() {
		this.tagger = new MaxentTagger("resources/english-bidirectional-distsim.tagger");
	}

	/**
	 * Performs POS Tagging on Question String.
	 * 
	 * @param line
	 *            Question String.
	 * @return POS Tagged String.
	 */
	public String tag(String line) {

		/* Creating InputStream from String 'line' and tokenizing. */
		InputStream in = new ByteArrayInputStream(line.getBytes());
		List<List<HasWord>> qLines = MaxentTagger.tokenizeText(new BufferedReader(new InputStreamReader(in)));

		for (List<HasWord> qLine : qLines) {
			List<TaggedWord> taggedLine = tagger.tagSentence(qLine);
			line = Sentence.listToString(taggedLine, false);
		}

		return line;
	}

}
