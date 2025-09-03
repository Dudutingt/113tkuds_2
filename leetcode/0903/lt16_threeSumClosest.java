import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;                  
            int right = nums.length - 1;       

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                
                if (currentSum < target) {
                    left++;  
                } else if (currentSum > target) {
                    right--;  
                } else {
                    return currentSum;
                }
            }
        }

        return closestSum;
    }
}

/*
解題思路：
1. 題目要求找到三個數字的總和，使它最接近 target。
2. 最直覺的解法是三重迴圈，但時間複雜度 O(n^3)，在 nums 長度 500 的情況下會太慢。
3. 改用「排序 + 雙指針」的方法：
   - 先將陣列排序。
   - 固定一個數字 nums[i]，然後用左右指針 (left, right) 在剩下的區間內搜尋另外兩個數字。
   - 每次計算總和 currentSum，若比現有 closestSum 更接近 target，就更新 closestSum。
   - 如果 currentSum < target，表示需要更大的總和，左指針右移。
   - 如果 currentSum > target，表示需要更小的總和，右指針左移。
   - 如果剛好等於 target，直接回傳，因為已經是最接近的結果。
4. 時間複雜度：
   - 排序 O(n log n)，外層迴圈 O(n)，內層雙指針 O(n)，總共 O(n^2)。
   - 在 nums.length ≤ 500 時完全可行。
5. 空間複雜度：
   - O(1)，除了排序外只用了常數額外空間。
*/
