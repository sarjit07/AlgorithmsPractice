package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFS {

	static List<Integer>[] adj;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int vertices = in.nextInt();
		int edges = in.nextInt();

		adj = new ArrayList[vertices + 1];

		for (int i = 0; i < vertices; i++)
			adj[i] = new ArrayList<Integer>();

		for (int i = 0; i < edges; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		boolean visited[] = new boolean[vertices + 1];
		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				DFS(visited, i);
				System.out.println(); //finds the disconnected components
			}
		}

	}

	public static void DFS(boolean visited[], int s) {
		visited[s] = true;
		System.out.println(s + " ");

		for (int i = 0; i < adj[s].size(); i++) {
			int u = adj[s].get(i);
			if (!visited[u]) {
				DFS(visited, u);
			}
		}
	}

}
