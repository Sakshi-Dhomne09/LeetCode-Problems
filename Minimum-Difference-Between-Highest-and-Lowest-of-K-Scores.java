1class Solution {
2    static public int minimumDifference(int[] nums, int k) {
3        final int n=nums.length;
4        if (k==1) return 0;
5        Arrays.sort(nums);
6        int diff=Integer.MAX_VALUE;
7        for(int l=0, r=k-1; r<n; ){
8            diff=Math.min(diff, nums[r++]-nums[l++]);
9        }
10        return diff;
11    }
12}