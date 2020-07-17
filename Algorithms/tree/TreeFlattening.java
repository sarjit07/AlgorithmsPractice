package tree;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class TreeFlattening {

	
	static List<Integer>[] adj; 
	static int flatTree[];
	static int startTime[];
	static int endTime[];
	static int timer = 1;
	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		int vertices = in.nextInt();
		int edges = in.nextInt();
		
		adj = new ArrayList[vertices + 1];
		
		for(int i=0;i<=vertices;i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<edges;i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		flatTree = new int[2*vertices + 1];
		startTime = new int[vertices + 1];
		endTime = new int[vertices + 1];
		
		flatenTreeUsingDFS(1, 0);
		
		for(int i=0;i<flatTree.length;i++) {
			System.out.print(flatTree[i] + " ");
		}
		System.out.println();
		for(int i=0;i<startTime.length;i++) {
			System.out.print(startTime[i] + " ");
		}
		System.out.println();
		for(int i=0;i<endTime.length;i++) {
			System.out.print(endTime[i] + " ");
		}
		
		out.close();

	}
	
	
	public static void flatenTreeUsingDFS(int root, int par) {
		flatTree[timer] = root;
		startTime[root] = timer;
		timer++;
		for(int i=0;i<adj[root].size();i++) {
			int child = adj[root].get(i);
			if(child!=par) {
				flatenTreeUsingDFS(child, root);
			}
		}
		flatTree[timer] = root;
		endTime[root] = timer;
		timer++;
	}
	
	
	
	
	
	/*
	 *  8 
		7
		1 2
		1 5
		2 3
		2 4
		5 6
		5 7
		5 8
		
		(indexed 1-based)
		Flatten Tree: 0 1 2 3 3 4 4 2 5 6 6 7 7 8 8 5 1 
		Start Time:   0 1 2 3 5 8 9 11 13 
		End TIme:     0 16 7 4 6 15 10 12 14 
	 */
	

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
