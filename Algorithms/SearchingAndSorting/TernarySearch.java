
package datastructures;

public class TernarySearch {
	public static void main(String args[]) {

		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int target = 6;
		int l = 0;
		int r = a.length-1;
		int index = tsearch(a, l, r, target);
		System.out.println(index);
		
		
	}
	
	
	public static int tsearch(int a[], int l, int r, int target) {
		if(l>r) return -1;
		
		int mid1 = l + (r-l)/3;
		int mid2 = r - (r-l)/3;
		
		if(a[mid1] == target)
			return mid1;
		if(a[mid2] == target)
			return mid2;
		if(a[mid1] > target)
			return tsearch(a, l, mid1-1, target);
		else if(a[mid2] < target)
			return tsearch(a, mid2+1, r, target);
		else 
			return tsearch(a, mid1+1, mid2-1, target);
		
	}

}


