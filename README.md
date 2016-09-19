## NLP Example

 This is an example project to identify question types. At present only the four following question categories are considered:
    Who, What, When, Affirmation 
  	Any sentence that does not fall in any of the above four is considered as "Unknown" type.
  	
  	Example questions: 
  	1. What is your name? Type: What
  	2. When is the show happening? Type: When
  	3. Is there a cab available for airport? Type: Affirmation 
  	
  	There are ambiguous cases to handle such as: 
  	What time does the train leave?(This looks like a what question but is actually a When type).  
 
 
### Data Sets
 * Dataset.label - Question data set. ( http://cogcomp.cs.illinois.edu/Data/QA/QC/train_1000.label )	
 * Sample-Input.txt : Some random questions	


### Frameworks/Libararies
 * Stanford CoreNLP - for POS Tagging and NER Tagging
 * Apache Spark - for data cleansing


### Getting Started

These instructions will get you a brief idea on setting up the environment and running on your local machine for development and testing purposes. 

**Prerequisities**

- Java
- StansfordCoreNLP
- Apache Spark 


**Setup and running tests**

1. Run `javac` and `java -version` to check the installation
   
2. Run `spark-shell` and check if Spark is installed properly. 
          
3. Execute the following commands from terminal to run the tests:

      `javac -classpath "Path to required jar files(Spark, StansfordNLP)" Main.java` 
      `java -classpath "Path to required jar files(Spark, StansfordNLP)" Main` 

     

###Classes
Please start exploring from Main.java

All classes in this project are listed below:

* **DataCleanser.java** - To cleanse the data set. Contains the following methods:
	
      	  `public void cleanse()`      	 
	
* **QuestionClassifier.java** - Identifies the type of each question as Who, What, When, Affirmation and Unknown. Contains the following method:
	
      	  `public void start()`
      	  `public void addToMap(String line, String type)`
      	  `public void display()`
      	  `public void writeToFile(String path)`
	
* **PosTagger.java** - Performs Part of Speech Tagging on a question string. Contains the following method:

	  	  `public String tag(String line)`
	
* **NamedEntityRecognizer.java** - Performs Named Entity Recognition on a question string, contains the following method:
	 
      	  `public String applyNer(String line)`
	 
* **QuestionTypeConfirmer.java** - Checking question types 'When' and 'Affirmation'., contains the following method:
	
	  	  `public boolean checkWhenType(String line, String tagLine, String nerLine)`
	  	  `public boolean affirmationCheck(String tagLine)`
	
* **Main.java** - Main class to test and run the classes in this project.	






