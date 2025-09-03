class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return res;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < wordCount) {
                int start = i + j * wordLen;
                String sub = s.substring(start, start + wordLen);
                if (!wordMap.containsKey(sub)) break;
                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordMap.get(sub)) break;
                j++;
            }
            if (j == wordCount) res.add(i);
        }

        return res;
    }
}

/*
解題思路：
1. 先判斷邊界情況：s 或 words 為空。
2. 每個 word 長度相同，計算每個 substring 的總長 totalLen = wordLen * words.length。
3. 用 HashMap 建立 words 的頻率表。
4. 遍歷 s 中可能的起始位置 i（0 到 s.length() - totalLen）。
5. 對每個起始位置，按照 wordLen 分段取子串，檢查是否在 words 頻率表中並且不超過次數。
6. 如果所有 word 都符合，將起始位置加入結果。
7. 時間複雜度 O((s.length - totalLen) * wordCount)，空間複雜度 O(wordCount)。
*/
