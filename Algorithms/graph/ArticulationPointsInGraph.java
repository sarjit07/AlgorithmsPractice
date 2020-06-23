package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Reference: https://www.youtube.com/watch?v=iGGolqb6gDE
 * https://cp-algorithms.com/graph/cutpoints.html
 * https://codeforces.com/blog/entry/68138
 */

public class ArticulationPointsInGraph {

	static List<Integer>[] adj;
	// Lowest ancester you are directly connected with.
	// ancester is not the parent. (its the parent of parent of node)
	static int low[];
	
	//entry time of the node in the DFS
	static int in[];
	static int visited[];
	static int timer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertices = sc.nextInt();
		int edges = sc.nextInt();

		adj = new ArrayList[vertices + 1];

		for (int i = 0; i <= vertices; i++)
			adj[i] = new ArrayList<Integer>();

		for (int i = 0; i < edges; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		low = new int[vertices+1];
		in = new int[vertices+1];
		visited = new int[vertices+1];
		timer = 0;
		
		
		for(int i=0;i<=vertices;i++) {
			if(visited[i]!=1)
				DFS(i,-1);
		}
		
	}
	

	public static void DFS(int u, int parent) {
		visited[u] = 1;
		low[u] = in[u] = timer;
		timer++;
		
		int children = 0;
		for(int i=0;i<adj[u].size();i++) {
			int v = adj[u].get(i);
			if(v == parent) continue;
			
			if(visited[v] == 1) {
				//back edge
				low[u] = Math.min(in[v], low[u]);
			}
			else {
				//forward edge
				DFS(v, u);
				low[u] = Math.min(low[u], low[v]);
				if(low[v]>=in[u] && u!=-1) {
					System.out.println("Articulation point at " + u + " found ");
				}
				++children;
				
			}
			
		}
		
		if(parent == -1 && children>0) {
			System.out.println("Articulation point at Root : " + u + " found ");
		}
		
	}

}
