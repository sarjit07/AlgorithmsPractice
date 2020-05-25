package Strings;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author: Arjit Sharma
 * 
 * Worst case Time complexity = o(n * m)
 * On average time complexity is o((n-m+1) + c*m)
 * Depends on the Hash Function, if hash function is bad, than every interval might be checked. hence becomes o(n*m)
 * Need to take care of edges. But simple algorithm.
 * Reference: https://www.youtube.com/watch?v=H4VrKHVG5qI
 */


public class RabinKarpAlgorithm {

	private int prime = 101;
	public static void main(String args[]) {
		
		String s = "abcdeabcde";
		String p = "abcdeabcde";
		
		RabinKarpAlgorithm rk = new RabinKarpAlgorithm();
		List<Integer> list = rk.patternSearch(s,p);
		if(list.isEmpty())
			System.out.println("No Match");
		else
			System.out.println(list);
	}
	
	public List<Integer> patternSearch(String s, String p) {
		int slen = s.length();
		int plen = p.length();
		
		long patternHash = createHash(p, p.length());
		long textHash = createHash(s,p.length());
		
		List<Integer> matches = new ArrayList<Integer>();
		
		for(int i=1; i <= slen-plen+1; i++) {
			if(patternHash == textHash && checkEqual(s, i-1, i+plen-1, p)) {
				matches.add(i-1);
			}
			if(i<slen-plen+1) {
				textHash = recalculateHash(textHash, s, i-1,i+plen-1, plen);
			}
		}
		return matches;
		
	}
	
	private long createHash(String s, int n) {
		long hash = 0;
		for(int i=0;i<n;i++) {
			hash+=s.charAt(i)*Math.pow(prime, i);
		}
		return hash;
	}
	
	private boolean checkEqual(String s, int start, int end, String p) {
		if(end-start!=p.length())
			return false;
		else {
			int i=0;
			while(start<end) {
				if(s.charAt(start)!=p.charAt(i)) {
					return false;
				}
				start++;
				i++;
			}
		}
		return true;
	}
	
	private long recalculateHash(long hash, String s, int exclude, int include, int plen) {
		long newHash = hash - s.charAt(exclude);
		newHash/=prime;
		newHash+=s.charAt(include)*Math.pow(prime, plen-1);
		return newHash;
	}
}
