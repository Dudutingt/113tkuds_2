import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1);
        }
    }
}

/*
解題思路：
1. 這是一道典型的回溯 (Backtracking) 題目。
2. 目標是生成所有長度為 2n 的合法括號字串。
3. 條件限制：
   - 左括號數量 (open) 不能超過 n。
   - 右括號數量 (close) 不能超過當前左括號數量，否則會不合法。
4. 使用遞迴，每次選擇加左括號或右括號，直到長度達到 2n。
5. 用 StringBuilder 暫存當前字串，避免過多字串拷貝。
6. 時間複雜度大約為 O(4^n / sqrt(n))，這是第 n 個 Catalan 數的數量級。
*/
