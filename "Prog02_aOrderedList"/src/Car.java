/**
* <Class is used to create car object and uses a comparable interface>
*
* CSC 1351 Programming Project No <2>
* Section <1>
*
* @author <Uluc Ozdenvar> 
* @since <Feb 25 2019>
*
*/

public class Car implements Comparable<Car> {

	//declaring variables
	private String make;
	private int year;
	private int price; 
	
	/**
	* <Main constructor>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	
	public Car(String Make, int Year, int Price) { //constructor that sets the values of make, year, and price
		
		year = Year;
		make = Make;
		price = Price;
		
	}
	
	/**
	* <accesor method for make variable>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	
	public String getMake() {
		return make;
	}
	
	/**
	* <accesor method for year variable>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public int getYear() {
		return year;
	}
	
	/**
	* <accesor method for price variable>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public int getPrice() {
		return price;
		
	}
	
	/**
	* <method used to compare car objects to one another depending on year and make>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	public int compareTo(Car other){
		int retVal;
		
		if (make.compareTo(other.getMake()) < 0) {
				retVal = -1;
				
		}
		else if(make.equals(other.getMake())){
			
			if (year < other.getYear())
				retVal = -1;
			else if (year == other.getYear())
				retVal = 0;
			else
				retVal = 1;
		}
		else
			retVal = 1;
		
		return retVal;
	}
	
	/**
	* <toString method helps display the items in println format>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public String toString() {
		String retString = ("[Make: " + make + ", Year : "+ year + ", Price: " + price + ";]");
		System.out.println(retString);
		return retString;
	}
	
}
