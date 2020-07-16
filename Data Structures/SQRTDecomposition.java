package optimization;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Problem: https://www.spoj.com/problems/RMQSQ/
 * https://www.youtube.com/watch?v=o7278rPg_9I&list=PL2q4fbVm1Ik7Ds5cuaoOmExjOQG31kM-p&index=1
 */
public class SQRTDecomposition {

	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		
		int q = in.nextInt();

		

		int block = (int) Math.sqrt(n) + 1;

		int b[] = new int[block];

		
		// pre-process: store minimum of each block in b
		Arrays.fill(b, Integer.MAX_VALUE);
		
		for (int i = 0; i < n; i++) {
			b[i / block] = Math.min(b[i / block], a[i]);
		}
		
		// ans queries
		for (int k = 0; k < q; k++) {
			int l = in.nextInt();
			int r = in.nextInt();

			int min = Integer.MAX_VALUE;

			int bl = l / block;
			int br = r / block;

			if (bl == br) {
				for (int i = l; i <= r; i++) {
					min = Math.min(min, a[i]);
				}

			} else {

				for (int i = l; i < (bl + 1) * block; i++) {
					min = Math.min(min, a[i]);
				}
				for (int i = bl + 1; i < br; i++) {
					min = Math.min(min, b[i]);
				}
				for (int i = block * br; i <= r; i++) {
					min = Math.min(min, a[i]);
				}

			}

			System.out.println(min);

		}

	}

}
