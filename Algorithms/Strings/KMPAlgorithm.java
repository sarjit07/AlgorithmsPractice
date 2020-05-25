package Strings;

public class KMPAlgorithm {

	public static void main(String args[]) {
		String s = "bacbabababacaca";
		String p = "ababaca";

		KMPAlgorithm kmp = new KMPAlgorithm();
		int match = kmp.patternSearch(s, p);
		if (match == -1)
			System.out.println("No Match");
		else
			System.out.println(match);
	}

	public int patternSearch(String s, String p) {
		int n = s.length();
		int m = p.length();

		int F[] = prefix(p);
		for (int i = 0, j = 0; i < n;) {
			if (s.charAt(i) == p.charAt(j)) {
				if (j == m - 1) {
					return (i - j);
				} else {
					i++;
					j++;
				}
			} else if (j > 0)
				j = F[j - 1];
			else
				i++;

		}
		return -1;
	}

	public int[] prefix(String p) {
		int i = 1, j = 0;
		int F[] = new int[p.length()];
		F[0] = 0;
		for (i = 1; i < p.length();) {
			if (p.charAt(i) == p.charAt(j)) {
				F[i] = j + 1;
				i++;
				j++;
			} else if (j > 0)
				j = F[j - 1];
			else {
				F[i] = 0;
				i++;
			}
		}
		return F;
	}
}
