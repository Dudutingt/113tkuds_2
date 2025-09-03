class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m == 0) return 0; 

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i; 
        }

        return -1; 
    }
}

/*
解題思路：
1. 題目要求返回 needle 在 haystack 中第一次出現的位置。
2. 使用暴力匹配：
   - 遍歷 haystack，從每個可能的起始位置 i 開始匹配 needle。
   - 若所有字符都匹配，則返回起始索引 i。
3. 如果遍歷完畢仍未匹配，返回 -1。
4. 時間複雜度 O((n-m+1)*m)，最壞情況需比較所有可能子串。
5. 空間複雜度 O(1)，只使用少量變數。
*/
