import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }
}

/*
解題思路：
這題用「堆疊 (stack)」來解。
1. 遇到左括號時，將「對應的右括號」放入堆疊。
   例如遇到 '(' 就放入 ')'
2. 遇到右括號時，檢查堆疊是否為空或堆疊頂端是否不等於當前右括號。
   - 若不匹配，代表括號不合法，直接 return false。
3. 最後檢查堆疊是否為空，若空代表完全匹配，回傳 true。
時間複雜度 O(n)，空間複雜度 O(n)。
*/
