package Strings;

import java.util.HashSet;

/*
 * @Author: Arjit Sharma
 * 
 * Problem: Given a String 's', find all the different substrings of 's'.
 * Solution: Based on String Hashing. 
 * 1. Create the Hash of the string 's'.
 * 2. Calculate the hash of all the substrings in o(1) time.
 * 
 * Time Complexity: o(n + n^2logn)
 */

public class AllDifferentSubstringsInAString {

	static long hash[];
	static long p_pow[];
	static int n;
	static long prime = 3;
	static long MOD = (long) 1e9 + 7;

	public static void main(String[] args) {

		String s = "abcab";
		n = s.length();
		hash = new long[n + 1];
		p_pow = new long[n + 1];
		System.out.println(substrings(s));
	}

	public static long substrings(String s) {
		createHash(s);
		long count = 0;
		for (int l = 1; l <= n; l++) {
			HashSet<Long> set = new HashSet<Long>();
			for (int i = 0; i <= n - l; i++) {
				long newHash = getHash(i + l, i);
				set.add(newHash);
			}
			count += set.size();
		}

		return count;
	}

	// creates the hash of a string in o(n) time.
	public static void createHash(String s) {
		hash[0] = 0;
		p_pow[0] = 1;
		for (int i = 0; i <= n - 1; i++) {
			hash[i + 1] = (hash[i] + (s.charAt(i) - 'a' + 1) * p_pow[i]) % MOD;
			p_pow[i + 1] = (p_pow[i] * prime) % MOD;
		}
	}

	// Gets the hash of a substring in a string in o(1) time.
	public static long getHash(int end, int start) {
		long newHash = (hash[end] - hash[start] + MOD) % MOD;
		newHash = (newHash * p_pow[n - start - 1]) % MOD;
		return newHash;
	}

}
