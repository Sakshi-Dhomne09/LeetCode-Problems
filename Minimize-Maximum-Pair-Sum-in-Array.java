1class Solution {
2    public int minPairSum(int[] nums) {
3        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
4        int[] freq = new int[100001];
5        for(int i = 0; i < nums.length; i++) {
6            if(nums[i] < min) min = nums[i];
7            if(nums[i] > max) max = nums[i];
8            freq[nums[i]]++;
9        }
10        int max_sum = 0, l = min, r = max;
11        while(l <= r) {
12            if(freq[l] == 0) l++;
13            else if(freq[r] == 0) r--;
14            else {
15                max_sum = Math.max(max_sum, l + r);
16                freq[l]--;
17                freq[r]--;
18            }
19        }
20        return max_sum;
21    }
22}