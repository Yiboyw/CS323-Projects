package sort.distribution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//This code follows LSDRadix sort so there is no extends Comparable and no generic type
public abstract class MSDRadixSort extends BucketSort{

    //global list of buckets
    public List<Integer> buckets;

    /**
     * @param bucketSize the total number of buckets.
     * @param sort
     * @param comparator
     */
    public MSDRadixSort(int bucketSize, boolean sort, Comparator comparator) {
        super(bucketSize, sort, comparator);
    }

    public void main(String args[]){
    	
    	long startTime = System.currentTimeMillis();
    	 
        // Find the maximum number to know number of digits
        Integer[] newArray = {170, 045, 075, 002, 024, 802, 066, 001};
        int count = newArray.length-1;
        // System.out.println("line 28 count" + count);

        Integer m = getMaxBit(newArray, count);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            sort(newArray, count, exp);
        
        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    public static int getBucketIndex(Integer key, int div) {
        // div is the divisor
        // e.g. 321/100 = 3%10 = 3
        // 321/10 = 32 => 32%10 = 2
        return (key / div) % 10;
    }
    
  //New sorting method. The sort method has to recursively sort the buckets.
    public void newSort(Integer[] array, int n)
    {
        Integer[] newArray = {170, 045, 075, 002, 024, 802, 066, 001};
        int arrayCount = newArray.length;

        int m = getMaxBit(newArray, arrayCount);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp >0; exp *=10){
            newSort(array,n);
        }

    }

    //Change this to an integer array
    public void sort(Integer[] arr, int n, int exp) {
        Integer[] output = new Integer[n]; // output array
        int i;
        Integer[] count = new Integer[10]; // output array

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
           count[i] += count[i - 1];

        // Build the output array
        for (i = n-1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }
        // Copy the output array to arr[], so that arr[] now
       // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
           arr[i] = output[i];
    }

    protected int getMaxBit(Integer[] array, int n){
        int max = array[0];

        //finds the max integer in the array
        for (int i = 1; i < n; i++) {
            max = Math.max(max, array[i]);
        }

        return Integer.toString(max).length();
    }
}