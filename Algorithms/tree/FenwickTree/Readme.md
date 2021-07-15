## Variations Of Fenwick Tree

####  1. Point Update and Range Query - Common Scenario.
####  2. Range Update and Point Query 


#### https://www.codechef.com/problems/SPREAD

http://zobayer.blogspot.com/2013/11/various-usage-of-bit.html
suppose we want to update range [2 4] with value 1, thus first we call update(2) which will update node 2,node 4 and node 8(lets say length of bit array is 10) with value 1. Now call update (5) and this will update node 5, node 6 and node 8 but with -1. Now doing a point query will back trace all the node and the nodes which were not within the range get updated with negative number and that within the range gets updated with positive number. Once you back trace these will cancel out and gives you the correct result.

0 0 0 0 0 0 0 0 0 0 0

0 0 1 0 1 0 0 0 1 0 0 - Update(2,1) - update node 2 -> node 4 -> node 8

0 0 1 0 1 -1 -1 0 0 0 0 - Update(4+1,-1) - update node 5 -> node 6 -> node 8

get (6) -> node 6 + node 4 + node 0 = -1 + 1 + 0 = 0 which proves the above algorithm holds true.


####  3. Range Update and Range Query - NOT YET DONE

####  4. 2D BIT

#### Problem  : https://lightoj.com/problem/points-in-rectangle
(Solution: https://sifatshishir.blogspot.com/2018/02/lightoj-points-in-rectangle.html)

#### https://www.spoj.com/problems/MATSUM/

```
class BIT {
	int size;
	long [] table;
	
	public BIT(int size) {
		table = new long[size];
		this.size = size;
	}
	
	//Update Position i by delta
	void update(int i, long delta) {
		while(i < size) {
			table[i] += delta;
			i += Integer.lowestOneBit(i);
		}
	}
	
	//Compute the prefix sum value [1, i]
	long sum(int i) {
		long sum=0;
		while(i > 0) {
			sum += table[i];
			i -= Integer.lowestOneBit(i);
		}
		return sum;
	}
	
	//Compute the sum value [i, j]
	long rangeSum(int i, int j) {
		return sum(j) - sum(i);
	}
}

BIT fen = new BIT(SIZE);

```
