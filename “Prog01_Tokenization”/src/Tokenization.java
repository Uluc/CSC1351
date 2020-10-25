import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
* <The main purpose of this class is to analyze the tokens provided by the user in their file>
*
* CSC 1351 Programming Project No <enter project number here> * Section <insert your section number here>
*
* @author <Uluc Ozdenvar>
* @since <January 29th 2019>
*
*/

public class Tokenization {

	//Deceleration of all class variables
	
	private int arrayLenght;
	private int[] tokenCount;
	private String[] tokenNames;
	private Scanner scan;
	private Scanner scanner;
	private Scanner scanner2;
	private Scanner scanLenght;
	private PrintWriter out;
	
	/**
	* <Constructor to grab the main file to analyze for user tokens>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/

	public Tokenization(File EvalFile) throws FileNotFoundException {
		
		//initialize variables
		scanner = new Scanner(EvalFile);
		scan = scanner.useDelimiter("(\\p{P}+\\s)|(\\s+\\p{P})|\\s|(\\p{P}+$)");
		
		arrayLenght = 0;
		tokenNames = null; 
		String temp = null;
		int spot = -1;
		
		arrayLenght = arrayLenghtFinder(EvalFile);
		tokenNames = new String[arrayLenght];
		tokenCount = new int[arrayLenght];
		
		//looping through the file to add to an array
		for (int i = 0; i < arrayLenght; i++) {
			
			if (scan.hasNext()) {
				temp = scan.next();
				
				//checks if the word previously exists or not and adds to array if needed
				if(searchExistence(tokenNames, temp) == false) {
					//System.out.println(searchExistence(tokenNames, temp));
					tokenNames[i] = temp.toLowerCase();
					tokenCount[i] = tokenCount[i] + 1;
					
				}
				else {
					i--;
					spot = searchSpot(tokenNames, temp);
					tokenCount[spot] = tokenCount[spot] + 1;
					
				}
			}
		}
		
		//System.out.println(Arrays.toString(tokenNames));
		//System.out.println(Arrays.toString(tokenCount));
		
		scanner.close(); //scanner closed
			
	}
	
	/**
	* <Constructor to analyze the file if the user chooses to incorporate a>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	public Tokenization(File EvalFile, File IgnoreFile) throws FileNotFoundException {
		
		scanner = new Scanner(EvalFile);
		scan= scanner.useDelimiter("(\\p{P}+\\s)|(\\s+\\p{P})|\\s|(\\p{P}+$)");
		
		
		arrayLenght = 0;
		tokenNames = null; 
		String temp = null;
		int spot = -1;
		
		arrayLenght = arrayLenghtFinder(EvalFile);
		tokenNames = new String[arrayLenght];
		tokenCount = new int[arrayLenght];
		
		//looping through the file to get tokens into respective array
		for (int i = 0; i < arrayLenght; i++) {
			
			if (scan.hasNext()) {
				temp = scan.next();
	
				if(searchIgnoreFile(IgnoreFile, temp) == false) {
					
					//System.out.println(Arrays.toString(tokenNames));		
					if(searchExistence(tokenNames, temp) == false) {
						//System.out.println(searchExistence(tokenNames, temp));
						//System.out.println(result);

						tokenNames[i] = temp.toLowerCase();
						tokenCount[i] = tokenCount[i] + 1;
							
					}
					else {
						i--;
						spot = searchSpot(tokenNames, temp);
						tokenCount[spot] = tokenCount[spot] + 1;
						
					}
				}	
			}
		}
		
		//filters both arrays to get of nulls and 0s that were created due to ignore file
		tokenNames = Arrays.stream(tokenNames).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
		tokenCount = clear0s(tokenCount);
		
		selectionSortParallel(tokenNames, tokenCount); // sort array
		
		//System.out.println(Arrays.toString(tokenNames));
		//System.out.println(Arrays.toString(tokenCount));
		
		scanner.close();
			
	}

	/**
	* <Method to output what has been analyzed into a text file user has chosen>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	public void OutputTokens(File OutFile) throws FileNotFoundException {
		
		out = new PrintWriter(OutFile);
		
		//loops through the arrays and writes them onto the selected output file
		for(int i = 0; i < tokenNames.length; i++) {
			out.println(tokenNames[i] + "," + tokenCount[i]);
		}
		
		
		out.close();
		
	}

	/**
	* <Method to find the length of a regular array>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private int arrayLenghtFinder(File evalFile) throws FileNotFoundException {
		
		scanner2 = new Scanner(evalFile);
		scanLenght = new Scanner(evalFile);
		
		String[] tempArray;
		int counter = 0;
		int counter2 = 0;
		String temp = "";
		
		while(scanner2.hasNext()) {
			counter++;
			System.out.println(scanner2.next());
		}
		
		tempArray = new String[counter];
		
		for (int i = 0; i < counter; i++) {
			
			if (scanLenght.hasNext()) {
				temp = scanLenght.next();
				System.out.println(temp);
				if(searchExistence(tempArray, temp) == false) {
					tempArray[i] = temp;
					counter2++;
				}
			}
		}
		
		scanner2.close();
		scanLenght.close();
		
		return counter2;
	}
	
	/**
	* <Method to selection sort parallel arrays so both arrays are  sorted >
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private void selectionSortParallel(String[] array, int[] intArray){
	    
	    for(int j=0; j < array.length-1; j++)
	    {
		    int min = j;
		    for(int k=j+1; k < array.length; k++)
		    	if(array[k].compareTo(array[min]) < 0) 
		    		min = k;  
		
		      swap(array, j, min);
		      swap(intArray, j, min);
	    }
  
	}

	/**
	* <get rid of the 0s in the tokensCount array as a result of choosing to ignore
	* certain words>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private int[] clear0s(int[] array) {
		
		/* Creates a temporary array and adds everything to that array
		 * as long as it is not a 0 then returns the filtered array
		 */
		
		int[] temp = new int[array.length];
		int zeroCount = 0;
		
		for (int i = 0; i<array.length; i++){
		  
			if (array[i] != 0){
				temp[i - zeroCount] = array[i];
			} 
			else {
		    zeroCount++;
		  }
		}
		//result array is declared depending on the zero count from the previous array
		int[] result = new int[temp.length-zeroCount];
		
		//temp array is copied onto the result array
		System.arraycopy(temp, 0, result, 0, result.length);
		
		return result;
	}
	
	/**
	* <swap strings in an string array>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private void swap(String[] array, int i, int j) {
		String temp;
		
		try {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	/**
	* <swap ints in an integer array >
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private void swap(int[] array, int i, int j) {
		int temp;
		
		try {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
		
	/**
	* <searches if a token already exists in the array>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private boolean searchExistence(String[] array, String searchItem) {
		//if("is".equals(searchItem))
		//	System.out.print(Arrays.toString(array));
		boolean result = false;
		
		try {
			for(int i = 0; i < array.length; i++) {
				
				if(searchItem.equalsIgnoreCase(array[i])) {
					//System.out.println(array[i] + "  " + searchItem);	
					result = true;
					
					break;
				}
			}
		}
		catch(Exception e) {
			
		}
		//if("is".equals(searchItem))
		//	System.out.println(result);
		return result;
	}
	
	/**
	* <Sees if a word is found in the search ignore file>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private boolean searchIgnoreFile(File ignore, String searchItem) throws FileNotFoundException {
		
		boolean found = false;
		String temp;
		
		Scanner scanner3 = new Scanner(ignore);
		Scanner searchIgnoreScanner2 = scanner3.useDelimiter("[^a-zA-Z\\d]*\\s+[^a-zA-Z\\d]*|[^a-zA-Z\\d'.]*\\s+");
		
		try {
			while(searchIgnoreScanner2.hasNext()) {
				
				temp = searchIgnoreScanner2.next();	
				if(temp.equalsIgnoreCase(searchItem)) {
					found = true;
					break;
				}	
			}
		}
		catch(NoSuchElementException e) {
			
		}
		searchIgnoreScanner2.close();
		
		return found;
		
	}
	
	/**
	* <searches where a certain token is in a given array>
	*
	* CSC 1351 Programming Project No <01> * Section <001>
	*
	* @author <Uluc Ozdenvar>
	* @since <Janaury 29th 2019>
	*
	*/
	
	private int searchSpot(String[] array, String searchItem) {
		
		int x = -1;
		
		try {
			for(int i = 0; i < array.length; i++) {
				if(searchItem.equalsIgnoreCase(array[i])) {
					x = i;
					break;
				}
			}
		}
		catch(Exception e) {
			
		}
		return x;
	}	
}
