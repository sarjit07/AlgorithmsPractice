
package datastructures;

import java.io.*;
import java.util.*;


/*
 * Reference: https://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html
 * 
 */

public class HashMapImpl<K,V> {
	
	
	private Entry<K, V>[] bucket;
	private int capacity = 4;
	
	
	public HashMapImpl() {
		bucket = new Entry[capacity];
	}
	
	public void put(K key, V value) {
		
		if(key == null)
			return;
		
		Entry<K,V> entry = new Entry(key, value, null);
		
		int hash = getHash(key);
		
		if(bucket[hash]==null) {
			bucket[hash] = entry;
		}
		else {
			Entry<K,V> prev = null;
			Entry<K,V> curr = bucket[hash];
			
			while(curr!=null) {
				if(curr.key.equals(entry.key)) {
					if(prev == null) {
						entry.next = curr.next;
						bucket[hash] = entry;
						return;
					}
					else {
						prev.next = entry;
						entry.next = curr.next;
					}
				}
				else {
					prev = curr;
					curr = curr.next;
				}
			}
		}
	}
	
	
	public V get(K key) {
		int hash = getHash(key);
		if(bucket[hash]==null) {
			return null;
		}
		else {
			Entry<K,V> curr = bucket[hash];
			while(curr!=null) {
				if(curr.key.equals(key)) {
					return curr.value;
				}
				curr = curr.next;
			}
			return null;
		}
	}
	
	
	public boolean remove(K key) {
		
		int hash = getHash(key);
		if(bucket[hash] == null) {
			return false;
		}
		else {
			Entry<K,V> prev = null;
			Entry<K,V> curr = bucket[hash];
			
			while(curr!=null) {
				if(curr.key.equals(key)) {
					if(prev == null) {
						bucket[hash] = curr.next;
						return true;
					}
					else {
						prev.next = curr.next;
						return true;
					}
				}
				else {
					prev = curr;
					curr = curr.next;
				}
			}
			return false;
		}
	}
	
	
	public void display() {
		for(int i=0;i<bucket.length;i++) {
			if(bucket[i]!=null) {
				Entry<K,V> curr = bucket[i];
				while(curr!=null) {
					System.out.println("Key : " + curr.key + ", Value : " + curr.value);
					curr = curr.next;
				}
			}
		}
	}
	
	
	public int getHash(K key) {
		return key.hashCode()%capacity;
	}

	
	
	public static void main(String args[]) {
		
		
		HashMapImpl<Integer, String> map = new HashMapImpl<Integer, String>();
		map.put(1,"Hello");
		map.put(2,"Arjit");
		map.put(3,"HashMap");
		map.put(4,"Impl");
		
		System.out.println(map.get(1));

		System.out.println();
		map.display();
		

	}
	
	
	
	
	
	
	static class Entry<K,V> {
		
		K key;
		V value;
		Entry<K,V> next;
		
		Entry(K key, V value, Entry<K,V> next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
}





