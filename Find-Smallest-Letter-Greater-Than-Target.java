1class Solution {
2    public char nextGreatestLetter(char[] letters, char target) {
3        char res = letters[0];
4        boolean flag = false;
5
6        for(char ch : letters){
7            if(!flag){
8                if(ch > target){
9                    res = ch;
10                    flag = !flag;
11                }
12            } else {
13                if(ch > target && ch < res)res = ch;
14            }
15        }
16
17        return res;
18    }
19}