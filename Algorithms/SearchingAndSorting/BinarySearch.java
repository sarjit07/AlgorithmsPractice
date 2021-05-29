class Solution {
    public int search(int[] a, int t) {
        int l = 0;
        int r = a.length-1;
        
        return binarySearch(a, l, r, t);
    }
    
    public int binarySearch(int a[], int l, int r, int t){
        if(l>r) return -1;
        
        int mid = (l+r)/2;
        
        if(a[mid] == t)
            return mid;
        else if(a[mid] > t)
            return binarySearch(a, l, mid-1, t);
        else 
            return binarySearch(a, mid+1, r, t);
    }
}
