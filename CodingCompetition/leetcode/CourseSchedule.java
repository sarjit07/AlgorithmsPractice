package leetcode;

import java.util.ArrayList;
import java.util.Iterator;

public class CourseSchedule {

	public static void main(String args[]) {
		
		int numCourses = 4;
		int prerequisites[][] = {{1,0},{2,0},{3,2},{3,1}};
		CourseSchedule cs = new CourseSchedule();
		System.out.println(cs.canFinish(numCourses, prerequisites));
	}
	
	ArrayList<ArrayList<Integer>> adj;
	int visited[];
	int recStack[];
	public boolean canFinish(int numCourses, int prerequisites[][]) {
		adj = createAdjMat(numCourses, prerequisites);
		visited = new int[numCourses+1];
		
		//this stack is used to check all nodes currently in recursion
		recStack = new int[numCourses+1];
		
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(visited, i, recStack))
				return false;
		}
		return true;
	}
	
	public boolean dfs(int visited[], int node, int recStack[]) {

		if(recStack[node]==1)
			return false;
		visited[node] = 1;
		recStack[node]=1;
		Iterator<Integer> itr = adj.get(node).iterator();
		while(itr.hasNext()) {
			int childNode = itr.next();
			 if(!dfs(visited, childNode, recStack))
				 return false;
		}
		recStack[node] = 0;
		return true;
	}
	
	public ArrayList<ArrayList<Integer>> createAdjMat(int n, int[][] dislikes){
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<=n;i++) {
			ArrayList<Integer> nodeList = new ArrayList<Integer>();
			list.add(nodeList);
		}
		for(int i=0;i<dislikes.length;i++) {
			ArrayList<Integer> list1 = list.get(dislikes[i][0]);
			list1.add(dislikes[i][1]);
		}
		return list;
	}
	
}
