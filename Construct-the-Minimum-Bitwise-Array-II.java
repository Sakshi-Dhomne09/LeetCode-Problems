1class Solution {
2    public int[] minBitwiseArray(List<Integer> nums) {
3        int ans[] = new int[nums.size()];
4        for(int i = 0; i < nums.size(); i++) {
5            int n = nums.get(i);
6            if(n != 2) ans[i] = n - ((n + 1) & (-n - 1)) / 2;
7            else ans[i] = -1;
8        }  
9        return ans;
10    }
11}