package dp;

public class KnapsackProblem {

	public static void main(String args[]) {
		
		int weights[] = {10,20,30, 40, 50};
		int values[] = {60,100,120, 150, 70};
		int w = 60;
		
		System.out.println(getMax(weights, values, w));
	}
	
	
	public static int getMax(int weights[], int values[], int w) {
		int dp[][] = new int[w+2][weights.length+2];
		for(int i=0;i<w+2;i++)
			for(int j=0;j<weights.length+2;j++)
				dp[i][j]=-1;
		return getMaxUtil(weights, values, w, 0, 0, dp);
		
	}
	
	
	public static int getMaxUtil(int weights[], int values[], int w, int x, int sum, int dp[][]) {
		if(w < 0 || x > weights.length ) {
			return 0;
		}
		else if(w == 0 || x==weights.length) {
			return sum;
		}
		if(dp[w][x]!=-1) 
			return dp[w][x]; 
		
		int include =  getMaxUtil(weights, values, w-weights[x], x+1, sum+values[x], dp);
		int notInclude = getMaxUtil(weights, values, w, x+1, sum, dp);
		
		return dp[w][x] = Math.max(include, notInclude );
	}
}
