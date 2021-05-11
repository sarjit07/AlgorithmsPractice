
package dp;

import java.io.*;
import java.util.*;


public class LongestCommonSubsequence {
	
	static int dp[][];
	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = in.nextInt();
		
		while (t-- != 0) {

			String s = in.nextLine();
			String p = in.nextLine();
			dp = new int[s.length()+1][p.length()+1];
			for(int i=0;i<=s.length();i++)
				Arrays.fill(dp[i], -1);
			
      			//Method-1 - Bottom Up
			System.out.println(longestCommonSubsequence(s,p, s.length(), p.length()));
      
      			//Method-2 - Top Down
			longestCommonSubsequence(s, p);
		}

		out.close();

	}
	
	
	//prints the longest common subsequence
	public static void longestCommonSubsequence(String s, String p) {
		
		int dp[][] = new int[s.length()+1][p.length()+1];
		
		for(int i=1;i<=s.length();i++) {
			for(int j=1;j<=p.length();j++) {
				if(s.charAt(i-1) == p.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		//printing the LCS
		int i=s.length(); int j = p.length();
		String new_s = "";
		
		while(i>0 && j>0) {
			
			if(s.charAt(i-1) == p.charAt(j-1)) {
				new_s = s.charAt(i-1) + new_s;
				i--;
				j--;
			}
			else {
				if(dp[i][j-1]>=dp[i-1][j]) {
					 j--;
				}
				else {
					i--;
				}
			}
			
		}
		System.out.println(new_s);
	}
	
	
	
	
	
	// recursion with memoization LCS
	public static int longestCommonSubsequence(String s, String p, int m, int n) {
		
		if(m==0 || n == 0) {
			return 0;
		}
		
		if(dp[m][n]!=-1)
			return dp[m][n];
		
		int count  = 0;

		if(s.charAt(m-1) == p.charAt(n-1)) {
			count += 1 + longestCommonSubsequence(s,p, m-1,n-1);
		}
		else {
			count +=  Math.max(longestCommonSubsequence(s,p, m-1, n), 
					longestCommonSubsequence(s,p, m, n-1));
		}
		
		return dp[m][n] = count;
		
		
	}
	
	
	
	//Recursive 
	
	public static int longestCommonSubstring(String s, String p, int m , int n, int res) {
		
		//3d dp would be needed for this
		
		if(m==0||n==0)
			return res;
		
		
		if(s.charAt(m-1) == p.charAt(n-1)) {
			res = longestCommonSubstring(s, p, m-1, n-1, res+1);
		}
		return Math.max(res, Math.max(longestCommonSubstring(s, p, m-1, n, 0),
					longestCommonSubstring(s, p, m, n-1, 0)));
		
		
//		return dp[m][n] = Math.max(count, count1);
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


