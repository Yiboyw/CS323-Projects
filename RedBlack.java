/*
COLLABORATION STATEMENT: THIS CODE IS MY OWN WORK. IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS. I referenced both the MIT Algorithmns book and the Princeton Algorithmns book. ___Yibo Wang___
*/

public class RedBlack {
	private Node root = new Node(null, null); // root of Binary Search Tree
	private Node nil = new Node(null, null); // nil of the Binary Search Tree why use Redblack(null,null)?
	
	private static final boolean RED = true; //if the link of the node to the parent is red, set the boolean to true
	private static final boolean BLACK = false; //if the link of the node to the parent is black, set the boolean to false
			
		//this is the Node class to construct the node
		public class Node {

			private String key; //key of the Node e.g. A
			private String value; //value of the Node e.g. 1
			private Node left, right; // links to subtrees
			//private boolean color;
			private Node parent = null;
			private boolean color;
		
			public Node(String key, String value, boolean color) {
			//this block of code is to create the Node constructor
				this.left = left;
				this.right = right;
				this.key = key; 
				this.value = value; 
				this.color = color;		
			}
			public Node(String key, String value) { //this is used to create the nil Node
				//this.left = left;
				//this.right = right;
				this.key = key; 
				this.value = value; 
				
			}
			public int compareTo(String key2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String toString(){
		    	   
		    	   String colorOfNode = "";
		    	   if (root.color == RED) {
		    		   colorOfNode+="*";
		    	   }
		    	   else if (root.color == BLACK) {
		    		   colorOfNode+="#";
		    	   }
				return colorOfNode+this.key+":"+this.value;
			}
		}
		
		public RedBlack(){
			root=new Node(null, null);
		}
		
		public static void main(String[] args) {
			
			RedBlack newTree = new RedBlack(); //creates a redblack tree
			newTree.add("A", "1");
			newTree.add("B", "2");
			newTree.add("C", "3");
		}
		
		//This private method isRed() tests the color of a node’s link to its parent to see if it's red or black
		private boolean isRed(Node x)
		{
		   if (x == null) return false;
		   return x.color == RED;
		}
		
		public void changeColor(Node n) { // changes the node's color from true to false to get the red and black
		n.color = !n.color;
		}
		
		 //This is the add method of the RedBlack Tree - it is referenced from page 315 of the MIT Algorithmns book
		 public void add(String key, String value) {
			 Node z = new Node(key, value, RED); //first I will create a new Node
			 if(root.key == null) {
			 	root = z;
			 }
			 Node y =  nil;
			 Node x =  root;

			 	while(x != y){
				 y = x; //root of the tree equals the nil node
				 //System.out.println(x);
				 if (z.key.compareTo(x.key) < 0){ //if the new Node's key is less than the root's key move it to the left
				 x = x.left; 
				 }
		    	 else if (z.key.compareTo(x.key) > 0) {//if the new Node's key is greater than the root's key move it to the right
					x = x.right;
		    	 }
			 	}
			 	z.parent = y; //sets the Node z's parent equal to the nil node
			
			 	if (y.equals(y)) { //if y equals to the newTree's nil node
				root = z;
			 	}
	
			 	else if (z.key.compareTo(y.key) < 0) {
			 		y.left = z;	
			 	}
			
			 	else if (y.right.compareTo(z.key) == 0){
				z.left = nil;
				z.right =  nil;
				z.color = RED;
			 	}
			 	insertFixup(key, value);
		 }
		 //This is the insertFixup method of the RedBlack Tree - it is referenced from page 316 of the MIT Algorithmns book
		 public void insertFixup(String key, String value){
			 Node z = new Node(key, value, RED); //first I will create a new Node 
			 
			 //while(z.parent.color == RED) {
			 
				 if (z.parent == z.parent.parent.left){ // if the parent of z equals z's parent's parent's left node
					 Node y = z.parent.parent.right; //set Node y equal to z's parent's parent's right node
					 if (y.color == RED) { //if Node y's color is RED
						 z.parent.color = BLACK; //set Node z's parent to black
						 y.color = BLACK;
						 z.parent.parent.color = RED;
						 z = z.parent.parent;
					 }
					 else if (z == z.parent.right) {//if Node z is equal to z.parent's right node
						 z = z.parent; //set Node z equal to z's parent
						 leftRotate(key, value);
						 z.parent.color = BLACK;
						 z.parent.parent.color = RED;
						 rightRotate(key, value);
					 }
					 else {
							 z = z.parent;
							 rightRotate(key, value);
							 z.parent.color = BLACK;
							 z.parent.parent.color = RED;
							 leftRotate(key, value);
					 }
			 
				 }
			 }

		 
		//this leftRotate method is to do a left rotation on a root - referenced from page 313 of the MIT Algorithmsn book
		 public void leftRotate(String key, String value){
			 Node z = new Node(key, value, RED); //first I will create a new Node 
			 Node y = nil;
			 Node x = root;
			 
			 y = x.right;//set y
			 x.right = y.left; //turns x's left subtree into y's right subtree
			 
			 if (y.left != root){
				 y.left.parent = x; 
				 
			 }
			 y.parent = x.parent;
			 if (x.parent == nil) {
				 
				 root = y;
			 }
			 else if (x == x.parent.left) {
				 x.parent.left = y;
			 }
			 else {
				 y.left = x;
				 x.parent = y;
			 }
		 }
			 //this rightRotate method is to do a right rotation on a root 
			 public void rightRotate(String key, String value){
				 Node z = new Node(key, value, RED); //first I will create a new Node 
				 Node y = nil;
				 Node x = root;
				 
				 y = x.left;//set y
				 y.left = x.right; //turns y's left subtree into x's right subtree
				 
				 if (x.right != nil){
					 x.right.parent = y; 
					 
				 }
				 x.parent = y.parent;
				 if (y.parent == nil) {
					 
					 root =  x;
				 }
				 else if (y == y.parent.right) {
					 y.parent.right = x;
				 }
				 else {
					 y.right = y;
					 y.parent = x;
				 }
			}
			 
			//This is the toString method that prints out the Red Black Tree's values in this format: (key:value (left-subtree) (right-subtree))
			 public String toString(){
				 return this.toString(this.root);
			 }
			 
			 public String toString(Node a) { //this is the helper method to the toStrin
			       if (root.equals(this.nil)){ //if the root is null, return nothing
			    	   return "()";
			       }  
			       else {
			    	   return "("+a.toString() + toString(a.left) + toString(a.right)+")";   
			       }
			}
		 
			//This search method searches for a String key in the red-black binary search tree - referenced from pg 290 of the MIT Algorithmns book
			public String search(String key) {
				return searchHelper(root, key); //calls the helper method
			}
			
			//this is the helper method to the search method
			private String searchHelper(Node x, String key) {
				if (x == null) return null; //if the root of the BST is null, return null
				
				int cmp = key.compareTo(x.key); //compares the parameter key to the root's key
				if      (cmp < 0) return searchHelper(x.left, key); //if the key in the searchHelper parameter is less than the root's key, move to the left
			    else if (cmp > 0) return searchHelper(x.right, key); //if the key in the searchHelper parameter is more than the root's key, move to the right
			    else return x.key; //else if they are the same, return the key
			}
			

			//this is the remove method of the BST - referenced from pg 455 of the Princeton Sedgewick Algorithmns book
			
			public Node min() { //this method returns the smallest root in the Red Black tree
			     return min(root);
			  }
			 
			private Node min(Node x) { //this is the min Helper method
				// If the left link of the root is null, the smallest key in a BST is the key at the root. However, if the left link is not null, 
				//the smallest key in the BST is the smallest key in the subtree rooted at the node referenced by the left link.
			     
				if (x.left == null) return x;
			     return min(x.left);
			 }
			
			public void deleteMin(){ //this method deletes the smallest key in the Red Blacl tree
				root = deleteMin(root);
			}
			
			private Node deleteMin(Node x) {
			
				if (x.left == null) return x.right;
				x.left = deleteMin(x.left);
				return x;
			}
			

			private Node delete(Node x, String key) {
				
				if (x == null) return null;
				int cmp = key.compareTo(x.key);
				if (cmp < 0) x.left = delete(x.left, key);
				if (cmp > 0) x.right = delete(x.right, key);
				
				else{
					if(x.right == null) return x.left;
					if (x.left == null) return x.right;
					Node t = x;
					x = min(t.right);
					x.right = deleteMin(t.right); 
					x.left = t.left;
				}
				return x;
			}			
} 
