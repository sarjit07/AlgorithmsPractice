package graph;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


/*
 * @Author: Arjit Sharma
 * 
 * Time Complexity: O(V+E) 
 * Space Complexity: O(V)
 * DFS
 */

public class TopologicalSort {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		//get the total no. of veritces and edges
		int vertices = in.nextInt();
		int edges = in.nextInt();
		
		//get a array of linkedlist to store a list of adjacent vertices for that particular vertex. (1-vertices)
		LinkedList<Integer> a[] = new LinkedList[vertices+1];
		for(int i=0;i<=vertices;i++)
			a[i]=new LinkedList<>();
		
		//enter all the edges between the vertices and add then into the list
		for(int i=0;i<edges;i++){
			int vertex1 = in.nextInt();
			int vertex2 = in.nextInt();	
			a[vertex1].add(vertex2);   //add vertex2 in vertex1's list
		}
		
		//print all the lists
		for(int i=1;i<=vertices;i++){
			System.out.println(a[i].toString());
		}
		
		int visited[] = new int[vertices+1];
		for(int i=0;i<vertices;i++)
			visited[i]=0;
		LinkedList<Integer> tempStack= new LinkedList<>();
		
		//loop to get the DFS for every vertex. Will work in case of a disconnected graph also.
		for(int i=1;i<=vertices;i++){
			if(visited[i]!=1)
				topologicalSort(a,i,visited,tempStack);
		}
		while(!tempStack.isEmpty()){
			System.out.println(tempStack.poll());
		}
	}
	public static void topologicalSort(LinkedList<Integer> a[],int pvertex,int[] visited,LinkedList<Integer> tempStack){
		visited[pvertex]=1;
		Iterator<Integer> itr = a[pvertex].listIterator();
		while(itr.hasNext()){
			int n = itr.next();
			if(visited[n]!=1){
				topologicalSort(a,n,visited,tempStack);
			}
			
		}
		tempStack.add(pvertex);
	}
}

