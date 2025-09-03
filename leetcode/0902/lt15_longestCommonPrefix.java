class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 題目要找一組字串陣列中的最長共同前綴。
2. 我們先假設第一個字串就是共同前綴 prefix。
3. 然後依次和後面的每個字串比對，如果不符合，就不斷縮短 prefix。
   - 用 indexOf(prefix) == 0 來檢查 prefix 是否為當前字串的開頭。
4. 如果 prefix 被縮短到空字串，表示沒有共同前綴，直接回傳 ""。
5. 最後剩下的 prefix 就是所有字串的最長共同前綴。
*/
