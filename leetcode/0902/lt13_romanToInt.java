class Solution {
    public int romanToInt(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i)); 

            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                result -= value; 
            } else {
                result += value; 
            }
        }

        return result;
    }
}

/*
解題思路：
1. 羅馬數字規則：
   - 一般情況：左到右，直接加總。
   - 特殊情況：小數字在大數字左邊，代表要減去該小數字（例如 IV = 4）。
2. 做法：
   - 使用 Map<Character, Integer> 存放每個字母的數值。
   - 從左到右掃描：
     * 若當前字母 < 下一個字母，代表是「減法」情況，扣掉這個值。
     * 否則就加上該值。
3. 最後得到的總和就是答案。
4. 時間複雜度 O(n)，空間複雜度 O(1)（map 固定大小，n 最多 15）。
*/
