1class Solution {
2    public int maximalRectangle(char[][] matrix) {
3        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
4            return 0;
5        
6        int rows = matrix.length;
7        int cols = matrix[0].length;
8        int[] heights = new int[cols + 1]; // Include an extra element for easier calculation
9        int maxArea = 0;
10        
11        for (char[] row : matrix) {
12            for (int i = 0; i < cols; i++) {
13                heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
14            }
15            
16            // Calculate max area using histogram method
17            int n = heights.length; // Number of bars in the histogram
18            
19            for (int i = 0; i < n; i++) {
20                for (int j = i, minHeight = Integer.MAX_VALUE; j < n; j++) {
21                    minHeight = Math.min(minHeight, heights[j]);
22                    int area = minHeight * (j - i + 1);
23                    maxArea = Math.max(maxArea, area);
24                }
25            }
26        }
27        
28        return maxArea;
29    }
30}