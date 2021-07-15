
package datastructures;

import java.io.*;
import java.util.*;


/*
 * Problem: Find the Kth Tallest person with updates and queries. 
 * https://www.youtube.com/watch?v=kPaJfAUwViY
 * 
 * Given: An array with indicies as height and a[i] as no. of people with that height.
 * Two types of operations 
 * 	1. Update(i, x) - add a[i] with +x values
 *  2. Query(k) - find kth tallest/shortest person
 * 
 * 
 * reference for approach 2: https://codeforces.com/blog/entry/61364
 * 
 */

public class FindKthTallestPerson {
	
	static int bit[];
	public static void main(String args[]) {

		int height[] = {8,2,10,100,1,2};
		
		bit = new int[(int)1e5];
		
		
		for(int i=0;i<height.length;i++) {
			update(i+1, height[i]);
		}
		
		//q queries can be given.
		
		// here i want to find 10th tallest person
		
		//Approach 1, Using Binary Search and BIT separately
		int k =122;
		System.out.println(binarySearch(k));
		
		//Approach 2, Using Binary Search on BIT. Fast.
		int k1 =122;
		System.out.println(binarySearchOnBIT(k1));
		
		
		

	}
	
	
	//Approach 2
	private static int binarySearchOnBIT(int k) {
		
		int sum = 0;
		int pos = 0;
		int logn = (int)(Math.log(bit.length)/Math.log(2));
		
		for(int i=logn;i>=0;i--) {
			
			if( pos + 1<<i < bit.length && sum + bit[pos + (1<<i)] < k) {
				sum += bit[pos + (1<<i)];
				pos += (1<<i);	
			}
		}
		
		return pos + 1;
	}
	
	
	
	// This method takes O(logn * logn) 
	// Approach 1
	private static int binarySearch(int k) {
		
		int l = 1; int r = bit.length;
		
		while(l<=r) {
			int mid = (l+r)/2;
			
			if(sum(mid) < k) {
				l = mid + 1;
			}
			else {
				r = mid - 1;
			}
		}
		
		return l;
	}
	
	
	private static void update(int index, int val) {
		
		while(index <= bit.length) {
			bit[index] += val;
			index += (index&(-index));
		}
	}
	
	private static int sum(int index) {
		int sum = 0;
		while(index > 0) {
			sum += bit[index];
			index -= (index&(-index));
		}
		return sum;
	}
	
	

}


