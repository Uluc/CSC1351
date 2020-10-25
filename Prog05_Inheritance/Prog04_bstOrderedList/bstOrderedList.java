import java.util.ArrayList;

public class bstOrderedList extends OrderedList{
	
	class Node {
		public Node leftChild;
		public Comparable<Movie> data;
		public Node rightChild;
		
		public void addNode(Node newNode) {
		
			int compare = newNode.data.compareTo((Movie) data);
			if(compare < 0)
				if(leftChild == null)
					leftChild = newNode;
				else 
					leftChild.addNode(newNode);
			else if(compare > 0)
				if(rightChild == null)
					rightChild = newNode;
				else 
					rightChild.addNode(newNode);
		}
	}
	
	private Node root; 
	private static ArrayList<Movie> movieList = new ArrayList<Movie>();
	
    //declaring node
	public bstOrderedList() {
		root = null;
	}
	
	/**
	 * <method to check if tree is empty>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
     public boolean isEmpty() {
		if(root == null)
			return true;
		else
			return false;
	}
	
	/**
	 * <method to add in the right position>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
	void add(Comparable<Movie> newMovie) {
		
		Node newNode = new Node();
		newNode.data = newMovie;
		
		newNode.leftChild = null;
		newNode.rightChild = null;
		
		if(root == null) {
			root = newNode;
			System.out.println(root.data.toString());
		}
			
		else {
			root.addNode(newNode);
			System.out.println(newNode.data.toString());
		}
		
	}
	
	/**
	 * <method to remove from the tree by finding appropriate location>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
	void remove(Comparable<Movie> removeMovie) {
		
		Node removal = root;
		Node parent = null;
		boolean found = false;
		while(!found && removal != null) {
			System.out.println("Removal data" + removal.data.toString());
			System.out.println("Removal data" + removeMovie.toString());
			int d = removal.data.compareTo((Movie) removeMovie);
			
		
			System.out.println(d); //never finds zero
			if(d == 0) {
				found = true;
			}
			else
			{
				parent = removal;
				if(d > 0)
					removal = removal.leftChild;
				else
					removal = removal.rightChild;
			}
		}

		
		if(removal.leftChild == null || removal.rightChild == null) {
			Node newChild;
			if(removal.leftChild == null)
				newChild = removal.rightChild;
			else
				newChild = removal.leftChild;
			
			if(parent == null)
				root = newChild;
			else if(parent.leftChild == removal)
				parent.leftChild = newChild;
			else
				parent.rightChild = newChild;
			return;
		}
		
		Node smallestParent = removal;
		Node smallest = removal.rightChild;
		
		while(smallest.leftChild != null) {
			smallestParent = smallest;
			smallest = smallest.leftChild;
		}
		
		removal.data = smallest.data;
		if(smallestParent == removal)
			smallestParent.rightChild = smallest.rightChild;
		else
			smallestParent.leftChild = smallest.leftChild;	
	}
	
	
	private int getSize(Node root){
		if(root == null){
			return 0;
		}
		return 1 + getSize(root.leftChild) + getSize(root.rightChild);
	}
	
	/**
	 * <returns the size of the tree>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
	public int size() {
		return getSize(root);
	}
	
	/**
	 * <mtoString to overide original method that displays the nodes in 
	 * tree>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
	@Override
	public String toString() {
		
		String returnString = toString(root);
		
		return returnString;
		
	}
	
	private static String toString(Node parent) {
		
		String rString = "";
		
		if(parent == null)
			return "";
		rString += toString(parent.leftChild);
		rString += toString(parent.rightChild);
		rString += parent.data.toString();	
		
		return rString;
	}
	
	/**
	 * <converts tree into an array>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	Comparable<Movie>[] toArray(String sorting){
		
		Comparable<Movie>[] movieArray;
		
		if(sorting.equals("inOrder"))
			movieList = inOrder(root);
		else if(sorting.equals("postOrder"))
			movieList = postOrder(root);
		else if(sorting.equals("preOrder")) 
			movieList = preOrder(root);	
		else
			return null;
		
		movieArray = (Comparable<Movie>[]) movieList.toArray(new Movie[movieList.size()]);
		return movieArray;
	}
	
	/**
	 * <method to sort array inOrder>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	public static ArrayList<Movie> inOrder(Node root){
		if(root == null) 
			return null;
		
		inOrder(root.leftChild);
		movieList.add((Movie) root.data);
		System.out.println("Added:" + root.data.toString());
		inOrder(root.rightChild);			
			
		return movieList;
	}
	
	/**
	 * <method to sort array postOrder>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
	public static ArrayList<Movie> postOrder(Node root){
		if(root == null) 
			return null;
		
		postOrder(root.leftChild);
		postOrder(root.rightChild);
		movieList.add((Movie) root.data);	
		System.out.println("Added:" + root.data.toString());
		
		return movieList;
	}
	
	/**
	 * <method to sort array preOrder>
	 *
	 * CSC 1351 Programming Project No <4> Section <1>
	 *
	 * @author <Uluc Ozdenvar>
	 * @since <April 1 2019>
	 *
	 */
	
	public static ArrayList<Movie> preOrder(Node root){
		if(root == null) 
			return null;
		
		movieList.add((Movie) root.data);	
		System.out.println("Added:" + root.data.toString());
		preOrder(root.leftChild);
		preOrder(root.rightChild);				
		
		return movieList;
	}

}
