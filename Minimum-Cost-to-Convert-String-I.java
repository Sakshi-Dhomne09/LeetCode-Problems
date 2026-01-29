1class Solution {
2    private static final int CHAR_COUNT = 26;
3    private static final int INF = Integer.MAX_VALUE / 2;
4
5    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
6        int[][] conversionGraph = buildConversionGraph(original, changed, cost);
7        optimizeConversionPaths(conversionGraph);
8        return computeTotalConversionCost(source, target, conversionGraph);
9    }
10
11    private int[][] buildConversionGraph(char[] original, char[] changed, int[] cost) {
12        int[][] graph = new int[CHAR_COUNT][CHAR_COUNT];
13        for (int[] row : graph) {
14            Arrays.fill(row, INF);
15        }
16        for (int i = 0; i < CHAR_COUNT; i++) {
17            graph[i][i] = 0;
18        }
19        for (int i = 0; i < cost.length; i++) {
20            int from = original[i] - 'a';
21            int to = changed[i] - 'a';
22            graph[from][to] = Math.min(graph[from][to], cost[i]);
23        }
24        return graph;
25    }
26
27    private void optimizeConversionPaths(int[][] graph) {
28        for (int k = 0; k < CHAR_COUNT; k++) {
29            for (int i = 0; i < CHAR_COUNT; i++) {
30                if (graph[i][k] < INF) {
31                    for (int j = 0; j < CHAR_COUNT; j++) {
32                        if (graph[k][j] < INF) {
33                            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
34                        }
35                    }
36                }
37            }
38        }
39    }
40
41    private long computeTotalConversionCost(String source, String target, int[][] graph) {
42        long totalCost = 0;
43        for (int i = 0; i < source.length(); i++) {
44            int sourceChar = source.charAt(i) - 'a';
45            int targetChar = target.charAt(i) - 'a';
46            if (sourceChar != targetChar) {
47                if (graph[sourceChar][targetChar] == INF) {
48                    return -1L;
49                }
50                totalCost += graph[sourceChar][targetChar];
51            }
52        }
53        return totalCost;
54    }
55}