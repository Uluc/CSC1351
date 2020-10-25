import java.io.File;
import javax.swing.JFileChooser;
import java.util.Scanner;


public class HelloEclipse {
	
	/**
	 * The InputFileName method allows the user to select the location and filename for input.
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc Ozdenvar>
	 * @since <January 17 2019>
	 *
	 */
	private static File InputFileName(String UserPrompt) {
		
		JFileChooser	chooser = new JFileChooser();	//file chooser object for dialog box interface to file structure
		boolean 		goodInput = false;				//flag for whether the input provided by the user is acceptable
		File 			selectedFile = null;			//the file selected by the user
		String 			inLine;							//input from user: used to wait for user to press "Enter" on the keyboard
		
		try {
			
			Scanner scan = new Scanner(System.in);
			while (!goodInput) {
				
				//prompt user for input
				System.out.printf(UserPrompt);
				inLine  = scan.nextLine();


				
				//user selected a valid file
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					selectedFile = chooser.getSelectedFile();
					goodInput = true;
				}//if
				
				//user failed to select a valid file
				else {
					System.out.printf("File for input must be selected.\n\n");
					System.out.printf(UserPrompt);
					inLine  = scan.nextLine();
				}//else
			}//while
		}//try
		catch (Exception e) {
			System.err.println(e);
		}//catch
		
		return selectedFile;
		
	}//InputFileName

	public static void main(String[] args) {

		boolean goodInputFile = false;					//flag for whether input file specified contains well formed input
		File 	inputFile = null;						//input file
		Scanner	in = null;								//scanner for prompting user
		
		JFileChooser	chooser = new JFileChooser();	//file chooser object for dialog box interface to file structure
		File 			selectedFile = null;			//the file selected by the user
		
		boolean goodOutputFile = false;					//flag for whether output file has been specified by the user
		File	outputFile;								//output file
		
		ManageData dataManager;							//object to manage the intput, output, and analysis of data
		
		String 			inLine;							//input from user: used to wait for user to press "Enter" on the keyboard
		
		try {
			
			
			//loop until a good input file is provided
			while (!goodInputFile) {
				
				//assume the input file specified by the user is good
				goodInputFile = true;
				
				// Input file to read
				inputFile = InputFileName("Select File Of Grades To Evaluate: <Enter>");		
		
					
				//Connect to input file
				try {
					in = new Scanner(inputFile);
					in.close();
				}//try
				
				//unable to open the file selected for some reason
				catch(java.io.FileNotFoundException e) {
					System.out.printf("Unable to open selected file\n\n");
					goodInputFile = false;
				}//catch
			}//while
			

			//input and analyze data
			dataManager = new ManageData(inputFile);
			dataManager.PerformAnalysis();


			//loop until the user provides good output file information
			try {
				Scanner scan = new Scanner(System.in);
				
				while (!goodOutputFile) {
						
					//prompt user for input
					System.out.printf("Select results output file: <Enter>\n");
					inLine  = scan.nextLine();
					
					
					//user selected a valid file
					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						selectedFile = chooser.getSelectedFile();
						goodOutputFile = true;
					}//if
					
					//user failed to select a valid file
					else {
						System.out.printf("\nFile for output must be selected.\n");
						System.out.printf("Select results output file: <Enter>\n");
						inLine  = scan.nextLine();
						goodOutputFile = false;
					}//else
				}//while
				
				scan.close();
			}//try
			catch (Exception e) {
				System.err.println(e);
				goodOutputFile = false;
			}//catch
					
			if (goodOutputFile) {
				dataManager.PrintStats(selectedFile);
				System.out.printf("HelloEclipse: Output Completed\n");
			}//if

		}//try
		catch (Exception e) {
			System.err.println(e);
		}//catch

	}//main

}//class HelloEclipse