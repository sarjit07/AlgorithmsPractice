
package datastructures;

import java.io.*;
import java.util.*;


/*
 * Reference: https://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html
 * Overriding Equals and HashCode methods: https://www.javamadesoeasy.com/2015/02/overriding-equals-and-hashcode-method.html
 * Full Java Class: https://www.javamadesoeasy.com/2015/02/override-equals-and-hashcode-method.html
 * 
 * 
 * 
 * HashMap Performance and Collisions in the HashMap : https://www.baeldung.com/java-hashmap-advanced
 * 
 * NOTE: implementing a good hash function, (that evenly distributes the keys in the bucket, can further reduce the worst case time complexity)
 * Reference: https://www.youtube.com/watch?v=udB-dHqS3ng
 * 
 * HashMap with LinkedList is the Chaining. Which is called Chaining in HashMap As Collision Resolution.
 * 
 *  
 * ReHashing: It is done when entries in the hashmap increases beyond a certain threshold. And now, computation of put and get may no longer be O(1).
 * In ReHashing, a new bucket of double the size of old bucket is created, and then each entry in old bucket is taken and called a put method on new bucket.
 * https://www.geeksforgeeks.org/load-factor-and-rehashing/
 * 
 *  
 * Double Hashing: https://www.geeksforgeeks.org/double-hashing/
 * Using 2 hash functions instead of one;
 * 
 */

public class HashMapImpl<K,V> {
	
	
	private Entry<K, V>[] bucket;
	private int capacity = 4;
	
	
	public HashMapImpl() {
		bucket = new Entry[capacity];
	}
	
	
	// this method can be optimized to insert just at the head, so complexity in worst case for insertion can come down from O(n) to O(1)
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
		
		public K getKey() {
	        return key;
	    }

	    public void setKey(K key) {
	        this.key = key;
	    }

	    public V getValue() {
	        return value;
	    }

	    public void setValue(V value) {
	        this.value = value;
	    }

		
		@Override
		public boolean equals(Object obj) {
			
			if(obj == null)
				return false;
			
			if(this.getClass() != obj.getClass())
				return false;
			
			Entry<K,V> entry = (Entry)obj;
			
			return ((this.key == entry.key || this.key.equals(entry.key))
					&& (this.value == entry.value || this.value.equals(entry.value)));
			
		}
		
		@Override
		public int hashCode() {
			return (this.key == null ? 0:this.key.hashCode()) 
					+ (this.value == null ? 0:this.value.hashCode());
		}
	}
	
}





