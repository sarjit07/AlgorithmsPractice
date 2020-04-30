package justforcoding;

public class SegmentTreeMinRangeQuery {

	public static void main(String[] args) {
		int a[] = {1,3,5,7,9,11};
		
		int segmentArray[] = createSegmentTree(a);
		
		System.out.println(findMinInRange(a, segmentArray, 1, 3));
		
		update(a, segmentArray, 1, -1);
		
		System.out.println(findMinInRange(a, segmentArray, 1, 3));
		
		
	}

	public static void update(int a[], int segmentArray[], int i, int value) {
		if (i > a.length - 1)
			return;

		
		a[i] = value;
		updateUtil(segmentArray, value, i, 0, a.length - 1, 0);
	}

	public static void updateUtil(int segmentArray[], int value, int i, int s, int e, int root) {
		if (i < s || i > e)
			return;
		if(segmentArray[root] > value)
			segmentArray[root] = value;
		if (s != e) {
			int mid = (s + e) / 2;
			updateUtil(segmentArray, value, i, s, mid, 2 * root + 1);
			updateUtil(segmentArray, value, i, mid + 1, e, 2 * root + 2);
		}

	}

	public static int findMinInRange(int a[], int[] segmentArray, int l, int r) {
		if (l > r || l < 0 || r > a.length - 1)
			return -1;

		return findMinUtil(segmentArray, 0, a.length - 1, l, r, 0);
	}

	public static int findMinUtil(int segmentArray[], int s, int e, int l, int r, int root) {
		if (l <= s && e <= r)
			return segmentArray[root];
		if (r < s || l > e)
			return Integer.MAX_VALUE;
		int mid = (s + e) / 2;
		return Math.min(findMinUtil(segmentArray, s, mid, l, r, 2 * root + 1), findMinUtil(segmentArray, mid + 1, e, l, r, 2 * root + 2));

	}

	public static int[] createSegmentTree(int a[]) {

		int heightOfTree = (int) Math.ceil(Math.log(a.length) / Math.log(2));

		int sizeOfTree = 2 * (int) Math.pow(2, heightOfTree) - 1;

		int segmentArray[] = new int[sizeOfTree];
		for (int i = 0; i < segmentArray.length; i++)
			segmentArray[i] = Integer.MAX_VALUE;

		createSegmentTreeUtil(a, segmentArray, 0, 0, a.length - 1);

		return segmentArray;
	}

	public static int createSegmentTreeUtil(int a[], int segmentArray[], int root, int start, int end) {

		if (start > end)
			return -1;
		if (start == end) {
			segmentArray[root] = a[start];
			return a[start];
		}

		int mid = (start + end) / 2;

		segmentArray[root] = Math.min(createSegmentTreeUtil(a, segmentArray, 2 * root + 1, start, mid), 
				createSegmentTreeUtil(a, segmentArray, 2 * root + 2, mid + 1, end));

		return segmentArray[root];
	}

}
