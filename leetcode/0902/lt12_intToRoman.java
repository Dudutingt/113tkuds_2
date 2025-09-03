class Solution {
    public String intToRoman(int num) {
        
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];      
                sb.append(symbols[i]); 
            }
        }
        
        return sb.toString();
    }
}

/*
解題思路：
1. 羅馬數字由固定組合構成，且必須從大到小拼接（除了 4、9 這些用減法表示的特殊情況）。
2. 我們可以建立一個「數值到羅馬字母的映射表」，並按照值從大到小排序：
   {1000:"M", 900:"CM", 500:"D", 400:"CD", 100:"C", 90:"XC", 50:"L", 40:"XL", 10:"X", 9:"IX", 5:"V", 4:"IV", 1:"I"}
3. 使用貪心法：
   - 每次從最大數值開始，若當前 num >= 這個值，就減去並拼接符號。
   - 持續直到 num 減為 0。
4. 因為 num 最大只到 3999，最多拼接 15 次左右，所以效率很好。
5. 時間複雜度 O(1)，空間複雜度 O(1)（映射表大小固定）。
*/
