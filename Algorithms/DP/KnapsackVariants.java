package dp;

public class KnapsackVariants {

	public static void main(String args[]) {
		
		int weights[] = {10,20,30};
		int values[] = {60,100,120};
		int w = 50;
		
		
		
		// subset sum 
		int set[] = {1, 2, 3};
		int sum = 100;
		int dp[][] = new int[set.length+1][sum+1]; 
		for(int i=0;i<set.length+1;i++)
			for(int j=0;j<sum+1;j++)
				dp[i][j]=-1;
		
		// print all subsets
		//printSubsets(set, new int[set.length], 0);
		
		
		//count subsets with sum = K
		int set1[] = { 3, 3, 3, 3 };
		int dp1[][] = new int[set1.length+1][sum+1]; 
		for(int i=0;i<set.length+1;i++)
			for(int j=0;j<sum+1;j++)
				dp1[i][j]=-1;
		
		
		// min difference subset partition
		int set2[] = {3, 1, 4, 2, 2, 1} ;
		int totSum = 13;
		int dp2[][] = new int[set2.length+1][totSum+1]; 
		for(int i=0;i<set2.length+1;i++)
			for(int j=0;j<totSum+1;j++)
				dp2[i][j]=-1;
		
		System.out.println(minDifferenceSubsetPartitionsDP(set2, 13, 0, 0, dp2));
		
		
		
	}
	
	// 1. knapsack porblem
	public static int getMax(int weights[], int values[], int w) {
		int dp[][] = new int[w+2][weights.length+2];
		for(int i=0;i<w+2;i++)
			for(int j=0;j<weights.length+2;j++)
				dp[i][j]=-1;
		return getMaxRecur(weights, values, w, 0, 0);
		
	}
	
	
	public static int getMaxRecur(int weights[], int values[], int w, int x, int sum) {
		
		if(x>weights.length || w < 0) {
			return 0;
		}
		if(w==0 || x==weights.length) {
			return sum;
		}
		return Math.max(getMaxRecur(weights, values, w-weights[x], x+1, sum+values[x]), 
				getMaxRecur(weights, values, w, x+1, sum));
		
	}
	
	public static int getMaxDP(int weights[], int values[], int w, int x, int sum, int dp[][]) {
		
		if(x>weights.length || w < 0) {
			return 0;
		}
		if(w==0 || x==weights.length) {
			return sum;
		}
		if(dp[w][x]!=-1)
			return dp[w][x];
		
		return dp[w][x] = Math.max(getMaxDP(weights, values, w-weights[x], x+1, sum+values[x], dp), 
				getMaxDP(weights, values, w, x+1, sum, dp));
		
	}
	
	
	// 2. Subset sum porblem
	public static boolean subsetSum(int set[], int sum, int i) {
		
		if(sum == 0 )
			return true;
		if(i == set.length || sum < 0)
			return false;
		
		if(subsetSum(set, sum-set[i], i+1) || subsetSum(set, sum, i+1))
			return true;
		
		return false;
	}
	
	public static boolean subsetSumDP(int set[], int sum, int i, int dp[][]) {
		
		if(sum == 0 )
			return true;
		if(i == set.length || sum < 0)
			return false;
		
		if(dp[i][sum]!=-1)
			return dp[i][sum]==1;
		
		if(subsetSumDP(set, sum-set[i], i+1, dp) || subsetSumDP(set, sum, i+1, dp)) {
			dp[i][sum] = 1;
			return true;
		}
		
		dp[i][sum] = 0;
		return false;
	}
	
	
	//3. Print all subsets of a set
	public static void printSubsets(int set[], int s[], int i) {
		
		if(i> set.length)
			return;
		
		if(i==set.length) {
			for(int x=0;x<s.length;x++) {
				if(s[x] == 0)
					continue;
				System.out.print(s[x] + " ");
			}
			System.out.println();
			return;
		}
		
		
		s[i]=set[i];
		printSubsets(set, s, i+1);
		s[i]=0;
		printSubsets(set, s, i+1);
		
	}
	
	
	// 4. Count subsets with given sum = K
	public static int countSubsetsSum(int set[], int sum, int i) {
		
		if(sum == 0) {
			return 1;
		}
		
		if(i >= set.length)
			return 0;
		
		return countSubsetsSum(set, sum-set[i], i+1) + countSubsetsSum(set, sum, i+1); 
		
	}
	
	
	public static int countSubsetsSumDP(int set[], int sum, int i, int dp[][]) {
		
		if(sum == 0) {
			return 1;
		}
		
		if(i >= set.length)
			return 0;
		
		if(dp[i][sum]!=-1)
			return dp[i][sum];
		
		return dp[i][sum] = countSubsetsSum(set, sum-set[i], i+1) + countSubsetsSum(set, sum, i+1); 
		
	}
	
	// 5. Minimum difference Subset Partitions
	// idea: take total sum, and substract the sum for all subsets from the totSum. And return the min.
	public static int minDifferenceSubsetPartitions(int set[], int totSum, int sum, int i) {
		
		if(i==set.length) {
			if(sum == totSum)
				return (int)1e5;
			return totSum - sum;
		}
		if(i>set.length) {
			return (int)1e5;
		}
		
		return Math.min(minDifferenceSubsetPartitions(set, totSum, sum+set[i], i+1),
						minDifferenceSubsetPartitions(set, totSum, sum, i+1));
			
		
	}
	
	public static int minDifferenceSubsetPartitionsDP(int set[], int totSum, int sum, int i, int dp[][]) {
		
		if(i==set.length) {
			if(sum == totSum)
				return (int)1e5;
			return totSum - sum;
		}
		if(i>set.length) {
			return (int)1e5;
		}
		if(dp[i][sum]!=-1)
			return dp[i][sum];
		
		return dp[i][sum] = Math.min(minDifferenceSubsetPartitionsDP(set, totSum, sum+set[i], i+1, dp),
									minDifferenceSubsetPartitionsDP(set, totSum, sum, i+1, dp));
			
	}
	
	//6. target Sum 
	// https://www.geeksforgeeks.org/number-of-ways-to-calculate-a-target-number-using-only-array-elements/
	
}
