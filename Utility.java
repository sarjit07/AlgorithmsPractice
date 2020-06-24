public class Utility {

	long MOD = (long) 1e9 + 7;
	
	// Time Complexity: O(sqrt(n))
	public static void printAllDivisors(int n) {

		// To store the divisors in sorted manner
		List<Integer> divisors1 = new ArrayList<Integer>();
		Stack<Integer> divisors2 = new Stack<Integer>();

		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (n / i == i)
					divisors1.add(i);
				else {
					divisors1.add(i);
					divisors2.add(n / i);
				}
			}
		}

		for (int i = 0; i < divisors1.size(); i++) {
			System.out.print(divisors1.get(i) + " ");
		}
		while (!divisors2.isEmpty())
			System.out.print(divisors2.pop() + " ");

	}

	public static long ModPow(long x, long y, long MOD) {
		long res = 1L;
		x = x % MOD;
		while (y >= 1) {
			if ((y & 1) > 0)
				res = (res * x) % MOD;
			x = (x * x) % MOD;
			y >>= 1;
		}
		return res;
	}

	public static long modPowRecur(long x, long y, long MOD) {
		if (y == 0)
			return 1;
		else if (y % 2 == 0) {
			long z = modPowRecur(x, y / 2, MOD);
			return (z * z) % MOD;
		} else {
			return (x % MOD) * modPowRecur(x, y - 1, MOD);
		}
	}

	public static long gcdExtended(long a, long b, long x, long y) {
		if (a == 0) {
			x = 0;
			y = 1;
			return b;
		}

		long x1 = 1, y1 = 1; // To store results of recursive call
		long gcd = gcdExtended(b % a, a, x1, y1);

		// Update x and y using results of recursive call
		x = y1 - (b / a) * x1;
		y = x1;

		return gcd;
	}
	// TimeComplexity: Log(y)
	public static double pow(long x, long y) {
		if (y == 0) {
			return 1;
		}
		if (y == 1) {
			return x;
		}
		double temp = pow(x, y / 2);
		if (y % 2 == 0)
			return temp * temp;
		else {
			if (y > 0) {
				return x * temp * temp;
			} else {
				return (temp * temp) / x;
			}
		}
	}

	public static double powItr(long x, long y) {
		double res = 1;
		while (y >= 1) {
			if ((y & 1) > 0) res = res * x;
			x = x * x;
			y >>= 1;
		}
		return res;
	}
	
	public static void Sorter(){
	    MyElement a[] = {Some MyElement objects};
		
	    // Way-1 sorting in increasing order
	    SortAscending compAscending = new SortAscending();
	    Arrays.sort(a, compAscending);
	    
	    // Way-2 Sorting in ascending order
	    Arrays.sort(a, Comparator.comparingLong(o -> o.x));
		
	    // sorting in descending order
	    SortDescending compDescending = new SortDescending();
	    Arrays.sort(a, compDescending);	
		
	}
	
	// returns binary representation of a Number
	public static String binaryRep(int n, String s) {
		if(n>1)  return binaryRep(n>>1, n%2 + s);
		return ((n%2) + s);
	}
	
	// Prints binary representation of a Number
	// Gives result in 32 bits representation
	public static void binaryRep(int n) {
		String s ="";
		for(int i =0;i<=31;i++) {
			int x = 1<<i;
			if((x&n) == 0) s = '0' + s;
			else
				s = '1' + s;
		}
		System.out.println(s);
	}
	
	// Prints all 2^N subsequences for an integer 
	public static void printAll2nCombinations(int n) {
		for(int i=0;i < (1<<n);i++) {
			
			for(int j=0;j<n;j++) {
				int x = 1 << j;
				if((i & x) > 0 ) {
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

}


class MyElement{
	int x;
	int a;

	public MyElement(int x, int a) {
		this.x = x;
		this.a = a;
	}	
}

class SortAscending implements Comparator<MyElement>{
	@Override
	public int compare(MyElement o1, MyElement o2) {
		return (int) (o1.x - o2.x);
	}
}

class SortDescending implements Comparator<MyElement>{
	@Override
	public int compare(MyElement o1, MyElement o2) {
		return (int) (o2.x - o1.x);
	}
}

