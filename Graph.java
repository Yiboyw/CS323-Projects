import java.util.*;

/*  COLLABORATION STATEMENT: 
 *  THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
 *	A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - YIBO WANG
 *  I worked on this code by referencing the MIT Introduction to Algorithms book and collaborated with a CS323 classmate Jian Chen 
 *  and got help from CS323 TA Lindsey. 
*/

public class Graph {

	static Map<String, Vertex> vertices; //this initializes the vertices in the graph
	
	static Map<String, ArrayList<Edge>> adjacent;//creates an adjacency list
	
	static List<String> searchSteps; //creates a list of Strings that record snapshots of the graph during the BFS and DFS algorithms
	
	static int vertexNumber = 0; //stores the number of vertex's in the graph
	
	static List<String> pathList = new ArrayList<String>(); //create a global variable that will store the pathList for the path method 
	static List<String> topList = new ArrayList<String>(); //create a global variable that will store the topological list for the bfs/dfs methods
	static Graph newGraph;
	
	public static void main(String[] args) { //this is the main method
		
		newGraph = new Graph();
		
		newGraph.addVertex("q");
		newGraph.addVertex("s");
		newGraph.addVertex("v");
		newGraph.addVertex("w");
		newGraph.addVertex("t");
		newGraph.addVertex("x");
		newGraph.addVertex("z");
		newGraph.addVertex("y");
		newGraph.addVertex("r");
		newGraph.addVertex("u");
		//System.out.println("vertexNumber "+ vertexNumber);
		
		newGraph.addEdge("q", "s", 10.0); 
		newGraph.addEdge("q", "w", 5.0);
		newGraph.addEdge("q", "t", 5.0);
		newGraph.addEdge("s", "v", 2.0);
		newGraph.addEdge("v", "w", 2.0);
		newGraph.addEdge("w", "s", 10.0);
		newGraph.addEdge("t", "x", 10.0);
		newGraph.addEdge("x", "z", 10.0);
		newGraph.addEdge("t", "y", 10.0);
		newGraph.addEdge("r", "y", 10.0);
		newGraph.addEdge("r", "u", 10.0);
		newGraph.addEdge("u", "y", 10.0);
		newGraph.addEdge("y", "q", 10.0);
		
		//to create antiparallel edges
		newGraph.addEdge("s", "q", 10.0);
		newGraph.addEdge("w", "q", 5.0);
		newGraph.addEdge("t", "q", 5.0);
		newGraph.addEdge("v", "s", 2.0);
		newGraph.addEdge("w", "v", 2.0);
		newGraph.addEdge("s", "w", 10.0);
		newGraph.addEdge("x", "t", 10.0);
		newGraph.addEdge("z", "x", 10.0);
		newGraph.addEdge("y", "t", 10.0);
		newGraph.addEdge("y", "r", 10.0);
		newGraph.addEdge("u", "r", 10.0);
		newGraph.addEdge("y", "u", 10.0);
		newGraph.addEdge("q", "y", 10.0);

		
		//System.out.println(newGraph.toString());
		
      	//newGraph.depthFirstSearch();
		//System.out.println(searchSteps);
		//searchSteps.clear();
		
		newGraph.breadthFirstSearch("r");
	    System.out.println(searchSteps);
		//System.out.println("line 59 path: "+ path("r", "v"));
		//System.out.println("Line 60 pathWeight "+ pathWeight("q", "v")); //should be 32
		//System.out.println("Line 61 topsort: " + topologicalSort());
		//System.out.println("totalWeight: "+totalWeight());
		
		//System.out.println("prim result: " + prim("q").toString());
		
		//System.out.println("bellmanFord " + bellmanFord("q"));
		System.out.println("dijkstra " + dijkstra("s"));
		
	}
	
	public Graph() { //creates the graph object
		vertices = new TreeMap<String, Vertex>();
		adjacent = new HashMap<String, ArrayList<Edge>>();
		searchSteps = new ArrayList<String>();
	}

	public void addVertex(String key) { //creates a method called addVertex that adds a string called key as a new vertex in the graph
		Vertex v = new Vertex(key); //creates a new Vertex called v and gives it a key
		vertices.put(key, v); //Put the vertex into the vertices 
		adjacent.put(key, new ArrayList<Edge>()); //put the edge into the adjacency list
		vertexNumber++;
	} 
	
	public void addEdge(String source, String target, double weight) { 
		//creates a method called addEdge that adds a string called target as a new edge from the source edge and gives target a weight
		if (!vertices.containsKey(source)) {
			addVertex(source);
		}
		if (!vertices.containsKey(target)) {
			addVertex(target);
		}
		ArrayList<Edge> edges = adjacent.get(source);
		Edge e = new Edge(vertices.get(source), vertices.get(target), weight);
		edges.add(e);
	}
	
	public String toString() { //this is the toString method that prints out the vertices in the graph
		// You modify this method
		StringBuilder s = new StringBuilder(); //creates a Stringbuilder called s
		s.append("digraph g{" + "\n");
		
		for (String key : vertices.keySet()) { //for each vertices, adds the vertices to the stringbuilder s
			//s.append(vertices.get(key) 
			Vertex x = vertices.get(key);
			
			if(x.color == Vertex.WHITE) {
				s.append("\"" + x.label + "\"" + "[label=\"" + x.label +"\"" + ", style=dashed];" + "\n");
				//adds the Vertex [label = "x.label", style=dashed]
				//e.g. A[label="A",style=dashed]
			}
			else if(x.color == Vertex.GRAY) {
				s.append("\"" + x.label + "\"" +"[label=\"" + x.label + "\"" + ", fillcolor = gray, style=filled];" + "\n");
			}
			else if (x.color == Vertex.BLACK) {
				s.append("\"" + x.label + "\"" +"[label=\"" + x.label + "\""+ ", style=bold];" + "\n");
			}
		}
	
		for (String key : vertices.keySet()) {//traverses all the vertices 
			ArrayList<Edge> edges = adjacent.get(key);
			for (Edge e : edges) { //for each vertices, adds all the adjacent edges to it
				//s.append(e + "\n");
				s.append("\""+ e.source + "\"" +"->"+ "\"" + e.target + "\""+ "[label=\"" + e.flow + "/"+ e.weight+ "\"" + "];" + "\n"); 
				//adds the Vertex -> Vertex and [label = edge's weight]
				//e.g. A->B[label="2.0"]
			}
		}
		return s.toString() + "}"; //returns the appended version of the Stringbuilder s
	}
	
	public void breadthFirstSearch(String startVertex) {

		// You implement this method
		for (String key : vertices.keySet()) {
			Vertex x = vertices.get(key);
			if(!key.equals(startVertex) ){ //if the key is not the startVertex
				x.color = Vertex.WHITE; //set the color of the Vertex to White
				x.distance = Double.POSITIVE_INFINITY; //set the distance to positive infinity
				x.parent = null; //set the parent to null
				searchSteps.add(toString()); //add the current representation to the searchSteps list
			}
			
			else{ //if the key is the startVertex
				x.color = Vertex.GRAY; //set the color of the Vertex to Gray
				x.distance = 0.0; //set the distance to 0 
				x.parent = null; //set the parent to null
				x.discoverStep = this.searchSteps.size();
				searchSteps.add(toString()); //add the current representation to the searchSteps list
		    }
			
		}
		Queue newQueue = new Queue(); //intializes a new queue called newQueue
		
		for (String key : vertices.keySet()) {
			Vertex s = vertices.get(key);
			
			if(key.equals(startVertex) ){
			s.label = startVertex; //set the Vertex s's label to the starting Vertex
			newQueue.enqueue(s); 
			}
		
			while(!newQueue.isEmpty()){
				Vertex u =  newQueue.dequeue(); //dequeue the Vertex u 
				ArrayList<Edge> edges = adjacent.get(u.label); //gets adjacent Edges of u
				for(Edge E: edges){ //for the edges in the adjacency list
						Vertex target = E.target;
					if (target.color == Vertex.WHITE) {
						target.color = Vertex.GRAY; //set the adjacent vertices to gray
						target.distance = u.distance + E.weight; //updates the vertex with the edges weight
						target.parent = u;
						target.discoverStep = this.searchSteps.size();
						newQueue.enqueue(target);
					}
					u.color = Vertex.BLACK;	//set the vertex color to black
					u.finishStep = this.searchSteps.size();
					searchSteps.add(toString()); //add the current representation to the searchSteps list
					
				}
			}
		}
	}

	//This depthFirstSearch Method is referenced from pg 604 of the MIT Algorithmns book
	public void depthFirstSearch() {
		// You implement this method
		
		for (String key : vertices.keySet()) {
			Vertex u = vertices.get(key);
			u.color = Vertex.WHITE; //set the Vertex color to white
			u.parent = null; //set the parent of the Vertex to pull
			searchSteps.add(toString()); //add the current representation to the searchSteps list
			
		}
		for (String key : vertices.keySet()) {
			Vertex u = vertices.get(key);
			if(u.color == Vertex.WHITE) {//if the vertex u's color is white
				u.distance = 0.0;
				depthFirstSearchVisit(u); //call the depthFirstSearchVisit method
			}
		}	
	}
	//This is the depthFirstSearchVisit helper method
	public void depthFirstSearchVisit(Vertex u) {
		u.color = Vertex.GRAY; //set the Vertex color to Gray
		u.discoverStep = this.searchSteps.size();
		searchSteps.add(toString()); //add the current representation to the searchSteps list
		ArrayList<Edge> edges = adjacent.get(u.label);
		
		for (Edge E: edges){
			Vertex v = E.target;
			
			if(v.color == Vertex.WHITE){ //if the vertex v's color is white
			v.parent = u; //set v's parent to u
			v.distance = u.distance + E.weight; //updates the vertex v with the edges weight
			depthFirstSearchVisit(v); //recursively call the depthFirstsearchVisit method
			}
	
		}
		u.color = Vertex.BLACK; //set the Vertex color to black
		u.finishStep = this.searchSteps.size();
		topList.add(0, u.label); //add the vertex u to the beginning of the topList list
		
		searchSteps.add(toString()); //add the current representation to the searchSteps list
	}
	
	public static List<String> path(String startVertex, String endVertex) {
		// You implement this method
		 
		Vertex v = vertices.get(endVertex);
		
		if (startVertex.equals(endVertex)){ //if the startVertex is the same as the endVertex
			pathList.add(startVertex); //add the startVertex to the pathList
			return pathList;
		}
		else if (v.parent == null) {//if the vertex with the label of endVertex is null
			System.out.println("no path is available from " + startVertex + " to "+ endVertex);
			return pathList;
		}
		else {
			path(startVertex, v.parent.label); //else recursively call the path method and add the Vertex v's parent.label to the pathlist
			pathList.add(v.label);
			return pathList;	
		}
		
	}
	
//	public static double pathWeight(String startVertex, String endVertex) {
//		// You implement this method
//		
//		double pathWeightDouble = 0.0; //initialize a double called pathweightDouble that is equal to zero
//		Vertex v = vertices.get(endVertex);
//		
//		if (startVertex.equals(endVertex)) { // when the startVertex eauals endVertex return 0.0
//		return 0.0;
//		}
//		
//		else if (v.parent == null){ //if the vertex v's parent is null return positive infinity
//		return Double.POSITIVE_INFINITY;
//		}
//		
//		else {
//			pathWeightDouble = v.distance; 
//			//set pathWeightdouble to the vertex v's distance - which in the BFS/DFS algorithm equals u.distance + E.weight	
//		}
//		return pathWeightDouble;
//	
//	}
//	public static List<String> topologicalSort() {
//		// You implement this method
//		return topList; //return the list 
//	}
//	
//	public static double totalWeight(){//returns the sum of the weights of all the edges in the graph 
//		
//		double pathWeightDouble = 0.0; //initialize a double called pathweightDouble that is equal to zero 
//		
//		//This is a double for-loop:
//		for (String key : vertices.keySet()) { //the first for loop is to go through the vertices
//			for(Edge edge : adjacent.get(key)){ //the second for loop is to add the adjacent edges of the vertice's weights together
//			pathWeightDouble += edge.getWeight(); 
//			}
//		}
//			
//		return (pathWeightDouble)/2; //divide by two since the edges are antiparallel
//	}
	
//	public static Graph prim(String root){
//		
//		return primHelper(root, newGraph); //calls the primHelper method
//	}
//	
//	public static Graph primHelper(String root, Graph newGraph1){
//		
//		ArrayList<Vertex> visited = new ArrayList<Vertex>(); //stores all visited vertices of the graph
//		ArrayList<Edge> output = new ArrayList<Edge>(); //stores the new edges of the minimum spanning tree
//		PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(); //initializes a new priority queue which stores the graph's adjacent edges to a vertex
//
//		for (Edge edge : adjacent.get(root)) { //gets adjacent edges of the vertex root
//			priorityQueue.add(edge); //adds the adjacent edges to the priorityQueue
//		}
//		
//		while (visited.size() != vertexNumber){//until visited.size does not equal the number of vertices in the graph - which is 10 vertices
//			//find the minimum edge
//			Edge min = priorityQueue.poll();
//			output.add(min); //adds the smallest edge to output
//			visited.add(min.target); //add the Vertex corresponding to the smallest edge to the visited arraylist
//			
//			//add new edges
//			for(Edge edge : adjacent.get(min.target.label)){
//				if(!priorityQueue.contains(edge))
//					priorityQueue.add(edge);
//			}
//		}
//		
//		Graph result = new Graph(); //create a graph called result and have it store all the edges in output
//		
//		result.addVertex("q");
//		result.addVertex("s");
//		result.addVertex("v");
//		result.addVertex("w");
//		result.addVertex("t");
//		result.addVertex("x");
//		result.addVertex("z");
//		result.addVertex("y");
//		result.addVertex("r");
//		result.addVertex("u");
//		
//		for(int i=0;i<output.size();i++){
//			result.addEdge(output.get(i).source.label, output.get(i).target.label, output.get(i).weight);
//			//result.addEdge(output.get(i).target.label, output.get(i).source.label, output.get(i).weight);
//		}
//		
//		return result;
//	}
	
	//referenced from pg 631 from the MIT Algorithmns textbook
//	public Graph kruskal(){
//		
//		Graph result = new Graph(); //create a graph called result and have it store all the edges
//		result.addVertex("q");
//		result.addVertex("s");
//		result.addVertex("v");
//		result.addVertex("w");
//		result.addVertex("t");
//		result.addVertex("x");
//		result.addVertex("z");
//		result.addVertex("y");
//		result.addVertex("r");
//		result.addVertex("u");
//		
//		ArrayList<Vertex> visited = new ArrayList<Vertex>(); //stores all visited vertices of the graph
//		ArrayList<Edge> allEdges = new ArrayList<Edge>(); //stores the new edges of the minimum spanning tree
//		ArrayList<Edge> output = new ArrayList<Edge>(); //stores the new edges of the minimum spanning tree
//
//		DisjointSet A = new DisjointSet(1); //creates an empty set A
//		
//		//for (String key : vertices.keySet()) { //for every vertex in the graph
//	    DisjointSet newSet = new DisjointSet(vertexNumber);//creates a new disjoint set for the number of vertices - which is 10
//		//}
//
//		while (visited.size() != vertexNumber){ //while the stored visited vertices is less than the total number of vertices in the graph
//			for (String key : vertices.keySet()) { //the first for loop is to go through the vertices
//				for(Edge edge : adjacent.get(key)){ //the second for loop is to go through the adjacent edges of the vertices
//					allEdges.add(edge); //adds the edges of the graph to the allEdges ArrayList
//					allEdges.sort((Comparator<? super Edge>) edge); //sorts the edges of the Graph from least to greatest
//					//System.out.println("allEdges line 393"+ allEdges);
//						//if(newSet.find(edge ) != newSet.find()){
//							
//						}
//				}
//			} 
//		
//		/*
//		if (newSet.find(u) =! newSet.find(v)){
//			//unite the edges and add edge to output 
//		}
//		*/
//		
//		for(int i=0;i<output.size();i++){
//			result.addEdge(output.get(i).source.label, output.get(i).target.label, output.get(i).weight);
//		}
//		
//		return result;
//		
//	}
	
	public static boolean bellmanFord(String source){
		initializeSingleSource(newGraph, source);
		
		for(int i = 1; i<vertexNumber-1; i++){
			for (String key : vertices.keySet()) {//traverses all the vertices 
				ArrayList<Edge> edges = adjacent.get(key);
				for (Edge e : edges) { 
					relax(e.source, e.target, e);
				}
			}
		}
		
		for (String key : vertices.keySet()) {//traverses all the vertices 
			ArrayList<Edge> edges = adjacent.get(key);
				for (Edge e : edges) {
					if (e.source.distance > e.target.distance +e.weight){
						return false;
					}
				}
		}
		return true;
	}
	
	
	public static void relax(Vertex u, Vertex v, Edge edge){
		if(v.distance > u.distance + edge.weight){
			v.distance = u.distance + edge.weight;
			v.parent = u;
		}
	}
	
	public static void initializeSingleSource(Graph newGraph, String source){
		for (String key : vertices.keySet()) {
			Vertex x = vertices.get(key);
			if(!x.equals(source)){//if the vertex x is not the source
				x.distance = Double.POSITIVE_INFINITY;
				x.parent = null;
			}
			else {
				x.distance=0; //else if the vertex x is the source, set the distance to 0
			}
		}
	}
	
	public static boolean dijkstra(String source){
			initializeSingleSource(newGraph, source);
			Set set = new HashSet(); //creates an empty set
			PriorityQueue<Vertex> newQueue = new PriorityQueue<Vertex>(); //initializes a new priority queue which stores the graph's adjacent edges to a vertex
			
			for (String key : vertices.keySet()) {
				Vertex x = vertices.get(key);
				newQueue.add(x);
			}
			
				for (Edge edge : adjacent.get(source)) { //gets adjacent edges of the vertex rouce					
					if(edge.weight < 0) {//if the edge is negative, return false
							return false;
							}
		     	}
				
			while(newQueue.isEmpty() == false){
					Vertex u = newQueue.poll(); //gets the smallest edge in the priority queue
					set.add(u); //adds the edge to the set
					
					for (Edge edge : adjacent.get(u.label)) { //gets adjacent edges of edge u
						
						if(edge.weight < 0) {//if the edge is negative, return false
							return false; 
						}
						
						//newQueue.add(edge); //adds the adjacent edges to the priorityQueue
						relax(edge.source, edge.target, edge);
					}
			}
				
			return true; //return true if the edge weights are not negative
	}
	
	
	
}	
//creates a disjointSet class for Kruskal's algorithmn
class DisjointSet{
	
	    int[] rank, parent;
		private int number;
	 
	    // Constructor for the DisjointSet
	    public DisjointSet(int number)
	    {
	        rank = new int[number];
	        parent = new int[number];
	        this.number = number;
	        makeSet();
	    }
	 
	    // Creates n sets with single item in each
	    void makeSet()
	    {
	        for (int i=0; i<number; i++)
	        {
	            // Initially, all elements are in
	            // their own set.
	            parent[i] = i;
	        }
	    }
	    
	    // Returns representative of x's set
	    int find(int x)
	    {
	        // Finds the representative of the set
	        // that x is an element of
	        if (parent[x]!=x)
	        {
	            // if x is not the parent of itself
	            // Then x is not the representative of
	            // his set,
	            parent[x] = find(parent[x]);
	 
	            // so we recursively call Find on its parent
	            // and move i's node directly under the
	            // representative of this set
	        }
	 
	        return parent[x];
	    }
	    
	    // Unites the set that has x and y
	    void union(int x, int y)
	    {
	        // Find representatives of two sets
	        int xRoot = find(x), yRoot = find(y);
	 
	        // Elements are in the same set, no need
	        // to unite anything.
	        if (xRoot == yRoot)
	            return;
	 
	         // If x's rank is less than y's rank
	        if (rank[xRoot] < rank[yRoot])
	 
	            // Then move x under y  so that depth
	            // of tree remains less
	            parent[xRoot] = yRoot;
	 
	        // Else if y's rank is less than x's rank
	        else if (rank[yRoot] < rank[xRoot])
	 
	            // Then move y under x so that depth of
	            // tree remains less
	            parent[yRoot] = xRoot;
	 
	        else // if ranks are the same
	        {
	            // Then move y under x (doesn't matter
	            // which one goes where)
	            parent[yRoot] = xRoot;
	 
	            // And increment the the result tree's
	            // rank by 1
	            rank[xRoot] = rank[xRoot] + 1;
	        }
	    }
}

class Vertex implements Comparable<Vertex>{
	
	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;

	String label; //initalize a string called label
	
	int color; //initialize a int called color
	
	double distance; //initialize a double called distance
	
	Vertex parent; //initialize a Vertex called parent that will be the parent Vertex
	
	int discoverStep; //initialize an int called discoverStep
	
	int finishStep; //initialize an int called finishStep

	public Vertex(String label) { 
		//constructs the Vertex object:
		this.label = label;
		color = WHITE; //sets the color of the Vertex to white
		distance = Double.POSITIVE_INFINITY; //sets this distance to positive infinity
		parent = null; //sets the parent to null
		discoverStep = 0; //initializs the discoverStep to zero
		finishStep = 0; //initializes the finishStep to zero
	}
	
	public String toString() { //the toString method returns label
		return label;
	}
	
	  public int compareTo(Vertex o2){
			//		return this.weight >o2.weight? 1:0;
			if(this.distance > o2.distance) //if the edge weights are greater, return 1
				return 1;
			else if(this.distance == o2.distance)  //if the edge weights are equal, return 0
				return 0;
			else //if the edge weights are smaller, return -1
				return -1;
			
		}

}

class Queue {

	public int n;         // number of elements on queue
    public Node first;    // beginning of queue
    public Node last;     // end of queue
    
    //helper linked list class that will construct the linked list
    public class Node {
        public Vertex newVertex;
        public Node next;
    }
    
    //initializes an empty queue
    Queue() {
    	first = null;
    	last = null;
    	n = 0;
    }
    
    //returns true if the queue is empty
    public boolean isEmpty(){
    	return first == null;
    }
    
    //This is the enqueue method for the queue
    public void enqueue(Vertex newString) {
    	Node oldlast = last; //creates a Node oldlast that stores the last node
    	last = new Node(); //creates a new Node last
    	last.newVertex = newString; //sets the last's Vertex to the newString parameter
    	last.next = null; //sets the next node to null
    	
    	if (isEmpty()) { //if the queue is empty
    		first = last; //set the first Node to the last Node
    	}
    	else{
    		oldlast.next = last; 
    		n++; //increases the number of nodes in the linked list
    	}
    }
  
    //This is the dequeue method for the queue
	 public Vertex dequeue() {
	        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
	        Vertex removedVertex = first.newVertex; //stores the removedString
	        first = first.next; //sets the link to first node to o the next Node
	        n--; //decreases the number of strings in the linked list
	        if (isEmpty()) { //if the queue is empty
	        	last = null;   // to avoid loitering
	        }
	        return removedVertex; //returns the removed String
	    }
	    
	    public int size(){ //returns the size of the linked list
	    	return n;
	    }
	    
	    public Vertex peek() { //returns the last item added
	        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
	        return first.newVertex;
	    }

	    
}


class Edge implements Comparable<Edge>{ //this is the Edge class
			
			Vertex source; //initialize a vertex called source
			Vertex target; //initialize a vertex called target
			double weight; //initialize a double called weight that will give each edge it's weight			
			double capacity; //stores the capacity
			double flow; //represents the flow
		
			public Edge(Vertex source, Vertex target, double weight) { 
				//this is the Edge constructor that has a source, target, and weight
				this.source = source; //sets the object's source to the source parameter
				this.target = target; //sets the object's target to the target parameter
				this.weight = weight;//sets the weight's weight to the source parameter
			}
		
			public String toString() { //this is the toString method that returns the source to target and the weight
				return "(" + source + " -> " + target + " : " + weight + ")";
			}
			
			public double getWeight(){
				return weight;
			}
			
			public int compareTo(Edge o2){
				//		return this.weight >o2.weight? 1:0;
				if(this.weight > o2.weight) //if the edge weights are greater, return 1
					return 1;
				else if(this.weight == o2.weight)  //if the edge weights are equal, return 0
					return 0;
				else //if the edge weights are smaller, return -1
					return -1;
				
			}
	
}