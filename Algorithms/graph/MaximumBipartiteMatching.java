package graph;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumBipartiteMatching {

	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);
		// A graph with M applicant and N jobs
		// vertices starting from 0.
		int applicants = in.nextInt();
		int jobs = in.nextInt();

//		int totalApplications = in.nextInt();
		boolean bipartiteGraph[][] = new boolean[][] { { false, true, true, false, false, false },
				{ false, true, true, true, false, false }, { true, true, true, false, true, false },
				{ false, false, true, false, false, false }, { false, false, true, true, true, false },
				{ false, false, false, false, false, false } };

		// new boolean[applicants][jobs];

//		for(int i=0;i<totalApplications;i++) {
//			int u = in.nextInt();
//			int v = in.nextInt();
//			bipartiteGraph[u][v] = true;
//		}

		System.out.println(findMaxMatch(bipartiteGraph, applicants, jobs));

	}

	public static int findMaxMatch(boolean bpg[][], int applicants, int jobs) {

		int match[] = new int[jobs];
		Arrays.fill(match, -1);

		int result = 0;

		for (int i = 0; i < applicants; i++) {

			boolean visited[] = new boolean[jobs];

			if (bpm(bpg, i, visited, match, jobs)) {
				result++;
			}
		}

		return result;
	}

	public static boolean bpm(boolean bpg[][], int applicant, boolean visited[], int match[], int jobs) {

		for (int v = 0; v < jobs; v++) {
			if (bpg[applicant][v] && !visited[v]) {
				visited[v] = true;

				if (match[v] < 0 || bpm(bpg, match[v], visited, match, jobs)) {
					match[v] = applicant;
					return true;
				}
			}
		}

		return false;
	}

}
