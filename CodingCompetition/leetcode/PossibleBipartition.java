package leetcode;

import java.util.ArrayList;
import java.util.Iterator;

public class PossibleBipartition {
	
	
	public static void main(String args[]) {
		int N = 10;
		int[][] dislikes = { { 4,7 }, { 4,8 }, { 2,8}, { 8,9 }, { 1,6 }, { 5,8}, {3,10}, {1,2}};
		PossibleBipartition pb = new PossibleBipartition();
		System.out.println(pb.possibleBipartition(N, dislikes));

	}

	ArrayList<ArrayList<Integer>> adj;
	int visited[];
	int color[];
	public boolean possibleBipartition(int N, int[][] dislikes) {
		adj = createAdjMat(N, dislikes);
		visited = new int[N+1];
		color = new int[N+1];
		for(int i=0;i<N;i++) {
			if(visited[i] == 0) {
				 if(!dfs(visited, i, 0))
					 return false;
			}
		}
		return true;
	}
	
	public boolean dfs(int visited[], int node, int c) {
		visited[node] = 1;
		color[node] = c;
		
		Iterator<Integer> itr = adj.get(node).iterator();
		while(itr.hasNext()) {
			int childNode = itr.next();
			if(visited[childNode] == 0) {
				dfs(visited, childNode, c^1);
			}else if(color[childNode] == color[node])
				return false;
		}
		
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
			ArrayList<Integer> list2 = list.get(dislikes[i][1]);
			list2.add(dislikes[i][0]);
		}
		
		return list;
	}
	
}
