import java.util.PriorityQueue;

public class Test {
	
	
	public static void main(String[] args){
		PriorityQueue<String> priorityQueue = new PriorityQueue<String>();//initializes a new priority queue which stores the graph's vertices
		//how do I add vertices to the minimum priority queue
		priorityQueue.add("q");
		priorityQueue.add("s");
		priorityQueue.add("v");
		priorityQueue.add("w");
		priorityQueue.add("t");
		priorityQueue.add("x");
		priorityQueue.add("a");
		priorityQueue.add("y");
		priorityQueue.add("r");
		priorityQueue.add("u");
		
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.isEmpty());
	}

}
