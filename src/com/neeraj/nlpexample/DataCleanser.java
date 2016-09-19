/**
 * DataCleanser
 */
package com.neeraj.nlpexample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * DataCleanser Class. Cleansing the DataSet 'Dataset.label'
 * 
 * @author neeraj
 *
 */
public class DataCleanser {
	/**
	 * DataCleanser Class contains the function 'cleanse()'.
	 */

	/**
	 * Perform cleansing on DataSet.
	 */
	public void cleanse() {

		/* Creating Spark Configuration and Spark Context */
		SparkConf conf = new SparkConf().setAppName("Cleansing DataSet").setMaster("local");
		JavaSparkContext spark = new JavaSparkContext(conf);

		/* Creating JavaRDD of String from 'Dataset.label' */
		JavaRDD<String> rawData = spark.textFile("resources/Dataset.label");

		/**
		 * CleanseFunction InnerClass. To perform cleansing operation from Spark
		 * Map Transformation.
		 * 
		 * @author neeraj
		 *
		 */
		@SuppressWarnings("serial")
		class CleanseFunction implements Function<String, String> {
			/**
			 * CleanseFunction InnerClass contains function 'call()'
			 */

			/**
			 * Performs Cleansing Operation.
			 * 
			 * @param line
			 *            Question String.
			 */
			public String call(String line) {

				/*
				 * Splitting String to words to remove Labels in
				 * 'Dataset.label'.
				 */
				String[] attList = line.split(" ");
				attList[0] = "";

				/* Joining words after removing label. */
				String str = String.join(" ", attList);

				return str;
			}

		}

		/* Performing Spark Map Transformation to cleanse Data. */
		JavaRDD<String> newData = rawData.map(new CleanseFunction());

		/* Writing the cleansed data new file. */
		PrintWriter writer = null;

		try {
			FileWriter write = new FileWriter("resources/CleansedDataset.txt");
			writer = new PrintWriter(write);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Object[] dataSet = newData.collect().toArray();
		ArrayList<String> data = new ArrayList<String>();

		for (int i = 0; i < dataSet.length; i++) {

			data.add(dataSet[i].toString());
			writer.println(dataSet[i].toString());
		}

		/* Closing SparkContext. */
		spark.close();
	}

}
