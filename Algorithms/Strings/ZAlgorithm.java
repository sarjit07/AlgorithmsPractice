package justforcoding;

//Can also provide all the substrings which are also the prefix of the given string in o(n+m).
//Can do pattern matching in o(n+m).
// Reference: https://www.youtube.com/watch?v=uFPSFsOlklE&t=8s

public class ZAlgorithm {
	
	static int z[];
	public static void main(String args[]) {
		String s = "abcdefcd";
		String p = "cd";
		String tot = p + "$" + s;
		z_algo(tot);
		
		// Patten Matching
		boolean flag = false;
		for(int i=0;i<z.length;i++) {
			if(z[i]==p.length()) {
				System.out.println("Match starts from idx = "+ (i-p.length()-1));
				flag=true;
			}
		}
		if(!flag) {
			System.out.println("Match not found");
		}
		
		System.out.println();
		
		// Finding repeating prefix in a String
		s = "abcdeabcgtykab";
		z_algo(s);
		
		flag = false;
		for(int i=0;i<z.length;i++) {
			if(z[i]!=0) {
				System.out.println("Repeating prefix starts from idx = "+ i+", length = "+z[i]);
				flag=true;
			}
		}
		if(!flag) {
			System.out.println("Repeating prefix not found");
		}

		
	}
	
	public static int[] z_algo(String s){
		z = new int[s.length()];
		int len = s.length();
		int l=0, r=0;
		
		for(int i=1;i<len;i++) {
			
			if(i>r) {
				l=r=i;
				while(r<len && s.charAt(r) == s.charAt(r-l))
					r++;
				z[i]=r-l;
				r--;
			}
			else {
				int idx = i-l;
				if(i+z[idx] <= r) {
					z[i]=z[idx];
				}
				else {
					l=i;
					while(r<len && s.charAt(r) == s.charAt(r-l))
						r++;
					z[i]=r-l;
					r--;
				}
			}
			
		}
		
		return z;
	}

}
