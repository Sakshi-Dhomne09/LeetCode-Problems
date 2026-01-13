1class Solution {
2    public double separateSquares(int[][] squares) {
3        double totalArea = 0;
4        double low = 2e9; // Initialize with a large value
5        double high = 0;
6
7        // 1. Calculate Total Area and initial bounds
8        for (int[] sq : squares) {
9            double y = sq[1];
10            double l = sq[2];
11            
12            // Cast to double BEFORE multiplying to prevent Integer Overflow
13            totalArea += l * l;
14            
15            low = Math.min(low, y);
16            high = Math.max(high, y + l);
17        }
18
19        double halfArea = totalArea / 2.0;
20
21        // 2. Binary Search with fixed iterations
22        
23        for (int i = 0; i < 100; i++) {
24            double mid = low + (high - low) / 2.0;
25            
26            if (calculateArea(squares, mid) >= halfArea) {
27                high = mid; // Area is sufficient, try to lower the line
28            } else {
29                low = mid;  // Area is too small, need to raise the line
30            }
31        }
32        
33        return high;
34    }
35
36    // Helper function to calculate area below the line 'currentY'
37    private double calculateArea(int[][] squares, double currentY) {
38        double area = 0;
39        for (int[] sq : squares) {
40            double y = sq[1];
41            double l = sq[2];
42            double top = y + l;
43
44            if (y >= currentY) {
45                // Case 1: Square is completely above the line
46                continue;
47            } else if (top <= currentY) {
48                // Case 2: Square is completely below the line
49                area += l * l;
50            } else {
51                // Case 3: Line cuts through the square
52                // We take the width (l) * the height of the bottom portion (currentY - y)
53                area += l * (currentY - y);
54            }
55        }
56        return area;
57    }
58}