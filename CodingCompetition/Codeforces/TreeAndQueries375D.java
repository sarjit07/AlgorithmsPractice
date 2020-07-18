package Codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;


public class TreeAndQueries375D {

	static List<Integer>[] adj;
	static int flatTree[];
	static int startTime[];
	static int endTime[];
	static int timer = 1;
	static int block;
	
	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		int n = in.nextInt();
		int q = in.nextInt();
		adj = new ArrayList[n+1];
		for(int i=0;i<=n;i++)
			adj[i] = new ArrayList<Integer>();
		int a[] = new int[n+1];
		for(int i=1;i<=n;i++) {
			a[i] = in.nextInt();
		}
		block = (int)Math.sqrt(a.length);
		for(int i=0;i<n-1;i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		flatTree = new int[2*n+1];
		startTime = new int[n+1];
		endTime = new int[n+1];
		
		flattenTreeUsingDFS(1,0);
		
		Q qs[] = new Q[q];
		for(int i=0;i<q;i++) {
			
			int node = in.nextInt();
			qs[i] = new Q(i, startTime[node], endTime[node], in.nextInt());
		}
			
		Comp c = new Comp();
		Arrays.sort(qs, c);
		
		int ans[] = solve(a, qs);
		
		for(int i=0;i<ans.length;i++)
			out.println(ans[i]);

		out.close();

	}
	
	
	static int freq[];
	static int nodeFreq[];
	static int FF[];
	static int b[];
	public static int[] solve(int a[], Q q[]) {
		freq = new int[(int)1e5 + 5];
		FF = new int[(int)1e5 + 5];
		b = new int[a.length/block+1];
		nodeFreq = new int[a.length+1];
		int ans[]= new int[q.length];
		
		int curLeft = q[0].l;
		int curRight = curLeft -1;
		for(int i=0;i<q.length;i++) {
			int l = q[i].l;
			int r = q[i].r;
			int k = q[i].k;
			
			while(curLeft > l) {
				curLeft--;
				add(a, curLeft);
			}
			while(curLeft < l) {
				remove(a, curLeft);
				curLeft++;
			}
			while(curRight < r) {
				curRight++;
				add(a, curRight);
			}
			while(curRight > r) {
				remove(a, curRight);
				curRight--;
			}
			
			ans[q[i].id] = getAns(k, a.length);
		}
		
		
		return ans;
	}
	
	
	public static int getAns(int k, int n) {
		int res = 0;
		int LB = getBlock(k);
		int RB = getBlock(n);
		
		
		
		if(LB == RB)
		{
			for(int i=k;i<=n;i++)
			res += FF[i];
		}
		else
		{
			for(int i=k;i<=LB*block;i++)
			res += FF[i];
			
			for(int i=LB+1;i<RB;i++)
			res += b[i];
			
			for(int i=RB*block+1;i<=n;i++)
			res += FF[i];
		}
		
		return res;
	}
	
	
	public static void add(int a[], int index) {
		int x = flatTree[index];
		int y = ++nodeFreq[x];
		if(y == 2) {
			freq[a[x]]++;
			int z = freq[a[x]];
			FF[z]++;
			FF[z - 1]--;
			b[getBlock(z)]++;
			b[getBlock(z-1)]--;
			
			
		}
	}
	
	public static int getBlock(int index) {
		
		return (index + block -1)/block;
	}
	
	public static void remove(int a[], int index) {
		nodeFreq[flatTree[index]]--;
		if(nodeFreq[flatTree[index]] == 1) {
			freq[a[flatTree[index]]]--;
			FF[freq[a[flatTree[index]]]]++;
			FF[freq[a[flatTree[index]]] + 1]--;
			b[getBlock(freq[a[flatTree[index]]])]++;
			b[(getBlock(freq[a[flatTree[index]]]+1))]--;
		}
	}
	
	
	
	public static void flattenTreeUsingDFS(int node, int parent) {
		flatTree[timer] = node;
		startTime[node] = timer;
		timer++;
		for(int i = 0;i<adj[node].size();i++) {
			int child = adj[node].get(i);
			if(child!=parent) {
				flattenTreeUsingDFS(child, node);
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
			if(x!=y){
				return x-y;
			}
			else
				return o1.r - o2.r;
		}
		
	}
	
	static class Q{
		int id;
		int l;
		int r;
		int k;
		Q(int id, int l , int r, int k){
			this.id =id;
			this.l = l;
			this.r = r;
			this.k = k;
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
