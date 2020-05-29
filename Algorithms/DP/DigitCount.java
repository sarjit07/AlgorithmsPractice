package dp;

public class DigitCount {

	
	public static void main(String args[]) {
		int m = 3;
		int n = 2;
		int a[] = {1,3, 6};
		System.out.println(findNumbers(a,n));
	}
	
	static int dp[][] = new int[12][12];
	public static int findNumbers(int a[], int n) {
		for(int i=0;i<12;i++) 
			for(int j=0;j<12;j++)
				dp[i][j] = -1;
		return findNumbersUtil(a, n, 0, 0);
	}
	
	public static int findNumbersUtil(int a[], int n, int prevd, int pos) {
		
		int result =0;
		if(dp[pos][prevd] != -1)
			return dp[pos][prevd];
		if(pos == n) {
				return 1;
		}
		for(int i=0;i<a.length;i++) {
			int x = a[i];
			if(prevd == 0 || Math.abs(prevd-x)<=2) {
				result = result + findNumbersUtil(a, n, x, pos+1);
			}
		}
		dp[pos][prevd] = result;
		return result;
		
	}
}
