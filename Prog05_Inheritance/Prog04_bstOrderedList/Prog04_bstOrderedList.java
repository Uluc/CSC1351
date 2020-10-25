import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prog04_bstOrderedList {

	// declaring scanners and printwriter
	private static Scanner scannerInput;
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

		bstOrderedList list = new bstOrderedList(); // calling ordered list object

		File evalFile = inputMovieData("Please write a text file: "); // getting input file

		scanner = new Scanner(evalFile);
		scanner.useDelimiter(",");

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

				System.out.println(movieTitle+ " " + movieYear + " " + movieRating + " " + movieReview);
				list.add(new Movie(movieTitle, movieYear, movieRating, movieReview));

			}

			// removal of items
			else if (operator == 'D') {

				stringArray = scanner.nextLine().split(",");

				tempTitle = stringArray[1];
				tempYear = Integer.parseInt(stringArray[2]);
				tempReview = 0;
				tempRating = "";
				System.out.println("remove: "+ tempTitle + tempYear);

				Movie tempMovie = new Movie(tempTitle, tempYear, tempRating, tempReview);

				System.out.println(list.toString());
				System.out.println(tempMovie.toString());
				list.remove(tempMovie);

			}
		}

		String sortingMethod;

		System.out.println(list.toString());
		out = GetOutputFile("Please pick an output file:"); //printwriter
		// declaration
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a sorting method as follows: inOrder, preOrder, or postOrder: ");
		sortingMethod = input.nextLine();

		Movie[] movieLists = (Movie[]) list.toArray(sortingMethod);
		
		Movie newMovie;
		 
		out.printf("The number of items : %d\n", list.size());
		
		//loop to print
		for(int i = 0; i < movieLists.length; i++) {
		 
		  newMovie = (Movie) movieLists[i];
		  out.printf("%-6s%10s \n","Title:",(newMovie.getTitle()));
		  out.printf("%-6s%10d \n","Year:",(newMovie.getYear()));
		  out.printf("%-6s%10s \n","Rating:",(newMovie.getRating())); 
		  out.printf("%-6s%10d \n","Review:",(newMovie.getReview()));
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