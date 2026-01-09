1class Solution {
2    class Result {
3        int height;
4        TreeNode node;
5        Result(int h, TreeNode n) {
6            height = h;
7            node = n;
8        }
9    }
10
11    private Result dfs(TreeNode node) {
12        if (node == null) return new Result(0, null);
13        
14        Result left = dfs(node.left);
15        Result right = dfs(node.right);
16        
17        if (left.height > right.height) {
18            return new Result(left.height + 1, left.node);
19        } else if (right.height > left.height) {
20            return new Result(right.height + 1, right.node);
21        } else {
22            return new Result(left.height + 1, node);
23        }
24    }
25
26    public TreeNode subtreeWithAllDeepest(TreeNode root) {
27        return dfs(root).node;
28    }
29}