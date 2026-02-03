1class Solution {
2    public boolean isTrionic(int[] nums) {
3        int n = nums.length;
4        int peak = -1, valley = n;
5
6        for (int i = 0; i < n - 1; i++) {
7            if (peak == -1 && nums[i] >= nums[i + 1])
8                peak = i;
9
10            if (valley == n && nums[n - 1 - i] <= nums[n - 2 - i])
11                valley = n - 1 - i;
12
13            if (peak > 0 && valley < n - 1 && peak < valley)
14                return isDecreasing(nums, peak, valley);
15        }
16
17        return false;
18    }
19
20    boolean isDecreasing(int[] nums, int a, int b) {
21        for (int i = a; i < b; i++)
22            if (nums[i] <= nums[i + 1])
23                return false;
24        return true;
25    }
26}
27