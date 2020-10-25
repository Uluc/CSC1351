//Code written by Brett Renz and Uluc Ozdenvar
//uozden1@lsu.edu brenz1@lsu.edu
import java.util.LinkedList;
import java.util.*;
import java.io.*;
import java.lang.*;

public class prog3 {

	//class trie node
	public static class TrieNode {   
		
		public TrieNode rightSibling;
		public TrieNode firstChild;
    	String label = "";
    	char leadingChar;
    	boolean isWord;
    	
		//constructor for trie node
		public TrieNode(String key, Object right, Object first, boolean newIsWord, char c) {
			rightSibling = (TrieNode) right;
        	firstChild = (TrieNode) first;
        	label = key;
        	leadingChar = c;
        	isWord = true;
        	          
        }
		
		//checking if label exists	
		public boolean labelCheck() {
			if(label != null)
				return true;
			else
				return false;
		}
    }
	
	//class for hash
	public static class HashNode{
		
		public TrieNode parent;
		public TrieNode child;
		String label;
		char c;		
		public HashNode next;
		
	}
	
	//declaring hash table
	static HashNode[] hashTable = new HashNode[173528];
	
	//creating hash nodes
	static HashNode createHashNode(TrieNode parent, TrieNode child) {
		HashNode newHash = new HashNode();
		newHash.parent = parent;
		newHash.child = child;
		newHash.c = child.label.charAt(0);
		return newHash;
		
	}
	
	//generating hash values
	static int generateValue(TrieNode parent, char c) {
		int value = System.identityHashCode(parent);
		int valueFinal = (value * Character.getNumericValue(c))%(173527);
		return valueFinal;
	}
	
	//inserting nodes into hash later to be used in table
	static void insertHash(TrieNode parent, TrieNode child) {
		HashNode newNode = createHashNode(parent, child);
		int hashKey = generateValue(parent, child.leadingChar);
		if(hashTable[hashKey] != null) {
			newNode.next = hashTable[hashKey];
			hashTable[hashKey] = newNode;
		}
		else
			hashTable[hashKey] = newNode;
	}
	
	
	//populating the hash table
	static void fillHashTable(TrieNode parent, TrieNode child) {
		if(child == null)
			return;
		else {
			insertHash(parent,child);
			fillHashTable(parent,child.rightSibling);
			fillHashTable(parent,child.firstChild);
			return;
		}
			
	}
	
	//finding hash child
	TrieNode findHashChild(TrieNode parent, char c) {
		
		HashNode curr =  new HashNode();
		int hashKey = generateValue(parent, c);
		curr = hashTable[hashKey];
		
		while(curr!= null && !( curr.parent == parent && curr.c == c)) 
			curr=curr.next;	
		if (curr == null) 
			return null;

		return curr.child;
	}
	
	//finding members of the hash to see if they exist
	boolean isMemberHash(TrieNode r, String s) {
		if (findHashChild(r, s.charAt(0)) == null)
			return false;
		TrieNode curr = findHashChild(r, s.charAt(0));
		if(s.length() == curr.label.length()) 
			return true;
		else if(s.charAt(0) == curr.label.charAt(0) && curr.firstChild != null) {
			return isMemberHash(curr, s.substring(curr.label.length(), s.length()));
		}
		else 
			return false;
	}
	
	
	boolean isMember(TrieNode r, String s) {
	if (s == r.label)
	    return true;
	else if (s.charAt(0) == r.label.charAt(0) && r.firstChild != null)
	    return isMember(r.firstChild, s.substring(r.label.length(), s.length() - 1));
	else if (s.charAt(0) != r.label.charAt(0) && r.rightSibling != null)
	    return isMember(r.rightSibling, s);
	else return false;
	}
		
	public static TrieNode root = new TrieNode("", null, null, false, '\u0000');
	
	
	/*public static void setFirstChild(TrieNode n, TrieNode p) {
		p.rightSibling = n.firstChild;
		n.firstChild = p;
	}*/
	
	public static TrieNode FindChild(TrieNode n, char c) {
		
    	TrieNode q = n.firstChild;
    	TrieNode newNode = new TrieNode("", null, null, false,'\u0000');
    	newNode.leadingChar = c;
    	
    	if(n.firstChild == null || q.leadingChar > c) {
    		newNode.rightSibling = n.firstChild;
    		n.firstChild = newNode;
    		return newNode;
    	}
    	
    	else if(q.leadingChar == c) 
    		return q;
    	else
    		while( q != null) {
    			if(q.rightSibling.leadingChar > c || q.rightSibling != null) {
    				newNode.rightSibling = q.rightSibling;
    				q.rightSibling = newNode;
    				return newNode;
    			}
    			if(q.rightSibling.leadingChar == c)
    				return q.rightSibling;
    			q = q.rightSibling;
    		}
    	
    	while(q != null && q.leadingChar != c)
    		if(q == null)
    			return null;
    	
    	return q;
   
    }
	
	
	//preorder method 
	static void preorder(TrieNode p, String prefix) {
		if(p == null)
			return;
		
		prefix = prefix + p.label;
		
		if(p.isWord)
			System.out.println(prefix);
		
		TrieNode q = p.firstChild;
		while(q!=null) {
			preorder(q, prefix);
			q = q.rightSibling;
		}
			
	}
		
	//insert into trie table
	static void insert(String key) 
    { 
    	int i = 0;      
        TrieNode q = root; 
        boolean spot = false; 
        boolean charMatch = false;
       
        while(spot == false) {
        	TrieNode newNode = FindChild(q, key.charAt(0));
        	if(!newNode.labelCheck()) {
        		newNode.label = key;
        		spot = true;
        	}
        
        	else {
        		
        		while(!charMatch) {
        			if(i == newNode.label.length() && i == key.length()) {
        				newNode.isWord = true;
        				charMatch = true;
        				spot = true;
        			}
        			else if(i == newNode.label.length()){
        				charMatch = true;
        				if(newNode.firstChild == null) {
        					TrieNode child = new TrieNode(key.substring(i), null, null, true, '\u0000');
        					child.leadingChar = key.charAt(i);
        					newNode.firstChild = child;
        					spot = true;
        				}
        			}
        			else if(i == key.length()) {
        				charMatch = true;
        				TrieNode br = new TrieNode(newNode.label.substring(i), null, null, newNode.isWord, '\u0000');
        				br.leadingChar = newNode.label.charAt(i);
        				br.rightSibling = newNode.firstChild;
        				newNode.label = key;
        				newNode.firstChild = br;
        				spot = true;
        			
        			}
        			else if(newNode.label.charAt(i) != key.charAt(i)) {
        				if(newNode.label.charAt(i) < key.charAt(i)) 
        					split(true,newNode.label.substring(i),key.substring(i), newNode);
        				
        				else
        					split(false,key.substring(i), newNode.label.substring(i), newNode);
        				
        				newNode.label = newNode.label.substring(0, i);
        				charMatch = true;
        			}
        			else 
        				i++;
        		}
        		
        		charMatch = false;
        		q = newNode;
        		key = key.substring(i);
	        }
        }
    } 
	        
	//split function to divide characters and make it compact
	public static void split(boolean isSplit, String first, String second, TrieNode p) {
		
		TrieNode secondTrieNode = new TrieNode("", null, null, false,'\u0000');
		secondTrieNode.leadingChar = second.charAt(0);
		secondTrieNode.label = second;
		TrieNode firstTrieNode = new TrieNode("", null, null, false,'\u0000');
		firstTrieNode.leadingChar = first.charAt(0);
		firstTrieNode.label = first;
		firstTrieNode.isWord = secondTrieNode.isWord = true;
		
		if(isSplit) {
			firstTrieNode.firstChild = p.firstChild;
			firstTrieNode.isWord = p.isWord;		
		}
		else {
			secondTrieNode.firstChild = p.firstChild;
			secondTrieNode.isWord = p.isWord;
		}
		
		p.isWord = false;
		p.firstChild = firstTrieNode;
	}
     
	//generate and fill arraylists up of 100000 and 1000 word queries
    static ArrayList<String> list = new ArrayList<String>();
    static ArrayList<String> list1000 = new ArrayList<String>();
    
    static void getList(String name) {
    	try {
    		Random rand  = new Random();
    		File file = new File(name);
    		Scanner scanner = new Scanner(file);
    		ArrayList<String> generator = new ArrayList<String>();
    		
    		while(scanner.hasNext()) {
    			generator.add(scanner.next());
    		}
    		for(int i = 0; i <= 100000; i++) {
    			int j = rand.nextInt(173528-i);
    			generator.get(j);
    			list.add(generator.get(j));
    			generator.remove(j);
    		}
    		
    		for(int i = 0; i <= 1000; i++) {
    			int j = rand.nextInt(173528-i);
    			generator.get(j);
    			list1000.add(generator.get(j));
    			generator.remove(j);
    		}
    	}
    	catch(Exception e){
    		
    	}
    }
    
    
	public static void main(String[] args) {
		
		
		getList("WORD.lst");
		prog3 newTrie = new prog3();
		for(String word: list) 
			newTrie.insert(word);
		
		fillHashTable(root, root.firstChild);

		//calcualte time trie 
		long timeStart = System.nanoTime();
		
		for(int i = 0; i < list1000.size(); i++)
			newTrie.isMember(root, list1000.get(i));
		
		long timeEnd  = System.nanoTime() - timeStart;
		
		System.out.println("Trie: " + timeEnd);
		
		//time calculations for Hash Child
		long timeStart2 = System.nanoTime();
		
		for(int i = 0; i < list1000.size(); i++)
			newTrie.isMemberHash(root, list1000.get(i));
		
		long timeEnd2  = System.nanoTime() - timeStart2;
		
		System.out.println("Hash: " + timeEnd2);
		
		

	}
	
	

}
