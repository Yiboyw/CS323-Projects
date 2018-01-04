/**
 * Created by yibowang on 3/31/17.
 */

import java.awt.List;
import java.util.ArrayList;

public class RodCutting {

    static ArrayList<Integer> price; 
    static ArrayList<Integer> resultList;

    //List prices = new ArrayList();

    public static void main(String[] args) {

        price = new ArrayList<Integer>(); //creates an ArrayList of integers

        ///////////////////////////////////////////////////
        //these are the list of prices for the price arraylist - referenced from 381 from the MIT Algorithms textbook
        int first = 1;
        int second = 5;
        int third = 8;
        int fourth = 9;
        int fifth = 10;
        int sixth = 17;
        int seventh = 17;
        int eigth = 20;
        int ninth = 24;
        int tenth = 30;

        price.add(first);
        price.add(second);
        price.add(third);
        price.add(fourth);
        price.add(fifth);
        price.add(sixth);
        price.add(seventh);
        price.add(eigth);
        price.add(ninth);
        price.add(tenth);
        //////////////////////////////////////////////////////

        List list = null;
        //rodCut1(1.0, list, list);
        //System.out.println(rodCut1(2.0, )); need to implement a list for prices and list for inches
        
        String resultOperations[] = new String[5]; 
    }

    //this is the direct top-down implementation - corresponds to pg 363 of the textbook
    public static double RodCutting(double[] price, int inches) { //this the rod cutting method

        int j = 0; //j is a place holder to be used for the price arraylist
        if (inches == 0) {
            return 0;
        }
        double revenue = Double.POSITIVE_INFINITY;

        for (int i = 1; i < inches; i++) { // edit this into for-loop
            revenue = Math.max(revenue, price[j] + RodCutting(price, inches - 1));//(revenue,price[i] + RodCutting(price, inches-1);

            //Notes:
            //how do I find the max revenue?..... coding.....//
            //System.out.println("Math.max(" + x + "," + y + ")=" + Math.max(x, y));
            //PRICE IS AN ARRAYLIST!
        }
        // TODO Auto-generated constructor stub
        return revenue;
    }
    
    public static double rodCut1(int length, ArrayList<Double> prices, ArrayList<Integer> resultCuts) {
		if(length <= 0) {
			return prices.get(0); //base case: if the length is 0, no revenue is possible
		}
		
		double maxCut = Double.NEGATIVE_INFINITY; //initialized so that the for loop can compute properly
		
		for(int i = 1; i <= length; i++) {
			maxCut = Math.max(maxCut, prices.get(i) + rodCut1(length-i, prices, resultCuts)); //computes optimal solution by taking maximum through recursion
		}
		return maxCut;
	}

    //this is the top down cut-rod implementation with memoization
    public static double rodCut2(int length, double[] prices, double resultCuts) {
        if (length == 0) {
            return 0.0;
        }

        double[] r = new double[100];

        int n = 10000; //declares the integer n

        for (int i = 0; i < n; i++) {
            r[i] = Double.POSITIVE_INFINITY;
        }

       return RodCutting(r, 3); //need to insert price into the RodCutting method
    }
    
    //this is the memoized cut rod Aux method - referenced from pg 366 of the textbook
    //this is also known as the top down cut-rod procedure- which is what the assignment is refering to as rodCut2
    public static double MemoizedCutRodAux(double[] p, double n, double[] r){ //fill in these variable by declaring type
        
    	if (r[(int) n] > 0){
            return r[(int) n];
        }

        int q = (int) 2.0;
        if (n == 0){
            q=0;
        }

        else {
            q = (int) Double.POSITIVE_INFINITY;
            for (int i = 1; i < n; i++){
                // insert max function call
                q = (int) Math.max(q, p[i] + MemoizedCutRodAux(p,n-1,r)); 
                		//+ RodCutting(price, inches - 1));//(revenue,price[i] + RodCutting(price, inches-1);
            }
            r[(int) n] = q;
            return q;
        }
        return q;
    }

    //bottom up version
    public static double bottomUpCutRod(int p, int n){
        double[] r = new double[100]; //create a new array called r
        r[0] = 0;

        int i;
        int j =0;
        for (i=1;i<j; i++){
            double q = Double.POSITIVE_INFINITY;

            for(i = 1; i<j;i++){
                //q = insert max function call
            }
            r[j] = q;
        }
        return r[(int) n];
    }
    
    
    //this is the bottom up iteration with memoization for rodCut3
    public static double rodCut3(int length, ArrayList<Double> prices, ArrayList<Integer> resultCuts) {
		ArrayList<Double> revenue = new ArrayList<Double>(); //arraylist to store results of subproblems
		double maxCut;
		revenue.add(0.0); //rod of length 0 earns no revenue
		for(int r = 1; r <= length; r++) {
			revenue.add(Double.NEGATIVE_INFINITY);
		}
		
		for(int j = 1; j <= length; j++) { //solves each subproblem of size j in order of increasing size
			maxCut = Double.NEGATIVE_INFINITY;
			for(int i = 1; i <= j; i++) {
				maxCut = Math.max(maxCut, prices.get(i) + revenue.get(j-i)); //makes reference to element in [j-i] rather than recursively calling 
			}
			revenue.set(j, maxCut); //saves solution to subproblem 
		}
		return revenue.get(length);
	} 
    
    
}