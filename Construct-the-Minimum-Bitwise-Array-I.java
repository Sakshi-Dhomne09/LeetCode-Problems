1class Solution {
2    public int[] minBitwiseArray(List<Integer> nums) {
3        int n = nums.size();
4        int[] ans = new int[n];
5        
6        for (int i = 0; i < n; i++) {
7            int val = nums.get(i);
8            
9            if (val % 2 == 0) {
10                ans[i] = -1;
11            } else {
12                long t = (long) val + 1;
13                
14                long lowbit = t & -t;
15                
16                ans[i] = val - (int)(lowbit >> 1);
17            }
18        }
19        
20        return ans;
21    }
22}