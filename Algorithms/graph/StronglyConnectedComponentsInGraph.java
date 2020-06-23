package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/*
 * @Author: Arjit Sharma
 * Time Complexity: O(V+E)
 * Reference: https://cp-algorithms.com/graph/strongly-connected-components.html
 * https://www.youtube.com/watch?v=RpgcYiky7uw
 */

public class StronglyConnectedComponentsInGraph {
	
	static List<Integer>[] adj;
	static List<Integer>[] adjReverse;
	static int visited[];
	static Stack<Integer> stack;
	static List<Set<Integer>> listOfComponents;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int vertices = sc.nextInt();
		int edges = sc.nextInt();
		
		adj = new ArrayList[vertices + 1];
		adjReverse = new ArrayList[vertices + 1];

		for (int i = 0; i <= vertices; i++) {
			adj[i] = new ArrayList<Integer>();
			adjReverse[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < edges; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			adj[u].add(v);
			adjReverse[v].add(u);
		}
		
		visited = new int[vertices+1];
		stack = new Stack<Integer>();
		listOfComponents = new ArrayList<Set<Integer>>();
		
		for(int i=0;i<=vertices;i++) {
			if(visited[i]!=1){
				dfs1(i);
			}
		}
		Arrays.fill(visited, 0);
		for(int i=0;i<=vertices;i++) {
			int v = stack.pop();
			if(visited[v]!=1) {
				Set<Integer> component = new HashSet<Integer>();
				dfs2(v, component);
				listOfComponents.add(component);
				Iterator<Integer> itr = component.iterator();
				System.out.print("Component: ");
				while(itr.hasNext()) {
					System.out.print(itr.next() + " ");
				}
				System.out.println();
			}
		}
		
		
	}
	
	public static void dfs1(int u) {
		visited[u] = 1;
		for(int i=0;i<adj[u].size();i++) {
			int v = adj[u].get(i);
			if(visited[v]!=1)
				dfs1(v);
		}
		stack.add(u);
	}
	
	public static void dfs2(int u, Set<Integer> component) {
		visited[u] = 1;
		component.add(u);
		for(int i=0;i<adjReverse[u].size();i++) {
			int v = adjReverse[u].get(i);
			if(visited[v]!=1) {
				dfs2(v, component);
			}
		}
	}
}
