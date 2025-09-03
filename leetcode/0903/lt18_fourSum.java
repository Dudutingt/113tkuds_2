import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums); 
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
        
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; 
                    } else {
                        right--; 
                    }
                }
            }
        }

        return result;
    }
}

/*
解題思路：
1. 題目要求找出所有四元組 [a, b, c, d]，使得 nums[a]+nums[b]+nums[c]+nums[d] == target，且不得重複。
2. 解法框架：
   - 先排序，方便處理重複元素和使用雙指針。
   - 兩層 for 迴圈固定前兩個數字 (i, j)。
   - 之後用雙指針 (left, right) 在區間 [j+1, n-1] 找符合的組合。
3. 避免重複：
   - 對 i、j、left、right 都需要檢查，若和前一個數字相同則跳過，避免產生重複解。
4. 為什麼 sum 用 long？
   - 因為 nums[i] 可能高達 10^9，四個相加會超出 int 範圍，所以用 long 避免溢位。
5. 複雜度分析：
   - 排序 O(n log n)。
   - 雙層迴圈 + 雙指針，最壞 O(n^3)。
   - 適用於 n ≤ 200 的範圍。
*/