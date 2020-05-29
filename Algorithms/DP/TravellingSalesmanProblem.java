package dp;

import java.util.Arrays;

public class TravellingSalesmanProblem {
	
	static int dp[][];
	static int n;
	public static void main(String args[]) {
	
		int a[][] = {{0, 10, 5, 20},
					 {10, 0, 35, 25},
					 {15, 35, 0, 10},
					 {20, 25, 30, 0}};
		n = a.length;
		
//		int visited[] = {0,0,0,0};
//		solve(a, visited, 0, 0, 0);
//		System.out.println(maxSum);
		
		dp = new int[1<<n][n];
		for(int i=0;i<(1<<n);i++)
			Arrays.fill(dp[i], -1);
		
		int sum = solve1(a, 1, 0);
		System.out.println(sum);
	}
	
	static int maxSum = (int)1e5;
	
	
	public static int solve1(int a[][], int mask, int i) {
		
		int sum =(int)1e5;
		if(mask == (1<<n) - 1) {
			return a[i][0];
		}
		
		if(dp[mask][i]!=-1)
			return dp[mask][i];
		
		for(int x =0;x<n;x++) {
			if(a[i][x]!=0 && (mask & (1<<x)) == 0) {
				int y = sum;
				int z = solve1(a, mask|1<<x, x) + a[i][x];
				sum =Math.min(y,z);
			}
		}
		
		dp[mask][i]= sum;
		return  sum;
	}

	public static void solve(int a[][], int visited[], int i, int sum, int n) {
		
		visited[i]=1;
		
		if(n+1==a.length) {
			if(sum + a[i][0] < maxSum)
				maxSum=sum + a[i][0];
		}

		for(int x=0;x<a.length;x++) {
			
			if(a[i][x]!=0 && visited[x]!=1)
				solve(a, visited, x, sum + a[i][x], n+1);
		}
		
		visited[i]=0;
	}
}
