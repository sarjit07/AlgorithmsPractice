package datastructures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;


/*
 * @Author: Arjit Sharma
 * Problem: https://www.spoj.com/problems/DQUERY/
 * Reference: https://www.youtube.com/watch?v=aZG0I9MM03s&list=PL2q4fbVm1Ik7Ds5cuaoOmExjOQG31kM-p&index=3
 * https://blog.anudeep2011.com/mos-algorithm/
 * https://cp-algorithms.com/data_structures/sqrt_decomposition.html
 * 
 * Things to Remember:
 * 1. Works on offline queries. (Meaning: Takes all queries -> Sorts them -> Process queries -> Output result at last) 
 * 2. Divides the search space into sqrt logical blocks to search
 * 3. Sorts based on block size for Left pointer and for same blocks sorts in increasing order of Right pointer.
 * 4. Uses the result of previous query to solve next query.
 * 
 * 5. Amount moved by right pointer is O(N) for each block. We have O(Sqrt(N)) blocks, so the total is O(N * Sqrt(N))
 * 	  Example: {1, 2} {0, 3} {1, 7} {2, 8} {4, 4} {4, 8} {7, 8} sorted queries. 3 blocks of size 3.
 *    The right pointer goes to max = 8, in first block
 * 6. In each block, the amount left pointer moves is O(X * Sqrt(N)) where X is number of queries falling in that block.
 * 	  Total queries = Q, so, time for left pointer movement = O(Q * Sqrt(N))
 * 7. Total Time: O( (N + Q) * Sqrt(N) )
 * 
 * Limitation: We should be able to write add remove functions.
 * Questions: Can it be used for solving update queries. ?
 * 
 * 
 */


public class MOsAlgorithm {

	static int block;
	public static void main(String args[]) throws Exception {
		
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String eol = System.getProperty("line.separator");
        byte[] eolb = eol.getBytes();
        String str[] = bufferedReader.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int a[] = new int[n];
		block = (int) Math.sqrt(n);

		str = bufferedReader.readLine().split(" ");
		for(int i=0;i<n;i++)
			a[i]= Integer.parseInt(str[i]);
		
		str = bufferedReader.readLine().split(" ");
		int q = Integer.parseInt(str[0]);
		
		Query queries[] = new Query[q];
		for(int k=0;k<q;k++) {
			str = bufferedReader.readLine().split(" ");
			int l = Integer.parseInt(str[0]);
			int r = Integer.parseInt(str[1]);
			queries[k] = new Query(k,l-1,r-1);
		}
		
		Comp c = new Comp();
		Arrays.sort(queries, c);
		
		/* Queries Sorted according to the order.
		for(int i=0;i<queries.length;i++) {
			System.out.print(queries[i].id + " " + queries[i].l + " " + queries[i].r);
			System.out.println();
		}*/
		
		int ans[] = MOs(queries, a);
		
		for(int i=0;i<ans.length;i++)
			out.println(ans[i]);
		
		out.flush();
		out.close();
		
		
	}
	
	static int freq[];
	static int count;
	
	public static int[] MOs(Query[] q, int a[]) {
		
		int currLeft  = q[0].l;
		int currRight = q[0].l-1;
		int ans[] = new int[q.length];
		freq = new int[(int)1e6 + 5];
		count = 0;
		
		for(int i=0;i<q.length;i++) {
			
			int l = q[i].l;
			int r = q[i].r;
			int id = q[i].id;
						
			while(currLeft > l) { 
				currLeft--; 
				add(a, currLeft);
			}
			
			while(currLeft < l) {
				remove(a, currLeft);
				currLeft++;
			}
			
			while(currRight < r) {
				currRight++; 
				add(a, currRight);
				
			}
			
			while(currRight > r) {
				remove(a, currRight);
				currRight--;
			}
			
			ans[id] = count;
		}
		
		return ans;
		
	}
	
	public static void add(int a[], int index) {
		freq[a[index]]++;
		if(freq[a[index]] == 1)
			count++;
	}
	
	
	public static void remove(int a[], int index) {
		freq[a[index]]--;
		if(freq[a[index]] == 0)
			count--;
	}
	
	
	
	
	static class Comp implements Comparator<Query>{
		@Override
		public int compare(Query o1, Query o2) {
			if(o1.l/block != o2.l/block)
				return (o1.l/block - o2.l/block);
			else
				return (o1.r - o2.r);
		}
	}
	
	static class Query{
		int l;
		int r;
		int id;
		Query(int id, int l, int r){
			this.id = id;
			this.l = l;
			this.r = r;
		}
	}
}
