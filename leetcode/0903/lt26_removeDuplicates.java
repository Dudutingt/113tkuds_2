class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast]; 
            }
        }

        return slow + 1;
    }
}

/*
解題思路：
1. 題目要求在排序好的陣列中移除重複元素，並保留唯一元素的順序，返回唯一元素的數量。
2. 使用雙指針技巧：
   - slow 指向已經處理好的唯一元素的最後位置。
   - fast 遍歷整個陣列，如果發現新元素與 slow 不同，則 slow++ 並將 nums[fast] 放到 nums[slow]。
3. 遍歷結束後，前 slow + 1 個元素即為所有唯一元素。
4. 時間複雜度 O(n)，每個元素訪問一次。
5. 空間複雜度 O(1)，僅使用兩個指標。
*/
