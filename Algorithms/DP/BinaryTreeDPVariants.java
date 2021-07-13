
package tree;

import java.io.*;
import java.util.*;


public class DPonBinaryTreeVariants {
	
	static int result = Integer.MIN_VALUE;
	
	public static void main(String args[]) {

		
		TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    root.left.left = new TreeNode(4);
	    root.left.right = new TreeNode(5);
	    
	    diameterOfTree(root);
	    System.out.println(result);
	    
	    
	    
	}
	
	// https://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
	// Here 1. each node calculates the result as if it was the root node. 
	// 2. and pass the max value from left and right in the recursion for upper nodes max value calculation.
	// Time complexity : O(n)
	public static int diameterOfTree(TreeNode root) {
		
		if(root == null) 
			return 0;
		
		int left = diameterOfTree(root.left);
		int right = diameterOfTree(root.right);
		
		result = Math.max(result, 1 + left + right);
		
		return 1 + Math.max(left, right);
	}
	
	
	
	// https://leetcode.com/problems/binary-tree-maximum-path-sum/
	// Maximum path sum from any node to any node in the tree.
	static int max_sum = Integer.MIN_VALUE;
	
	public static int maxPathSum(TreeNode root){
        
        if(root == null)
            return 0;
        
        int left = Math.max(maxPathSum(root.left), 0);
        int right = Math.max(maxPathSum(root.right),0);
        
        max_sum = Math.max(max_sum, root.val + left + right);
        
        return root.val + Math.max(left, right);
        
    }
		
	
	
	
	
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int val){
			this.val = val;
			this.left = null;
			this.right = null;
		}
	}

}


