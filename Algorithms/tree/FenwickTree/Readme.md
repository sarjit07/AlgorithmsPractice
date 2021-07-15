## Variations Of Fenwick Tree

#####  Point Update and Range Query - Common Scenario.
#####  Range Update and Point Query 


Question: https://www.codechef.com/problems/SPREAD

http://zobayer.blogspot.com/2013/11/various-usage-of-bit.html
suppose we want to update range [2 4] with value 1, thus first we call update(2) which will update node 2,node 4 and node 8(lets say length of bit array is 10) with value 1. Now call update (5) and this will update node 5, node 6 and node 8 but with -1. Now doing a point query will back trace all the node and the nodes which were not within the range get updated with negative number and that within the range gets updated with positive number. Once you back trace these will cancel out and gives you the correct result.

0 0 0 0 0 0 0 0 0 0 0

0 0 1 0 1 0 0 0 1 0 0 - Update(2,1) - update node 2 -> node 4 -> node 8

0 0 1 0 1 -1 -1 0 0 0 0 - Update(4+1,-1) - update node 5 -> node 6 -> node 8

get (6) -> node 6 + node 4 + node 0 = -1 + 1 + 0 = 0 which proves the above algorithm holds true.





## Problems on Fenwick Tree


#### 1. Problem  : https://lightoj.com/problem/points-in-rectangle
(Solution: https://sifatshishir.blogspot.com/2018/02/lightoj-points-in-rectangle.html)

#### 2. https://www.codechef.com/problems/SPREAD


