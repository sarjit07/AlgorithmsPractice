public class Utility {

	long MOD = (long) 1e9 + 7;

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

}
