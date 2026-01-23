1class Solution {
2    private long flipped;
3    private int[] left;
4    private int[] right;
5    private TreeSet<long[]> pairSum;
6
7    public int minimumPairRemoval(int[] nums) {
8        int N = nums.length;
9        if (N < 2) return 0;
10
11        long[] array = new long[N];
12        for (int i = 0; i < N; i++)
13            array[i] = nums[i];
14
15        flipped = 0;
16        left = new int[N];
17        right = new int[N];
18
19        pairSum = new TreeSet<>((a, b) -> {
20            if (a[0] != b[0])
21                return Long.compare(a[0], b[0]);
22            return Long.compare(a[1], b[1]);
23        });
24
25        for (int i = 0; i < N; i++) {
26            left[i] = i - 1;
27            right[i] = i + 1;
28        }
29
30        for (int i = 0; i < N - 1; i++) {
31            if (array[i] > array[i + 1]) flipped++;
32            pairSum.add(new long[] { array[i] + array[i + 1], i });
33        }
34
35        int op = 0;
36        while (flipped > 0 && !pairSum.isEmpty()) {
37            long[] first = pairSum.pollFirst();
38            int i = (int) first[1];
39
40            int j = right[i];
41            int h = left[i];
42            int k = right[j];
43
44            remove(h, N, array);
45            if (array[i] > array[j])
46                flipped--;
47            remove(j, N, array);
48
49            array[i] += array[j];
50
51            op++;
52            right[i] = k;
53            if (k < N) {
54                left[k] = i;
55            }
56
57            add(h, N, array);
58            add(i, N, array);
59        }
60
61        return op;
62    }
63
64    private void add(int i, int N, long[] array) {
65        if (i >= 0 && i < N) {
66            int j = right[i];
67            if (j < N) {
68                pairSum.add(new long[] { array[i] + array[j], i });
69                if (array[i] > array[j])
70                    flipped++;
71            }
72        }
73    }
74
75    private void remove(int i, int N, long[] array) {
76        if (i >= 0 && i < N) {
77            int j = right[i];
78            if (j < N) {
79                long[] target = new long[] { array[i] + array[j], i };
80                if (pairSum.contains(target)) {
81                    if (array[i] > array[j])
82                        flipped--;
83                    pairSum.remove(target);
84                }
85            }
86        }
87    }
88
89}