package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


/*
 * @Author: Arjit Sharma
 * Time Complexity: O(E * No.Of.AugmentingPaths) = O(E * EV)
 *  
 */

public class FordFulkersonMaxFlowProblem {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int vertices = in.nextInt();
		int edges = in.nextInt();

		int capacity[][] = new int[vertices + 1][vertices + 1];

		for (int i = 0; i < edges; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			capacity[u][v] = w;
			capacity[v][w] = w;
		}

//		int[][] capacity = {{0, 3, 0, 3, 0, 0, 0},
//							{0, 0, 4, 0, 0, 0, 0},
//							{3, 0, 0, 1, 2, 0, 0},
//							{0, 0, 0, 0, 2, 6, 0},
//							{0, 1, 0, 0, 0, 0, 1},
//							{0, 0, 0, 0, 0, 0, 9},
//							{0, 0, 0, 0, 0, 0, 0}};

		int source = in.nextInt();
		int sink = in.nextInt();

		FordFulkersonMaxFlowProblem ffmp = new FordFulkersonMaxFlowProblem();
		System.out.println(ffmp.maxFlow(capacity, source, sink));

	}

	public int maxFlow(int capacity[][], int source, int sink) {

		int residualCapacity[][] = new int[capacity.length][capacity[0].length];

		for (int i = 0; i < capacity.length; i++) {
			for (int j = 0; j < capacity[0].length; j++) {
				residualCapacity[i][j] = capacity[i][j];
			}
		}
		List<List<Integer>> augmentedPaths = new ArrayList<>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();

		int maxFlow = 0;
		while (BFS(residualCapacity, source, sink, parent)) {
			List<Integer> augmentedPath = new ArrayList<Integer>();
			int flow = Integer.MAX_VALUE;
			int v = sink;
			while (v != source) {
				augmentedPath.add(v);
				int u = parent.get(v);
				if (flow > residualCapacity[u][v]) {
					flow = residualCapacity[u][v];
				}
				v = u;
			}
			augmentedPath.add(source);
			Collections.reverse(augmentedPath);
			augmentedPaths.add(augmentedPath);
			maxFlow += flow;
			v = sink;
			while (v != source) {
				int u = parent.get(v);
				residualCapacity[u][v] -= flow;
				residualCapacity[v][u] += flow;
				v = u;
			}

		}
		printAugmentedPaths(augmentedPaths);
		return maxFlow;
	}

	public boolean BFS(int residualCapacity[][], int source, int sink, Map<Integer, Integer> parent) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		queue.add(source);
		visited.add(source);

		boolean augmentedPathFound = false;
		while (!queue.isEmpty()) {

			int u = queue.poll();
			for (int v = 0; v < residualCapacity.length; v++) {
				if (!visited.contains(v) && residualCapacity[u][v] > 0) {
					visited.add(v);
					parent.put(v, u);
					queue.add(v);
					if (v == sink) {
						augmentedPathFound = true;
						break;
					}
				}
			}

		}

		return augmentedPathFound;
	}

	private void printAugmentedPaths(List<List<Integer>> augmentedPaths) {
		System.out.println("Augmented paths");
		augmentedPaths.forEach(path -> {
			path.forEach(i -> System.out.print(i + " "));
			System.out.println();
		});
	}

}
