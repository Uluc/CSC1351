import java.util.NoSuchElementException;

public class llOrderedList extends OrderedList{
	private int numObjects;
	private Node first;
	private Node prev;
	private Node prev2;
	
	private Node curr;

	
	class Node {
        public Object data;
        public Node next;
	}
	
	/**
	* <Main Constructot for initializing variables>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	public llOrderedList() {
		
		numObjects = 0;
		first = null;
		prev = null;
		prev2 = null;
		curr = null;
	
	}
	
	/**
	* <used for adding items into a list in order>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	 
	public void add(Comparable<Car> newObject) {
		
		curr = first;
		prev = null;
		Node newCar = new Node();
		
		boolean foundPos = false;
		//search for position
		while (! foundPos) {
			if (curr == null) {
				foundPos = true;
			}
			else {
				if (newObject.compareTo((Car) curr.data) < 0) {
					foundPos = true;
				}
				else {
					prev = curr;
					curr = curr.next;
				}
			}
		}
		
		
		
		newCar.data = newObject;
		
		newCar.next = curr;
		//insert first element
		if (prev == null) {
			first = newCar;
		}
		else {
			prev.next = newCar;
		}
		
		System.out.println("Item Added");
		//System.out.println(toString());
		numObjects++;
		
	}

	/**
	* <display items as a println>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	
	public String toString() {
		
		String result = "";
		
		curr = first;
		
		while (curr != null){
			result += (curr.data.toString() + " ");
			curr = curr.next;
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
	* @since <Mar 13 2019>
	*
	*/ 
	
	public int size() {

		//System.out.println(numObjects);
		return numObjects;
		
	}
	
	/**
	* <checks if array is empty>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	public boolean isEmpty() {
		
		if(numObjects == 0)
			return true;
		else
			return false;
	}	
	
	/**
	* <resets the iterator>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	public void reset() {
		
		curr = first;
	}
	
	/**
	* <returns the next car in the ordered list>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	public Comparable<Car> next() throws NoSuchElementException {
		
		if (curr != first) {
        	prev2 = prev;
        }
		
			
		prev = curr; 
		curr = curr.next;
		return (Car) prev.data;
	}
		
	/**
	* <Checks if linked list has a item next>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	public boolean hasNext(){
		
		if (curr == null) {
			return false;
		}
		else {
			return curr != null;	
		}
	}
	
	
	/**
	* <Used to remove items from linklist.>
	*
	* CSC 1351 Programming Project No <2>
	* Section <1>
	*
	* @author <Uluc Ozdenvar>
	* @since <Mar 13 2019>
	*
	*/ 
	
	
	public void remove() {
		
		if (prev2 != null)
    		prev2.next = curr;
    	else
    		first = curr;
		
		numObjects--;
		System.out.println("Removed");
	}
}
