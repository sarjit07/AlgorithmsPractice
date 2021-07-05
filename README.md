# AlgorithmsPractice

``` MOD = (long)1e9+7;```

#### To Print upto 8 decimal places
```(double)Math.round(timenow * 100000000d) / 100000000d;```
```System.out.print(String.format("%.08f", val));```

#### GCD of two number
``` 
public static int gcd(int a, int b) {
		if(b==0)
			return a;
		return gcd(b, a%b);
	}
```

#### All primes till 100:
```{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97}```

#### Counting set bits in a Number  in constant time

```
static int lookup[] = new int[256];
static int eightSetBits = 0xff;

for (int i = 0; i < 256; i++)
	lookup[i] = (i & 1) + lookup[i / 2];
   
public static int countSetBitsRec(int num) {
	return lookup[num & eightSetBits] + lookup[num >> 8 & eightSetBits] + 
               lookup[num >> 16 & eightSetBits] + lookup[num >> 24 & eightSetBits];
}   

In o(logn) time using Brian Kernighan’s Algorithm

int countSetBit(long a){
	int count = 0;
	while(a!=0){
		a = (a & (a-1))
		count++;
	}
	return count;
}
 ```
 
 #### Getting the highest power of 2 in a Number Or Finding right most set bit.
 ```
 (int)(Math.log(n)/Math.log(2))
 ```
 
 #### Right most set bit / Least significant set bit in a Number
 ```
 int x = (n & (-n))

 2's Complement Of a Number = (~n)
=> 1. Invert all bits (1's complement). 
 => 2. Add 1 to it. 
 ```

#### What is coordinate compression ???
 ```
Let's say that in a problem, you're required to store N (1 <= N <= 10^5) elements 
and perform some operations on those elements (say put them in a Segment Tree). 
Normally, if the elements were also in the range (1, 10^5), inserting them into a data structure would be a cinch.

Say, for example, the elements are now in the range (1, 10^12). 
Now simply inserting elements into a Segment Tree is not possible because you cannot allocate memory for 10^12 integers.
This is where coordinate compression comes into play.

Let's read in all of the possible numbers, sort them, and assign each of them a number based off of increasing order.
Because N is <= 10^5, the maximum number you assign is going to be 10^5. 
Thus, by compressing the "coordinates", we maintain the relative order of points in a memory-efficient manner.

Reference: https://www.geeksforgeeks.org/convert-an-array-to-reduced-form-set-1-simple-and-hashing/
```
 
 
#### Priority Queue as max Heap 
```PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());```


```PriorityQueue<Student> queue = new PriorityQueue<Student>(new AnyCustomStudentDescendingSorter());```

#### Priority Queue as min Heap 
```PriorityQueue<Integer> queue = new PriorityQueue<>();``` 

Supports .add(), .remove(), .peek(), .poll() -> removes the first element



#### ASCII Value of A - 65, Z - 90, a - 97, z - 122, 0-48, 9-57
	
