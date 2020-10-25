import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prog06_MergeIt {

	// declaring scanners and printwriter
	private static Scanner scannerInput;
	private static Scanner scanner2;
	private static Scanner scanner;
	private static PrintWriter out = null;

	/**
	 * <main method which reads the file and writes the file adding and removing
	 * objects as necesary>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */

	public static void main(String[] args) throws FileNotFoundException {

		 // calling ordered list object

		aOrderedList list = new aOrderedList();
		aOrderedList list2 = new aOrderedList();
		
		File evalFile = inputMovieData("Please write a text file: "); // getting input file
		File evalFile2 = inputMovieData("Please write another text file: ");

		scanner = new Scanner(evalFile);
		scanner.useDelimiter(",");
		scanner2= new Scanner(evalFile2);
		scanner2.useDelimiter(",");

		// declaration of variables
		String movieTitle = null;
		int movieYear = 0;
		String movieRating = "";
		int movieReview;

		char operator;

		String tempTitle;
		int tempYear;
		String tempRating;
		int tempReview;

		String[] stringArray;

		while (scanner.hasNextLine()) {

			operator = scanner.next().charAt(0);

			// adding items
			if (operator == 'A') {

				stringArray = scanner.nextLine().split(",");
				movieTitle = stringArray[1];
				movieYear = Integer.parseInt(stringArray[2]);
				movieRating = stringArray[3];
				movieReview = Integer.parseInt(stringArray[4]);
				
				Movie temp = new Movie(movieTitle, movieYear, movieRating, movieReview);
				if(list.existance(temp) == false) 
					list.add(temp);
					

			}

			// removal of items
			else if (operator == 'D') {

				stringArray = scanner.nextLine().split(",");

				tempTitle = stringArray[1];
				tempYear = Integer.parseInt(stringArray[2]);
				tempReview = 0;
				tempRating = "";
				System.out.println("remove: "+ tempTitle + tempYear);

				Movie temp1;
				
				for(int j = 0; j < list.size(); j++) {
	 				temp1 = (Movie) list.get(j);
					
					if((temp1.getTitle().equals(tempTitle)) && (temp1.getYear() == tempYear)) {
						list.remove(j);
					}
				}

			}
		}
		
		while (scanner2.hasNextLine()) {

			operator = scanner2.next().charAt(0);

			// adding items
			if (operator == 'A') {

				stringArray = scanner2.nextLine().split(",");
				movieTitle = stringArray[1];
				movieYear = Integer.parseInt(stringArray[2]);
				movieRating = stringArray[3];
				movieReview = Integer.parseInt(stringArray[4]);

				Movie temp = new Movie(movieTitle, movieYear, movieRating, movieReview);
				if(list2.existance(temp) == false)
					list2.add(temp);

			}

			// removal of items
			else if (operator == 'D') {

				stringArray = scanner2.nextLine().split(",");

				tempTitle = stringArray[1];
				tempYear = Integer.parseInt(stringArray[2]);
				tempReview = 0;
				tempRating = "";
				System.out.println("remove: "+ tempTitle + tempYear);

				Movie temp1;
				
				for(int j = 0; j < list2.size(); j++) {
	 				temp1 = (Movie) list2.get(j);
					
					if((temp1.getTitle().equals(tempTitle)) && (temp1.getYear() == tempYear)) {
						list2.remove();
					}
				}

			}
		}
		
		boolean exist = false;
		
		for(int i = 0; i < list2.size(); i++) {
			Movie temp = (Movie) list2.next();
			System.out.println(temp.toString() + "1");
			
			exist = list.existance(temp);
			System.out.println("added - pre");
			
			
			if(exist = false) {
				list.add(temp);	
				System.out.println("added");
			}
		}
		
		Movie[] movieArray = (Movie[]) list.toArray();
		
		out = GetOutputFile("Please pick an output file:"); //printwriter
		
		Movie newMovie;
		 
		out.printf("The number of items : %d\n", list.size());
		
		//loop to print
		for(int i = 0; i < movieArray.length; i++) {
		 
		  newMovie = (Movie) movieArray[i];
		  
		  out.printf("%s ",(newMovie.getTitle()));
		  out.printf("%d ",(newMovie.getYear()));
		  out.printf("%s ",(newMovie.getRating())); 
		  out.printf("%d ",(newMovie.getReview()));
		  out.printf("\n");
		}

		 

		out.close();
		scanner.close();

	}

	/**
	 * <returning scanner to read input file>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */

	public static Scanner GetInputFile(String UserPrompt) throws FileNotFoundException {

		System.out.println(UserPrompt);
		Scanner scan = new Scanner(System.in);
		return scan;
	}

	/**
	 * <return a printwriter to be used as the main way to wirte on output file>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	public static PrintWriter GetOutputFile(String UserPrompt) throws FileNotFoundException {

		boolean yesOrNo = false;
		String userInput = null;
		scannerInput = new Scanner(System.in);
		File selectedFile = null;

		// makes sure user has valid input
		while (yesOrNo == false) {

			userInput = GetInputFile(UserPrompt).next();
			selectedFile = new File(userInput);

			if (selectedFile.isFile()) {
				yesOrNo = true;
			}

			else {

				System.out.println(
						"File specified <" + userInput + "> does not exist. Would you like" + "to continue? <Y/N>");

				if (scannerInput.next().equals("Y"))
					yesOrNo = false;
				else {
					yesOrNo = true;
					System.out.println("BYEEE");
					System.exit(0);
				}

			}

		}

		PrintWriter out = new PrintWriter(userInput);
		System.out.println(userInput);
		return out;
	}

	/**
	 * <Used to find the file to return >
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */

	public static File inputMovieData(String UserPrompt) throws FileNotFoundException {

		boolean yesOrNo = false;
		String userInput = null;
		scannerInput = new Scanner(System.in);
		File selectedFile = null;

		// makes sure input is valid
		while (yesOrNo == false) {

			userInput = GetInputFile(UserPrompt).next();
			selectedFile = new File(userInput);

			if (selectedFile.isFile()) {
				yesOrNo = true;
			}

			else {

				System.out.println(
						"File specified <" + userInput + "> does not exist. Would you like" + "to continue? <Y/N>");

				if (scannerInput.next().equals("Y"))
					yesOrNo = false;
				else {
					yesOrNo = true;
					System.out.println("BYEEE");
					System.exit(0);
				}

			}

		}

		return selectedFile;

	}

}