1class Solution {
2
3    public int largestMagicSquare(int[][] mat) {
4        int R = mat.length;
5        int C = mat[0].length;
6
7        // Row prefix sums
8        int[][] rowSum = new int[R][C];
9        for (int r = 0; r < R; r++) {
10            rowSum[r][0] = mat[r][0];
11            for (int c = 1; c < C; c++) {
12                rowSum[r][c] = rowSum[r][c - 1] + mat[r][c];
13            }
14        }
15
16        // Column prefix sums
17        int[][] colSum = new int[R][C];
18        for (int c = 0; c < C; c++) {
19            colSum[0][c] = mat[0][c];
20            for (int r = 1; r < R; r++) {
21                colSum[r][c] = colSum[r - 1][c] + mat[r][c];
22            }
23        }
24
25        int maxSize = 1;
26
27        // Try every cell as top-left
28        for (int r = 0; r < R; r++) {
29            for (int c = 0; c < C; c++) {
30                int maxPossible = Math.min(R - r, C - c);
31
32                // Try bigger squares first
33                for (int size = maxPossible; size > maxSize; size--) {
34                    if (isMagic(r, c, size, mat, rowSum, colSum)) {
35                        maxSize = size;
36                        break;
37                    }
38                }
39            }
40        }
41        return maxSize;
42    }
43
44    private boolean isMagic(
45            int sr, int sc, int size,
46            int[][] mat, int[][] rowSum, int[][] colSum) {
47
48        // Target sum from first row
49        int target = rowSum[sr][sc + size - 1]
50                   - (sc > 0 ? rowSum[sr][sc - 1] : 0);
51
52        // Check rows
53        for (int r = sr; r < sr + size; r++) {
54            int sum = rowSum[r][sc + size - 1]
55                    - (sc > 0 ? rowSum[r][sc - 1] : 0);
56            if (sum != target) return false;
57        }
58
59        // Check columns
60        for (int c = sc; c < sc + size; c++) {
61            int sum = colSum[sr + size - 1][c]
62                    - (sr > 0 ? colSum[sr - 1][c] : 0);
63            if (sum != target) return false;
64        }
65
66        // Main diagonal
67        int d1 = 0;
68        for (int k = 0; k < size; k++)
69            d1 += mat[sr + k][sc + k];
70        if (d1 != target) return false;
71
72        // Anti-diagonal
73        int d2 = 0;
74        for (int k = 0; k < size; k++)
75            d2 += mat[sr + size - 1 - k][sc + k];
76
77        return d2 == target;
78    }
79}