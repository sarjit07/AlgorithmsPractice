package Codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;



public class PowerfulArray86D {
	
	static int block;
	public static void main(String args[]) {
		
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = in.nextInt();
		int q = in.nextInt();
		
		int a[] = in.nextIntArray(n);
		block = (int)Math.sqrt(n);
		
		Q qs[] = new Q[q];
		for(int i=0;i<q;i++)
			qs[i] = new Q(i, in.nextInt()-1, in.nextInt()-1);
		
		Comp c = new Comp();
		Arrays.sort(qs, c);
		
		long ans[] = solve(qs, a);
		
		for(int i=0;i<ans.length;i++)
			out.println(ans[i]);
		
		out.flush();
		out.close();
		
	}
	
	static int freq[];
	static long sum; 
	public static long[] solve(Q qs[], int a[]) {
		freq = new int[(int)1e6+5];
		long ans[] = new long[qs.length];
		sum = 0;
		
		int currLeft = qs[0].l; int currRight = qs[0].l -1;
		
		for(int i=0;i<qs.length;i++) {
			int l = qs[i].l;
			int r = qs[i].r;
			int id = qs[i].id;
			
			while(currLeft > l) {
				currLeft--;
				add(a, currLeft);
			}
			while(currLeft < l) {
				remove(a, currLeft);
				currLeft++;
			}
			while(currRight < r) {
				currRight++;
				add(a, currRight);
			}
			while(currRight > r) {
				remove(a, currRight);
				currRight--;
			}
			ans[id] = sum;
		}
		
		
		return ans;
	}
	
	
	public static void add(int a[], int index) {
		
		int prevFreq = freq[a[index]];
		int prevSum = prevFreq*prevFreq*a[index];
		int newFreq = ++freq[a[index]];
		int newSum = newFreq*newFreq*a[index];
		sum += -prevSum  + newSum;
	}
	
	public static void remove(int a[], int index) {
		int prevFreq = freq[a[index]];
		int prevSum = prevFreq*prevFreq*a[index];
		int newFreq = --freq[a[index]];
		int newSum = newFreq*newFreq*a[index];
		sum += -prevSum  + newSum;
	}
	
	
	
	static class Comp implements Comparator<Q>{

		@Override
		public int compare(Q o1, Q o2) {
			
			if(o1.l/block != o2.l/block)
				return o1.l/block - o2.l/block;
			
			else
				return o1.r - o2.r;
		}
		
	}
	
	static class Q {
		int id;
		int l;
		int r;
		Q(int id, int l, int r){
			this.id = id;
			this.l = l;
			this.r = r;
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
