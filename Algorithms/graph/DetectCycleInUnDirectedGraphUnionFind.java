package graph;

import java.util.Arrays;
import java.util.Scanner;

public class DetectCycleInUnDirectedGraphUnionFind {

	static int parent[];
	static int rank[];

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int vertices = in.nextInt();
		int edges = in.nextInt();

		parent = new int[vertices + 1];
		rank = new int[vertices + 1];
		Arrays.fill(parent, -1);
		Arrays.fill(rank, 0);

		boolean flag = false;
		for (int i = 0; i < edges; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int parU = findByPathCompression(u);
			int parV = findByPathCompression(v);

			if (parU == parV) {
				System.out.println("Cycle exists");
				flag = true;
				break;
			} else {
				unionByRank(parU, parV);
			}
		}
		if (!flag)
			System.out.println("No Cyle");

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
