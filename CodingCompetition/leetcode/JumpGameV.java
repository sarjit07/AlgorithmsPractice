package leetcode;

import java.util.Arrays;

public class JumpGameV {

	public static void main(String args[]) {
		
		int arr[] = {59,8,74,27,92,36,95,78,73,54,75,37,42,15,59,84,66,25,35,61,97,16,6,52,49,18,22,70,5,59,92,85};
		
		int d = 20;
		JumpGameV jg = new JumpGameV();
		System.out.println(jg.maxJumps(arr, d));
		
	}
	
	int dp[];
	public int maxJumps(int arr[], int d) {
	
		dp = new int[arr.length];
		Arrays.fill(dp, -1);
		int max = 0;
		for(int i=0;i<arr.length;i++)
			max = Math.max(max, dfs(arr,d,i));
		return max;
	}
	
	public int dfs(int a[],int d, int i) {
		
		if(dp[i]!=-1) return dp[i];
		int max = 1;
		for(int j= i-1; j>=Math.max(0, i-d); j--) {
			if(a[j]>=a[i]) {
				break;
			}
			if(a[j]<a[i]) {
				max = Math.max(dfs(a,d,j) + 1, max);
			}
			
		}
		
		for(int j= i+1; j<Math.min(a.length, i+d+1); j++) {
			if(a[j]>=a[i]) {
				break;
			}
			if(a[j]<a[i]) {
				max = Math.max(dfs(a,d,j) + 1, max);
			}
			
		}
		
		return dp[i]=max;
	}
		
		
}
