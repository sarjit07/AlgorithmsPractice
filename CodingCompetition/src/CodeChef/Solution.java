package CodeChef;

import java.util.Scanner;
import java.io.PrintWriter;

public class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		if(in.hasNextInt()){
			int t = in.nextInt();
			while(t--!=0) {
				if(in.hasNextInt()){
					long x = in.nextInt();
					long n = in.nextInt();
					
					for(int i=0;i<n;i++) {
						for(int j=0;j<n;j++) {
						}
					}
					out.println();
				}
			}
		}
		out.flush(); 
		out.close();
		in.close();
		System.exit(0);
	}
}
