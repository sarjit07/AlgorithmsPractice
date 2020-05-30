# AlgorithmsPractice

``` MOD = (long)1e9+7;```


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
 ```
 
 #### Getting the highest power of 2 in a Number.
 ```
 (int)(Math.log(n)/Math.log(2))
 ```
