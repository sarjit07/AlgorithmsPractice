package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


/*
 * @Author: Arjit Sharma
 * 
 * Time Complexity: O((E+V)logV) ~= O(ElogV) as using priority queue with adjacency list
 * The time complexity remains O(ELogV)) as there will be 
 * at most O(E) vertices in priority queue and O(Log E) is same as O(Log V).
 * A case, when a vertex is connected with all other verticies...then no. of edges in PQ..can be at max O(V)
 * 
 * Single Source Shortest path to all Verticies. 
 * Prints the path as well.
 *
 * Reference: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
 */


public class DijkstraAlgorithm {

	static List<Node>[] adj;
	static int verticies;
	static int INF = (int) 1e8 + 5;
	static Map<Integer, Integer> parent;
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		verticies = in.nextInt(); // no. of verticies
		int m = in.nextInt(); // no. of edges.

		adj = new ArrayList[verticies + 1];
		for (int i = 0; i <= verticies; i++) {
			adj[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < m; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			adj[u].add(new Node(v, w));
		}

		int source = in.nextInt();
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
		int distance[] = dijkstra.findShortestPath(adj, source);
		for (int i = 0; i < distance.length; i++) {
			System.out.println(source + " to " + i + " is " + distance[i]);
		}
		printPath(source, verticies);

	}

	public int[] findShortestPath(List<Node>[] adj, int source) {

		int distance[] = new int[verticies + 1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>(verticies, new Node());
		HashSet<Integer> set = new HashSet<Integer>();
		// stores parent of every vertex in shortest distance
		 parent = new HashMap<>();


		for (int i = 0; i <= verticies; i++) {
			distance[i] = INF;
		}
		
		pq.add(new Node(source, 0));
		parent.put(source, -1);
		distance[source] = 0;

		while (!pq.isEmpty()) {
			
			int u = pq.remove().node;
			
			set.add(u);

			for (int i = 0; i < adj[u].size(); i++) {
				Node v = adj[u].get(i);

				if (!set.contains(v.node)) {
					int dist = v.weight;
					int newDist = distance[u] + dist;

					if (newDist < distance[v.node]) {
						distance[v.node] = newDist;
						parent.put(v.node, u);
					}
					pq.add(new Node(v.node, distance[v.node]));
				}
			}
			

		}
		return distance;
	}
	
	
	public static void printPath(int source, int dest) {
		System.out.print(dest + " ");
		while(parent.get(dest) != -1) {
			dest = parent.get(dest);
			System.out.print(dest + " ");
			
		}
		// System.out.print(-1);
	}
}

class Node implements Comparator<Node>{
	int node;
	int weight;

	public Node(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}
	
	public Node() 
    { 
    }
	
	@Override
	public int compare(Node node1, Node node2) 
    { 
        if (node1.weight < node2.weight) 
            return -1; 
        if (node1.weight > node2.weight) 
            return 1; 
        return 0; 
    } 
}
