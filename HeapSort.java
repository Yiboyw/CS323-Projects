public class HeapSort{ 
	
	/*  COLLABORATION STATEMENT: 
	 *  THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
	 *	A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - YIBO WANG
	 *  I worked on this code by referencing the MIT Introduction to Algorithms book and collaborated with a CS323 classmate Jian Chen and got help from CS323 TA Paul. 
	*/
	
	public static void main(String[] args){

		String[] aStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I"}; //initializes array with 20 indexes
		String[] aStrings2 = {"G","F","E", "D", "C","M", "B", "A"};
		
		//buildMaxHeap(aStrings, 9);
		//buildMinHeap(aStrings, 11);
		
		/*
		int index = 0;
		for (index =0; index < aStrings.length; index++){
			System.out.println("MaxHeap index " + index + " element in index " + aStrings[index]);
		}
		*/
		
		/*
		String[] selectOutput = selectionSort(aStrings2,8,false);
		
		for(int i=0;i<7;i++) {
			System.out.print(selectOutput[i]);
		}
		*/
		
	}
	
	//creates a parent integer that stores the index of the array for the parent
	public static int parent(int i) { 
		return ((i+1)/2)-1; //have to test this return statement and make sure this parent method returns the index
	}
	
	//creates an left integer that stores the index for the left Nodes
	public static int left(int i) {
		return 2 *i+1;
	}
	
	//creates a right integer that stores the index for the right Nodes
	public static int right(int i){ 
		return 2*i + 2;
	}
	
	//This is the build Max Heap method - which build a max heap recursively 
	public static void buildMaxHeap(String[] A, int n){
		for(int j=(n+1)/2;j>=0;j--){
		buildMaxHeapHelper(A, j, n); //calls the buildMaxHeapHelper
		}
	}
	
	public static void buildMaxHeapHelper(String[] A, int current, int n){
		int l = 1; //initialize an integer l - which will store the index of the left Node - let's say left is 
		l = left(current); //creates l = left(i) - based on pg 154 of the MIT Algorithmns book on Max- Heapify
	    int r = 1;  //initialize an integer r - which will store the index of the right Node 
		r = right(current); //creates r = right(i) - based on pg 154 of the MIT Algorithmns book on Max- Heapify
		int largest; //integer largest will store the index of the largest number in the array
		
				if (l <= n-1 && A[l].compareTo(A[current]) > 0){	//checks for one child
						 //compares the strings
						     largest = l;
				}
				else {
							largest = current; //else the largest index in the array is set to the parameter n
				}
				if (r <= n-1 && (A[r].compareTo(A[largest])) > 0){ //check for two children
							 //compares the strings
								 largest = r;
				}    
			   
				if (largest != current){
			    	// creates a temp integer that will store n - the parent Node -  during the swap between n and largest
			    	String temp = A[current];
			    	A[current] = A[largest];
			    	A[largest] = temp;
			    	buildMaxHeapHelper(A, largest, n); //finishes the recursive method - largest = 3
			    }	
	}
	
	//This is the build Min Heap method - which build a min heap recursively.
	//The compareTokey function returns -1 if the character precedes.  For example, B compare to C return -1
	//The compareToKey function returns 1 if the character comes after. For example, C compare to B returns 1
	
	public static void buildMinHeap(String[] A, int n){ //referenced from page 154 MAX-HEAPIFY(A,i)
		
		for(int i = A.length/2; i > 0; i--){
			buildMinHeapHelper(A, i, n); //calls the buildMaxHeapHelper
		}
	}
	public static void buildMinHeapHelper(String[] A, int current, int n){
		int l = left(n);
	    int r = right(n); //creates r = right(i) - based on pg 154 of the MIT Algorithms book on Max-Heapify
		int smallest = 0; //integer smallest will store the index of the smallest number in the array
		
				if (l <= n-1  && A[l].compareTo(A[n]) < 0){	
						     smallest = l; //set left Node equal to the smallest 
				}
					else {
							 smallest = n; //else the smallest index in the array is set to the parameter n
						}
				if (r <= n-1 && A[r].compareTo(A[smallest]) < 0){ 
							 smallest = r; 
				}
			    if (smallest != n){
						 // creates a temp integer that will store n - the parent Node -  during the swap between n and largest
						   String temp = A[n];
						   A[n] = A[smallest] ;  
						   A[smallest] = temp;
						  buildMinHeap(A, smallest); //finishes the recursive method - largest = 3
				}
	
	}
	
	//This is a switch method that switches the index elements in a String[] array x
	public static void switchPosition(String[] x, int first, int second) {
		String temp = x[second]; 
		x[first] = x[second];
		x[second] = temp;
	}
	
	//This addToHeap method adds a String to the String[] x array
	public static boolean addToHeap(String s, String[] x, int n){
		x = new String[n]; //sets n equal to the number of element used in the String x array
		
		boolean maxOrMin = true; //initialize a boolean called maxOrMin
		
		if(n<=1) {
			maxOrMin = false; //if the # of element in the String x is less than or equal to 1, the array will default to a min heap
		}
		
		if (x[0].compareTo(x[1]) > 0) { //compares the first index of the String[] x array with the second index
			System.out.println("This is a max-heap."); //The compareToKey function returns 1 if the character comes after. For example, C compare to B returns 1
		}
		else { 
			System.out.println("This is a min-heap."); //The compareTokey function returns -1 if the character precedes.  For example, B compare to C return -1
		}
		
		if(maxOrMin = true){ //if it is a max Heap, call the adddtoMaxHeapHelper to add the String s to the String[] x
			addtoMaxHeapHelper(s,x,n);
		}
		
		else {//it is a max Heap, call the adddtoMaxHeapHelper to add the String s to the String[] x
			addtoMinHeapHelper(s,x,n);
			}
		return true;
	}
	
	public static void addtoMinHeapHelper(String s, String[] x, int n){
		x[n] = s; //sets the n index of the String[] x to the String s to add the String s to the String index x
		
			if(n > 0) {
				if (x[parent(n)].compareTo(x[n]) > 0){ 
						switchPosition(x,n, parent(n));	 //perform the switch
						n = parent(n); //sets n to parent index
				}
			}
	}
	
	public static void addtoMaxHeapHelper(String s, String[] x, int n){
		x[n] = s; //sets the n index of the String[] x to the String s to add the String s to the String index x
		
			if(n > 0) {
				if (x[parent(n)].compareTo(x[n]) < 0){ 
						switchPosition(x,n, parent(n));	 //perform the switch
						n = parent(n); //sets n to parent index
				}
			}
	}
	
	public static String toTreeString(String[] x, int n){
		
		return toTreeStringHelper(x, n, 0); //calls the toStringHelper starting from the root of the tree
	}	
	
	public static String toTreeStringHelper(String[] x, int n, int index) {
		if (index >=n){ //if the actual index is greater than n - number of number of elements used in the array - then return an empty string 
			return "";
		}
		else { 
			return "(" + x[index] + toTreeStringHelper(x,n,left(index)) + toTreeStringHelper(x,n,right(index)) + ")"; 
		//else recursively return the elements in the string going down the tree from the left and right
		}
	}
	
	public static void heapSort(String[] A, int n, boolean descending) { //referenced from page 160 from the MIT Algorithmns textbook - not sure if this meethod works 
		if (!descending){ //if descending is not true, the elements are sorted low to high
			buildMinHeap(A, 9); //buildMaxHeap(A);
			for (int i = n-1; i>=1; i--){ 
			switchPosition(A, 1, i); //calls the switchPosition method
			n--;
			buildMinHeap(A,1); 
			}
		}
		else if (!descending){ //if descending is not true, the elements are sorted low to high
			buildMaxHeap(A,1);
			for (int i = n-1; i>=1; i--){ 
			switchPosition(A, 1, i); //calls the switchPosition method
			 n--;
			buildMaxHeap(A,1); 
			}
		}
	}

	//This method is for selection sort
	public static String[] selectionSort(String[] x, int n, boolean descending){
		//initialize some variables:
		int a; 
		int b; 
		int maximum = 0; //represents the maximum integer in the String[] x array
		String temp; //string temp is used for the swap
		String[] output = new String[x.length];
		
		if (!descending){ //if descending is not true, the elements are sorted low to high
			for (a = 0; a<n-1; a++){
				int min = minElement(x);
				output[a] = x[min];
				x[min] = "zzzzz";
				//set the minimum number to zzzzz to show that the element in x[min] how has been swapped and will be replaced with zzzzzz which has the highest ASCII value
			}
		}
		else if (descending){ //if descending is true, the elements are sorted high to low
			
			//output = new String[x.length];
			
			for (a = 0; a<n-1; a++){
				int max = maxElement(x);
				
				output[a] = x[max];
				
				//x[max] = Integer.toString(-100);
				x[max] = "-100"; //sets the maximum number to -100 to show that the element has been swapped
			}
		}
		return output;
	
	}
	
	public static int minElement(String[] arr){
		int minimum = 0;
		for(int b = 0; b<arr.length;b++){
			if (arr[minimum].compareTo(arr[b]) > 0){ //if the element in the minimum index is greater than the element in the compared index 
			minimum = b; //set the int mimimum equal to the index of the minimum
			}
		}
		return minimum;
	}
	
	//The maxElement method returns the maximum element in a string arr
	public static int maxElement(String[] arr){ 
		int maximum = 0;
		for (int b = 0; b<arr.length; b++){
			if (arr[maximum].compareTo(arr[b]) < 0){ //if the element in the maximum index is less than the element in the compared index
			maximum = b;
			}
		}
		return maximum;
	}
}

	/*
	//This is the bottom-up merge sort algorithm
	public static void mergeSort2(String[] x, int n, boolean descending){
		
		if (descending) { //if descending is true, elements are sorted high to low
			int start = 0; //creates an int to store the start of the String[] x index
			int end = n;
			
			if (start < n){
				int m = (start+ n/2); //stores the middle index of the String array[] x
				mergeSort2(x, start, descending);
				mergeSort2(x, m, descending);
				
				mergeSort2Helper(x, start, m, end);
				mergeSort2Helper(x, m+1, m, end);
			}
		}
	}
	*/
	
	/*
		//this mergeSort2Helper method basically combines the two sections of the arrays together
		public static void mergeSort2Helper(String[] x, int start, int m, int end) {
			x  = new String[10];
			
			int length = end-start+1;
			String[] temp = new String[length];
			int i = start;
			int j = m+1;
			int c = 0; 
			
			while(i<=m &&j<=end){
	            if(x[i].compareTo(x[j]) <0 ){
	                temp[c] = x[i];
	                i++;
	                c++;
	            }else{
	                temp[c]=x[j];
	                j++;
	                c++;
	            }
	        }
	        while(i<=m){
	            temp[c]=x[i];
	            i++;
	        }
	        while(j<=end){
	        temp[c]=x[j];
	        j++;
	        }
	        c=0;
	        for(int t=start;t<=end;t++,c++){
	            x[t]=x[c];
	        } 
	    }
		
		/*
		String[] l1 = null;
		System.arraycopy(x, 0, l1, 0, n/2); //copies the array x from 0 to n/2
		
		for(int index = 0; index < n; index++) {
		System.out.println(l1[index]);
		}
		
		String[] l2 = null;
		System.arraycopy(x, 0, l2, n/2, n); //copies the array x from n/2 to n
		*/

	

