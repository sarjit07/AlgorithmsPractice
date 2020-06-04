package graph;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Problem:
 *  We are given 10 individuals say,
 *	a, b, c, d, e, f, g, h, i, j
 *	Following are relationships to be added.
 *	a <-> b  
 *	b <-> d
 *	c <-> f
 *	c <-> i
 *	j <-> e
 *	g <-> j	
 *	Determine no. of groups..
 *	or not.
 * 
 * Reference: https://www.youtube.com/watch?v=1mhRZPYCkAY&list=PL2q4fbVm1Ik4JdzE2Bv_UUGBz0TXEIrai
 */

public class DisjointSetUnionFind {
	
	//contains the parent of the group
	static int parent[];
	//contains the height of the group
	static int rank[];
	// contains the size of a group
	static int size[];
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		
		parent = new int[n+1];
		rank = new int[n+1];
		size = new int[n+1];
		Arrays.fill(parent, -1);
		Arrays.fill(rank, 0);
		Arrays.fill(size, 1);
		
		while(m--!=0) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			int parA = findByPathCompression(a);
			int parB = findByPathCompression(b);
			
			if(parA == parB) return;
			else {
				unionByRank(parA, parB);
			}
		}
		int count =0;
		for(int i=1;i<=n;i++) {
			if(parent[i]<0) {
				System.out.println(i + " " + size[i]);
				count++;
			}
				
		}
		System.out.println(count);
		
	}
	
	public static int findByPathCompression(int a) {
		if(parent[a] < 0) {
			return a;
		}
		else {
			int x = findByPathCompression(parent[a]);
			parent[a] = x;
			return x;
			// return parent[a] = findByPathCompression(parent[a]);
		}
	}
	
	public static void unionByRank(int a, int b) {
		if(rank[a]>=rank[b]) {
			rank[a] = (rank[a] == rank[b]) ? rank[a]+1 : rank[a]; 
			parent[b]=a;
			size[a] += size[b];
		}else {
			parent[a]=b;
			size[b] += size[a];
		}
	}
	
}
