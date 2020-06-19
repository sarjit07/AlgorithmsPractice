package graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * @Author : Arjit Sharma
 * 
 * Time Complexity: O(V^3)
 * Space Complexity: O(V^2)
 * Takes care of negative edges as well. Can as well detect negative cycles
 * 
 */

public class FloydWarshallAllPairShortestPath {

	static int INF = (int) 1e9 + 5;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int verticies = in.nextInt();
		int edges = in.nextInt();

		int a[][] = new int[verticies + 1][verticies + 1];
		int parent[][] = new int[verticies + 1][verticies + 1];

		// filling the grpah matrix
		for (int i = 0; i < edges; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			a[u][v] = w;
		}
		// for a self loop a[i][j] = 0, else infinity.
		for (int i = 1; i <= verticies; i++) {
			for (int j = 0; j <= verticies; j++) {
				if (i == j) {
					a[i][j] = 0;
				} else if (a[i][j] == 0) {
					a[i][j] = INF;
				}
			}
		}

		// filling the parent matrix. for path i->j, i is the parent.
		for (int i = 1; i <= verticies; i++) {
			for (int j = 1; j <= verticies; j++) {
				if (a[i][j] != INF && i != j)
					parent[i][j] = i;
				else
					parent[i][j] = -1;
			}
		}

		// calculate the shortest path distance
		for (int k = 1; k <= verticies; k++) {
			for (int i = 1; i <= verticies; i++) {
				for (int j = 1; j <= verticies; j++) {
					if (a[i][k] != INF && a[k][j] != INF && a[i][j] > a[i][k] + a[k][j]) {
						a[i][j] = a[i][k] + a[k][j];
						parent[i][j] = parent[k][j];
					}
				}
			}
		}

		// look for negative weight cycle in the graph
		// if values on diagonal of distance matrix is negative
		// then there is negative weight cycle in the graph.
		for (int i = 0; i < a.length; i++) {
			if (a[i][i] < 0) {
				System.out.println("Negative Cycle Exists");
				break;
			}
		}

		// Print all the distances.
		for (int i = 1; i <= verticies; i++) {
			for (int j = 1; j <= verticies; j++) {
				if (a[i][j] == INF)
					System.out.print("INF ");
				else
					System.out.print(a[i][j] + "   ");
			}
			System.out.println();
		}

		// print the path from source to destination.
		int source = in.nextInt();
		int destination = in.nextInt();
		printPath(parent, source, destination);

	}

	public static void printPath(int parent[][], int start, int end) {
		if (start < 0 || end < 0 || start >= parent.length || end >= parent.length) {
			System.out.println("Invalid Source Destination");
			return;
		}
		Deque<Integer> stack = new LinkedList();
		stack.addFirst(end);
		while (true) {
			end = parent[start][end];
			if (end == -1)
				return;
			stack.addFirst(end);
			if (end == start)
				break;
		}
		while (!stack.isEmpty())
			System.out.print(stack.pollFirst() + " ");

	}

}
