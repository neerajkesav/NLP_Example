/**
 * QuestionTypeConfirmer 
 */
package com.neeraj.nlpexample;

/**
 * QuestionTypeConfirmer Class. Checking question types 'When' and 'Affirmation'.
 * 
 * @author neeraj
 *
 */
public class QuestionTypeConfirmer {

	/**
	 * QuestionTypeConfirmer Class contains two functions 'checkWhenType()' and
	 * 'affirmationCheck()'.
	 */

	/**
	 * Performs checking for 'When' type.
	 * 
	 * @param line
	 *            Question String.
	 * @param tagLine
	 *            POS Tagged String.
	 * @param nerLine
	 *            NER Tagged String.
	 * @return True or False.
	 */
	public boolean checkWhenType(String line, String tagLine, String nerLine) {

		/* Splitting Strings to words. */
		String[] tagWords = tagLine.split(" ");
		String[] nerWords = nerLine.split(" ");
		String[] words = line.split(" ");

		int j = 0;
		while (!nerWords[j].contains("/WHENTYPE")) {
			j++;
		}

		int i = 0;
		while (!tagWords[i].contains("/WDT")) {
			i++;
		}

		/*
		 * Testing question format: part of speeches and their position match.
		 */
		if (tagWords[i].contains("/WDT") && tagWords[i + 1].contains("/NN") && tagWords[i + 2].contains("/VB")) {
			if (tagWords[i + 1].contains(words[j]) && nerWords[j].contains(words[i + 2])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Performs checking for 'Affirmation' type.
	 * 
	 * @param tagLine
	 *            POS Tagged string.
	 * @return True or False.
	 */
	public boolean affirmationCheck(String tagLine) {

		/* Splitting String to words. */
		String[] tagWords = tagLine.split(" ");
		int i = tagWords.length;

		/*
		 * Testing question format: part of speeches and their position match.
		 */
		if (tagWords[0].contains("VB") && tagWords[1].contains("DT") && tagWords[1].contains("PRP")) {
			return true;
		} else if (tagWords[0].contains("VB") && tagWords[1].contains("PRP")) {
			return true;
		} else if (tagWords[i - 4].contains("VB") && tagWords[i - 3].contains("RB")
				&& tagWords[i - 2].contains("PRP")) {
			return true;
		} else if (tagWords[i - 3].contains("VB") && tagWords[i - 2].contains("PRP")) {
			return true;
		}

		return false;
	}

}
