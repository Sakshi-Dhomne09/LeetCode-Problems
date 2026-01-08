1
2class Solution {
3    public int maxDotProduct(int[] a, int[] b) {
4        int n = a.length, m = b.length;
5        int NEG = (int)-1e9;
6        int[][] dp = new int[n+1][m+1];
7        
8        for(int i=0;i<=n;i++)
9            for(int j=0;j<=m;j++)
10                dp[i][j] = NEG;
11
12        for(int i=1;i<=n;i++){
13            for(int j=1;j<=m;j++){
14                int take = a[i-1]*b[j-1] + Math.max(0, dp[i-1][j-1]);
15                dp[i][j] = Math.max(take, Math.max(dp[i-1][j], dp[i][j-1]));
16            }
17        }
18        return dp[n][m];
19    }
20}
21
22
23