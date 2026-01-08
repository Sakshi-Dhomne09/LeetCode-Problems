1class Solution {
2    static final int MOD = 1_000_000_007;
3    long totalSum = 0;
4    long maxProduct = 0;
5
6    // First DFS: compute total tree sum
7    long getTotalSum(TreeNode root) {
8        if (root == null) return 0;
9        return root.val 
10             + getTotalSum(root.left) 
11             + getTotalSum(root.right);
12    }
13
14    // Second DFS (postorder): compute subtree sums
15    long dfs(TreeNode root) {
16        if (root == null) return 0;
17
18        // Get sums of left and right subtrees
19        long left = dfs(root.left);
20        long right = dfs(root.right);
21
22        // Current subtree sum
23        long subSum = root.val + left + right;
24
25        // Try splitting above this node
26        long product = subSum * (totalSum - subSum);
27        maxProduct = Math.max(maxProduct, product);
28
29        return subSum;
30    }
31
32    public int maxProduct(TreeNode root) {
33        totalSum = getTotalSum(root); // O(n)
34        dfs(root);                    // O(n)
35        return (int)(maxProduct % MOD);
36    }
37}