
package Strings;

import java.io.*;
import java.util.*;



/*
 * 
 * Applications of Trie 
 * 	- Auto Complete / Recommendation engine
 *  - Lexographical printing of words. (when array is implemented, not when hashmap is implemented)
 *  - Prefix Search
 * 
 * 
 * Time Complexity of insert, search, delete = O(length of search key)
 * Space Complexity = O(SIZE * SIZE)
 * Traversal Complexity = O(SIZE^SIZE)
 * 
 * Ref: https://www.youtube.com/watch?v=fTSLJ0WUulQ&list=PL2q4fbVm1Ik6ThrYKCzgEpmaS_XWDGHjx&index=16
 * https://www.geeksforgeeks.org/trie-insert-and-search/
 * 
 * Q. Given a set of words, find all words with a given prefix. ?
 * Q. Given an array of words, convert it into a unique prefix array ?
 * 		i.e each word should be uniquely identified by its prefix
 * 		ex = arr["zebra", "dove", "dog", "duck"]
 * 		o/p = ["z", "dov", "dog", "du"]
 */



public class Trie {
	
	static int SIZE = 26;
	static TrieNode root;
	
	public static void main(String args[]) {

		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		// n elements to be inserted
		int n = in.nextInt();
		
		root = new TrieNode('\0');
		
		for(int i=0;i<n;i++) {
			insertIntoTrie(in.nextLine());
		}
		
		
		// Traverse the full trie and print words
		traverseTrie(root, "");
		
		
		// q patterns to be searched
		int q = in.nextInt();
		for(int i=0;i<q;i++) {
			if(searchInTrie(in.nextLine())){
				System.out.println("Present");
			}
			else {
				System.out.println("Not Present");
			}
		}
		
		removeFromTrie(root, in.nextLine());
		
		// Traverse the full trie and print words
		traverseTrie(root, "");
				

		out.close();

	}
	
	// time complexity to search a word in Trie data structure - O(length of word).
	private static boolean searchInTrie(String searchString) {
		
		TrieNode temp = root;
		
		for(int i=0;i<searchString.length();i++) {
			
			char ch = searchString.charAt(i);
			
			if(temp.children.get(ch) == null)
				return false;
			
			temp = temp.children.get(ch); 
				
		}
		return (temp!=null && temp.endOfWord);
	}

	// time complexity to insert a word in Trie data structure - O(length of word).
	private static void insertIntoTrie(String input) {
		
		TrieNode temp = root;
		
		for(int i=0;i<input.length();i++) {
			
			char ch = input.charAt(i);
			
			if(temp.children.get(ch)==null) {
				temp.children.put(ch, new TrieNode(ch));
			}
			
			temp = temp.children.get(ch);
		}
		
		temp.endOfWord = true;
		
	}
	
	// Time complexity to traverse = SIZE^SIZE. 
	// The method displays the content of trie in Lexographically Sorted order.
	private static void traverseTrie(TrieNode root, String s) {
		
		if(root.endOfWord) {
			System.out.println(s);
		}
		
		for(Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
			traverseTrie(entry.getValue(), s + entry.getKey());
		}
	
	}
	
	// Time complexity to remove = O(key)
	private static void removeFromTrie(TrieNode root, String s) {
		
		if(s.length() == 0) {
			if(root.endOfWord)
				root.endOfWord = false;
			return;
		}
		
		char ch = s.charAt(0);
		String restOfString = s.substring(1);
		// word not present to be removed.
		if(root.children.get(ch) == null)
			return;
		else {
			removeFromTrie(root.children.get(ch), restOfString);
		}
		
		
		// if the current node is not an end for any word,
		// and it does not have any further branches,
		// then we can remove entire node
		if(!root.endOfWord && root.children.size()==0) {
			root.children.remove(ch);
		}
		
	}





	static class TrieNode {
		
		Character ch;
		boolean endOfWord;
		HashMap<Character, TrieNode> children;
		
		TrieNode(Character ch){
			this.ch = ch
			this.endOfWord = false;
			this.children = new HashMap<Character, Trie.TrieNode>();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static class InputReader {

		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int snext() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int nextInt() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}

		public String readString() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String nextLine() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}


