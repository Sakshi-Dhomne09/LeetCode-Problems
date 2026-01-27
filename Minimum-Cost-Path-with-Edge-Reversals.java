1class Solution {
2    private List<List<int[]>> G;
3
4    private int dijkstra(int n) {
5        final int INF = 1_000_000_000;
6        int[] dist = new int[n];
7        boolean[] vis = new boolean[n];
8        Arrays.fill(dist, INF);
9        dist[0] = 0;
10
11        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
12        pq.offer(new int[]{0, 0}); 
13
14        while(!pq.isEmpty()){
15            int u = pq.poll()[1];
16
17            if(vis[u]) continue;
18            vis[u] = true;
19
20            for(int[] e : G.get(u)){
21                int v  = e[0];
22                int w  = e[1];
23                if(dist[u] + w < dist[v]){
24                    dist[v] = dist[u] + w;
25                    pq.offer(new int[]{dist[u] + w, v});
26                }
27            }
28        }
29
30        return dist[n - 1] == INF ? -1 : dist[n - 1];
31    }
32
33    public int minCost(int n, int[][] edges) {
34        G = new ArrayList<>(n);
35        for(int i = 0; i < n; i ++) G.add(new ArrayList<>());
36
37        for(int[] edge : edges){
38            int u = edge[0];
39            int v = edge[1];
40            int w = edge[2];
41
42            G.get(u).add(new int[]{v, w});
43            G.get(v).add(new int[]{u, 2 * w});
44        }
45
46        return dijkstra(n);
47    }
48}