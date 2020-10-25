import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class aOrderedList{
	
		
		final int SIZEINCREMENTS = 20;
		private Comparable[] oList; 
		private int listSize; 
		private int numObjects;
		private int curr;
		
		private static ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		
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
	        for (int i = index; i <= numObjects; i++) {
	            oList[i] = oList[i + 1];
	        }
	        numObjects--;
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
		 
		public void add(Comparable<Movie> newObject) {
			
			if (numObjects >= listSize) {
	            oList = Arrays.copyOf(oList, oList.length + SIZEINCREMENTS);
	        }

	        if (isEmpty())
	        {
	            oList[0] = newObject;
	        }
	        else
	        {
	            for (int i = 0; i < numObjects + 1; i++) {
	                if (oList[i] == null) {
	                    oList[i] = newObject;
	                }
	                else {
	                    int result = oList[i].compareTo(newObject);
	                    if (result > 0) {
	                        Comparable nextObject = newObject;
	                        for (int j = i; j <= numObjects; j++) {
	                            Comparable tmp = oList[j];
	                            oList[j] = nextObject;
	                            nextObject = tmp;
	                        }
	                        break;
	                    }
	                }
	            }
	        }

	        System.out.println("added");
	        numObjects++;
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
			
			return numObjects;
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
		
		public Comparable<Movie> get(int index) {
		
			Comparable<Movie> position = null;
			
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
		
		
		public Comparable<Movie> next() throws NoSuchElementException {
			
			Comparable<Movie> item = oList[curr];
	        curr++;
	        return item;

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
		
		Comparable<Movie>[] toArray(){
			
			while(hasNext()) {
				movieList.add((Movie) next());
			}
			
			Comparable<Movie>[] movieArray;
			
			movieArray = (Comparable<Movie>[]) movieList.toArray(new Movie[movieList.size()]);
			return movieArray;
		}
		
		public boolean hasNext() {
			if(oList[curr+1] == null) {
				return false;
			}
			else {
				return true;
			}
		}
		
		public void remove() {
			
			reset();
	        while(hasNext())
	        {
	            next();
	        }
	        oList[curr] = null;
	        reset();
			
		}
		
		public boolean existance(Movie newMovie) {
			for(int i = 0; i < numObjects; i++) {
				
				if(oList[i].compareTo(newMovie) == 0) {
					System.out.println(oList[i] + " " + newMovie.toString() + i);
					return true;
				}
			}
			return false;
		}
		
		
		
	}

