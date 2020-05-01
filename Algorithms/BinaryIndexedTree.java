package justforcoding;

import java.util.Arrays;

public class BinaryIndexedTree {

	public static void main(String[] args) {
	
		int freq[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
		
		int BIT[] = createBinaryIndexedTree(freq);
		System.out.println(getSum(BIT,10));
		freq[3] += 6; 
		updateBIT(BIT, 3, 6, freq.length);  
		System.out.println(getSum(BIT,10));
	}
	
	public static int[] createBinaryIndexedTree(int a[]) {
		int BIT[]  = new int[(int) 1e5];
		Arrays.fill(BIT, 0);
		
		for(int i=0;i<a.length;i++) 
			updateBIT(BIT, i, a[i], a.length);
		return BIT;
	}
	
	public static void updateBIT(int BIT[], int index, int val, int n) {
		index = index + 1;
		while(index < n) {
			BIT[index] += val;
			index += index & (-index); 
		}
	}
	
	public static int getSum(int BIT[], int index) {
		index = index + 1;
		int sum = 0;
		while(index > 0) {
			sum += BIT[index];
			index -= index & (-index);
		}
		return sum;
		
	}
	

}


/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
10000 - 16
1111 - 15
1110 - 14
1101 - 13
1100 - 12
1011 - 11
1010 - 10
1001 - 9
1000 - 8
0111 - 7
0110 - 6
0101 - 5
0100 - 4
0011 - 3
0010 - 2
0001 - 1
0000 - 0
 * 
 */
