package Strings;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author: Arjit Sharma
 * Time complexity: o(n*m)
 */

public class BruteForcePatternSearchAlgorithm {

	public static void main(String args[]) {

		String s = "AABAABA";
		String p = "AABA";

		BruteForcePatternSearchAlgorithm bfps = new BruteForcePatternSearchAlgorithm();
		List<Integer> list = bfps.patternSearch(s, p);
		if (list.isEmpty())
			System.out.println("No Match");
		else
			System.out.println(list);
	}

	private List<Integer> patternSearch(String s, String p) {
		List<Integer> matches = new ArrayList<Integer>();
		int n = s.length();
		int m = p.length();

		for (int i = 0; i < n - m + 1; i++) {
			int j;
			for (j = 0; j < m; j++)
				if (s.charAt(i + j) != p.charAt(j))
					break;
			if (j == m)
				matches.add(i);
		}

		return matches;
	}

}
