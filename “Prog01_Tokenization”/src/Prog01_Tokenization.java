
/**
* <Main objective of this program is for the user to select a file which they use to be
* analyzed in tokens form which is split into individual tokens then from there>
*
* CSC 1351 Programming Project No <01> * Section <001>
*
* @author <Uluc Ozdenvar>
* @since <Janaury 29th 2019>
*
*/

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Prog01_Tokenization{

	/**
	* <Method that asks the user to input as well as an output file. The prompt 
	* determines the kind of file that is wanted by the program>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private static File InputFile(String userPrompt){
		
		String enter = null; 
		
		JFileChooser chooser = new JFileChooser();
		boolean inputCheck = false;
		
		File evalFile = null;
		
		try {
			Scanner scan = new Scanner(System.in);
			
			while(!inputCheck) { //checks to make sure a input exists
				System.out.println(userPrompt);
				enter = scan.nextLine();
				
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					evalFile = chooser.getSelectedFile();
					inputCheck = true;
				}
				else {
					System.out.println("MUST SELECT PRESS ENTER TO CONTINUE");
					enter = scan.nextLine();
				}
			}	
		}
		catch(Exception e) {
			System.err.println(e);
		}	
		
		return evalFile;
	}
	
	/**
	* <Method that asks the file which signifies the words which they wish to ignore in the
	* final output>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private static File RemoveFile() {
		
		String prompt = "Press ENTER to select file to remove tokens.";
		String enter; 
		
		JFileChooser chooser = new JFileChooser();
		boolean inputCheck = false;
		
		File removalFile = null;
		
		//opening a file chooser to select a removal file
		try {
			Scanner scan = new Scanner(System.in);
			
			System.out.println(prompt);
			enter = scan.nextLine();
			
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				removalFile = chooser.getSelectedFile();
				inputCheck = true;
			}	
		}
		catch(Exception e) {
			System.err.println(e);
		}
	
		return removalFile;
	}

	/**
	* <Main method of the program that calls the methods for user to pick files>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	public static void main(String[] args) throws FileNotFoundException{
		
		
		//calls the methods above to get the user to input information
		File inputFile = InputFile("Press ENTER to select file to analyze tokens.");
		File ignoreFile = RemoveFile();
		File outputFile = InputFile("Press ENTER to select file to output tokens that were analyzed.");

		//checks if an ignore file exists and calls the according constructor
		if(ignoreFile != null) { 
			
			Tokenization tokens = new Tokenization(inputFile, ignoreFile);
			tokens.OutputTokens(outputFile);
		}
		else {
			
			Tokenization tokens = new Tokenization(inputFile);
			//System.out.println("YEH");
			tokens.OutputTokens(outputFile);
		}
		
		
		/*String str = "Hi, I'm a .Test Sen'tence for REGECX^% usage?!";
		String[] strArray = new String[10];
	
				
		strArray = str.split("(\\p{P}+\\s)|(\\s+\\p{P})|\\s|(\\p{P}+$)");
		
		
		for(int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);
		}*/
	
		
	}
}
