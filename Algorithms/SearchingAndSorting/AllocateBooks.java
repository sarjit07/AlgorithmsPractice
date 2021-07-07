

import java.io.*;
import java.util.*;


public class AllocateBooks {
	public static void main(String args[]) {

		System.out.println(Math.ceil(7/3));
		
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int t = in.nextInt();
		while (t-- != 0) {

			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(12);
			a.add(34);
			a.add(67);
			a.add(90);
			int b = 2;
			
			System.out.println(books(a, b));
		}

		out.close();

	}
	
	public static int books(ArrayList<Integer> a, int b) {
		
		int n = a.size();

        if(n < b)
            return -1;
		int low = 0;
		int high = 0;
				
		for(int i=0;i<a.size();i++) {
			int x = a.get(i);
			if(low < x)
				low = x;
			high = high + x;
		}
		int ans = (int)1e5;
		while(low<=high) {
			
			int mid = (low + high)/2;
			
			if(check(a, b, mid)) {
				ans = Math.min(ans,  mid);
				high = mid-1;
			}
			else {
				low = mid+1;
			}
			
		}

		return ans;
	}
	
	public static boolean check(ArrayList<Integer> a, int b, int mid) {
		
		int temp = 0;
		int readers =1;
		for(int i=0;i<a.size();i++) {
			
			temp += a.get(i);
			if(temp > mid) {
				temp = a.get(i);
				readers++;
			}
		}
		
		return readers <= b;
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


