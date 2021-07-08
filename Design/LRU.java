import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.print.attribute.standard.Sides;

public class LRU {
	
	private static Map<Integer, CacheNode> cache ;
	
	private static int SIZE ;
	private static int currentSize;
	private static CacheNode leastRecentNode;
	private static CacheNode mostRecentNode;
	public LRU(){
		this.cache = new HashMap<>();
		SIZE = 4;
		currentSize = 0;
		leastRecentNode = new CacheNode(0);
		mostRecentNode = leastRecentNode;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Enter the no. of inputs");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		LRU lru = new LRU();
		while(n--!=0){
			int input = in.nextInt();
			CacheNode node = getFromCache(input);
			if(node!=null){
				System.out.println("Value exists" );
				System.out.println("Key = " + input + " Value = "+node.toString());

			}
			else{
				if(putInCache(input)){
					System.out.println("Value successfully put in cache");
					for(Map.Entry<Integer, CacheNode> entry : cache.entrySet()){
						System.out.println("Key = " + entry.getKey() + " Value = "+entry.getValue().toString());
					}
					
				}
				else
					System.out.println("Put failed");
			
				
			}
			
			System.out.println("Least recent Node: " + leastRecentNode.toString());
			System.out.println("Most recent Node: " + mostRecentNode.toString());
			System.out.println();
		}
	}
	
	public static CacheNode getFromCache(int input){
		if(!cache.containsKey(input)){
			System.out.println("Value doesnt exists in cache");
			return null;
		}
		CacheNode node = cache.get(input);
		CacheNode nextNode = node.next;
		CacheNode prevNode = node.prev;
		if(node == leastRecentNode){
			leastRecentNode = leastRecentNode.next;
			leastRecentNode.prev = null;
		}
		else if(node == mostRecentNode) {
			return node;
		}
		else if (node!=leastRecentNode && node!=mostRecentNode){
			prevNode.next= node.next;
			nextNode.prev = node.prev;
		}
		
		
		mostRecentNode.next = node;
 		node.prev = mostRecentNode;
		mostRecentNode = node;
		mostRecentNode.next = null;
	
		
		return node;
	}
	
	public static boolean putInCache(int input){
		if(cache.containsKey(input)){
			return true;
		}
		
		CacheNode x = new CacheNode(input);
		if(currentSize == SIZE){
			cache.remove(leastRecentNode.data);
			leastRecentNode = leastRecentNode.next;
			leastRecentNode.prev = null;
			
			
		}
		else if( currentSize == 0){
			currentSize++;
			leastRecentNode = x;
			
		}
		else if(currentSize < SIZE){
			currentSize++;
		}
		
		mostRecentNode.next = x;
		x.prev = mostRecentNode;
		mostRecentNode =x;
		mostRecentNode.next = null;
		cache.put(input, x);
		
		return true;
	}
	
	
}


class CacheNode{
		int data;
		CacheNode next;
		CacheNode prev;
		
		
		public CacheNode(int data){
			this.data = data;
			this.next = null;
			this.prev = null;
		}


		@Override
		public String toString() {
			return "CacheNode [data=" + data +"]";
		}
		
}


