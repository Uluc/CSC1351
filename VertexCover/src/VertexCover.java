import java.util.ArrayList;

public class VertexCover {
  
	static class Node  
	{  
	    int weight;
	    int id;
	    int x;
	    Node left, right;  
	};  
	
	static ArrayList<Node> fullArray = new ArrayList<>();
	static ArrayList<Node> weightedArray = new ArrayList<>();
	  
	static Node newNode(int data)  
	{  
	    Node temp = new Node(); 
	    temp.id = fullArray.size();
	    temp.x = 0;
	    temp.weight = data;  
	    temp.left = null;
	    temp.right = null; 
	    fullArray.add(temp);
	    return temp;  
	} 
	
	static int vertexCover(Node root)  
	{  
	    if (root == null)  
	    	return 0;  
	    if (root.left == null && root.right == null) 
	    	return 0; 
	    if(root.x != 0)
	    	return root.x;
	  
	    int withRoot = root.weight;
	    
	    withRoot += vertexCover(root.left) + vertexCover(root.right);  
	  	      
	    int withoutRoot = 0;  
	    if (root.left!=null)  {
	    	withoutRoot += root.left.weight;
	    	withoutRoot += vertexCover(root.left.left) + vertexCover(root.left.right);  
	    }
	    if (root.right!=null)  {
	    	withoutRoot += root.right.weight;
	    	withoutRoot += vertexCover(root.right.left) +  vertexCover(root.right.right);  
	    }
	    
	    root.x = Math.min(withRoot, withoutRoot);  
	    
	    if(withRoot < withoutRoot) {
	    	addWeighted(root);
	    	fullArray.remove(root);
	    }
	    else {
	    	if(root.left != null) {
	    		addWeighted(root.left);
	    		fullArray.remove(root.left);
	    	}
	    	if(root.right != null) {
	    		addWeighted(root.right);
	    		fullArray.remove(root.right);
	    	}
	    		
	    }
	    
	    return root.x;  
	}  
	
	static void addWeighted(Node root) {
		if(!fullArray.contains(root)) {
			return;
		}
		else {
			weightedArray.add(root);
		}
			
	}
	 
	 
	public static void main(String[] args) {
		
		Node root = newNode(22);	
	    root.left = newNode(10); 
	    root.left.left = newNode(50); 
	    root.left.right = newNode(14); 
	    root.left.right.left = newNode(12); 
	    root.left.right.right = newNode(16); 
	    root.right = newNode(24); 
	    root.right.right = newNode(27);
	    root.right.left = newNode(31);
	    root.right.left.left = newNode(45);
	   
	    int num = vertexCover(root);
	    System.out.println(num);
	    for(int i = 0; i < weightedArray.size(); i++) {
	    	System.out.print(weightedArray.get(i).weight + " ");
	    }
	    
	    System.out.println("\nSize: " + weightedArray.size());
	    
	    
		
	}

}


