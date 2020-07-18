package tree;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

/*
 * Path Queries On Trees Using MOs Algorithm and LCA
 * Problem: Find all the nodes which lie on a path from node U to node V
 * Reference: https://www.youtube.com/watch?v=Psfxu9E6jtw&list=PL2q4fbVm1Ik4mjMTt7Po4DocBBI_Ai6s8&index=6
 * https://codeforces.com/blog/entry/43230
 * 
 * Time Complexity Analysis: 
 * Preprocess: O(NlogN)
 * Query: O(logN ) + O( (N+Q) * Sqrt(N))
 * ~ O(Q * Sqrt(N))
 * 
 */


public class FindAllNodesInAPathInTREE {

	static int flatTree[];
	static int startTime[];
	static int endTime[];
	static int level[];
	static int LCA[][];
	static int maxN;	
	static List<Integer>[] adj;
	static int timer = 1;
	static int block;
	static int nodeFreq[];
	
	public static void main(String args[]) {
		
		/*
		 *  Sample I/O
		 *  8 2
		 *	1 2
		 *	1 5
		 *	2 3
		 *	2 4
		 *	5 6
		 *	5 7
		 *	5 8
		 *	
		 *	3 8
		 *	6 8
		 *
		 *	2 3 5 8 1 
		 *	6 8 5 
		 * 
		 */

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int vertices = in.nextInt();
		int q = in.nextInt();
		
		adj = new ArrayList[vertices +1 ];
		for(int i=0;i<=vertices;i++)
			adj[i] = new ArrayList<Integer>();
		
		for(int i=0;i<vertices - 1;i++){
			int u = in.nextInt();
			int v =in.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		maxN = (int)(Math.log(vertices)/Math.log(2));
		block = (int)Math.sqrt(vertices);
		flatTree = new int[2*vertices+1];
		startTime = new int[vertices + 1];
		endTime = new int[vertices + 1];
		nodeFreq = new int[vertices + 1];
		level = new int[vertices + 1];
		LCA = new int[vertices + 1][maxN+1];
		for(int i=0;i<=vertices;i++)
			Arrays.fill(LCA[i], -1);
		
		
		
		flattenTreeAndLCAUsingDFS(1,0, -1);
		preCalLCA(vertices);
		
		Q qs[] = new Q[q];
		for(int i=0;i<q;i++) {
			
			int u = in.nextInt();
			int v = in.nextInt();
			
			if(startTime[u] > startTime[v]) {
				u = u + v;
				v = u - v;
				u = u - v;
			}
			
			int lca = getLCA(u, v);
			if(lca == u) {
				int l = startTime[u];
				int r = startTime[v];
				qs[i] = new Q(i, l, r, -1 );
			}
			else {
				int l = endTime[u];
				int r = startTime[v];
				
				qs[i] = new Q(i, l, r, lca);
			}
		}
		
		Comp c = new Comp();
		Arrays.sort(qs, c);
		
		
		solve(qs, vertices);
		
		for(int i=0;i<ans.length;i++) {
			for(int j=0;j<ans[i].size();j++)
				out.print(ans[i].get(j) + " ");
			out.println();
		}

		out.close();

	}
	
	static  List<Integer>[] ans;
	static int elementsInPath[];
	
	public static void solve(Q qs[], int vertices) {
		ans = new ArrayList[qs.length];
		for(int i=0;i<qs.length;i++)
			ans[i] = new ArrayList<Integer>();
		
		elementsInPath = new int[vertices+1];	
		int curLeft = qs[0].l; 
		int curRight = curLeft -1;
		for(int i=0;i<qs.length;i++) {
			
			int l = qs[i].l;
			int r = qs[i].r;
			int id = qs[i].id;
			int lca = qs[i].lca;
			
			
			while(curLeft > l) {
				curLeft--;
				add(curLeft);
			}
			while(curLeft < l) {
				remove(curLeft);
				curLeft++;
			}
			while(curRight < r) {
				curRight++;
				add(curRight);
			}
			while(curRight > r) {
				remove(curRight);
				curRight--;
			}
			
//			if(lca !=-1)
//				elementsInPath[lca]++;
			
			
			// O(N) operation here. Just to get the ans.
			for(int k=0;k<elementsInPath.length;k++) {
				if(elementsInPath[k] > 0)
					ans[id].add(k);
			}
			if(lca!=-1)
				ans[id].add(lca);
			
			
		}
		
	}
	
	//O(1)
	public static void add(int index) {
		nodeFreq[flatTree[index]]++;
		if(nodeFreq[flatTree[index]] == 1) {
			elementsInPath[flatTree[index]]++;
		}
		else {
			elementsInPath[flatTree[index]]--;
		}
	}
	
	//O(1)
	public static void remove(int index) {
		nodeFreq[flatTree[index]]--;
		if(nodeFreq[flatTree[index]] == 1) {
			elementsInPath[flatTree[index]]++;
		}
		else {
			elementsInPath[flatTree[index]]--;
		}
		
	}
	
	
	//QueryProcessing: O(logN)
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
	
	
	//preprocessing: O(N * LogN)
	public static void preCalLCA(int vertices) {
		for(int i =1;i<=maxN;i++) {
			for(int j =0;j<=vertices;j++) {
				if (LCA[j][i - 1] != -1) {
					int par = LCA[j][i - 1];
					LCA[j][i] = LCA[par][i - 1];
				}
			}
		}
	}

	//preprocessing: O(N) for  DFS
	public static void flattenTreeAndLCAUsingDFS(int node, int lvl, int parent) {
		
		flatTree[timer] = node;
		startTime[node] = timer;
		timer++;
		
		level[node] = lvl;
		LCA[node][0] = parent;
		
		for(int i=0;i<adj[node].size();i++) {
			int child = adj[node].get(i);
			if(child!=parent) {
				flattenTreeAndLCAUsingDFS(child, lvl+1, node);
			}
		}
		
		endTime[node] = timer;
		flatTree[timer] = node;
		timer++;
		
	}
	
	static class Comp implements Comparator<Q>{

		@Override
		public int compare(Q o1, Q o2) {
			int x = o1.l/block;
			int y = o2.l/block;
			if(x!=y)
				return x-y;
			else
				return o1.r - o2.r;
		}
		
	}
	
	static class Q{
		int id;
		int l;
		int r;
		int lca;
		Q(int id, int l, int r, int lca){	
			this.id = id;
			this.l = l;
			this.r = r;
			this.lca = lca;
		}
	}
	
	
	
	
	
	
	
	
	
	

	static class InputReader {

		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int snext() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int nextInt() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}

		public String readString() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String nextLine() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}

}
