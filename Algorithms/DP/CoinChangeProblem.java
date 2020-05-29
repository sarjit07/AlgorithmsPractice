package dp;

public class CoinChangeProblem {

	public static void main(String args[]) {
		int n = 5;
		int m = 3;
		int a[] = {1,2,3};
		
		System.out.println(findWays(n,a));
		
	}
	
	
	public static long findWays(int n, int a[]) {
		
		long b[][] = new long[a.length+1][n+1];
		
		for(int i=0; i<=a.length;i++) {
			for(int j = 1; j <= n; j++) {
				if(i==0) {
					b[i][j] = 0;
					continue;
				}
				if(j == a[i-1]) {
					b[i][j] = 1 + b[i-1][j];
				}else if( j > a[i-1]) {
					b[i][j] = b[i-1][j] + b[i][j - a[i-1]];
				}
				else if( j < a[i-1]) {
					b[i][j] = b[i-1][j];
				}
			}
		}
		
		return b[a.length][n];
	}
	
}
