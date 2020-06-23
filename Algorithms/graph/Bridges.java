package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bridges {

	static List<Integer>[] adj;
	static int low[];
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
		
		int startVertex = 1;
		
		DFS(startVertex,-1);
	}
	

	public static void DFS(int u, int parent) {
		visited[u] = 1;
		low[u] = in[u] = timer;
		timer++;
		
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
				
				if(low[v]>in[u]) {
					System.out.println("Bridge from " + u + " to " + v );
				}
				low[u] = Math.min(low[u], low[v]);
			}
			
		}
		
	}

}
