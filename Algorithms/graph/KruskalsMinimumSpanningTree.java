package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


/*
 * @Author: Arjit Sharma
 * Time Complexity:  O(ElogE + E)
 */

public class KruskalsMinimumSpanningTree {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int vertices = in.nextInt();
		int edges = in.nextInt();
		
		List<Edge> listOfEdges = new ArrayList<Edge>();
		
		for(int i=0;i<edges;i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			listOfEdges.add(new Edge(u, v, w));
		}
		
		
		List<Edge> mst= getMST(listOfEdges, vertices);
		int totalCostOfMST = 0;
		for (Edge edge : mst) {
            System.out.println(edge.start + " " + edge.end + " " + edge.weight);
            totalCostOfMST+=edge.weight;
        }
		System.out.println("Total Cost = "+totalCostOfMST);
	}
	
	static int parent[];
	static int rank[];
	public static List<Edge> getMST(List<Edge> listOfEdges, int vertices){
		
		List<Edge> mst = new ArrayList<Edge>();
		Collections.sort(listOfEdges, Comparator.comparing(o -> o.weight));
		parent = new int[vertices + 1];
		rank = new int[vertices + 1];
		Arrays.fill(parent, -1);
		Arrays.fill(rank, 0);

		
		for(int i=0;i<listOfEdges.size();i++) {
			Edge e = listOfEdges.get(i);
			int parentStart = findByPathCompression(e.start);
			int parentEnd = findByPathCompression(e.end);
			
			if(parentStart == parentEnd) {
				continue;
			}
			else {
				mst.add(e);
				unionByRank(parentStart, parentEnd);
			}
		}
		
		return mst;
	}
	
	public static int findByPathCompression(int a) {
		if (parent[a] < 0)
			return a;
		else {
			int x = findByPathCompression(parent[a]);
			parent[a] = x;
			return x;
		}
	}

	public static void unionByRank(int a, int b) {
		if (rank[a] >= rank[b]) {
			rank[a] = (rank[a] == rank[b]) ? rank[a] + 1 : rank[a];
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	

}

class Edge{
	int start;
	int end;
	int weight;
	
	Edge(int start, int end, int weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}