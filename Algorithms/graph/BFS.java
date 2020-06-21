package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * @Author: Arjit Sharma
 * Time Complexity: O(E+V)
 * 
 */

public class BFS {

	static List<Integer>[] adj;
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int vertices = in.nextInt();
		int edges = in.nextInt();
		
		adj = new ArrayList[vertices+1];
		
		for(int i=0;i<vertices;i++)
			adj[i]=new ArrayList<Integer>();
		
		for(int i=0;i<edges;i++) {
			int u= in.nextInt();
			int v = in.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		boolean visited[] = new boolean[vertices+1];
		for(int i=0;i<vertices;i++) {
			if(!visited[i]) {
				BFS(visited, i);
				System.out.println();
			}
		}
		
	}
	
	public static void BFS(boolean visited[], int s){
		LinkedList<Integer> queue = new LinkedList<Integer>();
		//to keep track of parent.
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		//to keep track of distance from s
		Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
		
		parent.put(s, -1);
		queue.add(s);
		visited[s] = true;

		while(!queue.isEmpty()) {
			int u = queue.poll();
			
			System.out.println(u + " ");
			
			for(int i=0;i<adj[u].size();i++) {
				int v = adj[u].get(i);
				if(!visited[v]) {
					queue.add(v);
					visited[v] =true;
					parent.put(v, u);
					distance.put(v, u+1);
				}
			}
		}
		
		
	}

}
