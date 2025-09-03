class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0; 

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast]; 
                slow++;
            }
        }

        return slow; /
    }
}

/*
解題思路：
1. 題目要求移除陣列中所有等於 val 的元素，並將剩下的元素放在前面，返回剩餘元素數量。
2. 使用雙指針：
   - slow 指向下一個非 val 元素應該放的位置。
   - fast 遍歷整個陣列，當遇到 nums[fast] != val，將其放到 nums[slow] 並 slow++。
3. 遍歷結束後，前 slow 個元素即為所有非 val 的元素。
4. 時間複雜度 O(n)，每個元素訪問一次。
5. 空間複雜度 O(1)，僅使用兩個指標。
*/
