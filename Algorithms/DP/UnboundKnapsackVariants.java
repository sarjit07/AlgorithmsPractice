
package dp;

import java.io.*;
import java.util.*;


public class UnboundedKnapsackVariants {
	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		
		int a[] = {1, 5, 8, 9, 10, 17, 17, 20};
		int dp[] = new int[a.length+1];
		Arrays.fill(dp, -1);
		//System.out.println(rodCuttingDP(a,  a.length, 0, dp));
		
		int a1[] = {1,2,3};
		int x = 4;
		// System.out.println(countWays(a1, x));

		
		int a2[] = {1,5,6,8};
		int x1 = 16;
		// System.out.println(minWays(a2, x1, 0));
		
		int n = 10;
		int dp1[] = new int[n+1];
		Arrays.fill(dp1, -1);
		System.out.println(maxProductRopeCutDP(n, n, 1, dp1));
		out.close();

	}
	
	
	public static int rodCutting(int a[], int len, int price) {
		
		if(len == 0)
			return price;
		
		if(len < 0)
			return 0;
		
		int max = -(int)1e5;
		for(int i=0;i<a.length;i++) {
			max = Math.max(max, rodCutting(a, len - i - 1, price + a[i]));
		}
		
		return max;
	}
	
	public static int rodCuttingDP(int a[], int len, int price, int dp[]) {
		
		if(len == 0)
			return price;
		
		if(len < 0)
			return 0;
		
		int max = -(int)1e5;
		
		if(dp[len]!=-1)
			return dp[len];
		
		for(int i=0;i<a.length;i++) {
			max = Math.max(max, rodCutting(a, len - i - 1, price + a[i]));
			dp[i]=max;
		}
		
		return dp[a.length-1];
	}
	
	
	//Coin change. maximum number of ways. including ordering.
	public static int countWays(int set[], int x) {
		
		if(x == 0)
			return 1;
		if(x<0)
			return 0;
		
		int count = 0;
		
		for(int i=0;i<set.length;i++) {
			count += countWays(set, x-set[i]);
		}
		
		return count;
	}
	
	// Coin change. maximum number of ways. No ordering. example 1,1,2 = 2,1,1 or 1,2,1
	// works in unbounded scenario, because, at every step, we either include or not include a value over all subsets.
	public static int countWays2(int set[], int x, int i) {
		
		if(x == 0)
			return 1;
		if(x<0)
			return 0;
		
		return countWays2(set, x - set[i], i+1) + countWays2(set, x , i+1) ;
	}
	
	//DP solution is simple for this one. Same like subsetsum one.
	
	
	
	// Coin change: minimum no. of coins needed to make X.
	public static int minWays(int set[], int x, int count) {
		
		if(x==0)
			return count;
		
		if(x<0)
			return (int)1e5;
		
		int min = (int)1e5;
		
		for(int i=0;i<set.length;i++) {
			int d = minWays(set, x - set[i] , count+1);
			min = Math.min(min, d);
		}
		
		return min;
	}
	
	
	
	//maximum product with rope cutting
	public static int maxProductRopeCut(int n, int x, int product) {
		
		if(x==0)
			return product;
		if(x<0)
			return 0;
		
		int max = -(int)1e5;
					
		for(int i=1;i<=n;i++) {
			max = Math.max(max,  maxProductRopeCut(n, x-i, product*i));
		}
		
		return max;
	}
	
	
	//maximum product with rope cutting
	public static int maxProductRopeCutDP(int n, int x, int product, int dp[]) {
		
		if(x==0)
			return product;
		if(x<0)
			return 0;
		
		if(dp[x]!=-1)
			return dp[x];
		
		int max = -(int)1e5;
					
		for(int i=1;i<=n;i++) {
			max = Math.max(max,  maxProductRopeCut(n, x-i, product*i));
			dp[i]=max;
		}
		
		return dp[n];
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


