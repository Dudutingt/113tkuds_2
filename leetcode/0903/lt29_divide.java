class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        
        boolean negative = (dividend < 0) ^ (divisor < 0);

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        long result = 0;

    
        while (ldividend >= ldivisor) {
            long temp = ldivisor, multiple = 1;
            while (ldividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            ldividend -= temp;
            result += multiple;
        }

        return negative ? (int) -result : (int) result;
    }
}

/*
解題思路：
1. 題目要求不使用乘除或模運算進行整數除法。
2. 首先處理溢位情況：dividend 為 Integer.MIN_VALUE 且 divisor 為 -1。
3. 判斷結果符號，用 XOR 判斷是否異號。
4. 將 dividend 和 divisor 轉成 long 型並取絕對值，避免溢位。
5. 使用位移來快速模擬乘法：
   - 不斷將 divisor 左移直到小於等於 dividend。
   - 每次減去這個“倍數”的 divisor，累加倍數到結果。
6. 最終根據符號返回結果。
7. 時間複雜度 O(log n)，n 為 dividend 的絕對值。
8. 空間複雜度 O(1)。
*/
