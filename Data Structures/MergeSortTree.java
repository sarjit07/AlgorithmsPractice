package datastructures;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

/*
 * Problem: Given an array of size N and Q queries of form l, r, k, find the number of elements
 * in the range L to R which are strictly smaller than K.
 * N : 10, Q: 1
 * Array: 1 4 3 5 6 4 3 2 4 1
 * Query: 1 5 4 
 * Answer: 2
 * 
 * 
 * TimeComplexity: Build: O(NLogN) because of merge sort
 * QueryProcessTime: O(LogN * LogN)  (merge tree + binary search)
 * SpaceComplexity: O(N*LogN)
 */
public class MergeSortTree {

	static int a[];
	static Node mst[];

	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = in.nextInt();
		int q = in.nextInt();

		a = new int[n + 1];
		mst = new Node[4 * n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = in.nextInt();
		}

		build(1, 1, n);

		while (q-- != 0) {
			int l = in.nextInt();
			int r = in.nextInt();
			int k = in.nextInt();

			System.out.println(query(1, 1, n, l, r, k));
		}

		out.close();

	}

	public static int query(int si, int ss, int se, int qs, int qe, int k) {

		if (ss > qe || se < qs)
			return 0;
		if (ss >= qs && se <= qe) {
			int res = Collections.binarySearch(mst[si].list, k - 1);
			if (res < 0)
				res = -res - 1;
			else
				res += 1;
			return res;
		}
		int mid = (ss + se) / 2;
		int left = query(2 * si, ss, mid, qs, qe, k);
		int right = query(2 * si + 1, mid + 1, se, qs, qe, k);

		return left + right;
	}

	public static void build(int si, int ss, int se) {
		if (ss == se) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(a[ss]);
			mst[si] = new Node(list);
			return;
		}

		int mid = (ss + se) / 2;
		build(2 * si, ss, mid);
		build(2 * si + 1, mid + 1, se);

		int i = 0;
		int j = 0;

		ArrayList<Integer> list = new ArrayList<Integer>();
		while (i < mst[2 * si].list.size() && j < mst[2 * si + 1].list.size()) {
			if (mst[2 * si].list.get(i) <= mst[2 * si + 1].list.get(j)) {

				list.add(mst[2 * si].list.get(i));
				i++;
			} else {
				list.add(mst[2 * si + 1].list.get(j));
				j++;
			}
		}

		while (i < mst[2 * si].list.size()) {
			list.add(mst[2 * si].list.get(i));
			i++;
		}
		while (j < mst[2 * si + 1].list.size()) {
			list.add(mst[2 * si + 1].list.get(j));
			j++;
		}
		mst[si] = new Node(list);

	}

	static class Node {
		ArrayList<Integer> list;

		Node(ArrayList<Integer> list) {
			this.list = list;
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
