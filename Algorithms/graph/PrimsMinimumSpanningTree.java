package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/*
 * 
 */

public class PrimsMinimumSpanningTree {

	static List<MyNode>[] adj;
	static int verticies;
	static int INF = (int) 1e9 + 5;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		verticies = in.nextInt();
		int edges = in.nextInt();

		adj = new ArrayList[verticies + 1];

		for (int i = 0; i <= verticies; i++) {
			adj[i] = new ArrayList<MyNode>();
		}

		for (int i = 0; i < edges; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			adj[u].add(new MyNode(v, w));
			adj[v].add(new MyNode(u, w));
		}

		PrimsMinimumSpanningTree pmst = new PrimsMinimumSpanningTree();
		int startVertex = 1;
		Set<String> setOfEdges = pmst.findMinimumSpanningTreeEdges(startVertex);
		Iterator<String> itr = setOfEdges.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public Set<String> findMinimumSpanningTreeEdges(int start) {

		int distance[] = new int[verticies + 1];
		PriorityQueue<MyNode> pq = new PriorityQueue<MyNode>(verticies, new MyNode());
		HashSet<Integer> set = new HashSet<Integer>();
		Set<String> setOfEdges = new HashSet();
		Map<Integer, String> parent = new HashMap<Integer, String>();
		for (int i = 0; i <= verticies; i++) {
			distance[i] = INF;
		}

		pq.add(new MyNode(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			int u = pq.remove().node;
			set.add(u);

			if (u != start && parent.containsKey(u)) {
				if (setOfEdges.contains(parent.get(u)))
					continue;
				else
					setOfEdges.add(parent.get(u));
			}

			for (int i = 0; i < adj[u].size(); i++) {
				MyNode v = adj[u].get(i);

				if (!set.contains(v.node)) {
					int dist = v.weight;
					if (dist < distance[v.node]) {
						distance[v.node] = dist;
						String edge = String.valueOf(u) + "_" + String.valueOf(v.node);
						parent.put(v.node, edge);
					}
					pq.add(new MyNode(v.node, distance[v.node]));
				}
			}
		}

		return setOfEdges;
	}

}

class MyNode implements Comparator<MyNode> {

	int node;
	int weight;

	public MyNode() {
	};

	public MyNode(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}

	@Override
	public int compare(MyNode o1, MyNode o2) {
		if (o1.weight < o2.weight)
			return -1;
		else if (o1.weight > o2.weight)
			return 1;
		else
			return 0;
	}
}