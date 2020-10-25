import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * The ManageData class inputs, outputs, and performs analysis on an array of grade values. The input file is assumed to include the number
 * off input values on the first line of input. Grades are then followed, one on each line.
 * 
 * CSC 1351 Laboratory Assignment No 1
 * Section <1>
 * 
 * @author <Uluc OZDENVAR>
 * @since <January 17 2019>
 *
 */

public class ManageData {
	
	private int 	m_ArraySize;			//size of the array
	private int[] 	m_ValueArray;			//array of input values
	
	private float 	m_Mean;					//mean (i.e., average) of the array values; contains a value of -1 if mean has not been calculated
	private float 	m_Median;				//median of the array values; contains a value of -1 if median has not been calculated
	private int 	m_Max;					//maximum of the array values; contains a value of -1 if maximum has not been calculated
	private int 	m_Min;					//minimum of the array values; contains a value of -1 if minimum has not been calculated
	private int 	m_GradeCounts[];		//array of letter grade counts of the array; contains 0 values if grade counts have not been calculated
	
	
	/**
	 * The ManageData default constructor method initializes member variables
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	public ManageData() {
		m_ValueArray = null;
		m_ArraySize = 0;
		
		m_Mean = -1;
		m_Median = -1;
		m_Max = -1;
		m_Min = -1;
		m_GradeCounts = new int[5];
	}//ManageData constructor
	
	
	/**
	 * The ManageData constructor method initializes member variables from the provided input file
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	public ManageData(File inFile) {
		
		Scanner	in = null;				//scanner for prompting user
		int 	arraySize = 0;			//size of the array
		int[] 	valueArray = null;		//array of input values
		boolean badInput;				//flag for whether the input provided by the user is acceptable
		
		m_ValueArray = null;
		m_ArraySize = 0;
		
		m_Mean = -1;
		m_Median = -1;
		m_Max = -1;
		m_Min = -1;
		m_GradeCounts = new int[5];
		
		try {
			//Read input file
			in = new Scanner(inFile);
			
			//read number of grades in input file
			if (!in.hasNextInt()) {
				System.out.printf("Input file does not contain integer data.\n\n");
				badInput = true;
			}//if
			else {
				
				//read number of grades from first line of input file
				arraySize = in.nextInt();
				
				//check for valid number of grades
				if (arraySize <= 0) {
					System.out.printf("Input file contains a non-positive header value.\n\n");
					badInput = true;
				}//if
				else {
					//initialize array for grades
					valueArray = new int[arraySize];
					badInput = false;
					
					//read in grades from input file
					for (int i = 0; i < arraySize; i++) {
						if (in.hasNextInt()) {
							valueArray[i] = in.nextInt();
						}//if
						else {
							badInput = true;
							String badValue = in.next();
						}//else
					}//for
				}//else
			}//else
			
			if (!badInput) {
				m_ValueArray = valueArray;
				m_ArraySize = arraySize;
			}//if
			
		}//try
		catch(Exception e) {
			m_ValueArray = null;
			m_ArraySize = 0;
		}//catch
		
	}//ManageData constructor
	
	

	/**
	 * The swap method exchanges two values in an array
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	private void swap(int[] TheArray, int A, int B) {
		
		int temp;	//tempoarary memory location needed to make the swap
		
		try {
		
			temp = TheArray[A];
			TheArray[A] = TheArray[B];
			TheArray[B] = temp;
		}//try
		
		catch (Exception e) {
			System.err.println(e);
		}//catch
	}//swap
	
	
	/**
	 * The BubbleSort method sorts an array in either ascending or descending order
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	private void BubbleSort(int[] TheArray, String SortType) {

		  boolean	sorted = false;		//flag to track whether the array is already sorted (can stop short)
		  int 		i = 1;				//index for iterations through the array

		  try {
			  //only need to sort if we have 2 or more elements in the array
				if (TheArray.length > 1) {
					
					//loop until the array is sorted
					while (!sorted) {
						
						//we're at the end, so the array is sorted
					    if (i == TheArray.length) {
					      sorted = true;
					    }//if
					    
					    //an iteration through the array
					    else {
	
					    	//assume that the array is sorted before we start the iteration
						    sorted = true;
						    
						    //check all pairs that have not already been sorted
						    for(int j = 0; j < TheArray.length-i; j++){ 
						                          
						      if((SortType == "asc") && (TheArray[j] > TheArray[j+1]) ||
						      		(SortType == "desc") && (TheArray[j] < TheArray[j+1])) {
						                             
						        swap(TheArray, j, j+1);
						      
						      	//we found a pair that is out of order, so we're not sorted, yet
						        sorted = false;
						      }//if
						    }//for
					    }//else
	
					    i++;
					  }//while
				}//if
		  }//try
		  
		  catch (Exception e) {
				System.err.println(e);
		  }//catch
		  
		}//BubbleSort
	
	
	/**
	 * The CalculateMean method calculates the average of the values in the array
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	private float CalculateMean(int[] TheArray) {
		
		float retVal = 0;	//the method return value
		
		try {
		
			if (TheArray.length > 0) {
				for (int i = 0; i < TheArray.length; i++) {
					retVal = retVal + TheArray[i];
				}//for
				
				retVal = retVal/TheArray.length;
			}//if
		}//try
		
		catch (Exception e) {
			System.err.println(e);
		}//catch
		
		return retVal;
	}//CalculateMean
	
	
	/**
	 * The CalculateMedian method calculates the median of the values in the array
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	private float CalculateMedian(int[] TheArray) {
		
		float retVal = 0;	//the method return value
		
		try {
			
			if (TheArray.length > 0) {
			
				if (TheArray.length == 1) {
					retVal = TheArray[0];
				}//if
				if (TheArray.length%2 ==1) {
					retVal = TheArray[TheArray.length/2];
				} //if
				else {
					retVal = ((float) TheArray[TheArray.length/2 - 1] + (float) TheArray[TheArray.length/2]) / (float) 2.0;
				}//else
			}//if
		}//try
		
		catch (Exception e) {
			System.err.println(e);
		}//catch
		

		
		return retVal;
	}//CalculateMedian
	
	
	/**
	 * The CalcualteGradeCounts method calculates the number of grades for each letter grade 
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	private void CalcualteGradeCounts(int[] TheArray, int[] GradeCounts) {
		
		try {
			if (TheArray.length > 0) {
				for (int i = 0; i < TheArray.length; i++) {
					if (TheArray[i] >= 90) {
						GradeCounts[0]++;
					}//if
					else if (TheArray[i] >= 80) {
						GradeCounts[1]++;
					}//else if
					else if (TheArray[i] >= 70) {
						GradeCounts[2]++;
					}//else if
					else if (TheArray[i] >= 60) {
						GradeCounts[3]++;
					}//else if
					else {
						GradeCounts[4]++;
					}//else
				}//for
				
			}//if
		}//try
		
		catch (Exception e) {
			System.err.println(e);
		}//catch
	}//CalcualteGradeCounts
	
	/**
	 * The PerformAnalysis method sorts the array and calculates the mean, median, maximum, min, and letter gra
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	public void PerformAnalysis() {
		
		//sort array
		BubbleSort(m_ValueArray, "desc");
		
		
		//calculate mean
		m_Mean = CalculateMean(m_ValueArray);
		
		//calculate median
		m_Median = CalculateMedian(m_ValueArray);
		
		//calculate max
		m_Max = m_ValueArray[0];
		
		//calculate min
		m_Min = m_ValueArray[m_ArraySize - 1];
		
		//calculate grade counts
		CalcualteGradeCounts(m_ValueArray, m_GradeCounts);
		
	}
	
	
	/**
	 * The PrintResults method allows the user to select the location and filename for output of results. The analysis results
	 * are then output to that location/file. 
	 * 
	 * CSC 1351 Laboratory Assignment No 1
	 * Section <1>
	 * 
	 * @author <Uluc OZDENVAR>
	 * @since <January 17 2019>
	 *
	 */
	public void PrintStats(File selectedFile) {
		
		//create the new output file
		try (PrintWriter outFile = new PrintWriter(selectedFile.getPath())){
			
			//loop through the array to print out the grades 
			for (int i = 0; i < m_ValueArray.length; i++) {
				outFile.println(m_ValueArray[i]);
			}//for
			
			outFile.printf("\n\n");
			
			//print analysis results
			outFile.printf("Mean: %.2f\n", m_Mean);
			outFile.printf("Median: %.2f\n", m_Median);
			outFile.printf("Minimum: %d\n", m_Min);
			outFile.printf("Maximum: %d\n", m_Max);
			outFile.printf("\n");
			outFile.printf("Number of As: %d\n", m_GradeCounts[0]);
			outFile.printf("Number of Bs: %d\n", m_GradeCounts[1]);
			outFile.printf("Number of Cs: %d\n", m_GradeCounts[2]);
			outFile.printf("Number of Ds: %d\n", m_GradeCounts[3]);
			outFile.printf("Number of Fs: %d\n", m_GradeCounts[4]);
			outFile.printf("\n");
			outFile.printf("Total Number of Grades: %d\n", m_ValueArray.length);
			
			outFile.close();
		}//try
		
		//file specified cannot be opened for writing for some reason
		catch(java.io.FileNotFoundException e) {
			System.out.printf("Unable to print to selected file\n\n");
		}//catch
		

	}//PrintStats

}//class ManageData