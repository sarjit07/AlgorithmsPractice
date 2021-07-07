
//https://leetcode.com/problems/first-missing-positive/


class Solution {
    public int firstMissingPositive(int[] a) {
        int f = 0;
        int n = a.length;
            
        for(int i=0;i<a.length;i++){
            if(a[i] == 1){
                f =1;
                break;
            }
        }
        if(f==0)
            return 1;
        
        for(int i=0;i<a.length;i++){
            if( (a[i] <= 0) || (a[i] > a.length)){
                a[i] =1;
            }
        }
        
        for(int i=0;i<n;i++){
            int z = Math.abs(a[i]);
            if(z == n)
                a[0] = - Math.abs(a[0]);
            else
                a[z] = - Math.abs(a[z]);
        }
        
        for (int i = 1; i < n; i++) {
            if (a[i] > 0)
                return i;
        }
        
        if (a[0] > 0)
            return n;
        
        return n+1;

    }
}
