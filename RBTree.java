//I am working on this assignment alone
//only with this semester's material

public class RBTree {
	
	RBTNode nil = new RBTNode(null,null);
	RBTNode root;
	
	public RBTree(){
		this.root = nil;
		nil.key = "This is a preset key for nil to avoid null pointer exception";
		nil.val = null;
		nil.color=false; //set nil to be black
	}
	
	class RBTNode{
		String key;
		String val;
		
		RBTNode left; //reference to the left, right, parent
		RBTNode right;
		RBTNode p; //parent of the RBTNode
		
	    boolean color;
		//If color is red, then ture
		//If color is black, then false
		
		public RBTNode(String k, String val) {
			this.key = k;
			this.val = val;
		}
		
		public String toString() {
			String colorOfNode = "";
			if (this.color) colorOfNode+="*";
			else colorOfNode+="#";
			return colorOfNode+this.key+":"+this.val;
		}
		
	}
	
	public static void main(String[] args) {
		RBTree a = new RBTree();
	
		/*
		a.insert("b", "b");
		a.insert("a", "l1");
		a.insert("c", "l3");
		a.insert("bs", "tt");
		a.insert("a","a");
		a.insert("b","b");
		a.insert("c","c");
		a.insert("d","d");
		a.insert("1", "2");
		a.insert("e","e");
		a.insert("f","f");// the property of RBTree is held, insert tested
		a.remove("e");
		a.insert("g","g");
		System.out.println(a);   // delete tested
		a.insert("h","h");
		System.out.println(a);
		a.remove("h");
		System.out.println(a);
		System.out.println(a.search("g"));//search tested
		//toString tested
		*/
		RBTree t = new RBTree();
		t.insert("K", "11"); 
		System.out.println(t);
		t.insert("B", "2");
		System.out.println(t);
		t.insert("N", "14");
		System.out.println(t);
		t.insert("A", "1");
		System.out.println(t);
		t.insert("G", "7");
		System.out.println(t);
		t.insert("O", "15");
		System.out.println(t);
		t.insert("E", "5");
		System.out.println(t);
		t.insert("H", "8");
		System.out.println(t);
		t.insert("D" , "4");
		System.out.println(t);
		//case 1: delete B
		t.remove("B");
		System.out.println(t);
		t.insert("B", "b");
		t.insert("C", "c");
		t.insert("F", "m");
		t.insert("I", "i");
		t.insert("J", "j");
		t.insert("L", "l");
		t.insert("M", "m");
		t.insert("P", "p");
		t.insert("Q", "q");
		t.insert("R", "r");
		System.out.println(t);
		//case 4
		t.remove("B");
		System.out.println(t);
		//case 2
		t.remove("I");
		System.out.println(t);
		//case 3
		t.remove("E");
		System.out.println(t);
		
		//test search
		System.out.println(t.search("G"));

	}
	//Left-Rotate
	//Copied from Algorithm, Cormen et al
	
	public void leftRotate(RBTNode x){
		RBTNode y = x.right; //set y
		
		x.right = y.left;    //turn y's left subtree into x's right subtree
		if (!y.left.equals(this.nil))
			y.left.p = x;
	
		y.p = x.p;  //link x's parent to y
		if (x.p.equals(this.nil))
			this.root = y;
		else if (x.equals(x.p.left))
			x.p.left = y;  
		else x.p.right = y;
		//,,,
		y.left = x;//put x on y's left
		x.p = y;
		}
		
	//right-Rotate
	//Copied from Algorithm, Cormen et al

	public void rightRotate(RBTNode y){
		RBTNode x = y.left;
			
		y.left = x.right;
		if(!x.right.equals(this.nil) )			
			x.right.p = x;

		x.p = y.p; //link y's parent to x;
		if(y.p.equals(this.nil))
			this.root = x;
		else if (y.equals(y.p.left))
			y.p.left = y;
		else y.p.right = y;

		x.right = y;
		y.p = x;	
		}
	
	//a helper that can produce a new node
	//only set key and value
	private RBTNode newNode(String key, String value){
		RBTNode a = new RBTNode(key, value);
		return a;
	}
	
	public void insert(String key, String value){
		RBTNode z = newNode(key, value); //create a new Node
		RBTNode y = this.nil;    //y points to nil
		RBTNode x = this.root;   //x points to the root;
		
		while(!x.equals(this.nil)){ //pass down the tree
			y = x; //y points to x
			if (z.key.compareTo(x.key)<0){
				x = x.left;
			}else if(z.key.compareTo(x.key)==0){//if have the same key, renew the value
				x.val = z.val;                  //just renew the value and terminate the method
				return;             //in this case -->Fix-up is not needed
			}else	x = x.right;
		}
		z.p = y;       
		if(y.equals(this.nil)){   //if the parent of the newly inserted node is nil-->root
			this.root = z;
		}else if(z.key.compareTo(y.key)<0)
			y.left = z;
		else y.right = z;
		
		z.left = this.nil;
		z.right = this.nil;
		z.color = true;	
		this.insertFixUp(z); //To restore the RBT properties
		//System.out.println(" "+z.p.p.left);
	}
	
	//insert fix up
	//Copied from Algorithm, Cormen et al
	public void insertFixUp(RBTNode z){

		while(z.p.color){
			if(z.p.equals(z.p.p.left)){
				RBTNode y = z.p.p.right;
				if (y.color){				
					z.p.color = false;     //case 1 : uncle y is red
					y.color = false;       //case 1   
					z.p.p.color = true;    //case 1
					z = z.p.p;             //case 1
				}else{
					if(z.equals(z.p.right)){//case 2 z's uncle y is black and
						z = z.p;				 //case 2   z is a the right child
						this.leftRotate(z);  //case 2
					}
					z.p.color = false;     //case 3    z's uncle is black
					z.p.p.color = true;    //case 3    z is a left child
					this.rightRotate(z.p.p); //case 3
				}
			}else{
				RBTNode y = z.p.p.left;   //this is the symmetric with case 1
				//System.out.println(y.color+"11");
				if(y.color){
					z.p.color = false;
					y.color = false;
					z.p.p.color = true;
					z = z.p.p;
				}else{
					if (z.equals(z.p.left)){ //symmetrical with case 2
						z = z.p;
						this.rightRotate(z);
					}
					z.p.color = false;  //symmetrical with case 3
					z.p.p.color = true;
					this.leftRotate(z.p.p);
				}
			}
		}
		this.root.color = false; //Set the root to be black
	}
	
	//Copied from Algorithm, Cormen et al
	//used in the delete method
	// 
	public void transplant(RBTNode u, RBTNode v){
		if(u.p.equals(this.nil))
			this.root = v;
		else if (u.equals(u.p.left))
			u.p.left = v;
		else u.p.right = v;
		v.p = u.p;
	} //transplant u
	//how about u's children?
	
	/*
	 * This is a helper method used to find the minimum value of
	 * a subtree with root "subtree", it return the minNode and then remove it from the tree
	 */
	private RBTNode minNode(RBTNode subtree){
		RBTNode helper = subtree;
		while(!helper.left.equals(this.nil) ){
			helper = helper.left;
		}
		return helper;
	}

	//Copied from Algorithm, Cormen et al
	public void delete(RBTNode z){
		//System.out.println(z);
		RBTNode x; 			//declare x (value assinged due to difference scenerio)
		RBTNode y = z;    
		boolean yOriginalColor = y.color; //store z's color
		if(z.left.equals(this.nil)){      //zero or 1 child
			x = z.right;
			this.transplant(z, z.right);
		}else if (z.right.equals(this.nil)){   //same as BST delete
			x = z.left;                       
			this.transplant(z, z.left);
		}else{ //two child
			y = this.minNode(z.right);
			yOriginalColor = y.color;
			x = y.right;
			if(y.p.equals(z))
				x.p = y;
			else{
				this.transplant(y, y.right);
				y.right = z.right;
				y.right.p = y;
			}
			this.transplant(z, y);
			y.left = z.left;
			y.left.p = y;
			y.color = z.color;
		}
		if (!yOriginalColor)//if node deleted is black
			this.deleteFixUp(x);
	}
	
	//Copied from Algorithm, Cormen et al
	public void deleteFixUp(RBTNode x){
		while(x.equals(this.nil)&&!x.color){
			if (x.equals(x.p.left)){
				RBTNode w = x.p.right;
				if(w.color){   // case 1 x's sibling w is red
					w.color = false;
					x.p.color = true;
					this.leftRotate(x.p);
					w = x.p.right;
				}
				if (!w.left.color && !w.right.color){
					//case 2L sibling w is black, and both of w's children are black
					w.color = true;
					x = x.p;
				}else{ //case 3 x's sibling w is black, w.left= red, w.right = black
					if(!w.right.color){
						w.left.color = false;
						w.color = true;
						this.rightRotate(w);
						w = x.p.right;
					}
					w.color = x.p.color; //case 4 x's sibling w is black and w's right child is red
					x.p.color = false;
					w.right.color = false;
					this.leftRotate(x.p);
					x = this.root;
				}
			}else{ // this is completely symetric with the problems addressed above
				RBTNode w = x.p.left;
				if(w.color){
					w.color = false;
					x.p.color = true;
					this.rightRotate(x.p);
					w = x.p.left;
				}
				if(!w.right.color && !w.left.color){
					w.color = true;
					x = x.p;
				}else{
					if(!w.left.color){
						w.right.color = false;
						w.color = true;
						this.leftRotate(w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = false;
					w.left.color = false;
					this.rightRotate(x.p);
					x = this.root;
				}	
			}
		}
		x.color = false;
	}
	
	
	public void remove(String key){
		RBTNode m = this.search(key);
		if(m==null);
		else
		this.delete(this.search(key));
	}
	
	
	public RBTNode search(String k){
		return this.find(k, this.root);
	}
	//Recursive helper for search method
	private RBTNode find(String k, RBTNode n) {
		if (n.equals(this.nil)) {
			// the element is not in the tree
			return null;
		} else if (k.equals(n.key)) {
			// we found the element
			return n;
		} else if (k.compareTo(n.key) < 0){
			// go to the left
			return find(k, n.left);
		} else {
			// go to the right
			return find(k, n.right);
		}
	}
	
	//print the tree only showing the color of each node
	//this is a helper method for checking red/black properties
	//true = red, false = black
	public String printColor(){
		return printColor(this.root);
	}
	
	//recursive helper for printColor()
	private String printColor(RBTNode n){
		if(n.equals(this.nil)){
			return "";
		}
		else 
			return "( " +n.color+" "+printColor(n.left)  + printColor(n.right) +" )";
	}
	
	// print the tree recursively with the format: (node(left child)(right child))
	public String toString() {
		return toString(this.root);
	}

	// recursive method for toString method
	private String toString(RBTNode n) {      //left child --> node --> right child 
		if (n == null) {
			return "";
		}
		else if(n.equals(this.nil))
			return "( )";
		else {
			String colorOfNode = "";
			if (n.color) colorOfNode+="*";
			else colorOfNode+="#";
			return " ("+n.toString()+" "+this.toString(n.left) + this.toString(n.right)+ " )";
		}
	}
}

