package justforcoding;

public class PrintSumOfDigitsInRange {

	
	static int dp[][][] = new int[10][2][90];
	public static void main(String args[]) {
		
		// Question: Print number of ways in which sum of digits is 19 and N <= R
		// For printing all numbers whose sum is 19, you need to remove memoization or dp. 
		// So, that recursion will calculate all the numbers.
		// can go upto 10 digits. max sum = 9*10
		
		int r = 399;
		
		for(int i=0;i<10;i++)
			for(int j=0;j<2;j++)
				for(int k = 0; k<90;k++)
					dp[i][j][k] = -1;
		
		
		String rs = Integer.toString(r);
		int x = createNumbers(rs, 0, 1, 0, "");
		System.out.println(x);
		
	}
	
	public static int createNumbers(String s, int pos, int tight, int sum, String newStr) {
		int result = 0;
		if(pos == s.length()) {
			if(sum == 19) {
				//System.out.println(newStr);
				return 1;
			}
			else 
				return 0;
		}
		else if(dp[pos][tight][sum]!=-1)
			return dp[pos][tight][sum];
		else if(tight == 1) {
			for(int i=0;i<=s.charAt(pos)-'0';i++) {
				if(i == s.charAt(pos) - '0') {
					result += createNumbers(s, pos+1, 1, sum + i, newStr + Integer.toString(i));
				}
				else {
					result += createNumbers(s, pos+1, 0, sum + i, newStr + Integer.toString(i));
				}
			}
			
		}else {
			for(int i=0;i<=9;i++) {
				result += createNumbers(s, pos+1, 0, sum + i, newStr + Integer.toString(i));
			}
		}
		dp[pos][tight][sum] = result;
		return result;
	}
	
	
	
	
	
}
