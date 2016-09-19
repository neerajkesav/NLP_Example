/**
 * MainClass
 */
package com.neeraj.nlpexample;

import java.io.IOException;

/**
 * MainClass. This an example project to identify question types.
 * At present only the four following question categories are considered:
 * Who, What, When, Affirmation
 * Any sentence that does not fall in any of the above four is considered as 
 * "Unknown" type.
 *  
 * Example questions: 
 * 1. What is your name? Type: What 
 * 2. When is the show happening? Type: When 
 * 3. Is there a cab available for airport? Type: Affirmation 
 * 
 * There are ambiguous cases to handle such as: 
 * What time does the train leave?(This looks like a what question but is actually
 * a When type).
 * 
 * @author neeraj
 *
 */
public class Main {

	/**
	 * MainClass implements the main method to test and run the solution. 
	 * - Cleansing DataSet. 
	 * - Classifying the questions. 
	 * - Display result. 
	 * - Write result to a file.
	 */
	
	/**
	 * Runs the solution.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		/*
		// Cleansing DataSet using Apache Spark
		DataCleanser cleanser = new DataCleanser();
		cleanser.cleanse();
		*/
		
		/*
		 * Identifying question type using StanfordNLP.
		 */

		/* Creating QuestionClassifier object */
		QuestionClassifier classifier = new QuestionClassifier();

		/* Starting Classification */
		classifier.start();

		/* Printing Result */
		classifier.display();

		/* Writing result to file. */
		classifier.writeToFile("resources/Output_QuestionType.txt");

	}

}
