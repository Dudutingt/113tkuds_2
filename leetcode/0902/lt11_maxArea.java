class Solution {
    public int maxArea(int[] height) {
        int left = 0;                 
        int right = height.length - 1; 
        int maxArea = 0;               

        while (left < right) {
            
            int width = right - left; 
            int minHeight = Math.min(height[left], height[right]); 
            int area = width * minHeight; 
            maxArea = Math.max(maxArea, area); 

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

/*
解題思路：
1. 這題的關鍵在於「容積 = 寬度 × 高度」，其中高度取決於左右兩根柱子中較矮的那一根。
2. 如果單純暴力枚舉所有左右組合 (O(n^2))，在 n 最大 10^5 時會超時。
3. 因此我們用「雙指針法」：
   - 初始化：一個指針放最左邊 (left=0)，另一個放最右邊 (right=n-1)。
   - 每次計算當前容積，並更新最大值。
   - 然後移動「較矮」的柱子：
     - 因為高度取決於最矮的柱子，如果不移動它，就不可能獲得更高的容積。
     - 移動較高的那根只會讓寬度變小，高度又沒增加，沒有意義。
4. 這樣只需要遍歷一次 (每次移動一個指針)，時間複雜度 O(n)，空間複雜度 O(1)。
*/
