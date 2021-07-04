
package justforcoding;

import java.io.*;
import java.util.*;

//https://www.hackerrank.com/challenges/qheap1/problem


public class QHEAP1 {
	
	static int a[] = new int[100001];
	
	static int index = 0;
	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int q = in.nextInt();
//		Arrays.fill(a, (int)1e9+5);
		while (q-- != 0) {

			String s = in.nextLine();
			String type = s.split(" ")[0];
			
			
			if(type.equalsIgnoreCase("1")) {
				int x = Integer.valueOf(s.split(" ")[1]);
				a[index] = x;
				index++;
				minHeapifyUp(index-1);
			}
			else if(type.equalsIgnoreCase("2")) {
				long x = Long.valueOf(s.split(" ")[1]);
				int i;
				for(i=0;i<index;i++) {
					if(a[i] == x)
						break;
				}
				
				int temp = a[i];
				a[i] = a[index-1];
				a[index-1] = 0;
				index--;
				minHeapify(i);
				
			}
			else if(type.equalsIgnoreCase("3")){
				System.out.println(a[0]);
			}
			
		}

		out.close();

	}
	
	static void minHeapifyUp(int i) {
		int parent = (i-1)/2;
		if(a[parent] > a[i]) {
			int temp = a[parent];
			a[parent]  = a[i];
			a[i] = temp;
			minHeapifyUp(parent);
		}
	}
	
	static void minHeapify(int i) {
		int smallest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		if(l< index && a[l] < a[smallest])
			smallest = l;
		if(r<index && a[r] < a[smallest])
			smallest = r;
		
		if(smallest != i) {
			int temp = a[i];
			a[i] = a[smallest];
			a[smallest] = temp;
			
			minHeapify(smallest);
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


