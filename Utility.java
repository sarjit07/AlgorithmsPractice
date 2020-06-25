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
	
		/* SieveOfEratosthenes Algo
	 * Time Complexity: O(n*log(logn)) 
	 * As outer loop runs => n/2 + n/3 + n/5 + ... + n/p
	 * => n * (1/2 +1/3+ 1/5 + ... + 1/p) - Harmonic Progression
	 * => n * log(log(n))
	 */
	
	public static void findAllPrimeNumbers(int n) {
		
		boolean prime[] = new boolean[n+1];
		Arrays.fill(prime, true);
		
		for(int i=2; i*i <=n ; i++) {
			if(prime[i] == true) {
				
				for(int j=i*i;j<=n;j=j+i) {
					prime[j] = false;
				}
			}
		}
		
		// Print all prime numbers 
		for(int i = 2; i <= n; i++) 
		{ 
		    if(prime[i] == true) 
			System.out.print(i + " "); 
		} 
				
	}
	

	
	/*
	 * Time Complexity: O(sqrt(n))
	 */
	public static void printAllPrimeFactors(int n) {
		
		List<Integer> primeFactors = new ArrayList<Integer>();
		while(n%2 == 0) {
			primeFactors.add(2);
			n = n/2;
		}
		
		for(int i=3;i<=Math.sqrt(n);i++) {
			while(n%i == 0) {
				primeFactors.add(i);
				n = n/i;
			}
		}
		
		if(n>2)
			primeFactors.add(n);
		
		for(int i=0;i<primeFactors.size();i++) {
			System.out.print(primeFactors.get(i) + " ");
		}
		
	}
	
	/*
	 * Precalculating Shortest Prime Factor for each number till 1e7, We can
	 * calculate all prime factors of a number in o(logn) time
	 * 
	 * PreCal time complexity: o(nlog(logn)) 
	 * Time Complexity: o(logn)
	 * 
	 */

	public static void findAllPrimeFactorsUsingSeive(int n) {

		int spf[] = sieve();

		List<Integer> primeFactors = new ArrayList<Integer>();

		while (n != 1) {
			primeFactors.add(spf[n]);
			n = n / spf[n];
		}

		for (int i = 0; i < primeFactors.size(); i++) {
			System.out.print(primeFactors.get(i) + " ");
		}
	}

	public static int[] sieve() {
		int MAX = (int) 1e7 + 1;
		int shortesPrimeFactor[] = new int[MAX];

		shortesPrimeFactor[1] = 1;

		for (int i = 2; i < MAX; i++) {
			shortesPrimeFactor[i] = i;
		}
		for (int i = 4; i < MAX; i += 2) {
			shortesPrimeFactor[i] = 2;
		}

		for (int i = 3; i * i < MAX; i++) {

			if (shortesPrimeFactor[i] == i) {

				for (int j = i * i; j < MAX; j += i) {
					if (shortesPrimeFactor[j] == j)
						shortesPrimeFactor[j] = i;
				}
			}
		}

		return shortesPrimeFactor;
	}
	
	/*
	 * Time Complexity: o(n)
	 * Reference: https://www.youtube.com/watch?v=aGjfSTr_0AE
	 * Proof also in notes.
	 */
	public static int nCrModP(int n, int r, int p) {
		
		if(r==0) return 1;
		int fac[] = new int[n+1];
		
		fac[0]=1;
		for(int i=1;i<=n;i++) {
			fac[i] = ( fac[i-1]*i ) % p;
		}
		
		return (int) ((fac[n]%p) * (ModPow(fac[r], p-2, p)%p) * (ModPow(fac[n-r], p-2, p)%p))%p; 
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

