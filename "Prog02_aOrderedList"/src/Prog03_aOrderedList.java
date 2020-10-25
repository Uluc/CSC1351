import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/**
* <main class used to execute to program and to setup scanners and printwriters>
*
* CSC 1351 Programming Project No <2>
* Section <1>
*
* @author <Uluc Ozdenvar> 
* @since <Feb 25 2019>
*
*/

public class Prog03_aOrderedList {
	
	//declaring scanners and printwriter
	private static Scanner scannerInput;
	private static Scanner scanner;
	private static PrintWriter out = null;

	/**
	* <main method which reads the file and writes the file adding and removing objects as 
	* necesary>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public static void main(String[] args) throws FileNotFoundException{
		
		aOrderedList list = new aOrderedList(); //calling ordered list object
		
		File evalFile = inputCarData("Please write a text file: "); //getting input file
		
		scanner = new Scanner(evalFile);
		scanner.useDelimiter(",");
		
		//declaration of variables
		String makeCar = null;
		int yearCar = 0;
		int priceCar = 0;
		char operator;
		String tempMake;
		int tempYear;
		
		String[] stringArray;
		
		while (scanner.hasNextLine()){
			
			operator = scanner.next().charAt(0);
		
			//adding items
			if(operator == 'A') {
				
				stringArray = scanner.nextLine().split(",");
				makeCar = stringArray[1];
				yearCar = Integer.parseInt(stringArray[2]);
				priceCar = Integer.parseInt(stringArray[3]);
	
				list.add(new Car(makeCar, yearCar, priceCar));

			}
			
			//removal of items
			else if(operator == 'D') {
				stringArray = scanner.nextLine().split(",");
				
				tempMake = stringArray[1];
				tempYear = Integer.parseInt(stringArray[2]);
				
				
				
				Car temp1;
				
	 			for(int j = 0; j < list.size(); j++) {
	 				temp1 = (Car) list.get(list.nextIndex());
					
					if((temp1.getMake().equals(tempMake)) && (temp1.getYear() == tempYear)) {
						list.remove();
					}
				}
			}
				
			
		}
			
		
		out = GetOutputFile("Please pick an output file: "); //printwriter declaration
		
		out.println("Number of cars: " + list.size());
		
		Car newCar;
		
		//printing out on the output file
		for(int i = 0; i < list.size(); i++) {
		
			newCar = (Car) list.next();
			out.printf("%-6s%10s \n","Make:",(newCar.getMake()));
			out.printf("%-6s%10d \n","Year:",(newCar.getYear()));
			out.printf("%-6s%10d \n","Price:",(newCar.getPrice()));
		}
		
		list.reset();
		
		out.close();
		scanner.close();
		
		
	}
	
	/**
	* <returning scanner to read input file>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
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
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	public static PrintWriter GetOutputFile(String UserPrompt) throws FileNotFoundException{
		
		boolean yesOrNo = false;
		String userInput = null;
		scannerInput = new Scanner(System.in);
		File selectedFile = null;
		
		//makes sure user has valid input
		while(yesOrNo == false) {
		
			userInput = GetInputFile(UserPrompt).next();
			selectedFile = new File(userInput);
			
			if(selectedFile.isFile()) {
				yesOrNo = true;
			}
		
			else{
				
				System.out.println("File specified <"+ userInput +"> does not exist. Would you like"+
				"to continue? <Y/N>");
				
				if(scannerInput.next().equals("Y"))
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
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public static File inputCarData(String UserPrompt) throws FileNotFoundException {
		
		boolean yesOrNo = false;
		String userInput = null;
		scannerInput = new Scanner(System.in);
		File selectedFile = null;
		
		//makes sure input is valid
		while(yesOrNo == false) {
		
			userInput = GetInputFile(UserPrompt).next();
			selectedFile = new File(userInput);
			
			if(selectedFile.isFile()) {
				yesOrNo = true;
			}
		
			else{
				
				System.out.println("File specified <"+ userInput +"> does not exist. Would you like"+
				"to continue? <Y/N>");
				
				if(scannerInput.next().equals("Y"))
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
