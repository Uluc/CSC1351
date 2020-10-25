import java.util.Arrays;
import java.util.NoSuchElementException;

/**
* <Class is used to order the list of cars as well as including methods to add and
* remove items from list as needed>
*
* CSC 1351 Programming Project No <2>
* Section <1>
*
* @author <Uluc Ozdenvar> 
* @since <Feb 25 2019>
*
*/

public class aOrderedList {
	
	final int SIZEINCREMENTS = 20;
	private Comparable[] oList; 
	private int listSize; 
	private int numObjects;
	private int curr;
	
	
	/**
	* <Main Constructot for initializing variables>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public aOrderedList() {
		
		numObjects = 0;
		listSize = SIZEINCREMENTS;
		oList = new Comparable[SIZEINCREMENTS];
	}
	
	/**
	* < used for removing items from a list>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	 
	public void remove(int index) {
		
		int j =0;
		if(numObjects == 1) {
			oList[1] = null;
			numObjects--;
		}
		
		else {
			Comparable<Car>[] newCar = new Car[listSize];
			for(int i = 0; i < listSize; i++) {
				if(i != index) {
					newCar[j] = oList[i];
					j++;
				}
			}
			newCar[listSize-1] = null;
			oList = newCar;
 		}
	}
	
	/**
	* <used for adding items into a list in order>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	 
	public void add(Comparable<Car> newObject) {
		
		int insertPosition = 0;
		if(numObjects == listSize) {
			Comparable[] newOList = Arrays.copyOf(oList, SIZEINCREMENTS + listSize); 
			oList = newOList;
			listSize = oList.length;
		}
		else {
			if(numObjects == 0) {
				oList[0] = newObject;
				numObjects++;
			}
			else {
				for(int i = 0; i < numObjects; i++) {
					if(newObject.compareTo((Car) oList[i]) < 0) {
						insertPosition = i;
						break;
					}
				}
				
				for(int j = numObjects-1; j >= insertPosition; j--) {
					oList[j+1] = oList[j];
					
				}
				
				oList[insertPosition] = newObject;	
				numObjects++;
				
			}
		}	

	}

	/**
	* <display items as a println>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	
	public String toString() {
		
		String result = "";
		
		for(int i = 0; i < numObjects; i++) {
			result += (oList[i].toString() + " ");
		}
		
		return result;
		
	}
		
			
	/**
	* <return the size of the elements in the array that are not null>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public int size() {
		
		int size = 0;
		
		for(int i = 0; i < oList.length; i++){
			if(oList[i] != null)
				size++;
				
		}
		
		return size;
	}
	
	/**
	* <checks if array is empty>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public boolean isEmpty() {
		
		if(numObjects == 0)
			return true;
		else
			return false;
	}

	/**
	* <returns the object of car in a given index>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public Comparable<Car> get(int index) {
	
		Comparable<Car> position = null;
		
		for(int i = 0; i < size(); i++) 
			if(oList[i] == oList[index]) 
				position = oList[index];
		
		return position;
	}
	
	
	/**
	* <resets the iterator>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	public void reset() {
		
		curr = 0;
	}
	
	/**
	* <returns the next car in the ordered list>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	
	public Comparable<Car> next() throws NoSuchElementException {
		
		if(hasNext()) {
			curr++;
			return oList[curr-1];
		}
		return null;
		
	}
	
	/**
	* >
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Feb 25 2019>
	*
	*/ 
	
	
	public int nextIndex() {
		return curr;
	}
	
	public boolean hasNext() {
		if(oList[curr] == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void remove() {
		
		remove(curr);
		
	}
	
	
	
}
