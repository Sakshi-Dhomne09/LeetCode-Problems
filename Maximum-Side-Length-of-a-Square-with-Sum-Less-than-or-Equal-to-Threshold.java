1class Solution {
2    public int maxSideLength(int[][] mat, int threshold) {
3        int m = mat.length, n = mat[0].length;
4        int maxSide = Math.min(m,n);
5
6        int[][] pref = new int[m+1][n+1];
7        for (int i = 1; i <= m; i++) {
8            for (int j = 1; j <= n; j++) {
9                pref[i][j] =
10                    mat[i-1][j-1]
11                  + pref[i-1][j]
12                  + pref[i][j-1]
13                  - pref[i-1][j-1];
14            }
15        }
16
17        while (maxSide > 0) {
18            for (int i = 0; i + maxSide <= m; i++) {
19                for (int j = 0; j + maxSide <= n; j++) {
20                    if (helper(pref, threshold, i, j, maxSide))
21                        return maxSide;
22                }
23            }
24            maxSide--;
25        }
26
27        return 0;
28    }
29
30    private boolean helper(int[][] pref, int t, int x, int y, int side) {
31        int x2 = x + side;
32        int y2 = y + side;
33
34        int sum =
35            pref[x2][y2]
36        - pref[x][y2]
37        - pref[x2][y]
38        + pref[x][y];
39
40        return sum <= t;
41    }
42
43}