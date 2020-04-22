public class Utility {
	
	long MOD = (long)1e9 + 7;
	
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

}
