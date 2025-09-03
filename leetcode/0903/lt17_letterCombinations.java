import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        // 
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        String[] mapping = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), digits, 0, mapping);
        return result;
    }

    
    private void backtrack(List<String> result, StringBuilder current, String digits, int index, String[] mapping) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        
        String letters = mapping[digits.charAt(index) - '0'];

        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(result, current, digits, index + 1, mapping);
            current.deleteCharAt(current.length() - 1);
        }
    }
}

/*
解題思路：
1. 題目要找出所有數字字串對應的字母組合（模擬手機鍵盤）。
   - 例如數字 "2" -> "abc"，"3" -> "def"，所以 "23" -> {"ad","ae","af","bd","be","bf","cd","ce","cf"}。
2. 方法選擇：回溯 (Backtracking)
   - 因為這是一個「所有可能組合」的問題，非常適合用回溯法。
   - 我們會逐步構建字串，每處理一個數字就嘗試所有對應字母，直到處理完全部數字。
3. 遞迴函數設計：
   - 當前組合 (StringBuilder current)。
   - 當前處理到 digits 的 index。
   - 若 index == digits.length，表示已經組出一個完整字串，加入結果集。
   - 否則取出 mapping[digits[index]] 的字母，逐一嘗試並遞迴下去。
4. 複雜度分析：
   - 假設 digits 長度為 n，每個數字最多對應 4 個字母 (7 或 9)。
   - 時間複雜度 O(4^n)，因為每個數字都可能有 4 種選擇。
   - 空間複雜度 O(n)，來自遞迴深度和 StringBuilder。
5. 輸出順序：
   - 不要求固定順序，所以回溯生成的任意順序皆可接受。
*/
