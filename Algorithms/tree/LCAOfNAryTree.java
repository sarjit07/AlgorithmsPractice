package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * @Author: Arjit Sharma
 * Time Complexity: Nlogn to precompute and logn to query.
 * Space Complexity: Nlogn for sparse table.
 */


public class LCAOfNAryTree {

	static List<Integer>[] adj;
	static int level[];
	static int LCA[][];
	static int maxN;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- != 0) {
			int n = in.nextInt();
			int q = in.nextInt();
			int a[] = new int[n];

			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}

			adj = new ArrayList[n + 1];
			for (int i = 0; i <= n; i++)
				adj[i] = new ArrayList<Integer>();

			for (int i = 0; i < n - 1; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}

			level = new int[n + 1];
			maxN = (int) (Math.log(n) / Math.log(2));
			LCA = new int[n + 1][maxN + 1];
			for (int i = 0; i <= n; i++)
				Arrays.fill(LCA[i], -1);

			dfs(1, 0, -1);
			preCalLCA(n);

			while (q-- != 0) {
				int x = in.nextInt();
				int y = in.nextInt();

				System.out.println(a[getLCA(x, y) - 1]);
			}

		}

	}

	public static int getLCA(int x, int y) {
		if (level[x] > level[y]) {
			x = x + y;
			y = x - y;
			x = x - y;
		}
		int d = level[y] - level[x];

		while (d > 0) {
			int i = (int) (Math.log(d) / Math.log(2));
			y = LCA[y][i];
			d = d - (1 << i);
		}
		if (x == y)
			return x;

		for (int i = maxN; i >= 0; i--) {
			if (LCA[x][i] != -1 && (LCA[x][i] != LCA[y][i])) {
				x = LCA[x][i];
				y = LCA[y][i];
			}
		}
		return LCA[x][0];
	}

	public static void preCalLCA(int n) {
		for (int i = 1; i <= maxN; i++) {
			for (int j = 0; j <= n; j++) {
				if (LCA[j][i - 1] != -1) {
					int par = LCA[j][i - 1];
					LCA[j][i] = LCA[par][i - 1];
				}
			}
		}
	}

	public static void dfs(int node, int lvl, int parent) {
		level[node] = lvl;
		LCA[node][0] = parent;

		for (int i = 0; i < adj[node].size(); i++) {
			int child = adj[node].get(i);
			if (child != parent) {
				dfs(child, lvl + 1, node);
			}
		}
	}

}
